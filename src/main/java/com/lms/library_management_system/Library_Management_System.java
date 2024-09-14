package com.lms.library_management_system;

import javax.swing.JFrame;

public class Library_Management_System 
{
    public static void main(String[] args) 
    {
        // Set the look and feel to the system's default
        try 
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        // Create an instance of the LoginPage
        JFrame loginPage = new LoginPage();
        
        // Set default close operation and make the frame visible
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setVisible(true);
    }
}

