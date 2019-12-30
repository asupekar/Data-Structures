package asupekar_lab3;

import java.util.List;

/**
 * A dictionary in this context means a mapping from String to String. This
 * interface describes how we expect all implementations of dictionary to
 * behave. The items in the dictionary are indexed by a key and each key in the
 * dictionary has a corresponding value.
 *
 * @author Aishwarya
 * @version r19
 */
public interface DictionaryADT {
	/**
	 * Returns true if the dictionary contains the specified key.
	 *
	 * @param key key whose presence in this dictionary is to be tested
	 * @return true if this dictionary contains the specified key
	 */
	public boolean has(String key);

	/**
	 * Returns the value corresponding to the specified key. A null is returned if
	 * there is no such key in the dictionary.
	 *
	 * @param key key whose value is sought
	 * @return value corresponding to the specified key (null if not found)
	 */
	public String get(String key);

	/**
	 * Adds the specified key/value pair to the dictionary. If the key is already
	 * present, its value is replaced with the specified value.
	 *
	 * @param key   key to be added to the dictionary
	 * @param value value to be added to correspond to the given key
	 */
	public void add(String key, String value);

	/**
	 * Remove the specified key from the dictionary (does nothing if the key is not
	 * present).
	 *
	 * @param key key to be removed from the dictionary
	 */
	public void remove(String key);

	/**
	 * Returns true if this dictionary contains no keys.
	 *
	 * @return true if this dictionary contains no keys
	 */
	public boolean empty();

	/**
	 * Returns the number of key/value pairs in this dictionary.
	 *
	 * @return the number of keys in this dictionary
	 */
	public int size();

	/**
	 * Returns a list of keys in this dictionary. Order is arbitrary at the
	 * discretion of the implementation. Note: Use the get method to retrieve any
	 * desired corresponding values.
	 *
	 * @return a list of keys in this dictionary
	 */
	public List<String> keys();
}
