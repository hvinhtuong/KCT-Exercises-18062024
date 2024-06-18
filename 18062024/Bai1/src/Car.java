/*
Sub-class of Vehicle
 */
public class Car extends Vehicle {

    public Car(int speed) {
        super(speed);
    }

    /*
        Configuration speedUp() for Car
         */
    @Override
    public void speedUp() {
        System.out.println("Car speed is " + this.speed + "Km/h");
    }
}
