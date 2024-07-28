package vn.edu.likelion.OfficeDemo.WarehouseManager.services;

import java.io.*;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Items;

public class ItemManager{
    static ConnectionDB db = new ConnectionDB();

    /*
     * getItemsByWarehouseId - Get Item via WarehouseId
     */
    public static List<Items> getItemsByWarehouseId(int warehouseId) {
        List<Items> items = new ArrayList<>();
        ConnectionDB db = new ConnectionDB();
        if (db.connect()) {
            try {
                String sql = "SELECT * FROM Item WHERE warehouse_id = ? ORDER BY NAME";
                PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                preparedStatement.setInt(1, warehouseId);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String detail = rs.getString("detail");
                    double price = rs.getDouble("price");
                    int amount = rs.getInt("amount");
                    String unit = rs.getString("unit");
                    items.add(new Items(id, warehouseId, name, detail, price, amount, unit));
                }
            } catch (SQLException e) {
                System.out.println("Error get Item list: " + e.getMessage());
            } finally {
                db.close();
            }
        }
        return items;
    }

    /*
     * importItemsFromExcel - Import Item to Excel file
     */
    public static void importItemsFromExcel(String filePath, int warehouseId) throws IOException, SQLException {
        if (db.connect()) {
            // Check Connected-User Warehouse
            if (WarehouseManager.getWarehouseByUserIdOrId("SELECT * FROM Warehouses WHERE ID = ?", warehouseId) != null) {
                if (WarehouseManager.getWarehouseByUserIdOrId("SELECT * FROM Warehouses WHERE ID = ?", warehouseId).getUserId() != 0) {
                    try (FileInputStream in = new FileInputStream(filePath);
                         Workbook workbook = new XSSFWorkbook(in);
                         PreparedStatement insertStmt = db.connection.prepareStatement("INSERT INTO Item (id, warehouse_id, name, detail, price, amount, unit) VALUES (?, ?, ?, ?, ?, ?, ?)");
                         PreparedStatement updateStmt = db.connection.prepareStatement("UPDATE Item SET amount = ? WHERE warehouse_id = ? AND name = ?");
                         PreparedStatement checkStmt = db.connection.prepareStatement("SELECT id FROM Item WHERE warehouse_id = ? AND name = ?")) {

                        Sheet sheet = workbook.getSheetAt(0);

                        for (int rowIndex = 1; rowIndex <= sheet.getPhysicalNumberOfRows() - 1; rowIndex++) {
                            Row row = sheet.getRow(rowIndex);

                            if (row == null) continue;

                            Cell idCell = row.getCell(0);
                            Cell nameCell = row.getCell(1);
                            Cell detailCell = row.getCell(2);
                            Cell priceCell = row.getCell(3);
                            Cell amountCell = row.getCell(4);
                            Cell unitCell = row.getCell(5);

                            if (idCell != null && idCell.getCellType() == CellType.NUMERIC &&
                                    nameCell != null && nameCell.getCellType() == CellType.STRING &&
                                    detailCell != null && detailCell.getCellType() == CellType.STRING &&
                                    priceCell != null && priceCell.getCellType() == CellType.NUMERIC &&
                                    amountCell != null && amountCell.getCellType() == CellType.NUMERIC &&
                                    unitCell != null && unitCell.getCellType() == CellType.STRING) {

                                checkStmt.setInt(1, warehouseId);
                                checkStmt.setString(2, nameCell.getStringCellValue());

                                try (ResultSet rs = checkStmt.executeQuery()) {
                                    if (rs.next()) {
                                        // If exist, update Amount
                                        updateStmt.setInt(1, (int) amountCell.getNumericCellValue());
                                        updateStmt.setInt(2, warehouseId);
                                        updateStmt.setString(3, nameCell.getStringCellValue());
                                        updateStmt.executeUpdate();
                                    } else {
                                        // If not exist, Insert Item
                                        insertStmt.setInt(1, (int) idCell.getNumericCellValue());
                                        insertStmt.setInt(2, warehouseId);
                                        insertStmt.setString(3, nameCell.getStringCellValue());
                                        insertStmt.setString(4, detailCell.getStringCellValue());
                                        insertStmt.setDouble(5, priceCell.getNumericCellValue());
                                        insertStmt.setInt(6, (int) amountCell.getNumericCellValue());
                                        insertStmt.setString(7, unitCell.getStringCellValue());
                                        insertStmt.executeUpdate();
                                    }
                                }
                            } else {
                                System.err.println("Invalid data in row " + rowIndex);
                            }
                        }
                        System.out.println(" Nhập sản phẩm vào kho thành công.");
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        throw e;
                    } catch (SQLException e) {
                        System.err.println("Error SQL Query: " + e.getMessage());
                        throw e;
                    } catch (IOException e) {
                        System.err.println("Error reading Excel file: " + e.getMessage());
                        throw e;
                    } finally {
                        db.close();
                    }
                } else {
                    System.err.println("Warehouse is not connected to User.");
                }
            } else {
                System.err.println("WarehouseId not found");
            }
        }
    }

    /*
     * ExportItemsFromExcel - Export Item to Excel Report - use for Users
     */
    public static void exportItemReportToExcelbyUser(int warehouseId, String filePath) throws SQLException, IOException {
        if (db.connect()) {
            String sql = "SELECT * FROM Item WHERE warehouse_id = ?";
            try (PreparedStatement preparedStatement = db.connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, warehouseId);
                try (ResultSet rs = preparedStatement.executeQuery()) {

                    // Create sheet
                    Workbook workbook = new XSSFWorkbook();
                    Sheet sheet = workbook.createSheet("ITEM REPORT");

                    Font boldFont = workbook.createFont();
                    boldFont.setBold(true);
                    // Bold style
                    CellStyle boldStyle = workbook.createCellStyle();
                    boldStyle.setFont(boldFont);

                    Row headerRow = sheet.createRow(1);
                    for (int i = 1; i <= 7; i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellStyle(boldStyle);
                        switch (i) {
                            case 1 -> cell.setCellValue("Item ID");
                            case 2 -> cell.setCellValue("Item Name");
                            case 3 -> cell.setCellValue("Detail");
                            case 4 -> cell.setCellValue("Price ($)");
                            case 5 -> cell.setCellValue("Total Amount");
                            case 6 -> cell.setCellValue("Unit");
                            case 7 -> cell.setCellValue("Total Price ($)");
                        }
                    }

                    int rowIndex = 2;
                    while (rs.next()) {
                        Row row = sheet.createRow(rowIndex++);
                        row.createCell(1).setCellValue(rs.getInt("id"));
                        row.createCell(2).setCellValue(rs.getString("name"));
                        row.createCell(3).setCellValue(rs.getString("detail"));
                        row.createCell(4).setCellValue(rs.getDouble("price"));
                        row.createCell(5).setCellValue(rs.getDouble("amount"));
                        row.createCell(6).setCellValue(rs.getString("unit"));
                        row.createCell(7).setCellValue(rs.getDouble("price")
                                                        * rs.getDouble("amount"));
                    }
                    // Save
                    try (FileOutputStream out = new FileOutputStream(filePath)) {
                        workbook.write(out);
                        System.out.println("Excel report exported successfully!");
                    }
                }
            }
        }
    }

    /*
     * exportAllToExcel - Export All Items to Excel Report - use for Admin
     */
    public static void exportAllToExcel(String filePath) throws SQLException, IOException {
        // Export warehouse Infor first
        if (db.connect()) {
            String sql = "SELECT w.id, w.name, i.id, i.name, i.detail, i.price, i.amount, i.unit " +
                    "FROM Warehouses w, Item i WHERE w.id = i.warehouse_id\n" +
                    "ORDER BY w.name, i.name";

            try (PreparedStatement preparedStatement = db.connection.prepareStatement(sql);
                 ResultSet rs1 = preparedStatement.executeQuery()) {

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("TOTAL REPORT - BY ADMIN");

                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                // Bold style
                CellStyle boldStyle = workbook.createCellStyle();
                boldStyle.setFont(boldFont);

                Row headerRow = sheet.createRow(1);
                for (int i = 1; i <= 9; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellStyle(boldStyle);
                    switch (i) {
                        case 1 -> cell.setCellValue("Warehouse ID");
                        case 2 -> cell.setCellValue("Warehouse Name");
                        case 3-> cell.setCellValue("Item ID");
                        case 4 -> cell.setCellValue("Item Name");
                        case 5 -> cell.setCellValue("Detail");
                        case 6 -> cell.setCellValue("Price ($)");
                        case 7 -> cell.setCellValue("Total Amount");
                        case 8 -> cell.setCellValue("Unit");
                        case 9 -> cell.setCellValue("Total Price ($)");
                    }
                }

                int rowIndex = 2;
                double total = 0;
                while (rs1.next()) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(1).setCellValue(rs1.getInt(1));
                    row.createCell(2).setCellValue(rs1.getString(2));
                    row.createCell(3).setCellValue(rs1.getInt(3));
                    row.createCell(4).setCellValue(rs1.getString(4));
                    row.createCell(5).setCellValue(rs1.getString(5));
                    row.createCell(6).setCellValue(rs1.getDouble(6));
                    row.createCell(7).setCellValue(rs1.getDouble(7));
                    row.createCell(8).setCellValue(rs1.getString(8));
                    row.createCell(9).setCellValue(rs1.getDouble(7)
                            * rs1.getDouble(6));
                    total += (rs1.getDouble(7) * rs1.getDouble(6));
                }
                // Create total cell label
                Row totalRow = sheet.createRow(rowIndex);
                Cell totalLabelCell = totalRow.createCell(8);
                totalLabelCell.setCellStyle(boldStyle);
                totalLabelCell.setCellValue("TOTAL");

                // Create total value cell
                Cell totalValueCell = totalRow.createCell(9);
                totalValueCell.setCellStyle(boldStyle);
                totalValueCell.setCellValue(total);

                // Save
                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    System.out.println("Excel report exported successfully!");
                }
            }
        }
    }
}

