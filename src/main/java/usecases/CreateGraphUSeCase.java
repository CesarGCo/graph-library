package main.java.usecases;

import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

public class CreateGraphUSeCase {
    private final IGraphRepository repository;

    public CreateGraphUSeCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public void execute(int size) {
        this.repository.saveGraph(new Graph(size));
    }
}
