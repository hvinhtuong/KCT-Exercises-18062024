package vn.edu.likelioon.models;

import vn.edu.likelioon.services.ICourseOnline;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CourseApp implements ICourseOnline {
    private List<CourseOnline> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int courseIdCounter = 101;
    private int studentIdCounter = 1;

    public CourseApp() {
        addData();
    }

     /*
      * addData - Default data
      */
    private void addData() {
        courses.add(new CourseOnline(101, "Basic Java", "Tran Anh Duong", 250, "Google Meet", "6 months"));
        courses.add(new CourseOnline(102, "Python Ultimate", "Chu Van Bien", 550, "Zoom", "3 months"));
        courses.add(new CourseOnline(103, "AWS Foundational", "Jonathan Bear", 250, "Udemy.com", "6 months"));
        students.add(new Student(1, "Tuong Ho", 21, "Basic Java", 101));
        students.add(new Student(2, "Anh Duong", 17, "AWS Foundational", 102));
    }

     /*
      * displayDetailCourse - Course detail
      */
    @Override
    public void displayDetailCourse() {
        if (courses.isEmpty()) {
            System.out.println("Course is empty.");
            return;
        }
        System.out.println("STT | ID |   Course Name   |   Mentor Name   |  Credit  |   Platform  |  Period  ");
        System.out.println("---------------------------------------------------");
        for (CourseOnline c : courses) {
            System.out.printf("%-4d| %-3d| %-15s | %-15s | %-10d| %-12s| %-10s\n",
                    courseIdCounter++, c.getCourseId(), c.getCourseName(), c.getMentorName(), c.getCredit(), c.getPlatform(), c.getPeriod());
        }
    }

     /*
      * displayStudent - Student detail
      */
    @Override
    public void displayStudent() {
        if (students.isEmpty()) {
            System.out.println("Student does not exist.");
            return;
        }
        System.out.println("STT | ID | Student Name | Age |  Course  | Course ID");
        System.out.println("---------------------------------------------------");
        for (Student c : students) {
            System.out.printf("%-3d| %-3d| %-15s | %-3d | %-15s| %-3d\n",
                    studentIdCounter++, c.getId(), c.getName(), c.getAge(), c.getCourseName(), c.getCourseId());
        }
    }

     /*
      * displayCoursebyId - display Course via Id
      */
    public void displayCoursebyId(int id) {
        System.out.println("Detail Course " + id + ": ");
        System.out.println("STT | ID |   Course Name   |   Mentor Name   |  Credit  |   Platform  |  Period  ");
        System.out.println("---------------------------------------------------");
        for (CourseOnline c : courses) {
            if (c.getCourseId() == id) {
                System.out.printf("%-4d| %-3d| %-15s | %-15s | %-10d| %-12s| %-10s\n",
                        1, c.getCourseId(), c.getCourseName(), c.getMentorName(), c.getCredit(), c.getPlatform(), c.getPeriod());
                return;
            }
        }
        System.out.println("Course not found.");
    }

     /*
      * displayStudentbyId - display student via Id
      */
    public void displayStudentbyId(int id) {
        System.out.println("Students in Course " + id + ": ");
        System.out.println("STT | ID | Student Name | Age |  Course");
        System.out.println("---------------------------------------------------");
        boolean found = false; // Check found student in course
        for (Student c : students) {
            if (c.getCourseId() == id) {
                System.out.printf("%-3d| %-3d| %-15s | %-3d | %-15s\n",
                        studentIdCounter++, c.getId(), c.getName(), c.getAge(), c.getCourseName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found in this course.");
        }
    }

    public static void main(String[] args) {
        CourseApp app = new CourseApp();

        while (true) {
            System.out.print("\n================== L I K E L I O N =================");
            System.out.println("      \n======= ONLINE COURSE MANAGER ========");
            System.out.printf("%-30s %-30s\n", "1. List Courses", "");
            System.out.printf("%-30s %-30s\n", "2. Course by Id", "");
            System.out.printf("%-30s %-30s\n", "3. List all Students", "");
            System.out.printf("%-30s %-30s\n", "4. Get Student by Id", "");
            System.out.println("                      0. Exit");
            System.out.print("Your chose: ");

            int choice;
            try {
                choice = app.scanner.nextInt();
            } catch (InputMismatchException e) {
                app.scanner.nextLine();
                System.out.println("Invalid selection. Please chose again.");
                continue;
            }
            app.scanner.nextLine();
            int id;
            switch (choice) {
                case 1: // Show Course
                    app.displayDetailCourse();
                    break;
                case 2: // Show Course by Id
                    System.out.print("Enter course id: ");
                    id = app.scanner.nextInt();
                    app.scanner.nextLine();
                    app.displayCoursebyId(id);
                    break;
                case 3: // List all student
                    app.displayStudent();
                    break;
                case 4: // List student via course Id
                    System.out.print("Enter course id: ");
                    id = app.scanner.nextInt();
                    app.scanner.nextLine();
                    app.displayStudentbyId(id);
                    break;
                case 0:
                    System.out.println("Exit Application. See you soon!");
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
}
