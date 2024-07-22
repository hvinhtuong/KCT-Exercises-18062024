package vn.edu.likelion.OfficeDemo.Resume;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class ResumeManager {
    public static void main(String[] args) {
        try {
            Resume resume = new Resume();
            getResumeInformation(resume);

            PdfGenerator generator = new PdfGenerator();
            generator.addHeader(resume);
            generator.addSummary(resume);
            generator.addSkills(resume);
            generator.addExperience(resume);
            generator.addEducation(resume);
            generator.addCertificates(resume);
            generator.save("output.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getResumeInformation(Resume resume) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your resume information:");

        System.out.print("Name: ");
        resume.setName(scanner.nextLine());

        System.out.print("Email: ");
        resume.setEmail(scanner.nextLine());

        System.out.print("Phone: ");
        resume.setPhone(scanner.nextLine());

        System.out.print("Address: ");
        resume.setAddress(scanner.nextLine());

        System.out.print("Summary: ");
        resume.setSummary(scanner.nextLine());

        getMultipleLinesInput(scanner, resume.getSkills(), "Skills");
        getMultipleLinesInput(scanner, resume.getExperiences(), "Experience");
        getMultipleLinesInput(scanner, resume.getEducations(), "Education");
        getMultipleLinesInput(scanner, resume.getCertificates(), "Certificates");
    }

    private static void getMultipleLinesInput(Scanner scanner, List<String> list, String sectionName) {
        System.out.println(sectionName + " (enter each item on a new line, type 'done' when finished):");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            list.add(input);
        }
    }
}
