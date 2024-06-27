package vn.edu.likelion;

import java.util.Arrays;
import java.util.Scanner;

public class Bai2 {

    public static void checkEven(String num) {
        boolean flag = true;
        for (int i = 0; i < num.length(); i ++) {
            if ((int) num.charAt(i) %2 == 0) {
                flag = false;
            }
        }
        System.out.println("Every digit of your number is even: " + flag);
    }

    public static void main(String[] args) {
        System.out.println("*Method 1: ");
        //Type an Interger
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your interger: ");
        int n = sc.nextInt();
        checkEven(String.valueOf(n));
    }
}
