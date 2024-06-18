import java.util.Scanner;

public class Nhanvien {
    static int salary;
    public Nhanvien() {

    }

    /*
    Employee working as
     */
    public void work() {
    System.out.println("working as an employee!");
    }

    /*
    Employee salary
     */
    public int getSalary(int vitri) {
        if (vitri == 1) {
            return 40000;
        } else {
            return 70000;
        }
    }

    public static void main(String[] args) {
        Nhanvien nv = new Nhanvien();
        HRmanager hr = new HRmanager();
        nv.work();
        System.out.println("Employee salary: " + nv.getSalary(1));
        System.out.println("");
        hr.work();
        System.out.println("Manager salary: " + nv.getSalary(2));
        System.out.println("");
        hr.addEmployee();
    }
}
