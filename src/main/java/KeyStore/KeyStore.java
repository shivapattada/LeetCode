package KeyStore;

import java.util.HashMap;

/**

Build a simple KeyValue store where you could do the following:

1. put(key, value) //key, value could be any type
2. get(key) -> [Val] //return val if present
3. putWithExpiry(key2, value, TTL) //put a value which stays till the TTL, post which it expires
4. get(key2) //if invoked after TTL expired, you could return empty
5. putWithUpdates(key3, value, TTL, func(){}) //upon TTL breach, execute the func which returns the value computed
6. get(key3) -> if <TTL -> return value; if >=TTL, you are expected to execute the func() and return value [user has option to select blocking or pre-compute within X ms before TTL expires and store it]
   example: putWithUpdates("name", "Naresh", 5sec, func() {return "Vamsi"}); after 5sec after putWithUpdates(..), if get("name") is invoked, you could either call func() directly and return output "Vamsi"  (blocking way); preschedule to execute  at 4.5secs and store the output "Vamsi" which is returned
*/

public class KeyStore<K extends Comparable<K>,V> {
	
	private HashMap<K,KeyStoreValue<V>> store;
	private static long DEFAULT_TIME = 8*60*60*1000;
	

	public KeyStore() {
		super();
		store = new HashMap<K,KeyStoreValue<V>>();
	}	
	
	public V get(K key) {
		V result = null;
		KeyStoreValue<V> value = store.get(key);
		if(value != null) {
			if(((System.currentTimeMillis() - value.getCreateTime()) > value.getTtl()) && value.getCallback() != null) {
				result = value.getCallback().invoke();
			}else {
				result = value.getValue();
			}
		}		
		return result;
	}
	
	public void put(K key, V value) {
		putWithUpdates(key,value,DEFAULT_TIME,null);
	}
	
	public void putWithExpiry(K key , V value, long ttl) {
		putWithUpdates(key,value,ttl,null);
	}
	
	public void putWithUpdates(K key , V value , long ttl , Callback<V> callback) {
		KeyStoreValue<V> keyValueStore = new KeyStoreValue<V>(value, ttl, System.currentTimeMillis(), callback);
		store.put(key, keyValueStore);
	}
}
