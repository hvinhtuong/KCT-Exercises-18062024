package Baitapsang;

import java.util.Arrays;
import java.util.List;

public class BaiTapSang {
    static double count;
    static List<Double> list = Arrays.asList(2.0, 3.0, 5.0);

    // Method 2
    public static double toDo(List<Double> list, IAverage s) {
    return s.avera(list);
    }

    public static double sum(List<Double> doub) {
        for (int i = 0; i < doub.size(); i++) count += doub.get(i);
        return count/doub.size()/2;
    }

    public static void main(String[] args) {
        // Viết chương trình Java để triển khai biểu thức lambda nhằm tìm giá trị trung bình của một danh sách các số double.
        // Lambda method 1
        list.forEach(element -> {
            count = count + element;
        });
        System.out.println("Method 1, Average: " + count/list.size());

        //Lambda Method 2
        double result = toDo(list, BaiTapSang::sum);
        System.out.println("Method 2, Average: " + result);
    }
}
