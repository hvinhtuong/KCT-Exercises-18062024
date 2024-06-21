import java.util.Arrays;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "hello";
    String str1 = "";
        for (int i = 0; i < str.length(); i++) {
            str1 += String.valueOf(str.charAt(i));
            str1 += String.valueOf(str.charAt(i));
        }
        System.out.print("Your original string is: " + str + "\n");
        System.out.print("Result string is: " + str1);
    }
}
