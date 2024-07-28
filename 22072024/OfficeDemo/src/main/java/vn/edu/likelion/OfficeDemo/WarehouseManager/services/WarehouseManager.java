package vn.edu.likelion.OfficeDemo.WarehouseManager.services;

import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Warehouse;
import vn.edu.likelion.OfficeDemo.WarehouseManager.view.IWarehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

import static vn.edu.likelion.OfficeDemo.WarehouseManager.services.UserManager.updateStatusUser;

public class WarehouseManager {
    private static final ConnectionDB db = new ConnectionDB();
    private static final Scanner scanner = new Scanner(System.in);

     /*
      * addWarehouse - Add a warehouse
      */
    public static void addWarehouse(Warehouse warehouse) {
        if (db.connect()) {
            try {
                // Check warehouse exist
                String checkWarehouseNameSql = "SELECT COUNT(*) FROM Warehouses WHERE NAME = ?";
                try (PreparedStatement checkWarehouseNameStmt = db.connection.prepareStatement(checkWarehouseNameSql)) {
                    checkWarehouseNameStmt.setString(1, warehouse.getWarehouseName());
                    ResultSet rs = checkWarehouseNameStmt.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            System.out.println("Warehouse is existed.");
                            return;
                        }
                    }
                }
                // Get max id
                String maxIdSql = "SELECT MAX(ID) FROM Warehouses";
                PreparedStatement maxIdStatement = db.connection.prepareStatement(maxIdSql);
                ResultSet rs = maxIdStatement.executeQuery();
                int nextWarehouseId = 1; // Default Id
                if (rs.next()) {
                    nextWarehouseId = rs.getInt(1) + 1;
                }

                // Add new Warehouse
                String insertSql = "INSERT INTO Warehouses (ID, USER_ID, NAME, CATEGORY) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = db.connection.prepareStatement(insertSql);
                insertStatement.setInt(1, nextWarehouseId);
                insertStatement.setInt(2, warehouse.getUserId());
                insertStatement.setString(3, warehouse.getWarehouseName());
                insertStatement.setInt(4, 0);
                insertStatement.executeUpdate();
                System.out.println("Warehouse added successfully.");
            } catch (SQLException e) {
                System.out.println("Error add warehouse: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

    /*
     * updateWarehouse - Update warehouse infor
     */
    public static void updateWarehouse(int oldId, String newName) {
        if (db.connect()) {
            try {
                // Get old name
                String getIdSql = "SELECT ID, NAME FROM WAREHOUSES WHERE ID = ?";
                PreparedStatement getIdStatement = db.connection.prepareStatement(getIdSql);
                getIdStatement.setInt(1, oldId);
                ResultSet rs = getIdStatement.executeQuery();
                if (rs.next()) {
                String sql = "UPDATE WAREHOUSES SET NAME = ? WHERE ID = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, rs.getInt(1));
                int result = preparedStatement.executeUpdate();
                if (result < 1) {
                    System.out.println("Warehouse not found.");
                } else {
                    System.out.println("Update thành công Warehouse có id " + rs.getInt(1) + " với tên mới: " + newName);
                }
            } else {
                    System.out.println("Warehouse not found.");
                }
            } catch (SQLException e) {
                System.out.println("Error when updating Warehouse: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

     /*
      * getWarehouseByUserIdOrId - get warehouse by UserID or WarehouseID
      */
    public static Warehouse getWarehouseByUserIdOrId(String sql, int id) throws SQLException {
        if (db.connect()) {
            try {
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int warehouseId = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    String warehouseName = rs.getString("name");
                    return new Warehouse(warehouseId, userId, warehouseName);
                }
            } catch (SQLException e) {
                System.out.println("Error get Warehouse Infor: " + e.getMessage());
                throw e;
            } finally {
                db.close();
            }
        }
        return null;
    }

     /*
      * getEmptyorNotWarehouse - Get connected-Warehouse or Non-connected-Warehouse
      */
    public static boolean getEmptyorNotWarehouse(String sql, int id) {
        if (db.connect()) {
            try {
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                if (id != 0) { // Hien nhung kho khac co the move Item
                    preparedStatement.setInt(1, id);
                }
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("Available Warehouse: ");
                boolean foundWarehouse = false;
                while (rs.next()) {
                    int warehouseId = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    String warehouseName = rs.getString("name");
                    int category = rs.getInt("category");
                    System.out.println("- WarehouseId: " + warehouseId + ", User_Id: " + userId
                            + ", WarehouseName: " + warehouseName + ", Category: " +
                            (category == 1 ? "Main Warehouse" : "Sub-Warehouse"));
                    foundWarehouse = true;
                }
                if (!foundWarehouse) {
                    System.out.println("No suitable warehouses. Please create a new Warehouse");
                } else {
                    System.out.println("*Tip: You can chose name from list or enter a new Warehouse name");
                }
                return foundWarehouse;
            } catch (SQLException e) {
                System.out.println("Error get Warehouse Infor: " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return false;
    }

     /*
      * getAllWarehouse - Get all warehouse in system
      */
    public static void getAllWarehouse() {
        if (db.connect()) {
            try {
                String sql = "SELECT * FROM Warehouses";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int warehouseId = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    String warehouseName = rs.getString("name");
                    if (rs.getInt(4) == 1) {
                        System.out.println("- WarehouseId: " + warehouseId + ", User_Id: " + userId
                                + ", WarehouseName: " + warehouseName + ", Category: Main Warehouse");
                    } else {
                        System.out.println("- WarehouseId: " + warehouseId + ", User_Id: " + userId
                                + ", WarehouseName: " + warehouseName + ", Category: Sub-Warehouse");
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error get Warehouse Infor: " + e.getMessage());
            } finally {
                db.close();
            }
        }
    }

     /*
      * noneWarehouse - Check warehouse exist
      */
    public static boolean noneWarehouse() {
        if (db.connect()) {
            try {
                String sql = "SELECT * FROM Warehouses";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery();
                if (!rs.next()) {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi lấy thông tin kho: " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return true;
    }

     /*
      * deleteWarehouse - Delete a warehouse
      */
    public static boolean deleteWarehouse(int warehouseId, int userId) {
        if (db.connect()) {
            try {
                // Check Warehouse exist Items
                String checkItemsSql = "SELECT COUNT(*) FROM Item WHERE warehouse_id = ?";
                try (PreparedStatement preparedStatement = db.connection.prepareStatement(checkItemsSql)) {
                    preparedStatement.setInt(1, warehouseId);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        rs.next();
                        int itemCount = rs.getInt(1);

                        int targetWarehouseId = 0;
                        // Move all items before delete warehouse
                        if (itemCount > 0) {
                            System.out.println("Kho này có sản phẩm. Vui lòng chọn kho để chuyển sản phẩm:");
                            getEmptyorNotWarehouse("SELECT * FROM Warehouses WHERE USER_ID != 0 AND ID != ?", warehouseId); // Show List connectedUser-warehouse
                            // Check if connection is closed
                            if (db.connection.isClosed()) {
                                db.connect();
                            }
                            System.out.print("Nhập Warehouse ID đích: ");
                            try {
                                targetWarehouseId = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Input not allowed.");
                                scanner.next();
                                return false;
                            }
                            // Check input if null
                            if (getWarehouseByUserIdOrId("SELECT * FROM Warehouses WHERE ID = ?", targetWarehouseId) == null) {
                                System.out.println("Warehouse ID đích không hợp lệ.");
                                return false;
                            }
                            // Check if connection is closed
                            if (db.connection.isClosed()) {
                                db.connect();
                            }
                            // Move Item to another Warehouse
                            String updateSql = "UPDATE Item SET warehouse_id = ? WHERE warehouse_id = ?";
                            try (PreparedStatement updateStatement = db.connection.prepareStatement(updateSql)) {
                                updateStatement.setInt(1, targetWarehouseId);
                                updateStatement.setInt(2, warehouseId);
                                updateStatement.executeUpdate();
                            }
                        }
                        // Delete Empty Warehouse
                        String deleteSql = "DELETE FROM Warehouses WHERE id = ?";
                        try (PreparedStatement preparedStatement2 = db.connection.prepareStatement(deleteSql)) {
                            preparedStatement2.setInt(1, warehouseId);
                            int rowsAffected = preparedStatement2.executeUpdate();
                            return rowsAffected > 0;
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error deleting warehouse (ID: " + warehouseId + "): " + e.getMessage());
                return false;
            } finally {
                db.close();
            }
        } else {
            return false;
        }
    }
}

