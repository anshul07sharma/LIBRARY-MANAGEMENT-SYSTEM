package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginPage() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        add(loginButton);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(260, 150, 100, 30);
        add(signupButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Action listener for signup button
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignupPage().setVisible(true);
            }
        });
    }

    private void login() {
    String username = usernameField.getText();
    String password = new String(passwordField.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection connection = DatabaseConnection.getConnection()) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new HomePage(username).setVisible(true); // Pass the username to HomePage
            dispose(); // Close the login page
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
