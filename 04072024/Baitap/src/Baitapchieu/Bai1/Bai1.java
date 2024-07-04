package Baitapchieu.Bai1;

import java.util.ArrayList;
import java.util.List;

public class Bai1{

    public static void main(String[] args) {
        // Tim chuoi padlindrome
        String str = "abcddcba";
        System.out.println("Input: " + str);
        Boolean result = toDo(str, Bai1::padlindrome);
        System.out.println("Result: " + result);
    }


    public static boolean toDo(String str, IBai1 s) {
        return s.find(str);
    }

    public static boolean padlindrome(String str) {
        boolean flag = true;
        List<String> list = new ArrayList<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            list.add(String.valueOf(str.charAt(i)));
        }

        for (int i = 0; i < str.length(); i++) {
            if (!list.get(i).equals(String.valueOf(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
