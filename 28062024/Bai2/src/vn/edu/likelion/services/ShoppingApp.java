package vn.edu.likelion.services;

import vn.edu.likelion.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingApp {
    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static List<Cart> Carts = new ArrayList<>();

    public static void addToCart(int stt, int id, String name, double price, String storeName) {
        for (Product c: products) {
            Carts.add( new Cart(Carts.size()+1, c.getProductId(), c.getProductName(), c.getPrice(), "ABC Bakery Store"));
        }
        System.out.println("Add item Successful.");
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

    public static void main(String[] agrs) {
        products.add(new Product(1, "Vina Milk", 15000));
        products.add(new Product(2, "Chinsu", 25000));
        products.add(new Product(3, "Eggs", 5000));
        System.out.println("Item list: ");
        for (Product c: products) {
            System.out.println("- Id: " + c.getProductId() + " - Name: " + c.getProductName()
                    + " - Price: " + c.getPrice());
        }
        System.out.println("Do you want to add to your Cart?");
        System.out.print("Y/N: ");
        String chose = sc.nextLine();
        if (chose.equalsIgnoreCase("Y")) {
            for (Product c: products) {
                Carts.add( new Cart(Carts.size()+1, c.getProductId(), c.getProductName(), c.getPrice(), "ABC Bakery Store"));
            }
            System.out.println("Successful add 3 item to cart.");
            System.out.println("----------------------------");
            System.out.println("Your cart: ");
            for (Cart c: Carts) {
                System.out.println("- STT: " + c.getStt() + " - Id: " + c.getProductId() + " - Name: " + c.getProductName()
                        + " - Price: " + c.getPrice() + " - Store: " + c.getStoreName());
            }

            // Add another item
            System.out.println("Do you want too add an more item to Cart: ");
            System.out.print("Y/N: ");
            chose = sc.nextLine();
            if (chose.equalsIgnoreCase("Y")) {
                System.out.println("Enter item id: ");
                int id = sc.nextInt();
                for (Product c: products) {
                    if (c.getProductId() == id) {
                        addToCart(Carts.size()+1, c.getProductId(), c.getProductName(), c.getPrice(), "ABC Bakery Store");
                    }
                }
                System.out.println("Your current cart: ");
                for (Cart c: Carts) {
                    System.out.println("- STT: " + c.getStt() +" - Id: " + c.getProductId() + " - Name: " + c.getProductName()
                            + " - Price: " + c.getPrice() + " - Store: " + c.getStoreName());
                }
                totalPrice();
            }
        } else {
            System.out.println("Ket thuc chuong trinh.");
        }
    }
}
