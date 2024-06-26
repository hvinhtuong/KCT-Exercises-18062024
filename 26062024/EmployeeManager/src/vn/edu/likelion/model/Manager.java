package vn.edu.likelion.model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends NhanVien{
    private List<NhanVien> managers = new ArrayList<>();

    public Manager(int id, String name, String position, double baseSalary) {
        super(id, name, position, baseSalary);
    }

    /*
      * countSalary - Count Manager Salary
      */
    @Override
    public double countSalary() {
        return baseSalary/6;
    }

    /*
     * displayInfor - Display Manager Information
     */
    @Override
    public void displayInfor() {
        System.out.println("---------------------------");
        System.out.println("Manager Name: " + name);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Salary Bonus: $" + countSalary());
        System.out.println("Total Salary: $" + (baseSalary+countSalary()));
        System.out.println("---------------------------");
    }


}
