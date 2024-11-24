package main.java.fileio;
import main.java.controllers.GraphController;
import main.java.domain.Graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String filePath;
    private GraphController graph;

    public Reader (String filePath, GraphController graph) {
        this.filePath = filePath;
        this.graph = graph;
    }

    public void createGraph() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int size = (Integer.parseInt(line));
            graph.createGraph(size);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                graph.addEdge(Integer.parseInt(values[0])-1, Integer.parseInt(values[1])-1, Double.parseDouble(values[2]));
            }

        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + filePath);
        }

    }
}
