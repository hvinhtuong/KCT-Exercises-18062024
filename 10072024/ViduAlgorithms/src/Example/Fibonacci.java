package Example;

public class Fibonacci {
    public static void main(String[] args) {
        // TIm 10 so dau tien cua day fibonacci
        int num = 10;
        System.out.print("First 10 number of Fibonacci: ");
        for (int i = 0; i < num; i++) {
            System.out.print(fibonacci(i) + ", ");
        }

    }
    public static int fibonacci(int num) {
        // Neu n = 0 -> 0, n = 1 -> 1
        if (num == 0 || num == 1) return num;
        return fibonacci(num-1) + fibonacci(num-2);
        }
    }
