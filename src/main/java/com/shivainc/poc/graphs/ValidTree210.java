/**
 * 
 */
package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <pre>
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 * </pre>
 */
public class ValidTree210 {

	public Boolean checkGraph(int[][] edges, int n, int m) {
		List<List<Integer>> graph = getNeighbour(n, edges);
		HashSet<Integer> vistedList = new HashSet<Integer>();

		return isValidTree(0, graph, vistedList) && vistedList.size() == n;
	}

	public boolean isValidTree(int node, List<List<Integer>> graph, HashSet<Integer> vistedList) {
		if (vistedList.contains(node)) {
			return false;
		}
		vistedList.add(node);
		if (graph.get(node).size() == 0) {
			return true;
		}
		List<Integer> childList = graph.get(node);
		for (Integer eachChild : childList) {
			if (!isValidTree(eachChild, graph, vistedList)) {
				return false;
			}
		}
		return true;
	}

	public List<List<Integer>> getNeighbour(int n, int[][] edges) {
		List<List<Integer>> list = createEmptyList(n);
		for (int[] each : edges) {
			list.get(each[0]).add(each[1]);
		}
		return list;
	}

	public List<List<Integer>> createEmptyList(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			result.add(i, new ArrayList<Integer>());
		}
		return result;
	}

	public static void main(String[] args) {
		ValidTree210 validTree = new ValidTree210();
		System.out.println(validTree.checkGraph(new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }, 5, 4));

	}

}
