package vn.edu.likelion.OfficeDemo.CV;

import vn.edu.likelion.OfficeDemo.CV.Information;

import java.util.Scanner;
import java.util.List;

public class Manager {
    public static void main(String[] args) {

    }

    private static void inputInformation(Information infor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your resume information:");

        System.out.print("Name: ");
        infor.setName(scanner.nextLine());

        System.out.print("Email: ");
        infor.setEmail(scanner.nextLine());

        System.out.print("Phone: ");
        infor.setPhone(scanner.nextLine());

        System.out.print("Address: ");
        infor.setAddress(scanner.nextLine());

        System.out.print("Summary: ");
        infor.setSummary(scanner.nextLine());

    }

    private static void getMultipleLinesInput(Scanner scanner, List<String> list, String sectionName) {
        System.out.println(sectionName + " (enter each line, type 'next' when finished):");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            list.add(input);
        }
    }
}
