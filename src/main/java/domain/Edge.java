package main.java.domain;

public class Edge {
    private double weight;
    private boolean isEdge;

    public Edge() {
        this.weight = 0.0;
        this.isEdge = false;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEdge() {
        return isEdge;
    }

    public void setEdge(boolean edge) {
        isEdge = edge;
    }
}
