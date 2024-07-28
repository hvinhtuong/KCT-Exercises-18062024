package vn.edu.likelion.OfficeDemo.WarehouseManager.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Authentication {
    public static ConnectionDB db = new ConnectionDB();

     /*
      * Authenticate - Authentication
      */
    public static boolean Authenticate(String username, String password) {
        if (db.connect()) {
            try {
                String sql = "SELECT * FROM Account WHERE USER_NAME = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    // Decode
                    String decodedPassword = new String(Base64.getDecoder().decode(hashedPassword));
                    return password.equals(decodedPassword);
                }
            } catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return false;
    }
}

