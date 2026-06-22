package assign07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Represents a sparse, directed, generic graph.
 * Each vertex stores one instance of Type.
 * 
 * @author Haoquan Wang
 * @version 2026-3-2
 * 
 * @param <Type> the type of data stored at each vertex
 */
public class Graph<Type> {

    // Maps each data value to its corresponding Vertex object
    private Map<Type, Vertex<Type>> vertices;

    /**
     * Creates an empty Graph.
     */
    public Graph() {
        vertices = new HashMap<>();
    }

    /**
     * Adds a directed edge from the vertex containing srcData
     * to the vertex containing dstData.
     * If either vertex does not exist yet, it is created automatically.
     *
     * @param srcData - data of the source vertex
     * @param dstData - data of the destination vertex
     */
    public void addEdge(Type srcData, Type dstData) {
        
    	// Get or create source vertex
        Vertex<Type> srcVertex;
        if (vertices.containsKey(srcData)) {
            srcVertex = vertices.get(srcData);
        } else {
            srcVertex = new Vertex<>(srcData);
            vertices.put(srcData, srcVertex);
        }

        // Get or create destination vertex
        Vertex<Type> dstVertex;
        if (vertices.containsKey(dstData)) {
            dstVertex = vertices.get(dstData);
        } else {
            dstVertex = new Vertex<>(dstData);
            vertices.put(dstData, dstVertex);
        }

        // Connect source → destination
        srcVertex.addEdge(dstVertex);
    }

    /**
     * Uses recursive depth-first search to determine whether
     * there is a path from the vertex with source data to
     * the vertex with destination data.
     *
     * @param source      - data value of the starting vertex
     * @param destination - data value of the target vertex
     * @return true if a path exists, false otherwise
     * @throws IllegalArgumentException if source or destination not in graph
     */
    public boolean depthFirstSearch(Type source, Type destination) {
        // Validate that both vertices exist in the graph
        if (!vertices.containsKey(source))
            throw new IllegalArgumentException("Source not in graph: " + source);
        if (!vertices.containsKey(destination))
            throw new IllegalArgumentException("Destination not in graph: " + destination);

        // Reset all visited flags before starting DFS
        for (Vertex<Type> v : vertices.values())
            v.setVisited(false);

        // Start recursive DFS from source vertex
        Vertex<Type> startVertex = vertices.get(source);
        Vertex<Type> goalVertex  = vertices.get(destination);

        return dfsRecursive(startVertex, goalVertex);
    }

    /**
     * Recursive DFS helper — mirrors the pseudocode from lecture:
     *
     * @param current - the vertex currently being explored
     * @param goal    - the vertex we are searching for
     * @return true if goal is reachable from current
     */
    private boolean dfsRecursive(Vertex<Type> current, Vertex<Type> goal) {
        if (current == goal)
            return true;

        current.setVisited(true);

        // Explore each neighbor
        Iterator<Edge<Type>> edges = current.edges();
        while (edges.hasNext()) {
            Vertex<Type> neighbor = edges.next().getOtherVertex();

            // Only visit unvisited neighbors (∞ distance equivalent)
            if (!neighbor.isVisited()) {
                if (dfsRecursive(neighbor, goal))
                    return true;  
            }
        }

        return false;
    }

    /**
     * Uses BFS to find the shortest path from source to destination.
     * Returns a list of vertex data in order from source to destination.
     *
     * @param source      - data of the starting vertex
     * @param destination - data of the target vertex
     * @return list of Type values along the shortest path
     * @throws IllegalArgumentException if either vertex not in graph
     * @throws IllegalArgumentException if no path exists
     */
    public List<Type> breadthFirstSearch(Type source, Type destination) {
        if (!vertices.containsKey(source))
            throw new IllegalArgumentException("Source not in graph: " + source);
        if (!vertices.containsKey(destination))
            throw new IllegalArgumentException("Destination not in graph: " + destination);

        // foreach vertex v do
        for (Vertex<Type> v : vertices.values()) {
            v.setDistanceFromStart(Double.POSITIVE_INFINITY);
            v.setPrevious(null);
        }

        Vertex<Type> start = vertices.get(source);
        Vertex<Type> goal  = vertices.get(destination);
        Queue<Vertex<Type>> queue = new LinkedList<>();
        queue.offer(start);
        start.setDistanceFromStart(0);

        // while Q is not empty do
        while (!queue.isEmpty()) {
            Vertex<Type> x = queue.poll();

            // foreach vertex w in x's adjacency list
            Iterator<Edge<Type>> edges = x.edges();
            while (edges.hasNext()) {
                Vertex<Type> w = edges.next().getOtherVertex();

                // if w.distanceFromStart is ∞ do
                if (w.getDistanceFromStart() == Double.POSITIVE_INFINITY) {
                    w.setDistanceFromStart(x.getDistanceFromStart() + 1);
                    w.setPrevious(x);
                    queue.offer(w);

                    if (w == goal)
                        return reconstructPath(goal);
                }
            }
        }

        throw new IllegalArgumentException("No path from " + source + " to " + destination);
    }

    /**
     * Reconstructs the path from start to goal by following previous pointers.
     * Used internally by breadthFirstSearch.
     */
    private List<Type> reconstructPath(Vertex<Type> goal) {
        LinkedList<Type> path = new LinkedList<>();
        Vertex<Type> current = goal;

        // Walk backwards from goal to start using previous pointers
        while (current != null) {
            path.addFirst(current.getData());  
            current = current.getPrevious();
        }

        return path;
    }

    /**
     * Uses topological sort to return a valid ordering of all vertices.
     * The graph must be a DAG (no cycles); throws exception otherwise.
     *
     * @return list of Type values in topological order
     * @throws IllegalArgumentException if graph has a cycle
     */
    public List<Type> topoSort() {
        // Count indegrees freshly 
        Map<Vertex<Type>, Integer> indegreeMap = new HashMap<>();
        for (Vertex<Type> v : vertices.values())
            indegreeMap.put(v, v.getIndegree());

        // Enqueue all vertices with indegree 0
        Queue<Vertex<Type>> queue = new LinkedList<>();
        for (Vertex<Type> v : vertices.values())
            if (indegreeMap.get(v) == 0)
                queue.offer(v);

        List<Type> result = new ArrayList<>();

        // Process queue
        while (!queue.isEmpty()) {
            Vertex<Type> x = queue.poll();
            result.add(x.getData());   

            // For each neighbor w of x:
            Iterator<Edge<Type>> edges = x.edges();
            while (edges.hasNext()) {
                Vertex<Type> w = edges.next().getOtherVertex();
                int newIndegree = indegreeMap.get(w) - 1;
                indegreeMap.put(w, newIndegree);

                // If w now has no more dependencies, it's ready
                if (newIndegree == 0)
                    queue.offer(w);
            }
        }

        // If not all vertices were processed, there's a cycle
        if (result.size() != vertices.size())
            throw new IllegalArgumentException("Graph has a cycle, cannot use topological sort.");
        return result;
    }

    /**
     * Returns a simple string representation of the graph.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<Type> v : vertices.values())
            sb.append(v).append("\n");
        return sb.toString();
    }
}