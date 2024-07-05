package Bai1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bai1 {
    public static void main(String[] args) {
        int count = 0;
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 4, 6, 9, 7, 8, 10, 11, 12, 14);
        System.out.println("Current list: " + Arrays.toString(list.toArray()));

        //Count so chan
        List<Integer> result = list.stream()
                .filter(s -> s % 2 == 0)
                .toList();
        for (int i = 0; i < result.size(); i++) {
            count += result.get(i);
        }
        System.out.println("Tong cac so chan: " + count);

        //Count so le
        count = 0;
        List<Integer> result1 = list.stream()
                .filter(s -> s % 2 == 1)
                .toList();
        for (int i = 0; i < result1.size(); i++) {
            count += result1.get(i);
        }
        System.out.println("Tong cac so le: " + count);
    }
}
