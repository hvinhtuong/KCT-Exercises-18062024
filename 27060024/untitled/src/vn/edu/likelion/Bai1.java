package vn.edu.likelion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bai1 {

    public static void filterZero(int[] num1) {
        int[] num2 = new int[num1.length];
        int count = 0;
        for (int i = 0; i < num2.length; i++) {
            if (num1[i] != 0) {
                num2[count++] = num1[i];
            }
        }
        System.out.println("Mang da sap xep: " + Arrays.toString(num2) + "\n");
    }

    public static void filterZero2(int[] num1) {

    }

    public static void main(String[] args) {
        System.out.println("*Method 1: ");
        //Type array
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of your array element: ");
        int n = sc.nextInt();
        int[] num1 = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("- Enter the element " + i + " :");
            num1[i] = sc.nextInt();
        }
        //int[] num1 = new int[0, 2, 1, 0, 0, 0, 5, 6, 3];
        System.out.print("Mang chua sap xep: " + Arrays.toString(num1) + "\n");
        filterZero(num1);
    }
}

