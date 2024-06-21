import java.util.Scanner;

public class Bai5 {

    /*
    Swap two string
     */
    public static void swapStrings(String str1, String str2){
        int len1 = str1.length();
        int len2 = str2.length();
        str1 = str1 + str2;
        str2 = str1.substring(0, len1);
        str1 = str1.substring(len1, len1 + len2);
        System.out.println("Your string 1 (swapped): " + str1);
        System.out.println("Your string 2 (swapped): " + str2);
    }
    public static void main(String[] args) {
        String str1 = "Hello Original Java";
        String str2 = "World";
        System.out.println("Your string 1: " + str1);
        System.out.println("Your string 2: " + str2);
        swapStrings(str1, str2);

        //Method 2 - type new strings
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your string 1: ");
        String str3 = sc.nextLine();
        System.out.println("Type your string 2: ");
        String str4 = sc.nextLine();
        swapStrings(str3, str4);

    }
}
