package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

public class GetSizeUseCase {
    private final IGraphRepository repository;

    public GetSizeUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public Integer execute() {
        Graph graph = this.repository.getGraph();

        if (graph == null) {
            System.out.println("Graph is null");
            return null;
        }

        return graph.getSize();
    }
}
