package vn.edu.likelion.Bai2;

import java.util.Scanner;
import java.util.function.BiFunction;

public class Bai2 {
    static Scanner scanner = new Scanner(System.in);

    /*
     * convertToBinary - Convert decimal to binary
     */
    /*public static void convertToBinary(int num) {
       int id = 0;
       while (num>0) {
           binary[id++] = num % 2;
           num = num/2;
       }
       for (int i = id -1; i >= 0; i--) {
           System.out.print(binary[i]);
       }
    } */

    public static void main(String[] args) {
        // Convert Decimal to Binary via Lambda Expression
        int[] binary = new int[30];
        do {
            System.out.print("Input interger to convert: ");
            int num = scanner.nextInt();
            System.out.print("Binary Output: ");
            // Lambda Expression for Convert Binary
            Convert convert = (int x) -> {int id = 0;
                while (x > 0) { binary[id++] = x % 2; x = x / 2;}
                for (int i = id - 1; i >= 0; i--) { System.out.print(binary[i]);}
                System.out.println("\n-------------------");};
            convert.convert(num);
        } while (true);
    }
}
