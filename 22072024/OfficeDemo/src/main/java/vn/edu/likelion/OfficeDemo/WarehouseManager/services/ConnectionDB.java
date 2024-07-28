package vn.edu.likelion.OfficeDemo.WarehouseManager.services;

import java.sql.*;

public class ConnectionDB {
    public Connection connection;

     /*
      * connect - Create db connection
      */
    public boolean connect() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl21",
                    "c##admin", "admin123");
            return true;
        } catch (SQLException e) {
            System.out.println("Error when connect database " + e.getMessage());
            return false;
        }
    }

     /*
      * close - Close connection
      */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error on close processing: " + e.getMessage());
        }
    }
}

