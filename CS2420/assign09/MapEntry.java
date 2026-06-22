package assign09;

/**
 * This class represents a single entry in a map; i.e., a key-value pair.
 * The key is assumed non-null, but the value can be null.
 * 
 * @author CS 2420 course staff
 * @version March 19, 2026
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 */
public class MapEntry<K, V> {
	private K key;
	private V value;

	/**
	 * Creates a new MapEntry with the specified key and value.
	 * 
	 * @param key - the key portion of the new map entry
	 * @param value - the value portion of the new map entry
	 */
	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Getter for the key portion of this MapEntry.
	 * 
	 * @return the key of this MapEntry
	 */
	public K getKey() {
		return this.key;
	}

	/**
	 * Getter for the value portion of this MapEntry.
	 * 
	 * @return the value of this MapEntry
	 */
	public V getValue() {
		return this.value;
	}
	
	/**
	 * Resets the value portion of this MapEntry.
	 * 
	 * @param value - the new value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	/**
	 * Overrides Objects's equals method to leverage the equals methods of the 
	 * key and value members.
	 * 
	 * @param other - the object to compare this MapEntry with
	 * @return true if the objects are equal, false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MapEntry<?, ?>))
			return false;
		
		MapEntry<?, ?> rhs = (MapEntry<?, ?>)other;
		
		if(value == null)
			return key.equals(rhs.key) && rhs.value == null;
		return key.equals(rhs.key) && value.equals(rhs.value);
	}
	
	/**
	 * Overrides Object's toString method to leverage the toString methods of the 
	 * key and value members.
	 * 
	 * @return a textual representation of this MapEntry; i.e., (key, value) pair
	 */
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}