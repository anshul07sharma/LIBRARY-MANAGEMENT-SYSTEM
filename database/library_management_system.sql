-- Create the database
CREATE DATABASE IF NOT EXISTS library_management_system;
USE library_management_system;

-- Table to store user credentials
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- Table to store student details
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    email VARCHAR(100),
    course VARCHAR(50)
);

-- Table to store book details
CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100),
    year_published INT,
    isbn VARCHAR(20) UNIQUE
);

-- Table to store book issues
CREATE TABLE IF NOT EXISTS issued_books (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    book_id INT NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Table to store book returns (if you need a separate table for returns)
CREATE TABLE IF NOT EXISTS returns (
    return_id INT AUTO_INCREMENT PRIMARY KEY,
    issue_id INT NOT NULL,
    return_date DATE NOT NULL,
    FOREIGN KEY (issue_id) REFERENCES issued_books(issue_id)
);

-- Sample data for users (for testing purposes)
INSERT INTO users (username, password) VALUES ('admin', 'admin'); -- Change the password in real applications

-- Sample data for books (for testing purposes)
INSERT INTO books (title, author, publisher, year_published, isbn) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 1925, '9780743273565'),
('To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', 1960, '9780060935467');

-- Sample data for students (for testing purposes)
INSERT INTO students (name, dob, address, phone_number, email, course) VALUES
('John Doe', '2000-01-15', '123 Elm Street', '555-1234', 'john.doe@example.com', 'Computer Science'),
('Jane Smith', '1999-05-22', '456 Oak Avenue', '555-5678', 'jane.smith@example.com', 'Mathematics');
