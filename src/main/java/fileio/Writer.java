package main.java.fileio;

import main.java.domain.Edge;
import main.java.domain.Graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Writer {
    private final String filePath;
    private final DecimalFormat df;

    public Writer(String filePath) {
        this.filePath = filePath;
        this.df = new DecimalFormat("0.0");
    }

    public void saveGraph(Graph graph) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(graph.getOrder() + "\n");

            double totalWeight = 0.0;
            Edge[][] matrix = graph.getAdjacencyMatrix();
            int edgeCount = 0;

            for (int i = 0; i < graph.getOrder(); i++) {
                for (int j = i + 1; j < graph.getOrder(); j++) {
                    if (matrix[i][j].isEdge()) {
                        edgeCount++;
                        double weight = matrix[i][j].getWeight();
                        totalWeight += weight;
                        writer.write(String.format("%d %d %s%n",
                                i + 1,
                                j + 1,
                                df.format(weight)));
                    }
                }
            }

            if (edgeCount != graph.getOrder() - 1) {
                System.out.println("Warning: Graph does not have the correct number of edges for an MST");
                System.out.println("Expected: " + (graph.getOrder() - 1) + " edges, Found: " + edgeCount);
            }

            writer.write("Total Weight: " + df.format(totalWeight) + "\n");

            System.out.println("Graph successfully saved to file: " + filePath);
            System.out.println("Total edges: " + edgeCount);
            System.out.println("Total weight: " + df.format(totalWeight));

        } catch (IOException e) {
            System.out.println("Error saving the graph to file: " + filePath);
        }
    }
}