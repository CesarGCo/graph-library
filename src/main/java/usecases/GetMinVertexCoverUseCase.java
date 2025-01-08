package main.java.usecases;
import java.util.ArrayList;
import java.util.List;

import main.java.domain.Graph;
import main.java.domain.Edge;
import main.java.repositories.IGraphRepository;

public class GetMinVertexCoverUseCase {
    private final IGraphRepository repository;

    public GetMinVertexCoverUseCase(IGraphRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        Graph graph = this.repository.getGraph();
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        boolean[][] visitedEdges = new boolean[graph.getOrder()][graph.getOrder()];
        List<Integer> vertexCover = new ArrayList<>();
        int coverNumber = 0;

        while (!allEdgesCovered(visitedEdges, graph)) {
            int vertex = findVertexWithMostEdges(visitedEdges, graph);

            vertexCover.add(vertex + 1);

            for (int j = 0; j < graph.getOrder(); j++) {
                if (adjacencyMatrix[vertex ][j].isEdge() && !visitedEdges[vertex][j]) {
                    visitedEdges[vertex][j] = true;
                    visitedEdges[j][vertex] = true;
                }
            }
            coverNumber++;
        }

        printVertexCover(vertexCover, coverNumber);
    }

    private boolean allEdgesCovered(boolean[][] visitedEdges, Graph graph) {
        for (int i = 0; i < visitedEdges.length; i++) {
            for (int j = 0; j < visitedEdges[i].length; j++) {
                if (!visitedEdges[i][j] && graph.getAdjacencyMatrix()[i][j].isEdge()) {
                    return false;
                }
            }
        }
        return true;
    }

    private int findVertexWithMostEdges(boolean[][] visitedEdges, Graph graph) {
        int maxEdges = 0;
        int bestVertex = -1;

        for (int i = 0; i < graph.getOrder(); i++) {
            int uncoveredEdges = 0;
            for (int j = 0; j < graph.getOrder(); j++) {
                if (graph.getAdjacencyMatrix()[i][j].isEdge() && !visitedEdges[i][j]) {
                    uncoveredEdges++;
                }
            }
            if (uncoveredEdges > maxEdges) {
                maxEdges = uncoveredEdges;
                bestVertex = i;
            }
        }
        return bestVertex;
    }

    private void printVertexCover(List<Integer> vertexCover, int coverNumber) {
        System.out.println("Below is the minimum vertex coverage and coverage number:");
        System.out.println("Vertex Coverage: " + vertexCover);
        System.out.println("Number Coverage: " + coverNumber);
    }
}
