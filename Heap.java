package asupekar_ice5;

public class Heap implements PriorityQueueADT<Integer> {
	private Integer[] data;
	private int size;
	private int capacity;

	// takes in the capacity
	// creates a min heap based on the capacity
	public Heap(int capacity) {
		this.capacity = capacity;
		data = new Integer[capacity];
		size = 0;
	}

	// returns the minimum value of the heap
	public Integer peek() {
		return data[0];
	}

	// takes in a value; inserts the value into the heap
	public void enqueue(Integer elem) {
		
		//Check if full
		if(size == capacity) {
			return;
		}
		
		System.out.println("Enqueueing elem: " + elem);
		
		// add to end of array
		data[size] = elem;

		// increase size
		size++;
		// create a recursive helper, percolateUp,
		percolateUp(size - 1);
	}

	// removes the minimum value from the heap and returns it
	public Integer dequeue() {
		// get last val in heap, copy value to index 0
		if(empty()) {
			return Integer.MIN_VALUE;
		}
		
		int minValue = data[0];
		System.out.println("Dequeueing: " + minValue);
		data[0] = data[size - 1];

		// decrease size
		size--;

		// create a recursive helper, percolateDown,
		// that allows you move the removed val
		// in the right place
		percolateDown(0);

		return minValue;
	}

	// determines if the heap is empty or not
	public boolean empty() {
		if(size == 0) {
			return true;
		}
		
		return false;
	}

	// returns the heap values, delimited by spaces
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size; i++) {
			sb.append(data[i] + " ");
		}
		return "MinHeap Structure: " + sb.toString();
	}

	private boolean hasLeft(int parentIndex) {

		int leftChild = left(parentIndex);

		if (leftChild <= size) {
			return true;
		}

		return false;
	}

	private boolean hasRight(int parentIndex) {
		int rightChild = right(parentIndex);

		if (rightChild <= size) {
			return true;
		}

		return false;
	}

	private int left(int parentIndex) {
		return 2*parentIndex + 1;
	}

	private int right(int parentIndex) {
		return 2*parentIndex + 2;
	}

	private int parent(int childIndex) {
		return (childIndex -1)/2;
	}

	private void percolateUp(int index) {
		if (index < 0) {
			return;
		}
		int parent = parent(index);
		if (data[parent] > data[index]) {
			int temp = data[parent];
			data[parent] = data[index];
			data[index] = temp;
			percolateUp(parent);
		}
	}

	private void percolateDown(int index) {

		if (index > size) {
			return;
		}

		int leftChild = left(index);
		int rightChild = right(index);
		
		if(hasLeft(index)) {
			int smallerChild = leftChild;
			
			if(hasRight(index)) {
				if (data[rightChild] < data[smallerChild]) {
					smallerChild = rightChild;
				}
			}
			
			if (data[index] > data[smallerChild]) {
				int temp = data[index];
				data[index] = data[smallerChild];
				data[smallerChild] = temp;
				percolateDown(smallerChild);
			}
		}
	}
}