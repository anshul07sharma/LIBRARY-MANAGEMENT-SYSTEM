package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class IssueBookPage extends JFrame 
{
    private JTextField studentIdField, bookIdField, issueDateField, returnDateField;

    public IssueBookPage() 
    {
        setTitle("Issue Book");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 50, 100, 30);
        add(studentIdLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(150, 50, 200, 30);
        add(studentIdField);

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(50, 100, 100, 30);
        add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(150, 100, 200, 30);
        add(bookIdField);

        JLabel issueDateLabel = new JLabel("Issue Date (YYYY-MM-DD):");
        issueDateLabel.setBounds(50, 150, 150, 30);
        add(issueDateLabel);

        issueDateField = new JTextField();
        issueDateField.setBounds(200, 150, 150, 30);
        add(issueDateField);

        JLabel returnDateLabel = new JLabel("Return Date (YYYY-MM-DD):");
        returnDateLabel.setBounds(50, 200, 150, 30);
        add(returnDateLabel);

        returnDateField = new JTextField();
        returnDateField.setBounds(200, 200, 150, 30);
        add(returnDateField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 250, 100, 30);
        add(saveButton);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(250, 250, 100, 30);
        add(closeButton);

        saveButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                issueBook();
            }
        });

        closeButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                dispose();
            }
        });
    }

    private void issueBook() 
    {
        int studentId = Integer.parseInt(studentIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());
        String issueDate = issueDateField.getText();
        String returnDate = returnDateField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String validateStudentQuery = "SELECT * FROM students WHERE student_id = ?";
            String validateBookQuery = "SELECT * FROM books WHERE book_id = ? AND available = true";

            PreparedStatement validateStudentPst = connection.prepareStatement(validateStudentQuery);
            PreparedStatement validateBookPst = connection.prepareStatement(validateBookQuery);

            validateStudentPst.setInt(1, studentId);
            validateBookPst.setInt(1, bookId);

            ResultSet studentRs = validateStudentPst.executeQuery();
            ResultSet bookRs = validateBookPst.executeQuery();

            if (studentRs.next() && bookRs.next()) 
            {
                String issueBookQuery = "INSERT INTO issued_books (student_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
                String updateBookAvailability = "UPDATE books SET available = false WHERE book_id = ?";

                PreparedStatement issueBookPst = connection.prepareStatement(issueBookQuery);
                PreparedStatement updateBookPst = connection.prepareStatement(updateBookAvailability);

                issueBookPst.setInt(1, studentId);
                issueBookPst.setInt(2, bookId);
                issueBookPst.setString(3, issueDate);
                issueBookPst.setString(4, returnDate);

                updateBookPst.setInt(1, bookId);

                issueBookPst.executeUpdate();
                updateBookPst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Book issued successfully!");
                clearFields();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid student ID or book ID!");
            }
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error issuing book!");
        }
    }

    private void clearFields() 
    {
        studentIdField.setText("");
        bookIdField.setText("");
        issueDateField.setText("");
        returnDateField.setText("");
    }
}

