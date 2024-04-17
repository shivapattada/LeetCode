package threadpool.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import threadpool.Task;
import threadpool.ThreadPoolConfig;
import threadpool.exceptions.ThreadPoolException;

public class Executor {
	private ThreadPoolConfig config;
	private BlockingQueue<Task> queue;
	private ThreadPool pool;

	public Executor(ThreadPoolConfig config) {
		super();
		this.config = config;
	}

	public void init() {
		queue = new ArrayBlockingQueue<Task>(config.getQueueSize());
		pool = new ThreadPool(queue, config.getInitialThreadCount(), config.getMaxThreadCount());
	}
	
	public void submit(Task task) throws ThreadPoolException {
		if(!pool.isMaxSizeReached()) {
			pool.submit(task);
		}else {
			
				if(!queue.offer(task)) {			
					throw new ThreadPoolException("Queueu is Full",null);
				}
			
		}
	}

	
	public void close() {
		pool.close();
	}
}
