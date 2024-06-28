package vn.edu.likelion.model;

class Car extends Vehicle {
    int numberOfDoors;

    public Car(String name, int numberOfDoors) {
        super(name);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    void move() {
        System.out.println("Car "+ id + " is moving.");
    }
}
