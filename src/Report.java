import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Report {

    public static void generateDailySummary(String logFilePath, String outputPath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(logFilePath));
            StringBuilder report = new StringBuilder();
            double dailyTotal = 0.0;
            int orderCount = 0;

            report.append("=== DAILY TRANSACTION REPORT ===\n\n");

            for (String line : lines) {
                line = line.trim();

                if (line.isEmpty() || line.equals("---")) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length == 2) {
                    orderCount++;
                    report.append("Order #").append(parts[0])
                            .append(" - ").append(parts[1]).append("\n");

                } else if (parts.length == 4) {
                    String itemName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    double subtotal = price * quantity;

                    dailyTotal += subtotal;

                    report.append("   ").append(itemName)
                            .append(" x").append(quantity)
                            .append(" = PHP").append(String.format("%.2f", subtotal))
                            .append("\n");
                }
            }

            report.append("\n=== SUMMARY ===\n");
            report.append("Total orders: ").append(orderCount).append("\n");
            report.append("Total earnings: PHP").append(String.format("%.2f", dailyTotal)).append("\n");

            Files.writeString(Path.of(outputPath), report.toString());
            System.out.println("Report saved to " + outputPath);

        } catch (IOException e) {
            System.out.println("Could not generate report: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Malformed line in log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        generateDailySummary("Transactions.txt", "DailyReport.txt");
    }
}