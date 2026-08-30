import java.io.FileWriter;
import java.io.IOException;

public class Logs {
    final private String filePath;

    public Logs(String filePath) {
        this.filePath = filePath;
    }

    public void logOrder(Order order) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(order.toString());
            writer.write("\n---\n"); // separator between orders
        } catch (IOException e) {
            System.out.println("Could not write to log file: " + e.getMessage());
        }
    }
}