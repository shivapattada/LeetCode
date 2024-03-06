/**
 * 
 */
package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> uniderectionalGraph = createUniDirectionalGraph(numCourses,prerequisites);
        List<Integer> completeList = new ArrayList<Integer>();
        for(int course =0 ;course < numCourses ; course ++ ){
            if(!courseCanBeCompleted(course,uniderectionalGraph,completeList,new HashSet<Integer>())){
                return new int[]{};
            }
            if(!completeList.contains(course)) {
                completeList.add(course);
            }
        }
        return convertToIntArray(completeList);
    }

    int[] convertToIntArray(List<Integer> completeList){
        int[] result = new int[completeList.size()];
        for(int i =0;i<completeList.size();i++){
            result[i]=completeList.get(i);
        }
        return result;
    }

     boolean courseCanBeCompleted(int course,List<List<Integer>> uniderectionalGraph,List<Integer> completeList,HashSet<Integer> visited){
        if(visited.contains(course)){
            return false;
        }
        visited.add(course);
        if(completeList.contains(course)){
            return true;
        }
        if(uniderectionalGraph.get(course).size() == 0){
            return true;
        }
        List<Integer> childrens = uniderectionalGraph.get(course);
       
        for(Integer eachChildren : childrens){
            if(!courseCanBeCompleted(eachChildren,uniderectionalGraph,completeList,new HashSet<Integer>(visited))){
                return false;
            }
            if(!completeList.contains(eachChildren)){
            completeList.add(eachChildren);
            }
        }
        return true;

    }


    public List<List<Integer>> createUniDirectionalGraph(int numCourses,int[][] prerequisites){
            List<List<Integer>> list = createEmptyList(numCourses);
            for(int[] each : prerequisites){
                list.get(each[0]).add(each[1]);
            }
            return list;
    }

    public List<List<Integer>> createEmptyList(int numCourses) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i =0;i<numCourses;i++){
            result.add(i,new ArrayList<Integer>());
        }
        return result;
    }


}
