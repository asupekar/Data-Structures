package asupekar_lab1;

import java.util.Scanner;

/**
 * Performs iterative and recursive binary search after sorting an array
 * 
 * @author Aishwarya
 * @version 1.0
 */
public class Lab1 {
	public static void main(String[] args) {
		final int SIZE = 20;
		int[] array = new int[SIZE];
		int key;

		Scanner keyboard = new Scanner(System.in);

		// Input numbers from user and add it to array
		for (int i = 0; i < SIZE; i++) {
			System.out.print("Enter a number: ");
			array[i] = keyboard.nextInt();
		}

		System.out.print("Enter the key to be found: ");
		key = keyboard.nextInt();

		keyboard.nextLine();
		keyboard.close();

		// Sort the array
		selectionSort(array);

		// Perform iterative binary search
		int iterativePosition = binarySearchIterative(array, key);
		if (iterativePosition == -1) {
			System.out.println("Iterative Search :: Key " + key + " not found");
		} else {
			System.out.println("Iterative Search :: Key " + key + " found at position " + iterativePosition);
		}

		// Perform recursive binary search
		int recursivePosition = binarySearchRecursive(array, key);
		if (recursivePosition == -1) {
			System.out.println("Recursive Search :: Key " + key + " not found");
		} else {
			System.out.println("Recursive Search :: Key " + key + " found at position " + recursivePosition);
		}
	}

	/**
	 * Sorts the input array in non descending order
	 * 
	 * @param array input array to be sorted
	 */
	public static void selectionSort(int[] array) {

		int minIndex, temp;
		for (int i = 0; i < array.length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			if (i != minIndex) {
				temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
	}

	/**
	 * Performs binary search iteratively
	 * 
	 * @param array input array
	 * @param key   number to be searched
	 * @return -1 if key is not found else return the position of the key in array
	 */
	public static int binarySearchIterative(int[] array, int key) {
		int first;
		int last;
		int middle;
		int position = -1;

		first = 0;
		last = array.length - 1;

		while (first <= last) {
			middle = (first + last) / 2;
			if (array[middle] == key) {
				position = middle;

				// To find the first occurrence of the key
				// We do not return the position immediately instead try searching
				// more in the left half of the array each time
				last = middle - 1;
			} else if (array[middle] < key) {
				first = middle + 1;
			} else {
				last = middle - 1;
			}
		}
		return position;
	}

	/**
	 * Performs binary search recursively
	 * 
	 * @param array input array
	 * @param key   number to be searched
	 * @return -1 if key is not found else return the position of the key in array
	 */
	public static int binarySearchRecursive(int[] array, int key) {
		return findHelper(array, 0, array.length - 1, key);
	}

	/**
	 * 
	 * @param array input array
	 * @param start left boundary in array for search
	 * @param end   right boundary in array for search
	 * @param key   number to be searched
	 * @return -1 if key is not found else return the position of the key in array
	 */
	private static int findHelper(int[] array, int start, int end, int key) {
		int mid = (start + end) / 2;

		if (end < start) {
			return -1;
		}

		// To return the first occurrence
		if (array[start] == key) {
			return start;
		}

		// Continue searching the key in the left half even when found to find the first
		// occurrence
		if (array[mid] >= key) {
			return findHelper(array, start, mid, key);
		} else {
			return findHelper(array, mid + 1, end, key);
		}
	}
}
