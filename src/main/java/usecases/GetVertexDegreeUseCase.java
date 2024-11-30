package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

public class GetVertexDegreeUseCase {
    private final IGraphRepository repository;

    public GetVertexDegreeUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public Integer execute(int vertex) {
        Graph graph = repository.getGraph();
        if (graph == null) {
            System.out.println("Graph is null");
            return null;
        }

        if (vertex < 1 || vertex > graph.getSize()) {
            System.out.println("Vertex does not exist");
            return null;
        }

        int adjustedVertex = vertex + 1;

        int degree = 0;
        int size = graph.getOrder();
        for (int i = 0; i < size; i++) {
            if (graph.getAdjacencyMatrix()[adjustedVertex][i].isEdge()) {
                degree++;
            }
        }
        return degree;
    }
}
