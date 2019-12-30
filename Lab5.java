package asupekar_lab5;

/**
 * Implements the HashTable using hash function.
 * 
 * @author Aishwarya
 * @version 1.0
 */

public class Lab5 {
	/**
	 * Implements a HashTable, using hash function and compares time taken by the
	 * last 10 elements to get added in the hash table.
	 * 
	 * @param args Contains command line argument
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		welcome();
		HashTable table = new HashTable(4093);
		final int countLimit = 3992;
		for (int i = 0; i <= countLimit; i++) {
			table.put(i, i + 1);
		}
		System.out.println("\nCreating a HashTable with capacity " + table.capacity + " and inserting " + countLimit
				+ "\n" + "random key-value pairs with unique keys..done.");

		System.out.println("Hashtable empty: " + table.isEmpty());

		System.out.println("Hashtable size : " + table.size());

		System.out.println("\nPerforming timing test as the HashTable starts to become full: ");
		gethashTimeSeconds(table);

		HashTable table1 = new HashTable(4093);

		final int limitCount = 4083;
		for (int i = 1; i <= 4083; i++) {
			table1.put(i, i + 1);
		}
		
		System.out.println("table 1 size " + table1.size());
		System.out.println("\nCreating a HashTable with capacity " + table1.capacity + " and inserting " + limitCount
				+ "\n" + "random key-value pairs with unique keys..done.");

		System.out.println("Inserting 10 additional key-values..");

		System.out.println("(1179, 120)");
		table1.put(1179, 120);
		System.out.println("(9702, 121)");
		table1.put(9702, 121);
		System.out.println("(7183, 122)");
		table1.put(7183, 122);
		System.out.println("(50184, 123)");
		table1.put(50184, 123);
		System.out.println("(4235, 124");
		table1.put(4235, 124);
		System.out.println("(644, 125)");
		table1.put(644, 125);
		System.out.println("(77, 126)");
		table1.put(77, 126);
		System.out.println("(3077, 127)");
		table1.put(3077, 127);
		System.out.println("(23477, 128)");
		table1.put(23477, 128);
		System.out.println("(90777, 129)");
		table1.put(90777, 129);

		System.out.println("\nTesting contains...");
		System.out.println("contains(50184): " + table1.contains(50184));
		System.out.println("contains(   77): " + table1.contains(77));
		System.out.println("contains(    0): " + table1.contains(0));
		System.out.println("contains(   -1): " + table1.contains(-1));

		System.out.println("\nTesting get..");
		System.out.println("get(50184): " + table1.get(50184));
		System.out.println("get(   77): " + table1.get(77));
		System.out.println("get(    0): " + table1.get(0));
		System.out.println("get(   -1): " + table1.get(-1));

		goodbye();

	}

	/**
	 * Prints a goodbye message on the console
	 */
	private static void goodbye() {
		System.out.println("\nThanks for using the HashTable testing program. Goodbye.");

	}

	/**
	 * Prints a welcome message on the console.
	 */
	public static void welcome() {
		System.out.println("Welcome to the HashTable timing and testing program.");
	}

	/**
	 * Calculates the time taken by the last 10 inputs before the hash table gets
	 * full.
	 * 
	 * @param table Object of HashTable class
	 * @throws Exception
	 */
	private static void gethashTimeSeconds(HashTable table) throws Exception {

		for (int i = 3993; i < 4093; i += 10) {
			long startTime;
			long endTime;
			long elapsedTime = 0;
			double averageElasedTime;
			for (int j = i; j < (i + 10); j++) {
				startTime = System.nanoTime();
				table.put(j, j + 1);
				endTime = System.nanoTime();
				elapsedTime += (endTime-startTime);
			}
			averageElasedTime = elapsedTime/10.0;
			
			System.out.print(i + " to " + (i + 9) + ", ");
			System.out.printf("%5.1f", ((table.size() / 4093.0) * 100));
			System.out.print("%: ");
			System.out.printf("%7.6f", (averageElasedTime / 1000000000.0));
			System.out.println();
		}

	}
}
