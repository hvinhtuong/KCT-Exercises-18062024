package vn.edu.likelion.OfficeDemo.WarehouseManager.view;

import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Warehouse;

import java.sql.SQLException;

public interface IWarehouse {
    void addWarehouse(Warehouse warehouse);
    void updateWarehouse(int oldId, String newName);
    Warehouse getWarehouseByUserIdOrId(String sql, int id) throws SQLException;
    boolean getEmptyorNotWarehouse(String sql);
    void getAllWarehouse();
    boolean noneWarehouse();
    boolean deleteWarehouse(int warehouseId);
}