/**
 * 
 */
package threadpool.exceptions;

/**
 * @author spattada
 *
 */
public class ThreadPoolException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	

	public ThreadPoolException(String message,Throwable t) {
		super(message,t);
		this.message = message;
	}



	public String getMessage() {
		return message;
	}
	

}
