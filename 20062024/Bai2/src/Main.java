import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /*
    Sort method 1
    */
    public static void sortList(String[] str) {
        List<String> list = new ArrayList<>(Arrays.asList(str));
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));
    }

    /*
    Sort method 2
     */
    public static void sortList2(ArrayList<String> arr) {
        Collections.sort(arr);
    }

    /*
    Sort method 3 bubbleSort
     */
    static void bubbleSort(String arr[], int n) {
        int i, j;
        String temp;
        boolean swap;
        for (i = 0; i < n - 1; i++) {
            swap = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j].charAt(0) > arr[j + 1].charAt(0)) {
                    // swap arr[j] và arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }

            // Nếu không có phần tử nào để hoán đổi
            // bên trong vòng lặp thì Break
            if (swap == false)
                break;
        }
    }

    public static void main(String[] args) {
        String[] str = new String[] {"Green", "Blue", "Black", "Yellow", "Orange"};

        System.out.println("*Method 1: ");
        System.out.print("Mang chua sap xep: " + Arrays.toString(str) + "\n");
        //Sort method 1
        System.out.print("Mang da sap xep: ");
        sortList(str);

        System.out.println("*Method 2: ");
        //Type array
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of your array element: ");
        int n = sc.nextInt();
        ArrayList<String> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            System.out.print("  Enter the element " + i + " :");
            arr.add(sc.next());
        }
        System.out.print("Mang chua sap xep: "+ arr.toString() + "\n");
        //Sort method 2
        sortList2(arr);
        System.out.print("Mang da sap xep: " + arr.toString() + "\n");

        //Sort method 3 bubbleSort
        System.out.println("*Method 3: ");
        String[] str1 = new String[] {"do", "cam", "vang", "luc", "lam", "cham", "tim"};
        int len = str1.length;
        System.out.print("Mang chua sap xep: " + Arrays.toString(str1) + "\n");
        bubbleSort(str1, len);
        System.out.print("Mang da sap xep: " + Arrays.toString(str1));
        }
    }