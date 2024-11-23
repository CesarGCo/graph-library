package main.java.fileio;
import main.java.domain.Graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String filePath;

    public Reader (String filePath) {
        this.filePath = filePath;
    }

    public Graph createGraph() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int size = (Integer.parseInt(line));
            Graph graph = new Graph(size);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                graph.createEdge(Integer.parseInt(values[0])-1, Integer.parseInt(values[1])-1, Double.parseDouble(values[2]));
            }
            return graph;

        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + filePath);
            return null;
        }

    }
}
