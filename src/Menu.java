import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int autoId = 1;
    private List<MenuItem> menuItems = new ArrayList<>();

    public void loadFromFile(String filePath) {
        try {
            List<String> allLines = Files.readAllLines(Path.of(filePath));

            for (String line : allLines) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // skip blank lines and comments
                }

                String[] parts = line.split(",");
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());

                menuItems.add(new MenuItem(name, price, autoId));
                autoId++;
            }

        } catch (IOException e) {
            System.out.println("Could not read menu file: " + e.getMessage());
        }
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public MenuItem getItemByName(String name){
        for(MenuItem menuItem : menuItems){
            if (menuItem.getName().equalsIgnoreCase(name)) {
                return menuItem;
            }
        }
        return null;
    }
}