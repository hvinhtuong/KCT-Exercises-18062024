package vn.edu.likelion.model;

class Bike extends Vehicle {
    boolean hasGear;

    public Bike(String name, boolean hasGear) {
        super(name);
        this.hasGear = hasGear;
    }

    @Override
    void move() {
        System.out.println("Bike "+ id + " is moving.");
    }
}
