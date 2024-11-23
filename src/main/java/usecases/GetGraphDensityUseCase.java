package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.GraphRepository;

public class GetGraphDensityUseCase {
    private final GraphRepository graphRepository;

    public GetGraphDensityUseCase() {
        this.graphRepository = new GraphRepository();
    }

    public double execute() {
        Graph graph = this.graphRepository.getGraph();
        int order = graph.getOrder();
        int size = graph.getSize();

        if(order < 2) return 0.0;
        return (2.0 * size) / (order * (order - 1));
    }
}
