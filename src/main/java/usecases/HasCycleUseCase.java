package main.java.usecases;

import main.java.domain.Edge;
import main.java.domain.Graph;

import java.util.ArrayList;
import java.util.List;

public class HasCycleUseCase {
    private final Graph graph;
    private final List<List<Integer>> cycles;

    public HasCycleUseCase(Graph graph) {
        this.graph = graph;
        this.cycles = new ArrayList<>();
    }

    public void execute() {
        int n = graph.getOrder();
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                auxDepthFirstSearch(v, -1, visited, path, adjacencyMatrix);
            }
        }

        printCycles();
    }

    private void auxDepthFirstSearch(int current, int parent, boolean[] visited, List<Integer> path, Edge[][] adjacencyMatrix) {
        visited[current] = true;
        path.add(current);

        for (int neighbor = 0; neighbor < adjacencyMatrix.length; neighbor++) {
            if (adjacencyMatrix[current][neighbor].isEdge()) {
                if (!visited[neighbor]) {
                    auxDepthFirstSearch(neighbor, current, visited, path, adjacencyMatrix);
                } else if (neighbor != parent) {
                    List<Integer> cycle = new ArrayList<>();
                    int startIndex = path.indexOf(neighbor);
                    if (startIndex != -1) {
                        for (int i = startIndex; i < path.size(); i++) {
                            cycle.add(path.get(i) + 1);
                        }
                        cycle.add(neighbor + 1);
                        cycles.add(cycle);
                    }
                }
            }
        }
        path.removeLast();
    }

    private void printCycles() {
        if(cycles.isEmpty()) {
            System.out.println("This graph has no cycles!");
            return;
        }
        System.out.println("Below are all the cycles of the graph:");
        for(int i = 0; i < cycles.size(); i++) {
            System.out.println( (i+ 1) + " : " + cycles.get(i));
        }
    }
}
