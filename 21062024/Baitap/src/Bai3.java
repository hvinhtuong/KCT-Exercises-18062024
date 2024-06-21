import java.util.Arrays;

public class Bai3 {

    /*
    Count Odd, Even number of a Array
    */
    public static void countOdd(int[] str) {
    int oddCount = 0;
    int evenCount = 0;
    for (int i = 0; i < str.length; i++) {
        if (str[i] % 2 == 0) {
            oddCount++;
        } else {
            evenCount++;
        }
    }
    System.out.println("Number of Odd number is: " + oddCount);
    System.out.println("Number of Even number is: " + evenCount);
    }

    public static void main(String[] args) {
        int[] str = new int[] {1,2,3,4,5,6,7,8,9};
        System.out.println("Your array is: " + Arrays.toString(str));
        countOdd(str);
    }
}
