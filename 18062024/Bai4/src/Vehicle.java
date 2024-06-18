public class Vehicle {
    String name;
    String color;
    int year;
    String fuel;
    public Vehicle(String name, String color, int year, String fuel) {
        this.name = name;
        this.color = color;
        this.year = year;
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double performance() {
        return 0;
    }

    public static void main(String[] args) {

    }
}
