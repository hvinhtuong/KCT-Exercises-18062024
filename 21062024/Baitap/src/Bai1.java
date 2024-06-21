import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Bai1 {

    /*
    Count all number which appear once
     */
    public static int count(int[] str) {
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < str.length; i++) {
            add(map, str[i]);
        }
        int count = 0;
        for (Integer key : map.keySet()) {
                count += key;
        }
        return count;
    }

    /*
    Count number appear of a number in your array
     */
    public static void add(Map<Integer, Integer> map, int num) {
        if (map.containsKey(num)) {
            int count = map.get(num) + 1;
            map.put(num, count);
        } else {
            map.put(num, 1);
        }
    }

    public static void typeArray() {

    }

    public static void main(String[] args) {
        int[] str = new int[] {5, 1, 8, 4, 9, 1, 5};

        //Method 1
        System.out.println("Your array is: "+ Arrays.toString(str));
        System.out.println("Sum of your array is: " +count(str));

        //Method 2 - type your new Array
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of your array element: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("  Enter the element " + i + " :");
            arr[i] = sc.nextInt();
        }
        System.out.println("Sum of your array is: " +count(arr));
        }

}