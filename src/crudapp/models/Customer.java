package crudapp.models;

public class Customer {
    
    private int id;
    private String name;
    
    public Customer(String name) {
        this.name = name;
    }

    // 3. Constructor for EXISTING Customers (Used when reading data from MySQL)
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 4. Getters and Setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 5. The toString Method
    @Override
    public String toString() {
        return "Customer ID: " + id + " | Name: " + name;
    }
}