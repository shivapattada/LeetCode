package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

 

Example 1:


Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Example 2:


Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
 * </pre>
 */

public class AllPathsFromSourceToTarget797 {

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> finalResult = new ArrayList<List<Integer>>();
		findAllPaths(graph, new ArrayList<Integer>(), 0, graph.length - 1, finalResult);
		return finalResult;
	}

	public void findAllPaths(int[][] graph, List<Integer> paths, int src, int dst, List<List<Integer>> finalResult) {
		paths.add(src);
		if (src == dst) {
			finalResult.add(paths);
			return;
		}
		int[] childs = graph[src];
		for (int eachChild : childs) {
			List<Integer> childPaths = new ArrayList<Integer>(paths);
			findAllPaths(graph, childPaths, eachChild, dst, finalResult);
		}
	}

	public static void main(String[] args) {
		AllPathsFromSourceToTarget797 solution = new AllPathsFromSourceToTarget797();
		solution.allPathsSourceTarget(new int[][] { { 1, 2 }, { 3 }, { 3 }, {} });
	}
}
