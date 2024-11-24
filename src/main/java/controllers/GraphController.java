package main.java.controllers;
import main.java.repositories.GraphRepository;
import main.java.usecases.CreateGraphUSeCase;
import main.java.usecases.GetGraphDensityUseCase;
import main.java.usecases.addEdgeUseCase;

public class GraphController {
    public GraphRepository repository;

    public GraphController() {
        this.repository = new GraphRepository();
    }

    public void createGraph(int size) {
        CreateGraphUSeCase uSeCase = new CreateGraphUSeCase(repository);
        uSeCase.execute(size);
    }

    public void addEdge(int from, int to, double weight) {
        addEdgeUseCase useCase = new addEdgeUseCase(repository);
        useCase.execute(from, to, weight);
    }

    public Double getDensity() {
        GetGraphDensityUseCase useCase = new GetGraphDensityUseCase(repository);
        return useCase.execute();
    }
}
