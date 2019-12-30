package asupekar_lab3;

import java.util.ArrayList;
import java.util.List;
/**
 * This class uses a Binary Search Tree to Implement a Dictionary
 * 
 * @author Aishwarya
 * @version 1.0
 *
 */

public class BSTDict implements DictionaryADT {

	private Node root;

	private int count;

	public BSTDict() {
		// these are redundant since Java sets references to null and integers to 0
		// automatically
		// but it's good to remind ourselves about the state of all our instance data
		root = null;
		count = 0;
	}

	/**
	 * Returns the size of the  tree.
	 */
	public int size() {
		return count;
	}

	/**
	 * Checks whether the tree is empty.
	 * 
	 * @return True if empty, false otherwise.
	 */
	public boolean empty() {
		return count == 0;
	}

	private static class Node {
		String key;
		String value;
		Node left;
		Node right;

		Node(String key, String value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}

	}

	/**
	 * Checks whether the given key is present or not.
	 * 
	 * @param Key Value to be found.
	 * @returnTrue if found, False otherwise.
	 */
	public boolean has(String key) {
		return hashelper(key, root);
	}

	/**
	 * Helper method to recursively find if the key exists.
	 * 
	 * @param key  To be found
	 * @param root Of the BST
	 * @return True if found, false otherwise.
	 */
	private boolean hashelper(String key, Node root) {
		if (root == null)
			return false;
		else if (key.compareTo(root.key) < 0)
			return hashelper(key, root.left); // Search the left branch
		else if (key.compareTo(root.key) > 0)
			return hashelper(key, root.right); // Search the right branch
		else // if (x == curr.value)
			return true;
	}

	/**
	 * Adds key and value to the tree
	 * 
	 * @param key   Key to be added
	 * @param value To be attached with key
	 */
	public void add(String key, String value) {
		root = addhelp(key, value, root);
	}

	/**
	 * Helper method to add key and value to the tree recursively.
	 * 
	 * @param key1   To be added
	 * @param value1 To be attached with the key.
	 * @param curr   Current node in the tree.
	 * @return Root of the tree
	 */
	private Node addhelp(String key1, String value1, Node curr) {
		if (curr == null) {
			count++;
			return new Node(key1, value1);
		}
		if (key1.compareTo(curr.key) < 0)
			curr.left = addhelp(key1, value1, curr.left);
		else if (key1.compareTo(curr.key) > 0)
			curr.right = addhelp(key1, value1, curr.right);
		else {
			curr.key = key1; // this does nothing since keys are not a reference type, but we'll keep it in
			curr.value = value1;
		}
		return curr;
	}

	/**
	 * Removes the given key from the tree.
	 * 
	 * @param Key to be removed
	 */
	public void remove(String key) {
		root = remove(key, root);
	}

	/**
	 * Helper method to remove key from the tree recursively.
	 * 
	 * @param key  To be removed
	 * @param curr Current node in the tree
	 * @return Root of the tree.
	 */
	private Node remove(String key, Node curr) {
		if (curr == null)
			return null; // x is not in the BST, so we "succeeded" before we even started

		if (key.compareTo(curr.key) < 0) {
			curr.left = remove(key, curr.left);
			return curr;
		} else if (key.compareTo(curr.key) > 0) {
			curr.right = remove(key, curr.right);
			return curr;
		} else {
			// x == curr.key, so remove this one
			if (curr.left == null) {
				count--;
				return curr.right; // works also if curr is a leaf, since this would return null
			} else if (curr.right == null) {
				count--;
				return curr.left;
			} else {
				// has both right and left, so we need to swap with predecessor node
				// the predecessor is the max value from left (equally could use successor node:
				// min value from right)
				Node predecessor = getMax(curr.left);

				// Copy both key and value from predecessor to the node to be deleted
				curr.key = predecessor.key;
				curr.value = predecessor.value;
				// and then remove the node that had the predecessor
				curr.left = remove(curr.key, curr.left);
				return curr;
			}
		}
	}

