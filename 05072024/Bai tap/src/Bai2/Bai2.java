package Bai2;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Bai2 {

     /*
      * removeRepeat - Remove repeat integer
      */
    public static void removeRepeat(LinkedList<Integer> list) {
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                if ((list.get(i).equals(list.get(j))) && i != j) {
                    list.remove(j);
                }
            }
        }
    }
    public static void main(String[] args) {
        //Original listing
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(10, 23, 22, 23, 24, 24, 33, 15, 26, 15));
        System.out.println("Your original array: " + list);

        //Call removeRepeat method
        removeRepeat(list);

        //Show result
        //list.stream().forEach(s -> System.out.print(s + "  "));
        List<Integer> result = list.stream()
                .sorted().toList();
        System.out.print("After remove repeated: " + result);
    }
}