package com.lms.library_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StatisticsPage extends JFrame 
{
    private JTextArea statisticsArea;

    public StatisticsPage() 
    {
        setTitle("Statistics");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        statisticsArea = new JTextArea();
        statisticsArea.setBounds(50, 50, 400, 250);
        add(statisticsArea);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(200, 320, 100, 30);
        add(closeButton);

        closeButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        showStatistics();
    }

    private void showStatistics() 
    {
        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String issuedBooksQuery = "SELECT COUNT(*) FROM issued_books";
            String returnedBooksQuery = "SELECT COUNT(*) FROM books WHERE available = true";

            Statement issuedBooksStmt = connection.createStatement();
            Statement returnedBooksStmt = connection.createStatement();

            ResultSet issuedBooksRs = issuedBooksStmt.executeQuery(issuedBooksQuery);
            ResultSet returnedBooksRs = returnedBooksStmt.executeQuery(returnedBooksQuery);

            issuedBooksRs.next();
            returnedBooksRs.next();

            int issuedBooksCount = issuedBooksRs.getInt(1);
            int returnedBooksCount = returnedBooksRs.getInt(1);

            String statistics = "Issued Books: " + issuedBooksCount + "\n" +
                                "Returned Books: " + returnedBooksCount;

            statisticsArea.setText(statistics);
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching statistics!");
        }
    }
}

