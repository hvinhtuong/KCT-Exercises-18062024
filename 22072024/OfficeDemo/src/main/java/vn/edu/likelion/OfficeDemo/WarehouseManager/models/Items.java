package vn.edu.likelion.OfficeDemo.WarehouseManager.models;

public class Items {
    private int id;
    private int warehouseId;
    private String name;         //Item name
    private String description; // Item detail
    private double price;
    private int amount;          // Quantity
    private String unit;

    public Items(int id, int warehouseId, String name, String description, double price, int amount, String unit) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public int getAmount() {
        return amount;
    }
    public String getUnit() {
        return unit;
    }
}

