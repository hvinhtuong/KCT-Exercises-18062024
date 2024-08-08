package vn.edu.likelion.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_warehouse")
public class WarehouseEntity extends BaseEntity {

    @Column
    private String warehouseName;

    @Column
    private String warehouseAddress;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }
}
