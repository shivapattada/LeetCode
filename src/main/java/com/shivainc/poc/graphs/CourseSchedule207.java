/**
 * 
 */
package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <pre>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * </pre>
 */
public class CourseSchedule207 {

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<List<Integer>> uniderectionalGraph = createUniDirectionalGraph(numCourses, prerequisites);
		List<Boolean> completeList = getCompleteList(numCourses);
		for (int course = 0; course < numCourses; course++) {
			if (!courseCanBeCompleted(course, uniderectionalGraph, completeList, new HashSet<Integer>())) {
				return false;
			}
			completeList.set(course, true);
		}
		return true;
	}

	boolean courseCanBeCompleted(int course, List<List<Integer>> uniderectionalGraph, List<Boolean> completeList,
			HashSet<Integer> visited) {
		if (visited.contains(course)) {
			return false;
		}
		visited.add(course);
		if (completeList.get(course)) {
			return true;
		}
		if (uniderectionalGraph.get(course).size() == 0) {
			return true;
		}
		List<Integer> childrens = uniderectionalGraph.get(course);

		for (Integer eachChildren : childrens) {
			if (!courseCanBeCompleted(eachChildren, uniderectionalGraph, completeList, new HashSet(visited))) {
				return false;
			}
		}
		return true;

	}

	public List<Boolean> getCompleteList(int numCourses) {
		List<Boolean> completeList = new ArrayList<Boolean>();
		for (int i = 0; i < numCourses; i++) {
			completeList.add(i, false);
		}
		return completeList;
	}

	public List<List<Integer>> createUniDirectionalGraph(int numCourses, int[][] prerequisites) {
		List<List<Integer>> list = createEmptyList(numCourses);
		for (int[] each : prerequisites) {
			list.get(each[0]).add(each[1]);
		}
		return list;
	}

	public List<List<Integer>> createEmptyList(int numCourses) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			result.add(i, new ArrayList<Integer>());
		}
		return result;
	}
	
	public static void main(String[] args) {
		CourseSchedule207 solution = new CourseSchedule207();
		System.out.println(solution.canFinish(3, new int[][] {{0,1},{0,2},{1,2}}));
	}

}
