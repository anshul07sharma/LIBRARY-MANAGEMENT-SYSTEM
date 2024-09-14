# Library Management System

A comprehensive Library Management System built with Java Swing (JFrame) and MySQL database. This application enables efficient management of library operations, including adding new students and books, issuing and returning books, and viewing issued and returned statistics. The project is designed to be user-friendly with a graphical user interface built in NetBeans.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Login System**: Secure login with validation.
- **Add New Student**: Add student details to the database.
- **Add New Book**: Add book details to the library database.
- **Issue Book**: Issue books to students with validation checks.
- **Return Book**: Return issued books with proper validation.
- **Statistics**: View statistics of issued and returned books.
- **User-friendly UI**: Built using Java Swing for easy navigation and interaction.

## Technologies Used
- **Java**: Core programming language.
- **JFrame (Swing)**: GUI components for building the user interface.
- **MySQL**: Database for storing and managing library data.
- **NetBeans**: Integrated Development Environment (IDE) used for development.
- **JDBC**: Java Database Connectivity for database interaction.

## Project Structure
The project follows a modular structure with separate classes for different functionalities:
```
LibraryManagementSystem/
│
├── src/
│   ├── librarymanagementsystem/
│   │   ├── DatabaseConnection.java
│   │   ├── LoginPage.java
│   │   ├── HomePage.java
│   │   ├── NewStudentPage.java
│   │   ├── NewBookPage.java
│   │   ├── IssueBookPage.java
│   │   ├── ReturnBookPage.java
│   │   ├── StatisticsPage.java
│
├── resources/
│   ├── database-schema.sql
│   ├── images/            # Folder for storing UI images/icons
│
└── README.md
```

## Setup and Installation

### Prerequisites
- **Java Development Kit (JDK)** installed on your system.
- **NetBeans IDE** for Java development.
- **MySQL** server installed and running.
  
### Database Setup
1. **Create the Database**:
   - Open MySQL Workbench or the MySQL command-line tool.
   - Run the following SQL commands to create the database and required tables:

```sql
CREATE DATABASE librarydb;

USE librarydb;

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    course VARCHAR(100),
    year INT
);

CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    year INT,
    available BOOLEAN DEFAULT true
);

CREATE TABLE issued_books (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    book_id INT,
    issue_date DATE,
    return_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);
```

2. **Update Database Connection**:
   - Modify `DatabaseConnection.java` to include your MySQL username and password:

```java
public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String user = "root"; // Replace with your MySQL username
        String password = "password"; // Replace with your MySQL password
        return DriverManager.getConnection(url, user, password);
    }
}
```

### Project Setup
1. **Clone the Repository**:
   - Clone the project repository or download the source code.

2. **Open in NetBeans**:
   - Open NetBeans and load the project by navigating to `File > Open Project`.

3. **Build and Run**:
   - Clean and build the project in NetBeans.
   - Run `LoginPage.java` to start the application.

## Usage
1. **Login**:
   - Use the login page to access the application.
2. **Add New Student**:
   - Navigate to "New Student" to add student details.
3. **Add New Book**:
   - Navigate to "New Book" to add book details.
4. **Issue Book**:
   - Issue books to students using their IDs.
5. **Return Book**:
   - Return books using student and book IDs.
6. **View Statistics**:
   - View the number of books issued and returned.

## Screenshots
### Login Page
![Login Page](resources/images/login-page.png)

### Home Page
![Home Page](resources/images/home-page.png)

### New Student Page
![New Student Page](resources/images/new-student-page.png)

*Add screenshots for other pages similarly*

## Contributing
Contributions are welcome! Please fork this repository and submit a pull request for any enhancements, bug fixes, or new features.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

---

### Additional Notes:
- Customize the paths in the `Screenshots` section to point to actual images/screenshots.
- Make sure to include a `LICENSE` file in your project if you mention it in the README.
- Modify the instructions under "Setup and Installation" if there are other dependencies or specific configurations needed for the project.
