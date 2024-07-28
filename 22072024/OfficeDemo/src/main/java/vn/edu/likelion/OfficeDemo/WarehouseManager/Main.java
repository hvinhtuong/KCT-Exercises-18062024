package vn.edu.likelion.OfficeDemo.WarehouseManager;

import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Items;
import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Warehouse;
import vn.edu.likelion.OfficeDemo.WarehouseManager.services.Authentication;
import vn.edu.likelion.OfficeDemo.WarehouseManager.services.UserManager;
import vn.edu.likelion.OfficeDemo.WarehouseManager.services.ItemManager;
import vn.edu.likelion.OfficeDemo.WarehouseManager.services.WarehouseManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static vn.edu.likelion.OfficeDemo.WarehouseManager.services.Authentication.db;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Items> items;

    public static void main(String[] args) throws SQLException, IOException {
        int choice;

        while (true) {
            System.out.println("\n===== WAREHOUSE MANAGEMENT APPLICATION =====");
            System.out.println("1. Login          2. Exit");
            System.out.print("Your chose: ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter number.!");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Pass: ");
                    String password = scanner.nextLine();
                    // Authentication
                    if (Authentication.Authenticate(username, password)) {
                        int getRole = UserManager.getUserByUsername(username).getRole();

                        if (getRole == 1) { // Admin
                            System.out.println("Đăng nhập thành công với tư cách Admin.");
                            adminMenu();
                        } else { // User
                            System.out.println("Đăng nhập thành công với tư cách Users.");
                            userMenu(UserManager.getUserByUsername(username).getId(), username);
                        }
                    } else {
                        System.out.println("Login failed. Please check username or password!");
                    }
                    break;
                case 2:
                    System.out.println("Exit.");
                    return;
                default:
                    System.out.println("Invalid selection. Please enter number.!");
                    break;
            }
        }
    }

    /*
     * adminMenu - menu for Admin account
     */
    private static void adminMenu() throws SQLException, IOException {
        while (true) {
            System.out.println("\n           ===== ADMIN MENU =====");
            System.out.println("-------USER OPTION--------WAREHOUSE OPTION------");
            System.out.println("1. Add an User        -       6. Add warehouse");
            System.out.println("2. Update an user     -       7. Update warehouse");
            System.out.println("3. Delete an user     -       8. Delete warehouse");
            System.out.println("4. List all user      -       9. Warehouse List");
            System.out.println("5. Print Report       -      10. List Item in warehouse");
            System.out.println("0. Back to login      -      11. Import item - to a Warehouse");
            System.out.print("Your chose: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid selection. Please enter number.");
                continue;
            }
            scanner.nextLine();

            if (choice == 0) {
                break; // Rollback
            }

            switch (choice) {
                case 1: //Add user
                    System.out.println("----------Add User----------");
                    String name, pass;
                    do {
                        System.out.print("Username: ");
                        name = scanner.nextLine();
                        System.out.print("Password: ");
                        pass = scanner.nextLine();
                        if (name.isEmpty() || pass.isEmpty()) {
                            System.out.println("Username and password múst be enter!");
                        }
                    } while (name.isEmpty() || pass.isEmpty());
                    pass = Base64.getEncoder().encodeToString(pass.getBytes());
                    UserManager.addUser(name, pass, 0);
                    break;
                case 2: //Update user
                    System.out.println("----------Update User----------");
                    System.out.println("Các User hiện có: ");
                    if (!UserManager.getAllUser()) {
                        System.out.println("No Users exist.");
                        break;
                    }
                    String oldName, newName;
                    do {
                        System.out.print("Nhập Username cần thay đổi: ");
                        oldName = scanner.nextLine();
                        System.out.print("Nhập tên mới: ");
                        newName = scanner.nextLine();
                        if (oldName.equalsIgnoreCase("admin")) {
                            System.out.println("WARNING - Admin account can not be changed!");
                        }
                        if (oldName.isEmpty() || newName.isEmpty()) {
                            System.out.println("Cần nhập đầy đủ thông tin.");
                        }
                    } while (oldName.isEmpty() || newName.isEmpty());
                    // Update
                    UserManager.updateUser(oldName, newName);
                    break;
                case 3: // Delete user
                    UserManager.getAllUser();
                    System.out.println("----------DELETE----------");
                    System.out.print("Enter UserId cần xóa: ");
                    int delUsId = scanner.nextInt();
                    scanner.nextLine();

                    if (delUsId != 0) {
                        try {
                            // Delete User's Warehouse before
                            Warehouse userWarehouse = WarehouseManager.getWarehouseByUserIdOrId(
                                    "SELECT * FROM Warehouses WHERE USER_ID = ?", delUsId);
                            if (userWarehouse != null) {
                                if (!WarehouseManager.deleteWarehouse(userWarehouse.getWarehouseId(), userWarehouse.getUserId())) {
                                    System.out.println("Can not delete User's Warehouse.");
                                    break;
                                }
                            }
                            // Delete User
                            UserManager.removeUser(delUsId);
                            System.out.println("User successfully deleted.");
                        } catch (SQLException e) {
                            System.err.println("Error deleting user: " + e.getMessage());
                        }
                    } else {
                        System.out.println("WARNING - You can not ever delete Admin account !!!");
                    }
                    break;
                case 4: //List all users
                    System.out.println("Cac User hien co: ");
                    if (!UserManager.getAllUser()) {
                        System.out.println("No Users exist.");
                    }
                    break;
                case 5: // Print Report
                    ItemManager.exportAllToExcel("Admin_Report.xlsx");
                    break;
                case 6: //Add warehouse
                    System.out.print("Nhập tên Warehouse muốn tạo: ");
                    String wareHouseName = scanner.nextLine();
                    System.out.print("Bạn có muốn gán User cho kho vừa tạo không? (Y/N): ");
                    String chose = scanner.nextLine();
                    // Add warehouse
                    if (chose.equalsIgnoreCase("Y")) {
                        // List những user chưa có kho
                        UserManager.getEmptyUser();
                        System.out.println("Chọn Username cho kho (Enter để bỏ qua): ");
                        String choseName = scanner.nextLine();
                        if (!choseName.isBlank() && UserManager.getUserByUsername(choseName) != null &&
                            UserManager.getUserByUsername(choseName).getStatus() == 0) {
                            WarehouseManager.addWarehouse(new Warehouse(UserManager.getUserByUsername(choseName).getId(), wareHouseName));
                        } else {
                            WarehouseManager.addWarehouse(new Warehouse(0, wareHouseName));
                        }
                    } else {
                        WarehouseManager.addWarehouse(new Warehouse(0, wareHouseName));
                    }
                    break;
                case 7: // Update warehouse
                    WarehouseManager.getAllWarehouse();
                    System.out.println("----------Update Warehouse----------");
                    System.out.print("Chose Warehouse Id: ");
                    int oldWH = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New name: ");
                    String newWH = scanner.nextLine();
                    WarehouseManager.updateWarehouse(oldWH, newWH);
                    break;
                case 8: //Delete warehouse
                    System.out.println("Current Warehouse: ");
                    WarehouseManager.getAllWarehouse();
                    System.out.print("Enter Warehouse Id cần xóa: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Bạn có chắc muốn xóa Warehouse " +
                            WarehouseManager.getWarehouseByUserIdOrId("SELECT * FROM Warehouses WHERE ID = ?", deleteId)
                                    .getWarehouseName() + " (Y/N): ");
                    String del = scanner.nextLine();
                    if (del.equalsIgnoreCase("Y")) {
                        if (!WarehouseManager.deleteWarehouse(deleteId, WarehouseManager.getWarehouseByUserIdOrId(
                                "SELECT * FROM Warehouses WHERE ID = ?", deleteId).getUserId())) {
                            System.out.println("Can not delete warehouse.");
                        } else {
                            UserManager.updateStatusUser(WarehouseManager.getWarehouseByUserIdOrId(
                                    "SELECT * FROM Warehouses WHERE ID = ?", deleteId).getUserId());
                            System.out.println("Xóa thành công.");
                        }
                    } else {
                        System.out.println("Đã hủy thao tác xóa.");
                    }
                    break;
                case 9: //List Warehouse
                    if (!WarehouseManager.noneWarehouse()) {
                        System.out.println("Hiện tại chưa có kho nào.");
                    } else {
                        System.out.println("Danh sach cac kho hien tai: ");
                        WarehouseManager.getAllWarehouse();
                    }
                    break;
                case 10: //List Item in a Warehouse
                    System.out.println("Current Warehouse list:");
                    WarehouseManager.getAllWarehouse();
                    System.out.print("Enter Warehouse Id cần xem (Enter để bỏ qua): ");
                    String showIdInput = scanner.nextLine();
                    if (!showIdInput.isEmpty()) {
                        try {
                            int showId = Integer.parseInt(showIdInput);
                            items = ItemManager.getItemsByWarehouseId(showId);
                            if (items.isEmpty()) {
                                System.out.println("Kho chưa có sản phẩm.");
                            } else {
                                System.out.println("Danh sách sản phẩm của kho " + showId + " - "
                                    + WarehouseManager.getWarehouseByUserIdOrId("SELECT * FROM Warehouses WHERE ID = ?", showId)
                                    .getWarehouseName() + ":");
                                for (Items item : items) {
                                    System.out.println("- ID: " + item.getId() + ", Item name: " + item.getName() +
                                            ", Price: " + item.getPrice() + "$, Amount: " + item.getAmount());
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid selection. Please enter number.");
                        }
                    }
                    break;
                case 11: // Import Item to a Warehouse
                    System.out.println("Current Warehouse list:");
                    WarehouseManager.getAllWarehouse();
                    System.out.print("Enter Warehouse Id cần nhập Item (Enter để bỏ qua): ");
                    String importId = scanner.nextLine();
                    if (!importId.isBlank()) {
                        int importIDInt = Integer.parseInt(importId);
                        ItemManager.importItemsFromExcel(importId + ".xlsx", importIDInt);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid selection. Please enter number!");
            }
        }
    }

    /*
     * userMenu - menu for User account
     */
    private static void userMenu(int id, String username) throws SQLException, IOException {
        while (true) {
            System.out.println("\n=== Menu User: " + username + " ===");
            System.out.println("1. Xem thông tin kho");
            System.out.println("2. Nhập sản phẩm từ file Excel");
            System.out.println("3. Print Item Manager Report");
            System.out.println("0. Back to login");
            System.out.print("Your chose: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid selection. Please enter number.");
                continue;
            }
            scanner.nextLine();
            if (choice == 0) {
                break; // Rollback
            }
            Warehouse warehouse = WarehouseManager.getWarehouseByUserIdOrId("SELECT * FROM Warehouses WHERE USER_ID = ?", id);
            switch (choice) {
                case 1: // Xem thông tin kho
                    if (warehouse != null) {
                        System.out.println("Thông tin kho của: " + username);
                        System.out.println("- ID: " + warehouse.getWarehouseId());
                        System.out.println("- Name: " + warehouse.getWarehouseName());
                        if (warehouse.getCategory() == 1) {
                            System.out.println("- Category: Main Warehouse");
                        } else {
                            System.out.println("- Category: Sub Warehouse");
                        }
                        items = ItemManager.getItemsByWarehouseId(warehouse.getWarehouseId());
                        if (items.isEmpty()) {
                            System.out.println("No item in the Warehouse.");
                        } else {
                            System.out.println("Danh sách sản phẩm:");
                            for (Items item : items) {
                                System.out.println("- ID: " + item.getId() + ", Item name: " + item.getName() +
                                        ", Detail: " + item.getDescription() + ", Price: " + item.getPrice() +
                                        "$, Amount: " + item.getAmount() + ", Unit: " + item.getUnit());
                            }
                        }
                    } else {
                        System.out.println("You dont have Warehouse. ");
                        System.out.println("1. Connect with a Non-connected Warehouse");
                        System.out.println("2. Create your own Warehouse");
                        System.out.println("0. Back");
                        System.out.print("Your chose: ");

                        int userChose;
                        try {
                            userChose = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            System.out.println("Invalid selection. Please enter number.");
                            continue;
                        }

                        switch (userChose) {
                            case 1: // Connect with a Non-connected Warehouse
                                if (!WarehouseManager
                                        .getEmptyorNotWarehouse("SELECT * FROM Warehouses WHERE USER_ID = 0", 0)) {
                                    break;
                                }
                                System.out.print("Enter Warehouse ID to connect: ");
                                int warehouseId;
                                try {
                                    warehouseId = scanner.nextInt();
                                    scanner.nextLine();
                                    // Check Warehouse exist or be used
                                    Warehouse warehouseToConnect = WarehouseManager.getWarehouseByUserIdOrId(
                                            "SELECT * FROM Warehouses WHERE ID = ? AND USER_ID != 0", warehouseId);
                                    if (warehouseToConnect == null) {
                                        System.out.println("Warehouse ID does not exist or is already in use.");
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    System.out.println("Invalid selection. Please enter numbe.");
                                    continue;
                                }
                                // Update current USER_ID for Warehouse
                                if (db.connect()) {
                                    try (PreparedStatement updateStmt = db.connection.prepareStatement(
                                            "UPDATE Warehouses SET USER_ID = ? WHERE ID = ?")) {
                                        updateStmt.setInt(1, id);
                                        updateStmt.setInt(2, warehouseId);
                                        // updateStmt.executeUpdate() is number of rows which are affected
                                        int rowsAffected = updateStmt.executeUpdate();
                                        if (rowsAffected > 0) {
                                            System.out.println("Successful connect " + username + "with warehouse.");
                                        } else {
                                            System.out.println("ID not found.");
                                        }
                                    } catch (SQLException e) {
                                        System.out.println("Error while connect warehouse: " + e.getMessage());
                                    } finally {
                                        db.close();
                                    }
                                }
                                break;
                            case 2: // Create
                                System.out.print("Enter new warehouse name: ");
                                String newWarehouseName = scanner.nextLine();
                                WarehouseManager.addWarehouse(new Warehouse(id, newWarehouseName));
                                break;
                            case 0: // Back
                                break;
                            default:
                                System.out.println("Invalid selection. Please enter a number");
                        }
                    }
                    break;
                case 2: // Import from Excel file
                    if (warehouse != null) {
                        try {
                            ItemManager.importItemsFromExcel(warehouse.getWarehouseId()+ ".xlsx", warehouse.getWarehouseId());
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found.");
                        } catch (SQLException | IOException e) {
                            System.out.println("Error while importing dâta...: " + e.getMessage());
                        }
                    } else {
                        System.out.println("You are not connected with any Warehouse.");
                    }
                    break;
                case 3: // Print report to Excel
                    ItemManager.exportItemReportToExcelbyUser(warehouse.getWarehouseId(), username + "_UserReport.xlsx");
                    break;
                case 0:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Invalid selection. Please enter number!");
            }
        }
    }
}

