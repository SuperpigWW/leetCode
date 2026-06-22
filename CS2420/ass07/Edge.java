package assign07;

/**
 * Represents a directed edge in a generic graph.
 * The source is implied by the Vertex that owns this edge.
 *
 * @author Haoquan Wang
 * @version 2026-3-2
 * 
 * @param <Type> the type of data stored at each vertex
 */
public class Edge<Type> {

    private Vertex<Type> destination;

    /**
     * Creates a directed Edge pointing to the given destination vertex.
     *
     * @param destination - the vertex this edge points to
     */
    public Edge(Vertex<Type> destination) {
        this.destination = destination;
    }

    /**
     * Returns the destination vertex of this edge.
     *
     * @return destination vertex
     */
    public Vertex<Type> getOtherVertex() {
        return this.destination;
    }

    /**
     * Returns a string representation of this edge (destination's data).
     */
    @Override
    public String toString() {
        return this.destination.getData().toString();
    }
}