import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /*
    Print first character no doublicate
     */
    public static void firstNoDoublicate(String str) {
        //str = "Kitukhonglaplai";
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length()-1; i++) {
            for (int j = 1; j < str.length()-2; j++) {
                if ((str.charAt(i) == str.charAt(j)) && i != j) {
                    arr[i] = 1;arr[j] = 1;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1) {
                System.out.println("Ki tu khong lap lai dau tien la: " + str.charAt(i));
                break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String str = sc.nextLine();
        firstNoDoublicate(str);
    }
}