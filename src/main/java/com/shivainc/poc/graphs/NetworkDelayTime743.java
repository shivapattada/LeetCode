/**
 * 
 */
package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * <pre>
 * You are given a network of n Node1s, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source Node1, vi is the target Node1, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given Node1 k. Return the minimum time it takes for all the n Node1s to receive the signal. If it is impossible for all the n Node1s to receive the signal, return -1.

 

Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 * 
 * </pre>
 */
public class NetworkDelayTime743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        Reference reference = new Reference();
        reference.sum = 0 ;
        HashSet<Integer> visitedNodes = new HashSet<Integer>();
        List<List<Node>> graph = createGraph(times,n);
        findMaxPath(graph,k,reference,0,visitedNodes);
        return visitedNodes.size() == n ?reference.sum:-1; 
    }

    public void findMaxPath(List<List<Node>> graph , int k,Reference reference,int sum ,HashSet<Integer> visitedNodes){
        visitedNodes.add(k);
        if(graph.get(k).size() == 0){
            if(sum > reference.sum){
                reference.sum = sum;
            }
            return;
        }
        List<Node> childNodes = graph.get(k);
        for(Node eachNode : childNodes) {
            findMaxPath(graph,eachNode.next,reference,sum+eachNode.distance,visitedNodes);
        }
    }

    public List<List<Node>> createGraph(int[][] times, int n){
        List<List<Node>> list = createEmptyList(n);
        for(int[] each : times){
            list.get(each[0]).add(new Node(each[1],each[2]));
        }
        return list;
    }

    public List<List<Node>> createEmptyList(int n){
        List<List<Node>> list = new ArrayList<List<Node>>();
        for(int i = 0 ; i <= n ; i ++){
            list.add(i,new ArrayList<Node>());
        }
        return list;
    }
    
}

 class Reference {
    int sum;
}

 class Node {
   public  int next;
   public int distance;

   Node(int next,int distance){
       this.next = next ;
       this.distance = distance;
   }
}