import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type your speed: ");
        int s = sc.nextInt();
        Car car = new Car(s);
        Bicycle bicycle = new Bicycle(s);
        if (s>0) {
            if (s >= 100) {
                car.speedUp();
            } else {
                bicycle.speedUp();
            }
        } else {
            System.out.println("Error value.");
        }
    }
}
