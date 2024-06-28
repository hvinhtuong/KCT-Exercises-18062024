package vn.edu.likelion.model;

public class Car extends Vehicle{
    int numberOfDoors;

    public Car(String name, int id) {
        super(name, id);
    }

    @Override
    void move() {
        System.out.println("Car is moving...");
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
