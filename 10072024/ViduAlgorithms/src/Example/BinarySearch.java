package Example;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {9, 12, 23, 34, 49, 51, 60, 75, 84, 92};
        // x o vi tri nao?
        int x = 23;
        BinarySearch(arr, x);
    }

    public static int BinarySearch(int[] arr, int x) {
        int left = 0;
        int right = arr.length-1;
        while (left <= right) {
            int mid = (right+left)/2;

            if (arr[mid] == x) {
                return mid;
            }

            if (arr[mid] > x) {
                right = mid -1;
            }
            if (arr[mid] < x) {
                left = mid + 1;
            }
        }
        return x;
    }
}
