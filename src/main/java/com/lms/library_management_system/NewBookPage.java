package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class NewBookPage extends JFrame 
{
    private JTextField titleField, authorField, publisherField, yearField;

    public NewBookPage() 
    {
        setTitle("New Book");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 50, 100, 30);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(150, 50, 200, 30);
        add(titleField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(50, 100, 100, 30);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(150, 100, 200, 30);
        add(authorField);

        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(50, 150, 100, 30);
        add(publisherLabel);

        publisherField = new JTextField();
        publisherField.setBounds(150, 150, 200, 30);
        add(publisherField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(50, 200, 100, 30);
        add(yearLabel);

        yearField = new JTextField();
        yearField.setBounds(150, 200, 200, 30);
        add(yearField);

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
                saveBook();
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

    private void saveBook() 
    {
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        int year = Integer.parseInt(yearField.getText());

        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String query = "INSERT INTO books (title, author, publisher, year, available) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, publisher);
            pst.setInt(4, year);
            pst.setBoolean(5, true);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            clearFields();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding book!");
        }
    }

    private void clearFields() 
    {
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        yearField.setText("");
    }
}
