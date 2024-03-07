/**
 * 
 */
package com.shivainc.poc.graphs;

import java.util.HashSet;

/**
 * <pre>
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique

 

Example 1:

Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
 

Constraints:

n == gas.length == cost.length
1 <= n <= 105
0 <= gas[i], cost[i] <= 104
 * </pre>
 */
public class GasStation134 {

	/**
	 * <pre>
	 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
	 * 
	 * logic : 
	 *   1. Caclulate profit  graph (gas - cost) to reach each node 
	 *   	graph<nextStation,expenditure> = [
	 *   										[1,-2]
	 *   										[2,-2]
	 *   										[3,-2]
	 *   	`									[4,3]
	 *   										[0,3]
	 *   									]
	 *   2. loop through the starting point which is alteast Zero or greater 
	 *   3. recursively calculate path sum from the node to itself . if sum is greater than or equals to zero then return the index 
	 *   4. if 3, doesent work , then go to next node . if end of loop then return -1 
	 * 
	 * </pre>
	 * @param gas
	 * @param cost
	 * @return
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int[][] graph = createGraph(gas, cost);
		for (int i = 0; i < graph.length; i++) {
			if (graph[i][1] < 0) {
				continue;
			}
			if (isValid(graph, i, new HashSet<Integer>(), 0)) {
				return i;
			}
		}
		return -1;
	}

	public boolean isValid(int[][] graph, int node, HashSet<Integer> visited, int sum) {
		sum = sum + graph[node][1];
		if (visited.contains(node)) {
			return sum >= 0;
		}
		visited.add(node);
		if (sum < 0) {
			return false;
		}
		return isValid(graph, graph[node][0], visited, sum);
	}

	public int[][] createGraph(int[] gas, int[] cost) {
		int[][] graph = new int[gas.length][2];
		for (int i = 0; i < gas.length; i++) {
			int nextNode;
			if (i == gas.length - 1) {
				nextNode = 0;
			} else {
				nextNode = i + 1;
			}
			graph[i][0] = nextNode;
			graph[i][1] = gas[i] - cost[i];
		}
		return graph;
	}
}
