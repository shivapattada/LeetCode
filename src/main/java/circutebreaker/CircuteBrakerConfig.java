/**
 * 
 */
package circutebreaker;

/**
 * @author spattada
 *
 */
public class CircuteBrakerConfig {
	
	private int threasholdPercentage;
	
	private long waitTime;

	public CircuteBrakerConfig(int threasholdPercentage, long waitTime) {
		super();
		this.threasholdPercentage = threasholdPercentage;
		this.waitTime = waitTime;
	}

	public int getThreasholdPercentage() {
		return threasholdPercentage;
	}

	public long getWaitTime() {
		return waitTime;
	}
	

}
