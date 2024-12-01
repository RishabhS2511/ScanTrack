/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    // Method to get the database connection
    public static Connection getCon() throws SQLException {
        // Database URL, username, and password should ideally be stored securely
        String url = "jdbc:mysql://localhost:3306/attendenceManagement";
        String username = "root";
        String password = "Ris846+-@";

        // Load MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Database driver not found", ex);
        }
    }
}
