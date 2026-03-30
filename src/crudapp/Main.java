package crudapp;

import crudapp.dao.CustomerDAO;
import crudapp.dao.ItemDAO;
import crudapp.dao.OrderDAO;
import crudapp.models.Customer;
import crudapp.models.Item;
import crudapp.models.Order;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // Setting up my keyboard scanner and my database workers (DAOs)
        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        ItemDAO itemDAO = new ItemDAO();
        OrderDAO orderDAO = new OrderDAO();

        boolean running = true;

        System.out.println("Welcome to the Workplace Order Management System!");

        // My main application loop that keeps the menu on screen until the user types '0'
        while (running) {
        	System.out.println("\n=== MAIN MENU ===");
        	System.out.println("1. View All Customers");
        	System.out.println("2. Add a New Customer");
        	System.out.println("3. View All Items");
        	System.out.println("4. Add a New Item");
        	System.out.println("5. View All Orders");
        	System.out.println("6. Create a New Order");
        	System.out.println("7. Add an Item to an Order");
        	System.out.println("8. Delete a Customer");
        	System.out.println("9. Delete an Item");
        	System.out.println("10. Delete an Order");
        	System.out.println("0. Exit Application");
        	System.out.print("Choose an option: ");

            // Grabbing the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Bug fix: Consuming the leftover "Enter" key press so my next text input doesn't get skipped!

            // Routing the user to the right code based on what number they typed
            switch (choice) {
                case 1:
                    System.out.println("\n--- CUSTOMER LIST ---");
                    List<Customer> customers = customerDAO.getAllCustomers();
                    for (Customer c : customers) {
                        System.out.println(c.toString());
                    }
                    break;

                case 2:
                    System.out.print("Enter the new customer's name: ");
                    String newName = scanner.nextLine();
                    customerDAO.addCustomer(new Customer(newName));
                    break;

                case 3:
                    System.out.println("\n--- INVENTORY LIST ---");
                    List<Item> items = itemDAO.getAllItems();
                    for (Item i : items) {
                        System.out.println(i.toString());
                    }
                    break;

                case 4:
                    System.out.print("Enter the new item's name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter the item's price (e.g., 19.99): ");
                    double itemPrice = scanner.nextDouble();
                    itemDAO.addItem(new Item(itemName, itemPrice));
                    break;

                case 5:
                    System.out.println("\n--- ORDER LIST ---");
                    List<Order> orders = orderDAO.getAllOrders();
                    for (Order o : orders) {
                        System.out.println(o.toString());
                    }
                    break;

                case 6:
                    System.out.print("Enter the Customer ID placing this order: ");
                    int customerId = scanner.nextInt();
                    // I am saving the newly generated Order ID so I can tell the user what it is
                    int newOrderId = orderDAO.addOrder(new Order(customerId));
                    System.out.println("Successfully created Order #" + newOrderId + " for Customer ID " + customerId);
                   break;

                case 7:
                    System.out.print("Enter the Order ID: ");
                    int orderIdToUpdate = scanner.nextInt();
                    System.out.print("Enter the Item ID to add to this order: ");
                    int itemIdToAdd = scanner.nextInt();
                    orderDAO.addItemToOrder(orderIdToUpdate, itemIdToAdd);
                    break;
                    
                case 8:
                    System.out.print("Enter the ID of the Customer to delete: ");
                    int deleteCustomerId = scanner.nextInt();
                    customerDAO.deleteCustomer(deleteCustomerId);
                    System.out.println("✅ Customer #" + deleteCustomerId + " successfully deleted.");
                    break;

                case 9:
                    System.out.print("Enter the ID of the Item to delete: ");
                    int deleteItemId = scanner.nextInt();
                    itemDAO.deleteItem(deleteItemId);
                    System.out.println("✅ Item #" + deleteItemId + " successfully deleted.");
                    break;

                case 10:
                    System.out.print("Enter the ID of the Order to delete: ");
                    int deleteOrderId = scanner.nextInt();
                    orderDAO.deleteOrder(deleteOrderId);
                    System.out.println("✅ Order #" + deleteOrderId + " successfully deleted.");
                    break;
                    
                case 0:
                    System.out.println("Saving data and shutting down. Goodbye!");
                    running = false; // Breaking the while loop
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number between 0 and 7.");
            }
        }
        
         scanner.close(); 
    }
}