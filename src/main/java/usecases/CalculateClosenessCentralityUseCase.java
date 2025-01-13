package main.java.usecases;

import main.java.domain.Graph;
import main.java.domain.Edge;
import main.java.repositories.IGraphRepository;

import java.util.LinkedList;
import java.util.Queue;

public class CalculateClosenessCentralityUseCase {
    private final IGraphRepository repository;

    public CalculateClosenessCentralityUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public void execute(int vertex) {
        vertex -= 1;
        Graph graph = repository.getGraph();
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null");
        }

        int n = graph.getOrder();
        if (vertex < 0 || vertex >= n) {
            throw new IllegalArgumentException("Invalid vertex index.");
        }

        double totalDistance = calculateTotalDistance(graph, vertex);

        if (totalDistance == 0) {
            printClosenessCentrality(0, vertex + 1);
        }else {
            // Fórmula: C(x) = (N - 1) / totalDistance
            printClosenessCentrality(n - 1 / totalDistance, vertex + 1);
        }
    }

    private double calculateTotalDistance(Graph graph, int source) {
        int n = graph.getOrder();
        boolean[] visited = new boolean[n];
        int[] distances = new int[n];

        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        distances[source] = 0;
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            Edge[] neighbors = graph.getAdjacencyMatrix()[u];
            for (int v = 0; v < n; v++) {
                if (neighbors[v].isEdge() && !visited[v]) {
                    visited[v] = true;
                    distances[v] = distances[u] + 1;
                    queue.add(v);
                }
            }
        }


        double totalDistance = 0;
        for (int distance : distances) {
            if (distance != Integer.MAX_VALUE) {
                totalDistance += distance;
            }
        }

        return totalDistance;
    }

    private void printClosenessCentrality(double closenessCentrality, int vertex) {
        System.out.printf("The proximity centrality of vertex %d: %.2f\n", vertex, closenessCentrality);
    }
}
