package BaiTapChieu;

import java.util.Arrays;

public class Bai1 {

    //Bubble sort
    public static int[] sortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] arr = {6, 0, 3, 5};
        System.out.print("Original: " + Arrays.toString(arr) + "\n");
        System.out.print("Sorted: " +         Arrays.toString(sortArray(arr)));
    }
}
