package vn.edu.likelion.OfficeDemo.WarehouseManager.view;

import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Items;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IItem {
    List<Items> getItemsByWarehouseId(int warehouseId);
    void importItemsFromExcel(String filePath, int warehouseId) throws IOException, SQLException;
    void exportItemReportToExcelbyUser(int warehouseId, String filePath) throws SQLException, IOException;
    void exportAllToExcel(String filePath) throws SQLException, IOException;
}
