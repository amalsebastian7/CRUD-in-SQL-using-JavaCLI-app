package crudapp.models;

public class Item {

    // 1. The Variable
    private int id;
    private String name;
    private double value;

    // 2. Constructor for NEW Items (No ID yet)
    public Item(String name, double value) {
        this.name = name;
        this.value = value;
    }

    // 3. Constructor for EXISTING Items
    public Item(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // 5. The toString Method
    @Override
    public String toString() {
        return "Item ID: " + id + " | Name: " + name + " | Value: $" + value;
    }
}