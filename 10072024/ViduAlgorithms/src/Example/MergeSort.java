package Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if (right > left) {
            int mid = arr.length/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);

            merge(arr, mid);
        }
    }

    public static int[] merge(int[] arr, int mid) {
        int n1 = mid+1;
        int n2 = mid;
        int[] trai = new int[mid+1];
        int[] phai = new int[mid];
        int[] sorted = new int[arr.length];

        for (int i = 0; i <= n1; i++) {
                trai[i] = arr[i];
        }
        for (int j = 0; j <= n2; j++) {
            phai[j] = phai[j];
        }

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (arr[i] > arr[j]) {
                sorted[k] = arr[j];
                j++;
            } else {
                sorted[k] = arr[i];
                i++;
            }
            k++;
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] arr = {9, 1, 5, 6, 3, 4, 2, 8, 7};
        int left = 0;
        int right = arr.length - 1;

        System.out.println("Original: " + Arrays.toString(arr));
        mergeSort(arr, left, right);
        System.out.println("Sorted: " + Arrays.toString(arr));
    }

}
