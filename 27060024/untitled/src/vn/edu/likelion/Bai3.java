package vn.edu.likelion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bai3 {
    private static Scanner scanner = new Scanner(System.in);
    private static int n;
     /*
      * getFirstCharacter - Extract the first digit from an Interger
      */
    public static void getFirstCharacter(String num) {
        if (num.charAt(0) != '-') {
            System.out.println("Extract the first digit from your Interger is: "
                    + num.charAt(0));
        } else {
            System.out.println("Extract the first digit from your Interger is: "
                    + num.charAt(1));
        }
    }

    public static void main(String[] args) {
        System.out.println("*Method 1: ");
        //Type an Interger
        System.out.print("Enter an interger (positive/negative): ");
        while (true) {
            try {
                n = scanner.nextInt();
                getFirstCharacter(String.valueOf(n));
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Input Error. Please Enter an interger (positive/negative): ");
                continue;
            }
        }
    }
}
