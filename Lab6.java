package asupekar_lab6;

import java.util.Arrays;
import java.util.Random;

public class Lab6 {
	private static Random rand = new Random();
	private static final String ARRAYS_SORT = "ArraysSort";
	private static final String INSERTION_SORT = "InsertionSort";
	private static final String MERGE_SORT = "MergeSort";
	private static final String QUICK_SORT = "QuickSort";
	private static final String HEAP_SORT = "HeapSort";
	private static final int low = 125;
	private static final int high = 16384000;

	public static void main(String[] args){
		for(int i=low;i<=high;i*=2) {
			
			System.out.println("Time taken for " + i + " elements to sort");
			
			int[] a = randomArray(i);
			
			double timeTakenByMergeSort = getTimeInSecondsForGivenSort(a, MERGE_SORT);
			System.out.println("\nMerge Sorting: " + timeTakenByMergeSort);

			a = randomArray(i);
			double timeTakenByHeapSort = getTimeInSecondsForGivenSort(a, HEAP_SORT);
			System.out.println("Heap Sorting: " + timeTakenByHeapSort);
			
			a = randomArray(i);
			double timeTakenByQuickSort = getTimeInSecondsForGivenSort(a, QUICK_SORT);
			System.out.println("Quick Sorting: " + timeTakenByQuickSort);
			
			a = randomArray(i);
			double timeTakenByArraySort = getTimeInSecondsForGivenSort(a, ARRAYS_SORT);
			System.out.println("Array Sorting: " + timeTakenByArraySort);// uses quick sort for array of integers
			
			a = randomArray(i);
			if(i < 256000) {
				double timeTakenByInsertionSort = getTimeInSecondsForGivenSort(a, INSERTION_SORT);
				System.out.println("Insertion Sorting: " + timeTakenByInsertionSort);
			}
		
			System.out.println();

		}
	}

	/**
	 * Returns a random array of length size
	 * @param length size of the array
	 * @return array of length size
	 */
	public static int[] randomArray(int length) {
		int[] a = new int[length];
		for (int i = 0; i < length; i++)
			a[i] = rand.nextInt(length) - length / 2;
		return a;
	}

	/**
	 * Executes the mystery function and returns the time elapsed in seconds.
	 * 
	 * @param a   array to sort
	 * @param sortType sorting algorithm
	 * @return time elapsed in seconds
	 */
	private static double getTimeInSecondsForGivenSort(int[] a, String sortType) {
		final long startTime = System.nanoTime();
		switch(sortType) {
			case ARRAYS_SORT:
				Arrays.sort(a);
				break;
			case INSERTION_SORT:
				Sort.insertionsort(a);
				break;
			case MERGE_SORT:
				Sort.mergesort(a);
				break;
			case HEAP_SORT: 
				Sort.quicksort(a);
				break;
			case QUICK_SORT: 
				Sort.quicksort(a);
				break;
		}
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

}
