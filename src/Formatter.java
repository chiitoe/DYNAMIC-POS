public class Formatter {

    public static String formatMenuItem(MenuItem item) {
        return item.getId() + ". " + item.getName() + " - PHP " + String.format("%.2f", item.getPrice());
    }

    public static String formatTransaction(Transaction t) {
        return t.getItem().getName() + " x" + t.getQuantity()
                + " = PHP " + String.format("%.2f", t.getSubtotal());
    }

    public static String formatOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(order.getId())
                .append(" - ").append(order.getName()).append("\n");

        for (Transaction t : order.getTransactions()) {
            sb.append(formatTransaction(t)).append("\n");
        }

        sb.append("Total: PHP ").append(String.format("%.2f", order.getTotal()));
        return sb.toString();
    }

    public static String formatMenu(Menu menu) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== MENU ===\n\n");
        for (MenuItem item : menu.getMenuItems()) {
            sb.append(formatMenuItem(item)).append("\n");
        }
        return sb.toString();
    }

    public static String formatMenuOptions(Menu menu) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SELECT AN ITEM ===\n\n");

        for (MenuItem item : menu.getMenuItems()) {
            sb.append("[").append(item.getId()).append("] ")
                    .append(item.getName())
                    .append(" - PHP ").append(String.format("%.2f", item.getPrice()))
                    .append("\n");
        }

        sb.append("Enter item number: ");
        return sb.toString();
    }
}