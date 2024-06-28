package vn.edu.likelion.services;

import vn.edu.likelion.model.Product;

import java.util.ArrayList;
import java.util.List;

import static vn.edu.likelion.services.ShoppingApp.products;

public class Cart {
    int stt;
    int productId;
    String productName;
    double price;
    String storeName;
    static List<Cart> Carts = new ArrayList<>();

    public Cart(int stt, int productId, String productName, double price, String storeName) {
        this.stt = stt;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.storeName = storeName;
    }

    public static void addToCart(int stt, int id, String name, double price, String storeName) {
        for (Product c: products) {
            if (c.getProductId() == id) {
                Carts.add(new Cart(Carts.size() + 1, c.getProductId(), c.getProductName(), c.getPrice(), "ABC Bakery Store"));
                System.out.println("Add item Successful.");
            } else {
                System.out.println("Item does not exits.");
                return;
            }
        }
    }

    /*
     * totalPrice - Count total Price in cart
     */
    public static void totalPrice() {
        double totalPrice = 0;
        for (Cart c: Carts) {
            totalPrice += c.price;
        }
        System.out.println("TOTAL CART: $" + totalPrice);
    }

    /*
     * totalsize - Get size of Cart
     */
    public int totalSize() {
        return Carts.size();
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
