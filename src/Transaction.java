public class Transaction {
    final private MenuItem item;
    final private int quantity;

    public Transaction(MenuItem item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    // getters
    public MenuItem getItem(){
        return this.item;
    }

    public int getQuantity(){
        return this.quantity;
    }

    // methods
    public double getSubtotal(){
        return (this.item.getPrice() * this.quantity);
    }

    @Override
    public String toString() {
        return item.getId() + "," + item.getName() + "," + item.getPrice() + "," + quantity;
    }
}