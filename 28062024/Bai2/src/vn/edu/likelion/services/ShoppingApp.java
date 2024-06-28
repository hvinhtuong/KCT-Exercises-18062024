package vn.edu.likelion.services;

import vn.edu.likelion.model.Product;

public class ShoppingApp {
    public static void main(String[] args) {
        // Create Items
        Product p1 = new Product(1, "CPU i9-13900K", 1500);
        Product p2 = new Product(2, "Mainboard Asus Z990", 1100);
        Product p3 = new Product(3, "Ram DDR5 4800HZ", 550);

        // Create Cart
        Cart cart = new Cart(10);
        cart.addToCart(p1);
        cart.addToCart(p2);
        cart.addToCart(p3);

        // Show infor
        System.out.println("Current items in Cart:");
        for (int i = 0; i < cart.itemCount; i++) {
            Product p = cart.items[i];
            System.out.println("- ID: " + p.getProductId() + ", Name: " + p.getProductName() + ", Price: $" + p.getPrice());
        }

        // totalPrice
        double total = cart.totalPrice();
        System.out.println("Totol price of your Cart: $" + total);
    }
}
