package vn.edu.likelion.model;

public abstract class Vehicle {
    String name;
    static int vehicleCount;
    final int id;

    public Vehicle(String name, int id) {
        this.name = name;
        this.id = id;
        vehicleCount++;
    }

    abstract void move();



    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
