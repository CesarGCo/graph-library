package main.java.usecases;

import main.java.domain.Graph;

import java.util.ArrayList;
import java.util.List;

public class IsArticulationVertex {
    private final Graph graph;

    public IsArticulationVertex(Graph graph) {
        this.graph = graph;
    }

    public boolean execute(int vertex) {
        int size = graph.getOrder();
        boolean[] visited = new boolean[size];
        List<Integer> initialComponent = dfs(0, vertex, visited);

        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }

        // Recalculando componente conexa sem o vértice dado
        List<Integer> newComponent = dfs(vertex == 0 ? 1 : 0, vertex, visited);

        return initialComponent.size() > newComponent.size();
    }

    // Utilizando a Busca em Profundidade para ajudar a determinar a conectividade de um grafo
    private List<Integer> dfs(int start, int excludedVertex, boolean[] visited) {
        List<Integer> component = new ArrayList<>();
        dfsHelper(start, excludedVertex, visited, component);
        return component;
    }

    private void dfsHelper(int current, int excludedVertex, boolean[] visited, List<Integer> component) {
        if (current == excludedVertex || visited[current]) return;

        visited[current] = true;
        component.add(current);

        for (int i = 0; i < graph.getOrder(); i++) {
            if (graph.getAdjacencyMatrix()[current][i].isEdge() && !visited[i]) {
                dfsHelper(i, excludedVertex, visited, component);
            }
        }
    }
}
