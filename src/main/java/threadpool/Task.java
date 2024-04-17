/**
 * 
 */
package threadpool;

/**
 * @author spattada
 * 
 * Clients must use this to submit job 
 *
 */
public interface Task {
	public void invoke();
}
