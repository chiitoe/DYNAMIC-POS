import java.util.Scanner;

public class Terminal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu();
        menu.loadFromFile("C:\\Users\\Ysha\\Git-Repos\\PERSONAL-PROJECTS\\DYNAMIC-POS\\src\\Menu.txt");
        System.out.println("Items loaded: " + menu.getMenuItems().size());

        Logs logs = new Logs("Transactions.txt");

        boolean keepRunning = true;

        while (keepRunning) {

            // --- Start a new order ---
            System.out.print("Customer name: ");
            String customerName = scanner.nextLine().trim();
            Order order = new Order(customerName);

            // --- Item selection loop ---
            boolean ordering = true;
            boolean cancelled = false;

            while (ordering) {
                clearScreen();
                System.out.println(Formatter.formatMenu(menu));
                System.out.println("Drinks in running order: " + order.getItemCount());
                System.out.println("\nEnter item id, 0 to finish order, or -1 to exit program.");

                int itemChoice = readValidInt(scanner, "Item id: ", -1, menu.getMenuItems().size());

                if (itemChoice == -1) {
                    ordering = false;
                    cancelled = true;
                    keepRunning = false;
                    continue;
                }

                if (itemChoice == 0) {
                    ordering = false; // done adding items
                    continue;
                }

                MenuItem chosenItem = menu.getMenuItems().get(itemChoice - 1);

                int quantity = readValidInt(scanner, "Quantity: ", 1, Integer.MAX_VALUE);

                Transaction transaction = new Transaction(chosenItem, quantity);
                order.addTransaction(transaction);

                System.out.println(Formatter.formatTransaction(transaction) + " added.");
                System.out.println("Drinks in running order: " + order.getItemCount() + "\n");
            }

            if (cancelled) {
                System.out.println("Exiting program. No order logged.");
                continue; // skips checkout, loop condition (keepRunning) will end it
            }

            // --- Checkout ---
            System.out.println("\n" + Formatter.formatOrder(order));

            double payment = readValidDouble(scanner, "\nEnter payment amount: PHP ");

            double change = payment - order.getTotal();
            if (change < 0) {
                System.out.println("Insufficient payment. Order not completed.\n");
                continue;
            }

            System.out.printf("Change due: PHP %.2f%n", change);

            logs.logOrder(order);
            System.out.println("Order logged.\n");

            System.out.print("Start a new order? (y/n): ");
            String again = scanner.nextLine().trim();
            if (!again.equalsIgnoreCase("y")) {
                keepRunning = false;
            }
        }

        System.out.println("Terminal closed.");
        scanner.close();
    }

    // --- Safe input helpers ---

    public static int readValidInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    public static double readValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }
}