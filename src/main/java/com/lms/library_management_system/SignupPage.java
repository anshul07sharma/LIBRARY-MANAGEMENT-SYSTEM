package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class SignupPage extends JFrame 
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton signupButton;
    private JButton backButton;

    public SignupPage() {
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 30);
        add(emailField);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(150, 200, 100, 30);
        add(signupButton);

        backButton = new JButton("Back");
        backButton.setBounds(250, 200, 100, 30);
        add(backButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the signup page
            }
        });
    }

    private void signUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String checkUserQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkUserPst = connection.prepareStatement(checkUserQuery);
            checkUserPst.setString(1, username);
            ResultSet rs = checkUserPst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String insertUserQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                PreparedStatement insertUserPst = connection.prepareStatement(insertUserQuery);
                insertUserPst.setString(1, username);
                insertUserPst.setString(2, password); // You should hash the password before storing it
                insertUserPst.setString(3, email);

                insertUserPst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Sign Up successful!");
                dispose(); // Close the signup page
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error signing up!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

