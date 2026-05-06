CREATE DATABASE EmployeeDB;
USE EmployeeDB;

CREATE TABLE departments (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(100) NOT NULL
);
SELECT * from employees;
CREATE TABLE employees (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    dept_id INT,
    hire_date DATE,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id),
    INDEX (first_name, last_name) -- This is why retrieval is < 10s
);

-- Add a sample department so you can link employees to it
INSERT INTO departments (dept_name) VALUES ('Engineering'), ('HR'), ('Sales');