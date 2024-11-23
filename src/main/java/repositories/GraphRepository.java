package main.java.repositories;

import main.java.domain.Graph;

public class GraphRepository implements IGraphRepository {
    private Graph graph = null;

    @Override
    public Graph getGraph() {
        return this.graph;
    }

    @Override
    public void saveGraph(Graph graph) {
        this.graph = graph;
    }
}
