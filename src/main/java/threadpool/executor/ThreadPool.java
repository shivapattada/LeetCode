package threadpool.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import threadpool.Task;

public class ThreadPool {
	
	private List<Worker> workerList;
	private int maxThreadsSize;
	private int initialThreadsSize;
	BlockingQueue<Task> queue;
	
	public ThreadPool(BlockingQueue<Task> queue, int maxThreadsSize, int initialThreadsSize) {
		super();
		this.queue = queue;
		this.maxThreadsSize = maxThreadsSize;
		this.initialThreadsSize = initialThreadsSize;
	}

	
	public void init() {
		workerList = new ArrayList<Worker>(maxThreadsSize);
		for(int i =0 ;i<initialThreadsSize;i++) {
			workerList.add(new Worker(false, false, queue));
		}
		for(Worker eachWorker:workerList) {
			Thread t = new Thread(eachWorker);
			t.start();
		}
	}
	
	public void submit(Task task) {
		Worker worker = new Worker(false, false, queue);
		workerList.add(worker);
		Thread t = new Thread(worker);
		t.start();
		queue.add(task);
	}
	
	public boolean isMaxSizeReached() {
		return workerList.size() >= maxThreadsSize;
	}
	
	public synchronized void close() {
		for(Worker eachW : workerList) {
			eachW.stop();
		}
	}
}
