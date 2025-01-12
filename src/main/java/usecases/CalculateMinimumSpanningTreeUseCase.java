package main.java.usecases;

import main.java.domain.Graph;
import main.java.domain.Edge;
import main.java.repositories.IGraphRepository;

public class CalculateMinimumSpanningTreeUseCase {
    private final IGraphRepository repository;

    public CalculateMinimumSpanningTreeUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public Graph execute() {
        Graph inputGraph = this.repository.getGraph();
        if (inputGraph == null) {
            System.out.println("Graph is null");
            return null;
        }

        int order = inputGraph.getOrder();
        Edge[][] adjacencyMatrix = inputGraph.getAdjacencyMatrix();
        Graph mstGraph = new Graph(order);

        boolean[] visited = new boolean[order];
        double[] key = new double[order];
        int[] parent = new int[order];

        for (int i = 0; i < order; i++) {
            key[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }

        key[0] = 0;
        for (int count = 0; count < order - 1; count++) {
            int u = minKey(key, visited, order);
            visited[u] = true;
            for (int v = 0; v < order; v++) {
                if (adjacencyMatrix[u][v].isEdge() &&
                        !visited[v] &&
                        adjacencyMatrix[u][v].getWeight() < key[v]) {
                    parent[v] = u;
                    key[v] = adjacencyMatrix[u][v].getWeight();
                }
            }
        }
        for (int i = 1; i < order; i++) {
            if (parent[i] != -1) {
                double weight = adjacencyMatrix[i][parent[i]].getWeight();
                mstGraph.createEdge(parent[i], i, weight);
            }
        }

        return mstGraph;
    }

    private int minKey(double[] key, boolean[] visited, int order) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < order; v++) {
            if (!visited[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}