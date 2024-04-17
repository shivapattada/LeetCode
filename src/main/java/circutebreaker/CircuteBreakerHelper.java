/**
 * 
 */
package circutebreaker;

import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @author spattada
 *
 */
public class CircuteBreakerHelper {
	HashMap registry;
	
	public <T> T subscribe(T service,int percentage,long waitTime) {
		if(registry.get(service) != null) {
			return (T)registry.get(service);
		}
		CircuteBrakerConfig config = new CircuteBrakerConfig(percentage, waitTime);
		T result =  (T)Proxy.newProxyInstance(service.getClass().getClassLoader(), new Class[] {service.getClass()}, new CircuteBreaker(config,new ResponseHandler() {
			
			@Override
			public boolean isSucess(Object respose) {
				
				return respose == null ? false: true;
			}
		}));
		 registry.put(service.getClass(), result);
		return result;
	}
}
