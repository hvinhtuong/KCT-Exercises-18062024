package vn.edu.likelion.model;

import java.util.ArrayList;
import java.util.List;

public class Developer extends NhanVien{
    double overtime;
    double rate;
    private List<NhanVien> developer = new ArrayList<>();


    public Developer(int id, String name, String position, double baseSalary, double overtime, double rate) {
        super(id, name, position, baseSalary);
        this.overtime = overtime;
        this.rate = rate;
    }

    /*
     * countSalary - Count Developer Salary
     */
    @Override
    public double countSalary() {
    return baseSalary/10;
    }

    /*
     * displayInfor - Show Developer Information
     */
    @Override
    public void displayInfor() {
        System.out.println("Manager Name: " + name);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Overtime Hours: $" + overtime);
        System.out.println("Hourly Rate: $"+ rate);
        System.out.println("Total Salary: $" + (baseSalary+countSalary()));
        System.out.println("---------------------------");
    }
}
