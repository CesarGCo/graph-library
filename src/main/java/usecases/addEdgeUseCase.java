package main.java.usecases;
import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

public class addEdgeUseCase {
    private final IGraphRepository repository;

    public addEdgeUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public void execute(int from, int to, double weight) {
        Graph graph = repository.getGraph();
        if (graph == null) {
            System.out.println("Graph is null");
            return;
        }
        this.repository.getGraph().createEdge(from, to, weight);
    }
}
