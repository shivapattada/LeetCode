package hakerrank;

import java.util.TreeSet;

public class RunningMedian {
	
	public void printMedian(int[] array) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int each : array) { 
			set.add(each);  
			int median = (set.size()-1)/2;
			Integer[] intArray = new Integer[set.size()];
			 set.toArray(intArray);
			 System.out.println(intArray[median]);
		}
	}
	public static void main(String[] args) {
		RunningMedian runningMedian = new RunningMedian();
		runningMedian.printMedian(new int[] {8, 2, 11, 9, 5});
	}

}
