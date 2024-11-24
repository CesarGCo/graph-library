package main.java.usecases;

import main.java.domain.Graph;

public class GetVertexDegree{
    private final Graph graph;

    public GetVertexDegree(Graph graph) {
        this.graph = graph;
    }

    public int execute(int vertex) {
        int degree = 0;
        int size = graph.getOrder();
        for (int i = 0; i < size; i++) {
            if (graph.getAdjacencyMatrix()[vertex][i].isEdge()) {
                degree++;
            }
        }
        return degree;
    }
}
