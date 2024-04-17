/**
 * 
 */
package threadpool.executor;

import java.util.concurrent.BlockingQueue;

import threadpool.Task;

/**
 * @author spattada
 *
 */
public class Worker implements Runnable {
	
	boolean isBusy;
	boolean isStopped;
	BlockingQueue<Task> queue;
	
	

	public Worker(boolean isBusy, boolean isStopped, BlockingQueue<Task> queue) {
		super();
		this.isBusy = isBusy;
		this.isStopped = isStopped;
		this.queue = queue;
	}

	@Override
	public void run() {
		while(!isStopped) {
			try {
				Task task = queue.take();
				isBusy = true;
				task.invoke();
			} catch (InterruptedException e) {
				// log error
				e.printStackTrace();
			}finally {
				isBusy = false;
			}
			
		}
		// log closing the thread
	}
	
	public boolean isBusy() {
		return isBusy;
	}
	
	public boolean isStopped() {
		return isStopped;
	}
	
	public void stop() {
		this.isStopped = true;
	}

}
