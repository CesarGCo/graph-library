package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

public class GetOrderUseCase {
    private final IGraphRepository repository;

    public GetOrderUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public Integer execute() {
        Graph graph = this.repository.getGraph();

        if (graph == null) {
            System.out.println("Graph is null");
            return null;
        }

        return graph.getOrder();
    }
}
