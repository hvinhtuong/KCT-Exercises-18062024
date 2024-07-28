package vn.edu.likelion.OfficeDemo.WarehouseManager.models;

public class Warehouse {
    private int warehouseId;
    private int userId;
    private String warehouseName;
    private int category;

    public Warehouse(int warehouseId, int userId, String warehouseName, int category) {
        this.warehouseId = warehouseId;
        this.userId = userId;
        this.warehouseName = warehouseName;
        this.category = category;
    }

    public Warehouse(int warehouseId, int userId, String warehouseName) {
        this.warehouseId = warehouseId;
        this.userId = userId;
        this.warehouseName = warehouseName;
    }

    public Warehouse(int userId, String warehouseName) {
        this.userId = userId;
        this.warehouseName = warehouseName;
    }

    public int getWarehouseId() {
        return warehouseId;
    }
    public int getUserId() {
        return userId;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public int getCategory() {
        return category;
    }
}

