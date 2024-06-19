/*
Sub-class of Vehicle
 */
public class Bicycle extends Vehicle {

    public Bicycle(int speed) {
        super(speed);
    }

    /*
    Configuration speedUp() for Bicycle
    */
    @Override
    public void speedUp() {
        System.out.println("Bicycle speed is " + this.speed + "Km/h");
    }
}