	/**
	 * Returns the node with maximum value on right side of the node
	 * 
	 * @param curr Node to compare with
	 * @return Node with maximum value on the right.
	 */
	private Node getMax(Node curr) {
		while (curr.right != null)
			curr = curr.right;
		return curr;
	}

	/**
	 * Returns the node with minimum value on right side of the node
	 * 
	 * @param curr Node to compare with
	 * @return Node with minimum value on the right.
	 */
	@SuppressWarnings("unused")
	private Node getMin(Node curr) {
		while (curr.left != null)
			curr = curr.left;
		return curr;
	}

	/**
	 * Like inorder, we want all the keys in sorted order, but instead of printing
	 * them out, return them in a list.
	 *
	 * @return sorted list of keys from this BST
	 */
	public List<String> keys() {
		List<String> ret = new ArrayList<>();
		keysInOrder(ret, root);
		return ret;
	}

	/**
	 * Returns the tree in-order.
	 * 
	 * @param ret  List of String
	 * @param curr Current Node
	 */
	private void keysInOrder(List<String> ret, Node curr) {
		if (curr != null) {
			keysInOrder(ret, curr.left);
			ret.add(curr.key);
			keysInOrder(ret, curr.right);
		}
	}

	/**
	 * Calls the inorder method
	 */
	public void inorder() {
		inorder(root);
	}

	/**
	 * Prints the Node in In-order.
	 * 
	 * @param curr Node for reference.
	 */
	private void inorder(Node curr) {
		if (curr != null) {
			inorder(curr.left);
			System.out.print(curr.key + ":" + curr.value + " ");
			inorder(curr.right);
		}
	}

	/**
	 * Prints the Tree in preorder.
	 */
	public void preorder() {
		preorder(root);
	}

	/**
	 * Prints the Tree in preorder.
	 * 
	 * @param curr Node for reference.
	 */
	private void preorder(Node curr) {
		if (curr != null) {
			System.out.print(curr.key + " " + curr.value);
			preorder(curr.left);
			preorder(curr.right);
		}
	}

	/**
	 * Prints the Tree in postorder.
	 */
	public void postorder() {
		postorder(root);
	}

	/**
	 * Prints the Tree in preorder.
	 * 
	 * @param curr Node for reference.
	 */
	private void postorder(Node curr) {
		if (curr != null) {
			postorder(curr.left);
			postorder(curr.right);
			System.out.print(curr.key + " ");
		}
	}

	/**
	 * Calls the get method.
	 * 
	 * @param key To be found
	 * @return The value for key.
	 */
	public String get(String key) {

		return get(key, root);
	}

	/**
	 * Finds the value for the corresponding key
	 * 
	 * @param key  Value for which to be found
	 * @param curr Reference Node
	 * @return value of the key.
	 */
	public String get(String key, Node curr) {
		if (curr == null) {
			return null;
		}
		if (key.compareTo(curr.key) < 0) {
			return get(key, curr.left);
		} else if (key.compareTo(curr.key) > 0) {
			return get(key, curr.right);
		}
		return curr.value;

	}

	public static void main(String[] args) {
		BSTDict tree = new BSTDict();
		String[] keys = { "bat", "fox", "ant", "cat", "yak", "dog", "elk", "poodle" };

		System.out.println("adding animals (keys lowercase, values uppercase)");
		for (String key : keys) {
			String value = key.substring(0, 1).toUpperCase() + key.substring(1);
			tree.add(key, value);
		}

		for (String key : keys)
			System.out.println("Value for " + key + " is: " + tree.get(key));

		System.out.print("\ninorder: ");
		tree.inorder();

		tree.remove("elk");
		System.out.println("\n\nremoving animals");
		for (String key : keys) {
			tree.remove(key);
			System.out.print("\ninorder: ");
			tree.inorder();
		}
	}
}
