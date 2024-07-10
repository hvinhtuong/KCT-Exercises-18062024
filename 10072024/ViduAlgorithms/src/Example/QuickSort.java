package Example;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {19, 17, 15, 12, 16, 18, 4, 11, 13};

        int left = 0;
        int right = arr.length - 1;

        System.out.println("Original: " + Arrays.toString(arr));
        quickSort(arr, left, right);
        System.out.println("Sorted: " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pa = partition(arr, left, right);

            //de qui
            quickSort(arr, left, pa -1);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        //Kiem tra xem neu phan tu nho hon chot pivot
        //Hoan vi arr[i] va arr[pv]
        int pivot = arr[right];
        int pv = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                int temp = arr[pv];
                arr[pv] = arr[i];
                arr[i] = temp;
            }
        }
        //Dua chot vao giua mang
        int temp = arr[pv+1];
        arr[pv+1] = arr[right];
        arr[right] = temp;
        return pv+1;

    }
}
