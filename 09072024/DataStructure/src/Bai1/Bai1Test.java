package Bai1;

import java.util.Arrays;
import java.util.LinkedList;

public class Bai1Test {
    static LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

    public static void main(String[] args) {
        // RETURN MIDDLE NODE THE PROGRAM
        //Author: Rains97
        //METHOD 1
        System.out.println("In put: " + list);
        int middle = list.size() / 2;
        if (list.size() % 2 == 0) {
            System.out.println("Out put: " + list.get(middle-1) + ", " + list.get(middle));
            System.out.println("Explanation: The middle node of the list is: " + list.get(middle-1) + ", " + list.get(middle));
        } else {
            System.out.println("Out put: " + list.get(middle));
            System.out.println("Explanation: The middle node of the list is: " + list.get(middle));
        }
        // METHOD 2
    }
}
