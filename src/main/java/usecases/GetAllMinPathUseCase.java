package main.java.usecases;

import main.java.domain.Edge;
import main.java.domain.Graph;
import main.java.repositories.IGraphRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAllMinPathUseCase {
    private final IGraphRepository repository;
    public final double[] dt;
    public final int[] rot;

    public GetAllMinPathUseCase(IGraphRepository repository) {
        this.repository = repository;
        Graph graph = repository.getGraph();
        int n = graph.getOrder();
        this.dt = new double[n];
        this.rot = new int[n];
    }

    public void execute(int vertex) {
        Graph graph = repository.getGraph();
        int n = graph.getOrder();
        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        boolean[] visited = new boolean[n];

        Arrays.fill(dt, Double.MAX_VALUE);
        dt[vertex] = 0.0;
        Arrays.fill(rot, -1);

        for (int i = 0; i < n; i++) {
            int u = minDistance(visited);
            if(u == -1) {
                break;
            }
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && adjacencyMatrix[u][v].isEdge()) {
                    double newDist = dt[u] + adjacencyMatrix[u][v].getWeight();
                    if (newDist < dt[v]) {
                        dt[v] = newDist;
                        rot[v] = u;
                    }
                }
            }
        }
        printAllMinPath(vertex);
    }

    private int minDistance(boolean[] visited) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < dt.length; i++) {
            if (!visited[i] && dt[i] < min) {
                min = dt[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printAllMinPath(int vertex) {
        int aux;
        List<Integer> path = new ArrayList<>();
        System.out.printf("Segue abaixo as distâncias e o menor caminho do vértice %d para os demais:\n", vertex + 1);
        for (int i = 0; i < dt.length; i++){
            System.out.printf("%d à %d - Distância total: ", vertex+1, i+1);

            if(dt[i] == Double.MAX_VALUE) System.out.println("infinito");
            else  System.out.println(dt[i]);

            System.out.print("Caminho mínimo: ");
            aux = i;
            while (aux != -1) {
                path.addFirst(aux);
                aux = rot[aux];
            }
            for (int j = 0; j < path.size(); j++) {
                if(path.getFirst() != vertex) {
                    System.out.println("Não existe esse caminho");
                    break;
                }
                if (j > 0) System.out.print(" -> ");
                System.out.print(path.get(j) + 1);
            }
            System.out.print("\n\n");
            path.clear();
        }
    }
}
