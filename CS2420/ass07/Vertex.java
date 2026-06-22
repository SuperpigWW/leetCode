package assign07;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents a vertex in a generic directed graph.
 * Each vertex holds one instance of Type and maintains an adjacency list.
 * 
 * @author Haoquan Wang
 * @version 2026-3-2
 * @param <Type> the type of data stored at this vertex
 */
public class Vertex<Type> {

    private Type data;
    private LinkedList<Edge<Type>> adjacencyList;

    // whether this vertex has been visited
    private boolean visited;

    // distance from start
    private double distanceFromStart;

    // the previous vertex 
    private Vertex<Type> previous;

    // the number of edges
    private int indegree;

    /**
     * Creates a Vertex storing the given data.
     *
     * @param data - the data to store at this vertex
     */
    public Vertex(Type data) {
        this.data = data;
        this.adjacencyList = new LinkedList<>();
        this.visited = false;
        this.distanceFromStart = Double.POSITIVE_INFINITY;
        this.previous = null;
        this.indegree = 0;
    }

    /** Returns the data stored at this vertex. */
    public Type getData() {
        return data;
    }

    /**
     * Adds a directed edge from this vertex to the given other vertex.
     * Also increments the indegree of the destination vertex.
     *
     * @param other - the destination vertex
     */
    public void addEdge(Vertex<Type> other) {
        adjacencyList.add(new Edge<>(other));
        other.indegree++;  // destination gains one more incoming edge
    }

    /** Returns an iterator over all edges leaving this vertex. */
    public Iterator<Edge<Type>> edges() {
        return adjacencyList.iterator();
    }

    // visited methods
    public void setVisited(boolean visited) { 
    	this.visited = visited; 
    }
    public boolean isVisited() { 
    	return visited; 
    }

    // distanceFromStart methods
    public void setDistanceFromStart(double d) { 
    	this.distanceFromStart = d; 
    }
    public double getDistanceFromStart() { 
    	return distanceFromStart; 
    }

    // previous methods
    public void setPrevious(Vertex<Type> previous) { 
    	this.previous = previous; 
    }
    public Vertex<Type> getPrevious() {
    	return previous; 
    }

    // indegree methods
    public int getIndegree() { 
    	return indegree; 
    }
    public void decrementIndegree() { 
    	indegree--; 
    }

    /** Returns a string representation of this vertex. */
    @Override
    public String toString() {
        String result = "Vertex " + data + " has edge(s) to vertice(s) ";
        Iterator<Edge<Type>> edges = adjacencyList.iterator();
        while (edges.hasNext())
            result += edges.next() + " ";
        return result;
    }
}