package main.java.views;

import main.java.controllers.GraphController;
import main.java.fileio.Reader;

import java.util.Scanner;

public class GraphCLI {
    private final GraphController controller;
    private final Scanner scanner;

    public GraphCLI() {
        this.controller = new GraphController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = getChoice();
            if (choice == 0) {
                System.out.println("Exiting... Goodbye!");
                break;
            }
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("\n====== Graph Library Menu ======");
        System.out.println("1. Create Graph (from file)");
        System.out.println("2. Add Edge");
        System.out.println("3. Get Graph Size");
        System.out.println("4. Get Graph Order");
        System.out.println("5. Get Graph Density");
        System.out.println("6. Perform Breadth-First Search (BFS)");
        System.out.println("7. Get Vertex Degree");
        System.out.println("8. Get Vertex Neighbors");
        System.out.println("9. Check Articulation Vertex");
        System.out.println("10. Get All Minimum Paths from a Vertex");
        System.out.println("11. Get Connected Components");
        System.out.println("12. Check if Graph Has Cycles");
        System.out.println("0. Exit");
        System.out.println("================================");
    }

    private int getChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
        try {
            switch (choice) {
                case 1 -> createGraphFromFile();
                case 2 -> addEdge();
                case 3 -> System.out.println("Graph Size: " + controller.getSize());
                case 4 -> System.out.println("Graph Order: " + controller.getOrder());
                case 5 -> System.out.println("Graph Density: " + controller.getDensity());
                case 6 -> performBreadthFirstSearch();
                case 7 -> getVertexDegree();
                case 8 -> getVertexNeighbors();
                case 9 -> checkArticulationVertex();
                case 10 -> getAllMinPaths();
                case 11 -> getRelatedComponents();
                case 12 -> checkHasCycles();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void createGraphFromFile() {
        System.out.print("Enter the file path: ");
        scanner.nextLine(); // Consume the newline
        String filePath = scanner.nextLine();

        Reader reader = new Reader(filePath, controller);
        reader.createGraph();
        System.out.println("Graph created from file: " + filePath);
    }

    private void addEdge() {
        System.out.print("Enter the 'from' vertex: ");
        int from = scanner.nextInt();
        System.out.print("Enter the 'to' vertex: ");
        int to = scanner.nextInt();
        System.out.print("Enter the weight of the edge: ");
        double weight = scanner.nextDouble();
        controller.addEdge(from, to, weight);
        System.out.println("Edge added between " + from + " and " + to + " with weight " + weight);
    }

    private void performBreadthFirstSearch() {
        System.out.print("Enter the starting vertex: ");
        int startVertex = scanner.nextInt();
        controller.BreadthFirstSearch(startVertex);
        System.out.println("Breadth-First Search completed from vertex " + startVertex);
    }

    private void getVertexDegree() {
        System.out.print("Enter the vertex: ");
        int vertex = scanner.nextInt();
        int degree = controller.getVertexDegree(vertex);
        System.out.println("Degree of vertex " + vertex + ": " + degree);
    }

    private void getVertexNeighbors() {
        System.out.print("Enter the vertex: ");
        int vertex = scanner.nextInt();
        var neighbors = controller.getVertexNeighbors(vertex);
        System.out.println("Neighbors of vertex " + vertex + ": " + neighbors);
    }

    private void checkArticulationVertex() {
        System.out.print("Enter the vertex: ");
        int vertex = scanner.nextInt();
        boolean isArticulation = controller.IsArticulationVertex(vertex);
        System.out.println("Vertex " + vertex + " is " + (isArticulation ? "" : "not ") + "an articulation vertex.");
    }

    private void getAllMinPaths() {
        System.out.print("Enter the starting vertex: ");
        int vertex = scanner.nextInt();
        controller.getAllMinPath(vertex);
        System.out.println("All minimum paths calculated from vertex " + vertex);
    }

    private void getRelatedComponents() {
        controller.getRelatedComponents();
        System.out.println("Connected components calculated.");
    }

    private void checkHasCycles() {
        controller.hasCycles();
        System.out.println("Cycle check completed.");
    }
}
