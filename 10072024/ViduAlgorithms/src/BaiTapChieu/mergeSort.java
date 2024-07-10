package BaiTapChieu;

// Java program for Merge Sort
import java.io.*;

class MergeSort {

    static void merge(int arr[], int left, int m, int right) {
        // Find sizes of two part
        int n1 = m - left + 1;
        int n2 = right - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy left
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy right
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            // middle
            int m = left + (right - left) / 2;

            // Sort parts
            mergeSort(arr, left, m);
            mergeSort(arr, m + 1, right);

            // Merge the sorted part
            merge(arr, left, m, right);
        }
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String[] args) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };

        System.out.print("Original: ");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.print("Sorted: ");
        printArray(arr);
    }
}

