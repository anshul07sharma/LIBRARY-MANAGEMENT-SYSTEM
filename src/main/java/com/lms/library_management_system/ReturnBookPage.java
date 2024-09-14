package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ReturnBookPage extends JFrame 
{
    private JTextField studentIdField, bookIdField;

    public ReturnBookPage() 
    {
        setTitle("Return Book");
        setSize(400, 300);
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

        JButton saveButton = new JButton("Return");
        saveButton.setBounds(150, 150, 100, 30);
        add(saveButton);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(250, 150, 100, 30);
        add(closeButton);

        saveButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                returnBook();
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

    private void returnBook() 
    {
        int studentId = Integer.parseInt(studentIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());

        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String validateIssueQuery = "SELECT * FROM issued_books WHERE student_id = ? AND book_id = ?";
            PreparedStatement validateIssuePst = connection.prepareStatement(validateIssueQuery);

            validateIssuePst.setInt(1, studentId);
            validateIssuePst.setInt(2, bookId);

            ResultSet issueRs = validateIssuePst.executeQuery();

            if (issueRs.next()) 
            {
                String returnBookQuery = "DELETE FROM issued_books WHERE student_id = ? AND book_id = ?";
                String updateBookAvailability = "UPDATE books SET available = true WHERE book_id = ?";

                PreparedStatement returnBookPst = connection.prepareStatement(returnBookQuery);
                PreparedStatement updateBookPst = connection.prepareStatement(updateBookAvailability);

                returnBookPst.setInt(1, studentId);
                returnBookPst.setInt(2, bookId);
                updateBookPst.setInt(1, bookId);

                returnBookPst.executeUpdate();
                updateBookPst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Book returned successfully!");
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
            JOptionPane.showMessageDialog(this, "Error returning book!");
        }
    }

    private void clearFields()
    {
        studentIdField.setText("");
        bookIdField.setText("");
    }
}
