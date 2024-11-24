package main.java.usecases;

import main.java.domain.Graph;
import java.util.ArrayList;
import java.util.List;

public class GetVertexNeighbors {
    private final Graph graph;

    public GetVertexNeighbors(Graph graph) {
        this.graph = graph;
    }

    public List<Integer> execute(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        int size = graph.getOrder();
        for (int i = 0; i < size; i++) {
            if (graph.getAdjacencyMatrix()[vertex][i].isEdge()) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
}
