public class Nhanvien {
    String name;
    String address;
    String salary;
    String position;
    /*
    Name
    */
    public String name() {
        return "";
    }

    /*
    Addredd
    */
    public String address() {
        return "";
    }

    /*
     Name
     */
    public String salary() {
        return "";
    }

    /*
    Position
    */
    public String position() {
        return "";
    }

    /*
    What are they doing?
    */
    public void working() {
    }


    public static void main(String[] args) {
    Manager manager = new Manager();
    Developer developer = new Developer();
    Programmer programmer = new Programmer();
    System.out.println("Manager's bonus is: " + manager.salary());
    System.out.println("Developer's bonus is: " + developer.salary());
    System.out.println("Programmer's bonus is: " + programmer.salary());
    System.out.println("Performance report for " + manager.name() + " : " + manager.classification());
    System.out.println("Performance report for " + developer.name() + " : " + developer.classification());
    System.out.println("Performance report for " + programmer.name() + " : " + programmer.classification());
    manager.working();
    developer.working();
    programmer.working();
    }
}
