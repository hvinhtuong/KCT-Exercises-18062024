package vn.edu.likelion.services;

import vn.edu.likelion.model.Product;

class Cart {
    Product[] items;
    int itemCount = 0;

    public Cart(int capacity) {
        items = new Product[capacity];
    }

     /*
      * addToCart - Add item to Cart
      */
    void addToCart(Product product) {
        if (itemCount < items.length) {
            items[itemCount++] = product;
        } else {
            System.out.println("You have already bough a lot of items");
        }
    }

    /*
     * totalPrice - Count total Price of Cart
     */
    double totalPrice() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += items[i].getPrice();
        }
        return total;
    }
}
