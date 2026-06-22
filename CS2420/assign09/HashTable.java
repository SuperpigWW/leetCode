package assign09;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * This class represents a hash table that maps keys to values, with collisions
 * resolved using separate chaining. The table automatically rehashes when the
 * load factor exceeds the specified maximum.
 *
 * @author Haoquan Wang
 * @version 2026-4-1
 * 
 */
public class HashTable<K, V> implements Map<K, V>{
	
	private Object[] hashTable;
	private double maxLoadFactor;
	private int size;
	
	/**
	 * Creates a new HashTable with a default maximum load factor of 10.0
	 * and an initial capacity of 10.
	 */
	@SuppressWarnings("unchecked")
    public HashTable() {
        this.hashTable = new Object[10]; 
        this.maxLoadFactor = 10.0;
        this.size = 0;
    }
	
	/**
	 * Creates a new HashTable with a user-specified maximum load factor
	 * and an initial capacity of 10.
	 *
	 * @param maxLoadFactor - the maximum load factor allowed before rehashing
	 * @throws IllegalArgumentException if maxLoadFactor is less than 1.0
	 */
	@SuppressWarnings("unchecked")
    public HashTable(double maxLoadFactor) {
		if (maxLoadFactor < 1.0)
			throw new IllegalArgumentException("Please input another maximum load factor that is bigger than 1.0.");
        this.hashTable = new Object[10]; 
        this.maxLoadFactor = maxLoadFactor;
        this.size = 0;
    }

	/**
	 * Returns the chain (linked list) stored at the given index of the hash table.
	 *
	 * @param index - the index into the hash table array
	 * @return the LinkedList of MapEntry objects at that index, or null if empty
	 */
    @SuppressWarnings("unchecked")
    private LinkedList<MapEntry<K, V>> getChain(int index) {
        return (LinkedList<MapEntry<K, V>>) hashTable[index];
    }
    
    /**
     * Computes the load factor for a given number of entries relative to the
     * current table length.
     *
     * @param mapSize - the number of entries to compute the load factor for
     * @return the load factor as a double
     */
    private double loadFactor(int mapSize) {
    	return (double) mapSize / hashTable.length;
    }
    
    /**
     * Computes the table index for a given hash code, ensuring a non-negative result.
     *
     * @param hashCode - the hash code of the key
     * @return a valid index into the hash table array
     */
    private int getIndex(int hashCode) {
    	return (hashCode & 0x7fffffff) % hashTable.length;
    }
    
    /**
     * Searches for and returns the MapEntry associated with the given key.
     *
     * @param key - the key to search for
     * @return the MapEntry with the matching key, or null if not found
     */
    private MapEntry<K, V> findEntryByKey(K key){
    	int hashKey = getIndex(key.hashCode());
        LinkedList<MapEntry<K, V>> list = getChain(hashKey);
        if (list != null) {
            for (MapEntry<K, V> mapEntry : list) {
                if (mapEntry.getKey().equals(key)) 
                	return mapEntry;
                numberOfCollisions++;
            }
        }
        return null;
    }

	/**
	 * Removes all mappings from this hash table by setting every slot in the
	 * underlying array to null and resetting the size counter to zero.
	 * 
	 * O(table length)
	 */
	@Override
	public void clear() {
		for(int i = 0; i < this.hashTable.length; i ++) 
			this.hashTable[i] = null;
		this.size = 0;
	}

