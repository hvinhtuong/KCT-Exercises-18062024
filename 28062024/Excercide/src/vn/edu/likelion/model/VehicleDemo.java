package vn.edu.likelion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleDemo {
    static Car car;
    static Bike bike;
    static Scanner sc = new Scanner(System.in);
    static List<Car> cars = new ArrayList<>();
    static List<Bike> bikes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Chose kind of Vehicle want to Add: ");
        System.out.println("1. Car       2. Bike");
        int opt = sc.nextInt();
        if (opt == 1) {
            cars.add(new Car("Lambor", 1));
            cars.add(new Car("Aston Martin", 2));
            for (Car c: cars) {
                System.out.println(c.id + " - " + c.name);
            }
            car.move();
        } else {
            bikes.add(new Bike("Martin", 1, true));
            bikes.add(new Bike("MOUNTAIN", 2, true));
            for (Bike c: bikes) {
                System.out.println(c.id + " - " + c.name);
            }
            bike.move();
        }
    }
}
