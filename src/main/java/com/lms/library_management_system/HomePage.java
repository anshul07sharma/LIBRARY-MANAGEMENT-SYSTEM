package com.lms.library_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {
    private JButton newStudentButton;
    private JButton newBookButton;
    private JButton issueBookButton;
    private JButton returnBookButton;
    private JButton statisticsButton;
    private JButton logoutButton;
    private JLabel usernameLabel;

    public HomePage(String username) {
        setTitle("Library Management System - Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create and configure the username label
        usernameLabel = new JLabel("Logged in as: " + username);
        usernameLabel.setBounds(400, 10, 180, 30);
        add(usernameLabel);

        // Create and configure buttons
        newStudentButton = new JButton("New Student");
        newStudentButton.setBounds(50, 50, 150, 30);
        add(newStudentButton);

        newBookButton = new JButton("New Book");
        newBookButton.setBounds(250, 50, 150, 30);
        add(newBookButton);

        issueBookButton = new JButton("Issue Book");
        issueBookButton.setBounds(50, 100, 150, 30);
        add(issueBookButton);

        returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(250, 100, 150, 30);
        add(returnBookButton);

        statisticsButton = new JButton("Statistics");
        statisticsButton.setBounds(50, 150, 150, 30);
        add(statisticsButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(250, 150, 150, 30);
        add(logoutButton);

        // Add action listeners for buttons
        newStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NewStudentPage().setVisible(true);
            }
        });

        newBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NewBookPage().setVisible(true);
            }
        });

        issueBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IssueBookPage().setVisible(true);
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReturnBookPage().setVisible(true);
            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatisticsPage().setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the home page
                new LoginPage().setVisible(true); // Return to the login page
            }
        });
    }
}
