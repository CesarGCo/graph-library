package main.java.domain;

public class Graph {
    private Edge[][] adjacencyMatrix;

    public Graph(int size) {
        this.adjacencyMatrix = new Edge[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.adjacencyMatrix[i][j] = new Edge();
            }
        }
    }

    public void createEdge(int from, int to, int weight) {
        this.adjacencyMatrix[from][to].setWeight(weight);
        this.adjacencyMatrix[to][from].setEdge(true);
    }

    public void removeEdge(int from, int to) {
        this.adjacencyMatrix[from][to].setEdge(false);
    }

    public Edge[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
