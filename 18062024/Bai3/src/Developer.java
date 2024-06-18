public class Developer {
    String name;
    String address;
    String salary;
    String position;

    /*
    Name
     */
    public String name() {
        return name = "Iver Dipali";
    }

    /*
    Addredd
    */
    public String address() {
        return address = "2000 Street 1, Ontario, Canada";
    }

    /*
     Name
     */
    public String salary() {
        return salary = "$7200.0";
    }

    /*
    Position
    */
    public String position() {
        return position = "Developer";
    }

    /*
    Classification
    */
    public String classification() {
        return "Good";
    }

    /*
    What are they doing?
     */
    public void working() {
        System.out.println(position + " " + name + " is writing code in Java.");
    }
}
