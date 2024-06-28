package vn.edu.likelion.model;

public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Car("Lamborghini", 4);
        vehicles[1] = new Bike("Martin 2007", true);
        vehicles[2] = new Car("McLarens 707", 2);

        System.out.println("List vehicle: ");
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i].id + " - " + vehicles[i].name);
        }

        for (Vehicle c : vehicles) {
            c.move();
        }

        System.out.println("Total vehicles: " + Vehicle.vehicleCount);
    }
}
