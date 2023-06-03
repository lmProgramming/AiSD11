import java.util.*;

public class Graph<T> {
    private final int[][] adjacencyMatrix;
    private final List<T> vertices;
    private final int[][] shortestPaths;

    final static int NOT_NEIGHBOURS = Integer.MAX_VALUE;
    final static int NOT_VISITED = Integer.MIN_VALUE;

    public Graph(List<Edge<T>> edges)
    {
        vertices = new ArrayList<>();
        for (Edge<T> edge : edges)
        {
            if (!vertices.contains(edge.getNode1()))
            {
                vertices.add(edge.getNode1());
            }
            if (!vertices.contains(edge.getNode2()))
            {
                vertices.add(edge.getNode2());
            }
        }

        int vertexCount = vertices.size();
        adjacencyMatrix = new int[vertexCount][vertexCount];
        shortestPaths   = new int[vertexCount][vertexCount];

        for (int x = 0; x < vertexCount; x++)
        {
            for (int y = 0; y < vertexCount; y++)
            {
                adjacencyMatrix[x][y] = NOT_NEIGHBOURS;
                shortestPaths[x][y] = NOT_VISITED;
            }
        }

        for (Edge<T> edge : edges)
        {
            int sourceIndex = vertices.indexOf(edge.getNode1());
            int destinationIndex = vertices.indexOf(edge.getNode2());
            adjacencyMatrix[sourceIndex][destinationIndex] = edge.getDistance();
        }
    }

    public boolean adjacent(int vertexIndex1, int vertexIndex2)
    {
        int weight = adjacencyMatrix[vertexIndex1][vertexIndex2];
        return weight != NOT_NEIGHBOURS;
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótsze ścieżki do każdego wierzchołka w grafie (Dijkstra)

        int startNodeIndex = vertices.indexOf(startNode);

        if (startNodeIndex == -1)
        {
            throw new NoSuchElementException();
        }

        Map<T, Integer> shortestPathsMap = new HashMap<>(vertices.size() - 1);
        for (T endNode : vertices)
        {
            int distance = calculateShortestPathTo(startNode, endNode);
            shortestPathsMap.put(endNode, distance);
        }

        return shortestPathsMap;
    }

    public int calculateShortestPathTo(int sourceIndex, int destinationIndex)
    {
        return 0;
    }

    public int calculateShortestPathTo(T startNode, T endNode)
    {
        int sourceIndex = vertices.indexOf(startNode);
        int destinationIndex = vertices.indexOf(endNode);

        return calculateShortestPathTo(sourceIndex, destinationIndex);
    }
}
