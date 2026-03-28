package crudapp.dao;

import crudapp.models.Customer;
import crudapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Adding a new customer to my database
    public void addCustomer(Customer customer) {
        // I'm using a '?' placeholder here to protect my database from SQL injection attacks
        String sql = "INSERT INTO customers (name) VALUES (?)"; 
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Swapping out my '?' placeholder with the actual name the user typed
            pstmt.setString(1, customer.getName()); 
            pstmt.executeUpdate(); // Running the query
            System.out.println("✅ Customer added successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error adding customer.");
            e.printStackTrace();
        }
    }

    // Grabbing all customers so I can show them in my main menu
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // rs temporarily holds the table data I get back
             
            // Looping through every single row MySQL handed back to me
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                // Building a new Java object using the row data, then adding it to my list
                customers.add(new Customer(id, name)); 
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching customers.");
            e.printStackTrace();
        }
        return customers; // Handing the finished list back to my Main class
    }

    // Updating a specific customer's name based on their ID
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Setting the two '?' placeholders in order
            pstmt.setString(1, customer.getName());
            pstmt.setInt(2, customer.getId());
            
            pstmt.executeUpdate();
            System.out.println("✅ Customer updated successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error updating customer.");
            e.printStackTrace();
        }
    }

    // Deleting a customer (Because of my ON DELETE CASCADE rule in MySQL, their orders will vanish too!)
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Customer deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error deleting customer.");
            e.printStackTrace();
        }
    }
}