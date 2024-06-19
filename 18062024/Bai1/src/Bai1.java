import java.util.Scanner;


public class Bai1 {
    static int s;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Type your speed (must be negative): ");
            s = sc.nextInt();
        } while (s < 0);
        Car car = new Car(s);
        Bicycle bicycle = new Bicycle(s);
            if (s >= 100) {
                car.speedUp();
            } else {
                bicycle.speedUp();
            }
        }
    }
