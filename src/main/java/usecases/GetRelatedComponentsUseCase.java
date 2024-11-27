package main.java.usecases;

import main.java.domain.Graph;
import main.java.domain.Edge;

import java.util.ArrayList;
import java.util.List;

public class GetRelatedComponentsUseCase {
    private final Graph graph;
    private final List<List<Integer>> connectedComponents;

    public GetRelatedComponentsUseCase(Graph graph) {
        this.graph = graph;
        this.connectedComponents = new ArrayList<>();
    }

    public void execute() {
        int n = graph.getOrder();
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                auxDepthFirstSearch(i, adjacencyMatrix, visited, component);
                connectedComponents.add(component);
            }
        }

        printConnnectedComponents();
    }

    private void auxDepthFirstSearch(int vertex, Edge[][] adjacencyMatrix, boolean[] visited, List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex + 1);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i] && adjacencyMatrix[vertex][i].isEdge()) {
                auxDepthFirstSearch(i, adjacencyMatrix, visited, component);
            }
        }
    }

    private void printConnnectedComponents() {
        if(connectedComponents.isEmpty()) {
            System.out.println("This graph has no connected components!");
            return;
        }
        System.out.println("Below are all the connected components of the graph:");
        for(int i = 0; i < connectedComponents.size(); i++) {
            System.out.println( (i+ 1) + " : " + connectedComponents.get(i));
        }
    }
}
