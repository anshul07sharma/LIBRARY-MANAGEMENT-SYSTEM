package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class NewStudentPage extends JFrame 
{
    private JTextField nameField, emailField, courseField, yearField;

    public NewStudentPage() 
    {
        setTitle("New Student");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);
        add(emailField);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 150, 100, 30);
        add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(150, 150, 200, 30);
        add(courseField);

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
                saveStudent();
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

    private void saveStudent() 
    {
        String name = nameField.getText();
        String email = emailField.getText();
        String course = courseField.getText();
        int year = Integer.parseInt(yearField.getText());

        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String query = "INSERT INTO students (name, email, course, year) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, course);
            pst.setInt(4, year);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearFields();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student!");
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        courseField.setText("");
        yearField.setText("");
    }
}
