import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int autoId = 1;

    private String name;
    private int id;
    private List<Transaction> transactions = new ArrayList<>();
    private double total;

    public Order(String name){
        this.name = name;
        this.id = autoId;
        autoId++;
    }

    // getters
    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public List<Transaction> getTransactions(){
        return this.transactions;
    }

    public double getTotal(){
        return this.total;
    }

    // methods
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        total += transaction.getSubtotal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",").append(name).append("\n");
        for (Transaction t : transactions) {
            sb.append(t.toString()).append("\n");
        }
        sb.append("TOTAL,").append(total);
        return sb.toString();
    }
}
