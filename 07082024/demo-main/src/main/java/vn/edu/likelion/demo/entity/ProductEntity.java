package vn.edu.likelion.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_product")
public class ProductEntity extends BaseEntity{

    @Column
    private String productName;

    @Column
    private int quantity;

    @Column
    private int isDeleted;

    @Override
    public String toString() {
        return "Product name: " + productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
