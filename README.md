# 📦 Java CLI Order Management System (CRUD)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)

A robust, menu-driven Command-Line Interface (CLI) application built in Java that performs core CRUD (Create, Read, Update, Delete) operations on a relational MySQL database. 

This project demonstrates core backend engineering principles, including **Object-Oriented Programming (OOP)**, the **DAO (Data Access Object) Design Pattern**, secure credential management, and complex SQL relationships.

---

## ✨ Key Features

- **Full CRUD Functionality:** Seamlessly create, view, update, and delete records across multiple database tables.
- **Relational Data Mapping:** Handles complex database relationships, including a many-to-many bridge table (`order_items`) linking orders to multiple inventory items.
- **Automated Database Cleanup:** Utilizes `ON DELETE CASCADE` in MySQL to prevent orphaned data when deleting customers or orders.
- **Separation of Concerns:** Code is strictly organized into `models`, `dao`, and `utils` packages for high maintainability and scalability.
- **Security Best Practices:** - Utilizes Java `PreparedStatement` to securely query the database and prevent SQL Injection attacks.
  - Database credentials are externalized into a `.properties` file and hidden via `.gitignore` to prevent credential leaks.

---

## 🏗️ Project Architecture

This application follows the industry-standard DAO pattern to separate database operations from the main application logic:

* **`models/`**: Contains Plain Old Java Objects (POJOs) representing database entities (`Customer`, `Item`, `Order`).
* **`dao/`**: Contains the Data Access Objects that handle all raw SQL queries (`INSERT`, `SELECT`, `UPDATE`, `DELETE`).
* **`utils/`**: Contains the `DatabaseConnection` class, establishing a single secure tunnel to the MySQL server using JDBC.
* **`Main.java`**: The front-facing interactive menu loop handling user inputs via `Scanner`.

---

## 🗄️ Database Schema

The backend is powered by a locally hosted MySQL database consisting of four interconnected tables:

1. **`customers`**: Stores `id` and `name`.
2. **`items`**: Stores inventory `id`, `name`, and `value`.
3. **`orders`**: Stores an `id` and a `customer_id` (Foreign Key).
4. **`order_items`**: A bridge table storing `order_id` and `item_id` to allow multiple items per order.

---

## 🚀 Getting Started

### Prerequisites
To run this project locally, you will need:
* Java Development Kit (JDK) 11 or higher
* MySQL Server installed and running locally

### Installation & Setup

**1. Clone the repository**
```bash
git clone {link}
cd CRUD-in-SQL-using-JavaCLI-app