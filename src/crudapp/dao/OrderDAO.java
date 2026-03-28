package crudapp.dao;

import crudapp.models.Order;
import crudapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Creating a new order. I need this to return the new Order ID (int) so I can attach items to it in the next step!
    public int addOrder(Order order) {
        String sql = "INSERT INTO orders (customer_id) VALUES (?)"; 
        int newOrderId = -1; // Default fallback if it fails
        
        // I am telling Java: "Hey, when you run this, bring back the auto-generated ID that MySQL creates!"
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setInt(1, order.getCustomerId()); 
            pstmt.executeUpdate(); 
            
            // Grabbing the shiny new ID MySQL just made for me
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newOrderId = rs.getInt(1);
                System.out.println("✅ Order created successfully! (Order #" + newOrderId + ")");
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error creating order.");
            e.printStackTrace();
        }
        
        return newOrderId; // Handing the ID back to my Main class
    }

    // Linking an item to an order using my 'order_items' bridge table
    public void addItemToOrder(int orderId, int itemId) {
        String sql = "INSERT INTO order_items (order_id, item_id) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, itemId);
            pstmt.executeUpdate();
            
            System.out.println("✅ Item successfully added to Order #" + orderId);
            
        } catch (SQLException e) {
            System.out.println("❌ Error adding item to order.");
            e.printStackTrace();
        }
    }

    // Grabbing a list of all orders 
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            // Looping through every order in the database
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                
                orders.add(new Order(id, customerId)); 
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching orders.");
            e.printStackTrace();
        }
        return orders; 
    }

    // Deleting an order. Because of my ON DELETE CASCADE rule in MySQL, the links in the bridge table will vanish automatically!
    public void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Order deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("❌ Error deleting order.");
            e.printStackTrace();
        }
    }
}