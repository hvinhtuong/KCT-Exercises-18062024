package vn.edu.likelion.model;

public class Bike extends Vehicle{
    boolean hasGear;

    protected Bike(String name, int id, boolean hasGear) {
        super(name, id);
        this.hasGear = hasGear;
    }

    @Override
    void move() {
        System.out.println("Bike is moving...");
    }

    public boolean isHasGear() {
        return hasGear;
    }

    public void setHasGear(boolean hasGear) {
        this.hasGear = hasGear;
    }
}
