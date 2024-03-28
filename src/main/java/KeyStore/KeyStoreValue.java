/**
 * 
 */
package KeyStore;

/**
 * 
 */
public class KeyStoreValue<V> {
	private V value;
	private long ttl ; 
	private long createTime;
	private Callback<V> callback;
	public KeyStoreValue(V value, long ttl, long createTime, Callback<V> callback) {
		super();
		this.value = value;
		this.ttl = ttl;
		this.createTime = createTime;
		this.callback = callback;
	}
	public V getValue() {
		return value;
	}
	public long getTtl() {
		return ttl;
	}
	public long getCreateTime() {
		return createTime;
	}
	public Callback<V> getCallback() {
		return callback;
	}
	
	

}
