-- Create Database
CREATE DATABASE IF NOT EXISTS asset_management_app;
USE asset_management_app;


CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE assets (
    asset_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    serial_number VARCHAR(100) UNIQUE,
    purchase_date DATE,
    location VARCHAR(100),
    status VARCHAR(50),
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES employees(employee_id)
);

CREATE TABLE maintenance_records (
    maintenance_id INT PRIMARY KEY AUTO_INCREMENT,
    asset_id INT,
    maintenance_date DATE,
    description TEXT,
    cost DECIMAL(10, 2),
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id)
);

CREATE TABLE asset_allocations (
    allocation_id INT PRIMARY KEY AUTO_INCREMENT,
    asset_id INT,
    employee_id INT,
    allocation_date DATE,
    return_date DATE,
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE reservations (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    asset_id INT,
    employee_id INT,
    reservation_date DATE,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

select * from assets;
select * from employees;
select * from reservations;
select * from asset_allocations;
select * from maintenance_records;

INSERT INTO employees (employee_id, name, department, email, phone)
VALUES (201, 'Ravi Kumar', 'IT', 'ravi.kumar@example.com', '9876543210');


-- Insert dummy employees
INSERT INTO employees (employee_id, name, department, email, password) VALUES
(202, 'Anjali Sharma', 'HR', 'anjali.sharma@example.com', 'pass456');


INSERT INTO employees (employee_id, name, department, email, password)
VALUES (201, 'Ravi Kumar', 'IT', 'ravi.kumar@example.com', 'pass123');

INSERT INTO employees (employee_id, name, department, email, password) VALUES
(203, 'Shraddha Gavkare', 'HR', 'shraddha@example.com', 'pass140');

INSERT INTO employees (employee_id, name, department, email, password) VALUES
(204, 'Shravani Patil', 'IT', 'shrau@example.com', 'pass100');

INSERT INTO employees (employee_id, name, department, email) 
VALUES (1, 'Radha Kumari', 'IT', 'radhakumari@example.com');

INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status, owner_id)
VALUES (1, 'Mouse', 'Peripheral', 'SN1234', '2023-10-01', 'Office', 'Available', 1);

INSERT INTO reservations (reservation_id, asset_id, employee_id, reservation_date, start_date, end_date)
VALUES (1, 1, 1, '2024-04-01', '2024-04-05', '2024-04-10');
 
INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status, owner_id)
VALUES (2, 'Keyboard', 'Peripheral', 'SN5678', '2023-11-15', 'Office', 'Available', 1);


SELECT serial_number FROM assets;

INSERT INTO assets (name, type, serial_number, purchase_date, location, status, owner_id)
VALUES ('Laptop', 'Electronics', 'SN1235', '2024-04-10', 'Office-10', 'Available', 1);

SELECT asset_id, serial_number FROM assets;

DELETE FROM assets WHERE serial_number = 'SN1234';

SELECT asset_id FROM assets WHERE serial_number = 'SN1234';

DELETE FROM maintenance_records WHERE asset_id = 1;
DELETE FROM reservations WHERE asset_id = 1;
DELETE FROM asset_allocations WHERE asset_id = 1;

DELETE FROM assets WHERE asset_id = 1;