	/**
	 * Determines whether this hash table contains the specified key by computing
	 * the key's hash index and searching the corresponding chain for a matching entry.
	 *
	 * O(1)
	 *
	 * @param key - the key being searched for
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		return findEntryByKey(key) != null;
	}

	/**
	 * Determines whether this hash table contains the specified value by iterating
	 * through every chain in the table and comparing each entry's value. Handles
	 * the case where the target value is null.
	 *
	 * O(table length + number of entries)
	 *
	 * @param value - the value being searched for
	 * @return true if this map contains one or more keys mapped to the specified value,
	 *         false otherwise
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean containsValue(V value) {
		for(int i = 0; i < this.hashTable.length; i ++) {
			if (this.hashTable[i] == null)
				continue;
			else {
				LinkedList<MapEntry<K, V>> list = (LinkedList<MapEntry<K, V>>) hashTable[i];
				if (list != null) {
					for (MapEntry<K, V> mapEntry : list) {
						V entryVal = mapEntry.getValue();
						if (entryVal == null) {
						    if (value == null) return true; 
						} else {
						    if (entryVal.equals(value)) return true; 
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns a list of all key-value mappings contained in this hash table by
	 * iterating through every chain and collecting each MapEntry into an ArrayList.
	 * The ordering of entries in the returned list is insignificant.
	 *
	 * O(table length + number of entries)
	 *
	 * @return a List containing all MapEntry objects in this map
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> listOfEntries = new ArrayList<>(this.size);
	    for (int i = 0; i < hashTable.length; i++) {
	        LinkedList<MapEntry<K, V>> list = (LinkedList<MapEntry<K, V>>) hashTable[i];
	        if (list != null) {
	            for (MapEntry<K, V> mapEntry : list) {
	            	listOfEntries.add(mapEntry);
	            }
	        }
	    }
	    return listOfEntries;
	}

	/**
	 * Returns the value to which the specified key is mapped by computing the key's
	 * hash index and searching the corresponding chain for a matching entry.
	 *
	 * O(1)
	 *
	 * @param key - the key whose mapped value is to be returned
	 * @return the value mapped to the specified key, or null if no mapping exists
	 */
	@Override
	public V get(K key) {
		MapEntry<K, V> MapEntry = findEntryByKey(key);
	    if (MapEntry == null)
	    	return null;
	    else
	    	return MapEntry.getValue();
	}

	/**
	 * Determines whether this hash table contains any mappings by checking
	 * if the size counter is zero.
	 *
	 * O(1)
	 *
	 * @return true if this map contains no mappings, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Associates the specified value with the specified key in this hash table.
	 * If the key already exists, its value is updated and the previous value is
	 * returned. If the key is new, a new MapEntry is added to the appropriate chain.
	 * If adding the new entry would cause the load factor to exceed the maximum,
	 * the table is rehashed before insertion.
	 *
	 * O(1)
	 *
	 * @param key - the key for which to update the value (if exists) or to be added
	 * @param value - the value to be mapped to the key
	 * @return the previous value associated with the key, or null if no mapping existed
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		MapEntry<K, V> findEntry = findEntryByKey(key);
		if (findEntry != null) {
			V entryValue = findEntry.getValue();
			findEntry.setValue(value);
			return entryValue;
		}
		else {
			if (loadFactor(size + 1) > maxLoadFactor) {
				rehash();
			}
			int hashKey = getIndex(key.hashCode());
			LinkedList<MapEntry<K, V>> list = (LinkedList<MapEntry<K, V>>) hashTable[hashKey];
			if (list == null) {
			    list = new LinkedList<MapEntry<K, V>>();
			}
			MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
			list.add(mapEntry);
			hashTable[hashKey] = list;
			this.size ++;
			return null;
		}
	}
	
	/**
	 * Doubles the size of the hash table array and rehashes all existing entries
	 * into the new, larger table.
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		Object[] previousTable = this.hashTable; 
	    this.hashTable = new Object[previousTable.length * 2]; 
	    this.size = 0; 

	    for (int i = 0; i < previousTable.length; i++) {
	        LinkedList<MapEntry<K, V>> list = (LinkedList<MapEntry<K, V>>) previousTable[i];
	        
	        if (list != null) {
	            for (MapEntry<K, V> mapEntry : list) {
	                this.put(mapEntry.getKey(), mapEntry.getValue());
	            }
	        }
	    }
	}

	/**
	 * Removes the mapping for the specified key from this hash table if it is present.
	 * The key's hash index is computed, the corresponding chain is searched, and the
	 * matching entry is removed from the linked list.
	 *
	 * O(1)
	 *
	 * @param key - the key whose mapping is to be removed
	 * @return the previous value associated with the key, or null if no mapping existed
	 */
	@Override
	public V remove(K key) {
	    int hashKey = getIndex(key.hashCode());
	    LinkedList<MapEntry<K, V>> list = getChain(hashKey);
	    if (list != null) {
	        for (MapEntry<K, V> entry : list) {
	            if (entry.getKey().equals(key)) {
	                V value = entry.getValue();
	                list.remove(entry);
	                this.size--;
	                return value;
	            }
	        }
	    }
	    return null;
	}

	/**
	 * Returns the number of key-value mappings currently stored in this hash table.
	 *
	 * O(1)
	 *
	 * @return the number of mappings in this map
	 */
	@Override
	public int size() {
		return size;
	}
	
	private int numberOfCollisions;

	protected int getNumberOfCollisions() {
	    return this.numberOfCollisions;
	}

	protected void resetNumberOfCollisions() {
	    this.numberOfCollisions = 0;
	}
}