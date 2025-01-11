package main.java.usecases;

import main.java.domain.Graph;
import main.java.domain.Edge;
import main.java.repositories.IGraphRepository;

public class MaximumMatchingUseCase {
    private final IGraphRepository repository;

    public MaximumMatchingUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public Integer execute() {
        Graph graph = repository.getGraph();
        if (graph == null) {
            System.out.println("Graph is null");
            return null;
        }

        int order = graph.getOrder();
        boolean[] visited = new boolean[order];
        int[] match = new int[order];

        // Inicializa as correspondências como -1 (nenhuma correspondência)
        for (int i = 0; i < order; i++) {
            match[i] = -1;
        }

        int matchingCount = 0;

        // Tenta encontrar um caminho de aumento para cada vértice
        for (int vertex = 0; vertex < order; vertex++) {
            if (match[vertex] == -1) {
                // Redefine o array de visitados para cada DFS
                for (int i = 0; i < order; i++) {
                    visited[i] = false;
                }
                if (dfs(vertex, graph, visited, match)) {
                    matchingCount++;
                }
            }
        }

        return matchingCount;
    }

    private boolean dfs(int vertex, Graph graph, boolean[] visited, int[] match) {
        visited[vertex] = true;
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();

        for (int neighbor = 0; neighbor < graph.getOrder(); neighbor++) {
            if (adjacencyMatrix[vertex][neighbor].isEdge() && !visited[neighbor]) {
                visited[neighbor] = true;

                // Se o vizinho não está emparelhado ou podemos encontrar um caminho de aumento
                if (match[neighbor] == -1 || dfs(match[neighbor], graph, visited, match)) {
                    match[vertex] = neighbor;
                    match[neighbor] = vertex;
                    return true;
                }
            }
        }

        return false;
    }
}
