public class MenuItem {
    final private String name;
    final private double price;
    final private int id;

    public MenuItem(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price;
    }
}