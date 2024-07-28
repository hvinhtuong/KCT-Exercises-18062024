package vn.edu.likelion.OfficeDemo.WarehouseManager.services;

import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Users;
import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Warehouse;
import vn.edu.likelion.OfficeDemo.WarehouseManager.view.IUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserManager {
    static int result;
    static ConnectionDB db = new ConnectionDB();
    static Scanner scanner = new Scanner(System.in);

    /*
     * addUser - Add user
     */
    public static void addUser(String name, String pass, int role) {
        // Check if enter null value
        if (name == null || name.isBlank() || pass == null || pass.isBlank()) {
            System.out.println("Please enter valid username & password.");
            return;
        }
        if (db.connect()) {
            try {
                String checkUsernameSql = "SELECT COUNT(*) FROM Account WHERE USER_NAME = ?";
                try (PreparedStatement checkUsernameStmt = db.connection.prepareStatement(checkUsernameSql)) {
                    checkUsernameStmt.setString(1, name);
                    ResultSet rs = checkUsernameStmt.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            System.out.println("Account is existed.");
                            return;
                        }
                    }
                }
                // Get max id
                String maxIdSql = "SELECT MAX(ID) FROM Account";
                PreparedStatement maxIdStatement = db.connection.prepareStatement(maxIdSql);
                ResultSet rs = maxIdStatement.executeQuery();
                int nextUserId = 1; // Default
                if (rs.next()) {
                    nextUserId = rs.getInt(1) + 1;
                }

                // Thêm user moi
                String sql = "INSERT INTO Account (ID, USER_NAME, PASSWORD, ROLE, STATUS) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setInt(1, nextUserId);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, pass);
                preparedStatement.setInt(4, role);
                // Add warehouse for new user
                System.out.println("Thêm kho cho user " + name);
                System.out.print("Nhập tên kho (Enter để bỏ qua): ");
                String warehouseName = scanner.nextLine();
                if (!warehouseName.isBlank()) {
                    preparedStatement.setInt(5, 1);
                    preparedStatement.executeUpdate();
                    WarehouseManager.addWarehouse(new Warehouse(nextUserId, warehouseName));
                } else {
                    preparedStatement.setInt(5, 0);
                    preparedStatement.executeUpdate();
                }
                System.out.println("User added successfully.");
            } catch (SQLException e) {
                System.out.println("Error add user: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

    /*
     * removeUser - Delete user
     */
    public static void removeUser(int id) {
        result = 0;
        if (db.connect()) {
            try {
                String deleteSql = "DELETE FROM Account WHERE id = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, id);
                result = preparedStatement.executeUpdate();
                if (result < 1) {
                    System.out.println("Không tìm thấy User cần xóa");
                } else {
                    System.out.println("Xóa thành công User co Id: " + id);
                }
            } catch (SQLException e) {
                System.out.println("Error occur deleting User: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

    /*
     * updateUser - Update username
     */
    public static void updateUser(String oldName, String newName) {
        result = 0;
        if (db.connect()) {
            try {
                // Get old name
                String getIdSql = "SELECT ID, USER_NAME FROM Account WHERE USER_NAME = ?";
                PreparedStatement getIdStatement = db.connection.prepareStatement(getIdSql);
                getIdStatement.setString(1, oldName);
                ResultSet rs = getIdStatement.executeQuery();
                if (rs.next()) {
                    System.out.println("Id: " + rs.getInt(1) + ", Tên cũ: " + rs.getString(2));
                } else {
                    System.out.println("User does not exist.");
                    return;
                }

                String sql = "UPDATE Account SET USER_NAME = ? WHERE ID = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, rs.getInt(1));
                result = preparedStatement.executeUpdate();
                if (result < 1) {
                    System.out.println("User not found.");
                } else {
                    System.out.println("Update thành công User có id " + rs.getInt(1) + " với tên mới: " +newName);
                }
            } catch (SQLException e) {
                System.out.println("Error when updating user: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

    /*
     * updateStatusUser - Update status when delete Warehouse
     */
    public static void updateStatusUser(int id) {
        if (db.connect()) {
            try {
                String sql = "UPDATE Account SET STATUS = 0 WHERE ID = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error when updating status user: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

    /*
     * getUserByUsername - Get user by username
     */
    public static Users getUserByUsername(String username) {
        if (db.connect()) {
            try {
                String deleteSql = "SELECT ID, ROLE, STATUS FROM Account WHERE USER_NAME = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(deleteSql);
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    return new Users(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                }
            } catch (SQLException e) {
                System.out.println("Error occur: " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return null;
    }

    /*
     * getEmptyUser - Get Empty User
     */
    public static boolean getEmptyUser() {
        if (db.connect()) {
            try {
                String select = "SELECT * FROM Account WHERE STATUS = 0";
                PreparedStatement preparedStatement = db.connection.prepareStatement(select);
                ResultSet rs = preparedStatement.executeQuery();
                boolean foundUser = false;
                System.out.println("User chưa có kho hiện có: ");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("user_name");
                    int role = rs.getInt("role");
                    System.out.println("- ID: " + id + ", Name: " + name
                            + ", Role: " + role);
                    foundUser = true;
                }
                if (!foundUser) {
                    System.out.println("Không có.");
                }
                return foundUser;
            } catch (SQLException e) {
                System.out.println("Error occur: " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return false;
    }

    /*
     * getAllUser - Get all Current User
     */
    public static boolean getAllUser() {
        if (db.connect()) {
            try {
                // Cập nhật trạng thái trước khi hiển thị
                String updateSql = "UPDATE Account SET STATUS = 1 " +
                        "WHERE ID IN (SELECT DISTINCT USER_ID FROM WAREHOUSES)";
                PreparedStatement updateStatement = db.connection.prepareStatement(updateSql);
                updateStatement.executeUpdate();

                // Hiển thị tất cả người dùng
                String selectSql = "SELECT * FROM Account";
                PreparedStatement preparedStatement = db.connection.prepareStatement(selectSql);
                ResultSet rs = preparedStatement.executeQuery();

                boolean foundUser = false;
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("user_name");
                    int role = rs.getInt("role");
                    int status = rs.getInt("status"); // Lấy trạng thái sau khi cập nhật

                    System.out.println("- ID: " + id + ", Name: " + name
                            + ", Role: " + role + ", Status: " + (status == 1 ? "Warehouse connected" : "Warehouse disconnected"));
                    foundUser = true;
                }
                return foundUser;
            } catch (SQLException e) {
                System.out.println("Error occur: " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return false;
    }
}
