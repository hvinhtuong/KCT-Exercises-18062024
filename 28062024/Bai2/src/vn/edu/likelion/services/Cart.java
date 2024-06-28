package vn.edu.likelion.services;

import vn.edu.likelion.model.Product;

class Cart {
    Product[] items; // Mảng chứa các sản phẩm
    int itemCount = 0; // Số lượng sản phẩm hiện tại

    public Cart(int capacity) {
        items = new Product[capacity]; // Khởi tạo mảng với sức chứa
    }

     /*
      * addToCart - Add item to Cart
      */
    void addToCart(Product product) {
        if (itemCount < items.length) {
            items[itemCount++] = product;
        } else {
            System.out.println("Giỏ hàng đã đầy!");
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
