import vn.edu.likelion.model.Developer;
import vn.edu.likelion.model.Manager;
import vn.edu.likelion.model.NhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Manager manager;
    static Developer developer;
    static List<NhanVien> employee = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hiển thị thông tin các nhân viên: ");
        developer = new Developer(1, "Ho Vinh Tuong", "Developer", 5000, 20, 25);
        manager = new Manager(2, "Tran Anh Duong", "Manager", 6000);
        employee.add(manager);
        employee.add(developer);

        manager.displayInfor();
        developer.displayInfor();
    }
}