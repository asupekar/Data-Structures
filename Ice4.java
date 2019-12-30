package asupekar_ice4;

import java.util.Scanner;

public class Ice4 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Enter the value: ");
		int value = keyboard.nextInt();
		System.out.print("Enter the value for n: ");
		int n = keyboard.nextInt();
		keyboard.nextLine();
		for (int i = n; i <= 6400; i *= 2) {
			double time = getFooTimeSeconds(i, value);
			System.out.println("n: " + i + " time: " + time);

		}
		keyboard.close();
	}

	/**
	 * Mystery function.
	 * 
	 * @param n   mystery argument 1
	 * @param val mystery argument 2
	 */
	private static void foo(long n, long val) {
		long b = 0;
		long c = 0;
		for (long j = 4; j < n; j++) {
			for (long i = 1; i < j; i++) {
				b = b * val;
				for (long k = 1; k <= n; ++k) {
					c = b + c;
				}
			}
		}
	}

	/**
	 * Executes the mystery function and returns the time elapsed in seconds.
	 * 
	 * @param n   mystery argument 1 to call foo with
	 * @param val mystery argument 2 to call foo with
	 * @return time elapsed in seconds
	 */
	private static double getFooTimeSeconds(long n, long val) {
		final long startTime = System.nanoTime();
		foo(n, val);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
}
