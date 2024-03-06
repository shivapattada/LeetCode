package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Node {
 public int val;
 public List<Node> neighbors;
 public Node() {
     val = 0;
     neighbors = new ArrayList<Node>();
 }
 public Node(int _val) {
     val = _val;
     neighbors = new ArrayList<Node>();
 }
 public Node(int _val, ArrayList<Node> _neighbors) {
     val = _val;
     neighbors = _neighbors;
 }
}

/*
 * 133. Clone Graph 
 * 
 */

class Clonegraph {
	
 public Node cloneGraph(Node node) {
     HashMap<Integer,Node> visitedMap = new HashMap<Integer,Node>();
    return doCloneGraph(node,visitedMap);
 }

 public Node doCloneGraph(Node node,HashMap<Integer,Node> visitedMap){
     if(node == null){
         return node;
     }
     if(visitedMap.get(node.val) != null){
         return visitedMap.get(node.val);
     }
     Node cloneNode = new Node();
     cloneNode.val = node.val;
     visitedMap.put(node.val,cloneNode);
     if(node.neighbors != null){
         cloneNode.neighbors = new ArrayList<Node>();
         for(Node eachNode : node.neighbors){
             cloneNode.neighbors.add(doCloneGraph(eachNode,visitedMap));
         }
     }
     return cloneNode;
     
 }
 
 
}
