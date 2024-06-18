public class Car extends Vehicle {
    double fuelEffi;
    double distance;
    double speed;
    public Car(String name, String color, int year, String fuel, double fuelEffi, double distance, double speed) {
        super(name, color, year, fuel);
        this.fuelEffi = fuelEffi;
        this.distance = distance;
        this.speed = speed;
    }

    public void model() {
        System.out.println("Car model: " + this.name);
    }

    public void fuelEffi() {
        System.out.println("Fuel Efficiency: " + this.fuelEffi + " mpg");
    }

    public void distance() {
        System.out.println("Distance Traveled: " + this.distance + " miles");
    }

    public void maxSpeed() {
        System.out.println("Max speed: " + this.speed + " mph");
    }

    public double performance() {
        return this.fuelEffi/this.distance;
    }
}
