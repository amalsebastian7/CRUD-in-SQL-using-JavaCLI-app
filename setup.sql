DROP DATABASE IF EXISTS ordersystem;
CREATE DATABASE ordersystem;
USE ordersystem;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value DECIMAL(10, 2) NOT NULL
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE order_items (
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);

INSERT INTO customers (name) VALUES ('Alice Smith'), ('Bob Jones'), ('Charlie Brown'), ('Diana Prince'), ('Ethan Hunt'), ('Fiona Gallagher'), ('George Costanza'), ('Hannah Abbott'), ('Ian Malcolm'), ('Julia Child'), ('Kevin Scott'), ('Laura Croft'), ('Michael Scott'), ('Nina Simone'), ('Oscar Martinez'), ('Pam Beesly'), ('Quincy Adams'), ('Rachel Green'), ('Steve Rogers'), ('Tony Stark');

INSERT INTO items (name, value) VALUES ('Laptop', 999.99), ('Wireless Mouse', 25.50), ('Mechanical Keyboard', 85.00), ('Monitor 24in', 150.00), ('HDMI Cable', 15.00), ('USB-C Hub', 45.00), ('Webcam 1080p', 60.00), ('Studio Microphone', 120.00), ('Noise Cancelling Headphones', 250.00), ('Mousepad', 10.00), ('LED Desk Lamp', 35.00), ('Ergonomic Chair', 200.00), ('Standing Desk', 350.00), ('External Hard Drive 2TB', 80.00), ('WiFi Router', 110.00), ('Ethernet Cable 10ft', 8.00), ('Laser Printer', 130.00), ('Black Ink Cartridge', 40.00), ('Printer Paper Ream', 5.00), ('Laptop Stand', 25.00);

INSERT INTO orders (customer_id) VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12), (13), (14), (15), (16), (17), (18), (19), (20);

INSERT INTO order_items (order_id, item_id) VALUES (1, 1), (1, 2), (2, 5), (3, 10), (4, 15), (5, 20), (6, 3), (7, 8), (8, 12), (9, 11), (10, 6), (11, 9), (12, 13), (13, 14), (14, 19), (15, 4), (16, 7), (17, 18), (18, 17), (19, 16);