package com.shivainc.poc.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <pre>
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 

Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
 * 
 * </pre>
 */

public class ChepeastFlightWithKStops787 {

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		List<List<Flight>> graph = getGraph(flights, n);
		Reference reference = new Reference();
		findNumberOfStops(graph, k, src, dst, -1, 0, reference);
		return reference.min == null ? -1 : reference.min;
	}

	public void findNumberOfStops(List<List<Flight>> graph, int k, int src, int dst, int numOfStops, int flightCost,
			Reference reference) {
		if (numOfStops > k) {
			return;
		}
		if (src == dst) {
			if (reference.min == null) {
				reference.min = flightCost;
			} else {
				if (reference.min > flightCost) {
					reference.min = flightCost;
				}
			}
			return;
		}
		if (graph.get(src).size() == 0) {
			return;
		}
		List<Flight> childFlights = graph.get(src);
		for (Flight eachChildFlight : childFlights) {
			findNumberOfStops(graph, k, eachChildFlight.destination, dst, numOfStops + 1,
					flightCost + eachChildFlight.price, reference);
		}

	}

	public List<List<Flight>> getGraph(int[][] flights, int n) {
		List<List<Flight>> graph = getEmptyGraph(n);
		for (int[] eachFlights : flights) {
			graph.get(eachFlights[0]).add(new Flight(eachFlights[1], eachFlights[2]));
		}
		return graph;
	}

	public List<List<Flight>> getEmptyGraph(int n) {
		List<List<Flight>> graph = new ArrayList<List<Flight>>();
		for (int i = 0; i < n; i++) {
			graph.add(i, new ArrayList<Flight>());
		}
		return graph;
	}
}

class Reference {
	Integer min;
}

class Flight {
	int destination;
	int price;

	public Flight(int destination, int price) {
		this.destination = destination;
		this.price = price;
	}
}
