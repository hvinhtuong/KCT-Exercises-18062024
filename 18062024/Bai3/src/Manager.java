public class Manager {
    String name;
    String address;
    String salary;
    String position;

    /*
    Name
     */
    public String name() {
    return name = "Avril Aroldo";
    }

    /*
    Addredd
    */
    public String address() {
        return address = "1584 Avenue, Ontario, Canada";
    }

    /*
     Name
     */
    public String salary() {
       return salary = "$12000.0";
    }

    /*
    Position
    */
    public String position() {
        return position = "Manager";
    }

    /*
    Classification
     */
    public String classification() {
        return "Excellent";
    }

    /*
    What are they doing?
    */
    public void working() {
        System.out.println(position + " " + name + " is managing a project.");
    }

}
