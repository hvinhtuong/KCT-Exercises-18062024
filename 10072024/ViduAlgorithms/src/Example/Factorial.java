package Example;

public class Factorial {

    public static void main(String[] args) {
        int num = 5;
        // Tin giai thua cua 5
        factorial(num);
        System.out.println("Giai thua cua 5: " + factorial(num));
    }

    public static int factorial(int num) {
        // Neu n = 0 or n = 1 -> 1
        if (num == 0 || num == 1) return 1;
        // De qui factorial n-1
        return num*factorial(num-1);
    }
}
