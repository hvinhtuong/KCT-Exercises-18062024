public class Main {
    public static void main(String[] args) {
        Truck truck = new Truck("Tatra 810 4x4", "Red", 1890, "Eron 95", 8.075659532105526, 65.50975012444003, 80);
        Car car = new Car("Virtus", "Green", 1990, "Hidro", 2.335, 14.419665, 120);
        Bicycle bicycle = new Bicycle("Martin 2000", "Silver", 2001, "Manual", 1, 50, 50);
        Motobike motobike = new Motobike("Warrior200", "Red & White", 1985, "E5", 2.1, 4.41, 80);

        truck.model();
        truck.fuelEffi();
        truck.distance();
        truck.maxSpeed();
        System.out.println("Truck performance: " + truck.performance() + " miles/mpg");
        System.out.println("");

        car.model();
        car.fuelEffi();
        car.distance();
        car.maxSpeed();
        System.out.println("Car performance: " + car.performance() + " miles/mpg");
        System.out.println("");

        bicycle.model();
        bicycle.fuelEffi();
        bicycle.distance();
        bicycle.maxSpeed();
        System.out.println("Bicycle performance: " + bicycle.performance() + " miles/mpg");
        System.out.println("");

        motobike.model();
        motobike.fuelEffi();
        motobike.distance();
        motobike.maxSpeed();
        System.out.println("Motor performance: " + motobike.performance() + " miles/mpg");
        System.out.println("");
    }
}
