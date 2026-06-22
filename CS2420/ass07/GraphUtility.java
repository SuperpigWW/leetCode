package assign07;

import java.util.List;

/**
 * Contains several static methods for solving problems on graphs.
 * Each method builds a graph from the given sources and destinations lists,
 * then delegates to the Graph class methods.
 *
 * @author Haoquan Wang
 * @version 2026-3-4
 */
public class GraphUtility {

    /**
     * Builds a directed graph from the sources and destinations lists,
     * then uses depth-first search to determine whether there is a path
     * from the vertex with srcData to the vertex with dstData.
     *
     * @param <Type>       - type of data stored at each vertex
     * @param sources      - list of source vertex data for each edge
     * @param destinations - list of destination vertex data for each edge
     * @param srcData      - data of the starting vertex
     * @param dstData      - data of the destination vertex
     * @return true if a path exists from srcData to dstData
     * @throws IllegalArgumentException if sources and destinations sizes differ
     * @throws IllegalArgumentException if srcData or dstData not in graph
     */
    public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations,
                                               Type srcData, Type dstData) {
        if (sources.size() != destinations.size())
            throw new IllegalArgumentException("Sources and destinations lists must have the same size.");

        Graph<Type> graph = buildGraph(sources, destinations);

        return graph.depthFirstSearch(srcData, dstData);
    }

    /**
     * Builds a directed graph from the sources and destinations lists,
     * then uses breadth-first search to find the shortest path from
     * the vertex with srcData to the vertex with dstData.
     *
     * @param <Type>       - type of data stored at each vertex
     * @param sources      - list of source vertex data for each edge
     * @param destinations - list of destination vertex data for each edge
     * @param srcData      - data of the starting vertex
     * @param dstData      - data of the destination vertex
     * @return list of vertex data along the shortest path, from srcData to dstData
     * @throws IllegalArgumentException if sources and destinations sizes differ
     * @throws IllegalArgumentException if srcData or dstData not in graph
     * @throws IllegalArgumentException if no path exists between the two vertices
     */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations,
                                                  Type srcData, Type dstData) {
        if (sources.size() != destinations.size())
            throw new IllegalArgumentException("Sources and destinations lists must have the same size.");

        Graph<Type> graph = buildGraph(sources, destinations);

        return graph.breadthFirstSearch(srcData, dstData);
    }

    /**
     * Builds a directed graph from the sources and destinations lists,
     * then uses topological sort to generate a valid ordering of all vertices.
     *
     * @param <Type>       - type of data stored at each vertex
     * @param sources      - list of source vertex data for each edge
     * @param destinations - list of destination vertex data for each edge
     * @return list of vertex data in topological order
     * @throws IllegalArgumentException if sources and destinations sizes differ
     * @throws IllegalArgumentException if the graph contains a cycle
     */
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        if (sources.size() != destinations.size())
            throw new IllegalArgumentException("Sources and destinations lists must have the same size.");

        Graph<Type> graph = buildGraph(sources, destinations);

        return graph.topoSort();
    }

    /**
     * Helper method: builds a Graph from parallel sources and destinations lists.
     * Each pair (sources.get(i), destinations.get(i)) becomes a directed edge.
     *
     * @param <Type>       - type of data stored at each vertex
     * @param sources      - list of source vertex data for each edge
     * @param destinations - list of destination vertex data for each edge
     * @return constructed Graph
     */
    private static <Type> Graph<Type> buildGraph(List<Type> sources, List<Type> destinations) {
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++)
            graph.addEdge(sources.get(i), destinations.get(i));
        return graph;
    }
}