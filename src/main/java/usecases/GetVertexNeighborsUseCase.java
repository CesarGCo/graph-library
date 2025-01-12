package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

import java.util.ArrayList;
import java.util.List;

public class GetVertexNeighborsUseCase {
    private final IGraphRepository repository;

    public GetVertexNeighborsUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public List<Integer> execute(int vertex) {
        Graph graph = this.repository.getGraph();

        if (graph == null) {
            return new ArrayList<>();
        }

        if (vertex < 1 || vertex > graph.getSize()) {
            return new ArrayList<>();
        }

        int adjustedVertex = vertex - 1;

        List<Integer> neighbors = new ArrayList<>();
        int size = graph.getOrder();
        for (int i = 0; i < size; i++) {
            if (graph.getAdjacencyMatrix()[adjustedVertex][i].isEdge()) {
                neighbors.add(i + 1);
            }
        }
        return neighbors;
    }
}