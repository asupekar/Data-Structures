package asupekar_ice5;

public class HeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap minHeap = new Heap(30);
		minHeap.enqueue(2);
		minHeap.enqueue(2);
		minHeap.enqueue(6);
		minHeap.enqueue(8);
		minHeap.enqueue(10);
		minHeap.enqueue(1);
		minHeap.enqueue(3);
		minHeap.enqueue(5);
		minHeap.enqueue(7);
		minHeap.enqueue(9);
		minHeap.enqueue(-1);
		minHeap.enqueue(-3);
		minHeap.enqueue(-5);
		minHeap.enqueue(12);
		minHeap.enqueue(22);
		minHeap.enqueue(64);
		minHeap.enqueue(85);
		minHeap.enqueue(101);
		minHeap.enqueue(12);
		minHeap.enqueue(32);
		minHeap.enqueue(53);
		minHeap.enqueue(74);
		minHeap.enqueue(95);
		minHeap.enqueue(-16);
		minHeap.enqueue(-37);
		minHeap.enqueue(-58);
		minHeap.enqueue(32);
		minHeap.enqueue(62);
		minHeap.enqueue(84);
		minHeap.enqueue(-95);
		minHeap.enqueue(01);
		minHeap.enqueue(82);
		minHeap.enqueue(92);
		
		System.out.println("\n" + minHeap);
		
		System.out.println("\nPeek is " + minHeap.peek());
		
		System.out.println("\n" + minHeap);
	}

}
