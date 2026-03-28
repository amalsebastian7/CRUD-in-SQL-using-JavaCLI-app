package crudapp.models;

public class Order {

    private int id;
    private int customerId;

    public Order(int customerId) {
        this.customerId = customerId;
    }

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + " | Belongs to Customer ID: " + customerId;
    }
}