package asupekar_lab2;
/**
 * Checks whether the given String is a palindrome or not using recursion.
 * 
 * @author Aishwarya
 * @version 1.0
 */

import java.util.Scanner;

public class Lab2 {

	public static Scanner keyboard = new Scanner(System.in);

	/**
	 * Checks whether given String is a palindrome or not.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String userInput;
		welcome();

		do {

			System.out.print("\nTest text (ENTER to exit): ");
			userInput = keyboard.nextLine().toLowerCase();

			// Check if palindrome if the length of String is gretaer than zero.
			if (userInput.length() > 0) {
				if (isPalindrome(userInput)) {
					System.out.println("This is a palindrome!");
				} else {
					System.out.println("This is NOT a palindrome.");
				}
			}
		} while (userInput.length() != 0);
		goodbye();
	}

	/**
	 * Prints welcome message on console.
	 */
	private static void welcome() {
		System.out.println("Welcome to the palindrome testing program!");
	}

	/**
	 * Checks if the given String is a palindrome.
	 * 
	 * @param text input String from the user
	 * @return true if palindrome, false otherwise
	 */
	public static boolean isPalindrome(String text) {
		return isPalindromeHelper(text, 0, text.length() - 1);
	}

	/**
	 * Using recursive method, checks whether the given String is palindrome.
	 * 
	 * @param text  Given inputted String
	 * @param start Pointer to the first charcter in the String
	 * @param end   Pointer to the last charcter in the String
	 * @return true if palindrome, otherwise false.
	 */
	public static boolean isPalindromeHelper(String text, int start, int end) {
		if (text == null) {
			return false;
		}
		if (start >= end) {
			return true;
		}
		if (text.charAt(start) != text.charAt(end)) {
			return false;
		}
		return isPalindromeHelper(text, start + 1, end - 1);

	}

	/**
	 * Displays goodbye message on the console.
	 */
	public static void goodbye() {
		System.out.println("\nThanks for using the palindrome tester!");
	}

}
