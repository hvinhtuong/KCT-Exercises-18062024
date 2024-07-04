package Baitapchieu.Bai2;

import Baitapchieu.Bai1.IBai1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bai2 {

     /*
      * bubbleSort - Sort list
      */
    static List<Integer> bubbleSort(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        int i, j;
        boolean swap;
        for (i = 0; i < sortedList.size() - 1; i++) {
            swap = false;
            for (j = 0; j < sortedList.size() - i - 1; j++) {
                if (sortedList.get(j) > sortedList.get(j + 1)) {
                    // Swap
                    int temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                    swap = true;
                }
            }
            if (swap == false) {
                break;
            }
        }
        return sortedList;
    }

    public static void toDo(List<Integer> list, IBai2 s) {
        s.findSeconmax(list);
    }

     /*
      * getSecond - Find and print 2nd maximum of array and minimum
      */
    public static void getSecond(List<Integer> list) {
        System.out.println("So lon thu hai trong day la: " + bubbleSort(list).get(list.size()-2));
        System.out.println("So nho nhat cua day la: " + bubbleSort(list).getFirst());
    }

    public static void main(String[] args) {
    List<Integer> list = Arrays.asList(10, 8, 1, 3, 5, 7, 4, 2, 6, 0);
    System.out.println("Your array: " + list);
    toDo(list, Bai2::getSecond);
    }
}
