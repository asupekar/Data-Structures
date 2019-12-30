
package asupekar_lab6;

import java.util.Arrays;
import java.util.Random;

/**
 * Demonstrate all the sorting algorithms. TODO: Use this as a starting point or
 * reference for Lab 6.
 *
 * @author Aishwarya
 * @version 1.0
 */
public class Sort {

	public static void insertionsort(int[] a) {
		insertionsort(a, 0, a.length - 1);
	}

	private static void insertionsort(int[] a, int lo, int hi) {
		for (int candidate = lo + 1; candidate <= hi; candidate++)
			for (int position = candidate - 1; position >= lo && a[position] > a[position + 1]; position--)
				swap(a, position, position + 1);
	}

	public static void mergesort(int[] a) {
		mergesort(a, 0, a.length - 1);
	}

	private static void mergesort(int[] a, int lo, int hi) {
		if (hi - lo > 0) {
			int mid = (lo + hi) / 2;
			mergesort(a, lo, mid);
			mergesort(a, mid + 1, hi);
			merge(a, lo, hi);
		}
	}

	private static void merge(int[] a, int lo, int hi) {
		int mid = (lo + hi) / 2;

		// set up temporary scratch space
		int[] scratch = new int[hi - lo + 1];
		int k = 0;

		int i = lo, j = mid + 1;
		while (i <= mid && j <= hi)
			if (a[i] < a[j])
				scratch[k++] = a[i++];
			else
				scratch[k++] = a[j++];

		// pick up the remains of whichever one has elements left in it
		while (i <= mid)
			scratch[k++] = a[i++];
		while (j <= hi)
			scratch[k++] = a[j++];

		// copy back scratch contents to original array
		for (k = 0; k < scratch.length; k++)
			a[lo + k] = scratch[k];
	}

	public static void quicksort(int[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static void quicksort(int[] a, int lo, int hi) {
		if (hi - lo > 10) {
			int pivotPosition = partition(a, lo, hi);
			quicksort(a, lo, pivotPosition - 1);
			quicksort(a, pivotPosition + 1, hi);
		} else {
			insertionsort(a, lo, hi);
		}
	}

	private static int partition(int[] a, int lo, int hi) {
		median3(a, lo, hi);
		int i = lo, j = hi - 1;
		int pivot = a[j]; // as left there by median3
		while (true) {
			while (a[++i] < pivot)
				; // move i forward to first "wrong" value
			while (pivot < a[--j])
				; // move j backward to first "wrong" value
			if (i >= j)
				break; // done looking at everything
			swap(a, i, j); // swap the two "wrong" values -- now they are both "right"
		}
		swap(a, i, hi - 1); // this is where the pivot was stashed by median3, place it in the middle
		return i;
	}

	private static void median3(int[] a, int lo, int hi) {
		int mid = (lo + hi) / 2;
		if (a[mid] < a[lo])
			swap(a, lo, mid);
		if (a[hi] < a[lo])
			swap(a, lo, hi);
		if (a[hi] < a[mid])
			swap(a, mid, hi);
		// place pivot at postion hi-1
		swap(a, mid, hi - 1);
	}

	public static void heapsort(int[] a) {
		MaxHeap heap = new MaxHeap(a);
		while (!heap.empty())
			heap.dequeue();
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void print(String s) {
		System.out.print(s);
	}

	private static void print(int n, String sep) {
		System.out.print(n);
		System.out.print(sep);
	}

	private static void println(String s) {
		System.out.println(s);
	}

	private static void println() {
		System.out.println();
	}

	public static void printArray(int[] a) {
		printArray(a, 0, a.length - 1);
	}

	private static void printArray(int[] a, int lo, int hi) {
		int n = hi - lo + 1;
		for (int i = lo; i < lo + 10 && i <= hi; i++)
			print(a[i], " ");
		if (n <= 10)
			return;
		print("... ");
		int skip = n / 5;
		for (int i = lo + 10 + skip; i <= hi - 10; i += skip)
			print(a[i], " ... ");
		for (int i = hi - 9; i <= hi; i++)
			print(a[i], " ");
		println();
	}

	public static void printAndCheck(int[] a) {
		print((isSorted(a) ? "" : "not ") + "sorted ");
		printArray(a);
	}

	public static void printAndCheck(int[] a, int lo, int hi) {
		print((isSorted(a, lo, hi) ? "" : "not ") + "sorted " + "[" + lo + ":" + hi + "] ");
		printArray(a, lo, hi);
	}

	public static boolean isSorted(int[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(int[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			if (a[i] < a[i - 1])
				return false;
		}
		return true;
	}

	private static class MaxHeap {
		int[] data;
		int size;

		public MaxHeap(int[] a) {
			size = a.length;
			data = a;
			for (int i = size / 2; i >= 0; i--)
				percolateDown(i);
		}

		public boolean empty() {
			return size == 0;
		}

		public void dequeue() {
			swap(data, 0, --size);
			percolateDown(0);
		}

		private void percolateDown(int p) {
			if (left(p) < size) {
				int maxchild = (right(p) < size && data[right(p)] > data[left(p)]) ? right(p) : left(p);
				if (data[p] < data[maxchild]) {
					swap(data, p, maxchild);
					percolateDown(maxchild);
				}
			}
		}

		private int left(int p) {
			return p * 2 + 1;
		}

		private int right(int p) {
			return left(p) + 1;
		}
	}
}
