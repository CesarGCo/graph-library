package main.java.usecases;
import main.java.domain.Graph;
import main.java.domain.Edge;
import java.util.LinkedList;

public class BreadthFirstSearch {
    private Graph graph;

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void createTree(int startVertex) {
        int n = graph.getOrder();
        LinkedList<Boolean> visited = new LinkedList<>();
        LinkedList<Integer> visitOrder = new LinkedList<>();
        LinkedList<String> nonTreeEdges = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            visited.add(false);
        }

        queue.add(startVertex);
        visited.set(startVertex, true);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            visitOrder.add(currentVertex);

            for (int i = 0; i < n; i++) {
                Edge edge = graph.getAdjacencyMatrix()[currentVertex][i];

                if (edge.isEdge()) {
                    if (!visited.get(i)) {
                        visited.set(i, true);
                        queue.add(i);
                    } else {
                        String edgeRepresentation = "(" + currentVertex + ", " + i + ")";
                        if (!nonTreeEdges.contains(edgeRepresentation)) {
                            nonTreeEdges.add(edgeRepresentation);
                        }
                    }
                }
            }
        }

        System.out.println("Sequência de vértices visitados: " + visitOrder);
        System.out.println("Arestas não pertencentes à árvore de busca em largura:");
        for (String edge : nonTreeEdges) {
            System.out.println(edge);
        }
    }
}
