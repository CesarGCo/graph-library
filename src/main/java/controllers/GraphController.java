package main.java.controllers;
import main.java.repositories.GraphRepository;
import main.java.usecases.*;

import java.util.List;

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

    public Integer getSize() {
        GetSizeUseCase useCase = new GetSizeUseCase(repository);
        return useCase.execute();
    }

    public Integer getOrder() {
        GetOrderUseCase useCase = new GetOrderUseCase(repository);
        return useCase.execute();
    }

    public Double getDensity() {
        GetGraphDensityUseCase useCase = new GetGraphDensityUseCase(repository);
        return useCase.execute();
    }

    public void BreadthFirstSearch(int startVertex) {
        BreadthFirstSearchUseCase useCase = new BreadthFirstSearchUseCase(repository);
        useCase.execute(startVertex);
    }

    public int getVertexDegree(int vertex) {
        GetVertexDegreeUseCase useCase = new GetVertexDegreeUseCase(repository);
        return useCase.execute(vertex);
    }

    public List<Integer> getVertexNeighbors(int vertex) {
        GetVertexNeighborsUseCase useCase = new GetVertexNeighborsUseCase(repository);
        return useCase.execute(vertex);
    }

    public boolean IsArticulationVertex(int vertex) {
        IsArticulationVertexUseCase useCase = new IsArticulationVertexUseCase(repository);
        return useCase.execute(vertex);
    }

    public void getAllMinPath(int vertex) {
        GetAllMinPathUseCase useCase = new GetAllMinPathUseCase(repository);
        useCase.execute(vertex);
    }

    public void getRelatedComponents() {
        GetRelatedComponentsUseCase useCase = new GetRelatedComponentsUseCase(repository);
        useCase.execute();
    }

    public void hasCycles() {
        HasCycleUseCase useCase = new HasCycleUseCase(repository);
        useCase.execute();
    }

    public void minVertexCover() {
        GetMinVertexCoverUseCase useCase = new GetMinVertexCoverUseCase(repository);
        useCase.execute();
    }
}
