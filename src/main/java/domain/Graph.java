package main.java.domain;

public class Graph {
    private Edge[][] adjacencyMatrix;

    public Graph(int order) {
        this.adjacencyMatrix = new Edge[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                this.adjacencyMatrix[i][j] = new Edge();
            }
        }
    }

    public void createEdge(int from, int to, double weight) {
        this.adjacencyMatrix[from][to].setWeight(weight);
        this.adjacencyMatrix[from][to].setEdge(true);
        this.adjacencyMatrix[to][from].setWeight(weight);
        this.adjacencyMatrix[to][from].setEdge(true);
    }

    public void removeEdge(int from, int to) {
        this.adjacencyMatrix[from][to].setEdge(false);
        this.adjacencyMatrix[to][from].setEdge(false);
    }

    public Edge[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    public int getOrder() {
        return adjacencyMatrix.length;
    }

    public int getSize() {
        int count = 0;
        int n = adjacencyMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (adjacencyMatrix[i][j].isEdge()) {
                    count++;
                }
            }
        }
        return count;
    }
}
