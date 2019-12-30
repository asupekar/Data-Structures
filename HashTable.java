package asupekar_lab5;

import java.util.Random;

/**
 * Implements a hash table.
 * 
 * @author Aishwarya
 * @version 1.0
 *
 */
public class HashTable {
	int capacity;
	HashBucket[] hashTable;

	/**
	 * Constructor to set the capacity and initialize HashBucket
	 * 
	 * @param capacity of the HashBucket
	 */
	public HashTable(int capacity) {
		this.capacity = capacity;
		hashTable = new HashBucket[capacity];
	}

	/**
	 * Inserts the given key in the hash table.
	 * 
	 * @param key   Location at which the value should be added
	 * @param value Associated with respective key
	 * @return if the key is present return value or -1 if there is no mapping for
	 *         the key.
	 * @throws Exception if hash table is full
	 */
	public int put(int key, int value) throws Exception {
		int hash = hash(key);
		
		int bucket = hash % capacity;
		
		if (bucket < 0) {
			bucket = (bucket * (-1));
		}
		
		//System.out.println("Bucket is "+ bucket);
		
		if (hashTable[bucket] == null) {
			hashTable[bucket] = new HashBucket(key, value);
		} else {
			if (contains(key)) {
				int oldValue = get(key);
				int count = 0;
				while (count < capacity) {		
					if(hashTable[bucket] != null) {
						if(hashTable[bucket].key == key) {
							hashTable[bucket].value = value;
							return oldValue;
						}
					}
					bucket = (bucket + 1) % capacity;
					count++;
				}
				return oldValue;
			} else {
				int count = 0;
				while (hashTable[bucket] != null && count < capacity) {
					bucket = (bucket + 1) % capacity;
					count++;
				}

				if (count == capacity) {
					throw new Exception("Full");
				}

				hashTable[bucket] = new HashBucket(key, value);
			}
		}

		return -1;
	}

	/**
	 * Calculates the hash function for the given key
	 * 
	 * @param key Hash function of which is to be calculated.
	 * @return hash value of the key.
	 */
	private int hash(int key) {
		int hash = new Random().nextInt((4092 - 1) + 1) + 1;
		return hash;
	}

	/**
	 * Returns the value to which the specified key is mapped, or -1 if this
	 * HashTable contains no mapping for the key.
	 * 
	 * @param key the key for which value is to be obtained
	 * @return a value associated with the specified key or -1 if it does not exist
	 * @throws Exception
	 */
	public int get(int key) throws Exception {
		int hash = hash(key);
		
		int bucket = hash % capacity;
		if (bucket < 0) {
			bucket = (bucket * (-1));
		}

		if (hashTable[bucket] != null && hashTable[bucket].key == key) {
			return hashTable[bucket].value;
		}
		int count = 0;
		
		while (count < capacity) {		
			if(hashTable[bucket] != null) {
				if(hashTable[bucket].key == key) {
					return hashTable[bucket].value;
				}
			}
			bucket = (bucket + 1) % capacity;
			count++;
		}

		return -1;
	}

	/**
	 * Checks whether HashTable contains a mapping for the specified key
	 * 
	 * @param key to be found in HashTable
	 * @return true if this HashTable contains a mapping for the key
	 */
	public boolean contains(int key) {
		int hash = hash(key);
		
		int bucket = hash % capacity;
		if (bucket < 0) {
			bucket = (bucket * (-1));
		}
		
		int count = 0;
		while (hashTable[bucket] != null && hashTable[bucket].key != key && count < capacity) {
			bucket = (bucket + 1) % capacity;
			count++;
		}
		if (hashTable[bucket] != null && hashTable[bucket].key == key) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of unique keys stored in the table.
	 * 
	 * @return count of unique numbers
	 */
	public int size() {
		int uniqueKeyCount = 0;

		for (HashBucket bucket : hashTable) {
			if (bucket != null) {
				uniqueKeyCount++;
			}
		}

		return uniqueKeyCount;
	}

	/**
	 * Checks whether the HashTable is empty.
	 * 
	 * @return true if the HashTable is empty.
	 */
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Class to hold key and value pair together
	 */
	private static class HashBucket {
		private final int key;
		private int value;

		/**
		 * Constructor to set key and value.
		 * 
		 * @param key   value of key
		 * @param value value for key
		 */
		public HashBucket(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}
