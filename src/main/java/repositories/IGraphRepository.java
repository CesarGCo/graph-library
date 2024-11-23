package main.java.repositories;

import main.java.domain.Graph;

public interface IGraphRepository {
    Graph getGraph();
    void saveGraph(Graph graph);
}
