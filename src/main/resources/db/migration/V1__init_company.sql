-- V1__init_company.sql
-- Create product table
CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    sku VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- Insert dummy products
INSERT INTO product (sku, name, price, stock) VALUES
    ('SKU001', 'Laptop', 1200.00, 10),
    ('SKU002', 'Mouse', 25.50, 100),
    ('SKU003', 'Keyboard', 45.00, 50),
    ('SKU004', 'Monitor', 300.00, 20),
    ('SKU005', 'USB Hub', 15.99, 75);

-- Create order table
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES product(id),
    created_at TIMESTAMP DEFAULT NULL,
    qty INT NOT NULL,
    discount_code VARCHAR(50) DEFAULT NULL
);
