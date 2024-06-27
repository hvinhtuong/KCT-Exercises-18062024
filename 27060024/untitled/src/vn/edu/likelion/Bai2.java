package vn.edu.likelion;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Bai2 {
    private static Scanner scanner = new Scanner(System.in);
    private static int n;

     /*
      * checkEven - Check every digit of your number is even
      */
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
        while (true) {
            try {
                n = scanner.nextInt();
                checkEven(String.valueOf(n));
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Input Error. Please Enter an interger (positive/negative): ");
                continue;
            }
        }
    }
}
