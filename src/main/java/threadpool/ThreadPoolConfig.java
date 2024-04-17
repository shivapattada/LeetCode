/**
 * 
 */
package threadpool;

/**
 * @author spattada
 *
 */
public class ThreadPoolConfig {
	private int initialThreadCount;
	private int maxThreadCount;
	private int queueSize;
	public ThreadPoolConfig(int initialThreadCount, int maxThreadCount, int queueSize) {
		super();
		this.initialThreadCount = initialThreadCount;
		this.maxThreadCount = maxThreadCount;
		this.queueSize = queueSize;
	}
	public int getInitialThreadCount() {
		return initialThreadCount;
	}
	public int getMaxThreadCount() {
		return maxThreadCount;
	}
	public int getQueueSize() {
		return queueSize;
	}
	
}
