/**
 * 
 */
package circutebreaker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @author spattada
 *
 */
public class CircuteBreaker implements InvocationHandler {
	private CircuteBrakerConfig config;
	private LinkedList<Boolean> slidingWindow;
	private int sizeOfSlidingWindow;
	private Status status= Status.CLOSED;
	private ResponseHandler responseHandler;
	private long timeWhenStatusIsOpen;
	
	public CircuteBreaker(CircuteBrakerConfig config, ResponseHandler responseHandler) {
		super();
		this.config = config;
		this.responseHandler = responseHandler;
		this.slidingWindow = new LinkedList<Boolean>();
		this.sizeOfSlidingWindow = 100;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object response;
		switch(status) {
			case OPEN :
				if((timeWhenStatusIsOpen - System.currentTimeMillis()) <= config.getWaitTime()) {
					throw new RuntimeException("Circute Breaker is Open");
				}else {
					 response = method.invoke(proxy, method, args);
					if(responseHandler.isSucess(response)) {
						status = status.CLOSED;
						slidingWindow = new LinkedList<Boolean>();					
					}else {
						timeWhenStatusIsOpen = System.currentTimeMillis();
					}
					
				}
				break;
			default : 
				try {
					 response = method.invoke(proxy, method, args);
					addToSlidingWindow(responseHandler.isSucess(response));
					if(isThreasholdReached()) {
						status = Status.OPEN;
						timeWhenStatusIsOpen = System.currentTimeMillis();
					}
					
				}catch(Throwable exception) {
					addToSlidingWindow(false);
					throw exception;
				}
				
		}
		
		return response;
	}
	
	private void addToSlidingWindow(boolean value) {
		if(slidingWindow.size() <= sizeOfSlidingWindow) {
			slidingWindow.add(value);
		}else {
			slidingWindow.pop();
			slidingWindow.add(value);
		}
	}
	
	private boolean isThreasholdReached() {
		int errorCount = 0 ; 
		for(Boolean each : slidingWindow) {
			if(!each) {
				errorCount++;
			}
		}
		return ((errorCount/sizeOfSlidingWindow) * 100)>=config.getThreasholdPercentage();
	}
	

}
