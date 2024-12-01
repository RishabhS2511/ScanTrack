/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class tables {
    
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet resultSet = null;
        
        try {
            con = ConnectionProvider.getCon();
            st = con.createStatement();

            // Create 'userdetails' table if it doesn't exist
            if (!tableExists(st, "userdetails")) {
                st.executeUpdate("CREATE TABLE userdetails (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL, " +
                        "gender VARCHAR(50) NOT NULL, " +
                        "email VARCHAR(255) NOT NULL, " +
                        "contact VARCHAR(20) NOT NULL, " +
                        "address VARCHAR(500) NOT NULL, " +
                        "state VARCHAR(100), " +
                        "country VARCHAR(100), " +
                        "uniqeregid VARCHAR(100) NOT NULL, " +
                        "imagename VARCHAR(100)" +
                        ");");
            }

            // Create 'userattendance' table if it doesn't exist
            if (!tableExists(st, "userattendance")) {
                st.executeUpdate("CREATE TABLE userattendance (" +
                        "userid INT NOT NULL, " +
                        "date DATE NOT NULL, " +
                        "checkin DATETIME, " +
                        "checkout DATETIME, " +
                        "workduration VARCHAR(100)" +
                        ");");
            }

            JOptionPane.showMessageDialog(null, "Tables Checked/Created Successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        } finally {
            try {
                // Close the resources properly
                if (resultSet != null) resultSet.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to check if a table exists
    private static boolean tableExists(Statement st, String tableName) throws Exception {
        ResultSet resultSet = null;
        try {
            resultSet = st.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
            return resultSet.next();
        } finally {
            if (resultSet != null) resultSet.close(); // Ensure ResultSet is closed
        }
    }
}



