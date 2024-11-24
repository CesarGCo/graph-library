package main.java.usecases;

import main.java.domain.Edge;
import main.java.domain.Graph;
import java.util.Arrays;

public class GetAllMinPathUseCase {
    private final Graph graph;
    public final double[] dt;
    public final int[] rot;

    public GetAllMinPathUseCase(Graph graph) {
        int n = graph.getOrder();
        this.graph = graph;
        this.dt = new double[n];
        this.rot = new int[n];
    }

    public void execute(int vertex) {
        int n = graph.getOrder();
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        boolean[] visited = new boolean[n];

        Arrays.fill(dt, Double.MAX_VALUE);
        dt[vertex] = 0.0;
        Arrays.fill(rot, -1);

        for (int i = 0; i < n; i++) {
            int u = minDistance(visited);
            if(u == -1) {
                break;
            }
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && adjacencyMatrix[u][v].isEdge()) {
                    double newDist = dt[u] + adjacencyMatrix[u][v].getWeight();
                    if (newDist < dt[v]) {
                        dt[v] = newDist;
                        rot[v] = u;
                    }
                }
            }
        }
    }

    private int minDistance(boolean[] visited) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < dt.length; i++) {
            if (!visited[i] && dt[i] < min) {
                min = dt[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
