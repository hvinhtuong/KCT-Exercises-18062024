package vn.edu.likelion.Bai1;

import java.util.Scanner;
import java.util.function.BiFunction;

public class Bai1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Tim uoc nguyen to lon nhat cua 1 so
        do {
            System.out.print("Input an interger : ");
            int str = scanner.nextInt();
            IMax ref = UNTLN::new; // Find Max UNTLN via Method References
            ref.findMax(str);
        } while (true);
    }
}
