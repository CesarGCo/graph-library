package main.java.domain;

public class Graph {
    private Edge[][] adjacencyMatrix;

    public Graph(int size) {
        this.adjacencyMatrix = new Edge[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
