package Baitapchieu.Bai2;

import Baitapchieu.Bai1.IBai1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bai2 {


    /*
Sort method 3 bubbleSort
 */
     /*
      * bubbleSort - Sort list
      */
    static void bubbleSort(List<Integer> list) {
        int i, j;
        int temp;
        boolean swap;
        for (i = 0; i < list.size() - 1; i++) {
            swap = false;
            for (j = 0; j < list.size() - i - 1; j++) {
                if (list.get(i) > list.get(j+1)) {
                    // swap
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swap = true;
                }
            }
            if (swap == false)
                break;
        }
    }

    public static void toDo(List<Integer> list, IBai2 s) {
        getSecond(list);
    }

     /*
      * getSecond - Find and print 2nd maximum of array and minimum
      */
    public static void getSecond(List<Integer> list) {
        bubbleSort(list);
        System.out.println("So lon thu hai trong day la: " + list.getFirst());
        System.out.println("So nho nhat cua day la: " + list.get(1));
    }

    public static void main(String[] args) {
    List<Integer> list = Arrays.asList(10, 8, 1, 3, 5, 7, 4, 2, 6);
    System.out.println("Your array: " + (list));
    toDo(list, Bai2::getSecond);
    }
}
