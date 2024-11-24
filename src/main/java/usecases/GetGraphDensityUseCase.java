package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.GraphRepository;
import main.java.repositories.IGraphRepository;

public class GetGraphDensityUseCase {
    private final IGraphRepository graphRepository;

    public GetGraphDensityUseCase(IGraphRepository repository) {
        this.graphRepository = repository;
    }

    public Double execute() {
        Graph graph = this.graphRepository.getGraph();
        if (graph == null) {
            System.out.println("Graph is null");
            return null;
        }

        int order = graph.getOrder();
        int size = graph.getSize();

        if(order < 2) return 0.0;
        return (2.0 * size) / (order * (order - 1));
    }
}
