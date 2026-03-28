package crudapp.dao;

import crudapp.models.Item;
import crudapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // Adding a new item to my inventory
    public void addItem(Item item) {
        // I need two '?' placeholders this time: one for the name, one for the value
        String sql = "INSERT INTO items (name, value) VALUES (?, ?)"; 
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Swapping out the placeholders in the exact order they appear in the SQL string
            pstmt.setString(1, item.getName()); 
            pstmt.setDouble(2, item.getValue()); 
            
            pstmt.executeUpdate(); // Running the query
            System.out.println("✅ Item added successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error adding item.");
            e.printStackTrace();
        }
    }

    // Grabbing all items so I can show them in my main menu
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            // Looping through every single row MySQL hands back
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double value = rs.getDouble("value"); // Grabbing the decimal price
                
                // Building a new Java Item object and adding it to my list
                items.add(new Item(id, name, value)); 
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching items.");
            e.printStackTrace();
        }
        return items; 
    }

    // Updating a specific item's name and price based on its ID
    public void updateItem(Item item) {
        String sql = "UPDATE items SET name = ?, value = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getValue());
            pstmt.setInt(3, item.getId()); // The ID is the 3rd question mark in my SQL string
            
            pstmt.executeUpdate();
            System.out.println("✅ Item updated successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error updating item.");
            e.printStackTrace();
        }
    }

    // Deleting an item from the database
    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Item deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error deleting item.");
            e.printStackTrace();
        }
    }
}