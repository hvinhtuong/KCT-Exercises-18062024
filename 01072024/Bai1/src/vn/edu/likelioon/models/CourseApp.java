package vn.edu.likelioon.models;

import vn.edu.likelioon.services.ICourseOnline;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CourseApp implements ICourseOnline {
    static List<CourseOnline> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static CourseOnline courseOnline;
    static int courseIdCounter = 1;
    static int studentIdCounter = 1;
    static Scanner scanner = new Scanner(System.in);

    /*
     * displayDetailCourse - Display Course detail
     */
     public static void displayDetailCourse() {
        if (courses.isEmpty()) {
            System.out.println("Course is empty.");
            return;
        }
        System.out.println("STT | ID |   Course Name   |   Mentor Name   |  Credit  |   Flatform  |  Period  | Status ");
        System.out.println("---------------------------------------------------");
        for (CourseOnline c: courses) {
            System.out.printf("%-4d| %-3d| %-15s | %-15s | %-10d| %-12s| %-10s\n",
                    courseIdCounter++, c.getCourseId(), c.getCourseName(), c.getMentorName(), c.getCredit(), c.getFlatForm(), c.getPeriod());
        }
    }

    /*
     * displayStudent - Display all students
     */
    public static void displayStudent() {
        if (students.isEmpty()) {
            System.out.println("Student does not exist.");
            return;
        }
        System.out.println("STT | ID | Student Name | Age |  Course  | Course ID");
        System.out.println("---------------------------------------------------");
        for (Student c: students) {
            System.out.printf("%-3d| %-3d| %-15s | %-3d | %-15s| %-3d\n",
                    studentIdCounter++, c.getId(), c.getName(), c.getAge(), c.getCourseName(), c.getCourseId());
        }
    }

    public static void displayCoursebyId(int id) {
        System.out.println("Detail Course " + id + ": ");
        System.out.println("STT | ID |   Course Name   |   Mentor Name   |  Credit  |   Flatform  |  Period  | Status ");
        System.out.println("---------------------------------------------------");
        for (CourseOnline c: courses) {
            if (courses != null && c.courseId == id) {
                System.out.printf("%-4d| %-3d| %-15s | %-15s | %-10d| %-12s| %-10s\n",
                        courseIdCounter++, c.getCourseId(), c.getCourseName(), c.getMentorName(), c.getCredit(), c.getFlatForm(), c.getPeriod());
            }
        }
    }

    public static void displayStudentbyId(int id) {
        System.out.println("Studen in Course " + id + ": ");
        System.out.println("STT | ID | Student Name | Age |  Course");
        System.out.println("---------------------------------------------------");
        for (Student c: students) {
            if (students != null) {
                if (c.getCourseId() == id) {
                    System.out.printf("%-3d| %-3d| %-15s | %-3d | %-15s| %-3d\n",
                            studentIdCounter++, c.getId(), c.getName(), c.getAge(), c.getCourseName());
                }
            }
        }
    }

     /*
      * addData - Add default data
      */
    public static void addData() {
        CourseOnline cour = new CourseOnline(101, "Basic Java", "Tran Anh Duong",
                250, "Google meet", "6 months");
        CourseOnline cour1 = new CourseOnline(102, "Python Ultimate", "Chu Van Bien",
                550, "Zoom", "3 months");
        CourseOnline cour2 = new CourseOnline(103, "AWS Foudational", "Jonathan Bear",
                250, "Udemy.com", "6 months");
        courses.add((cour));
        courses.add((cour1));
        courses.add((cour2));

        Student student = new Student(1, "Tuong Ho", 21, "Basic Java", 101);
        Student student1 = new Student(2, "Anh Duong", 17, "AWS Foudational", 102);
        students.add(student);
        students.add(student1);
    }


    public static void main(String[] args) {
        addData();
        while (true) {
            System.out.print("\n================== L I K E L I O N =================");
            System.out.println("      \n======= ONLINE COURSE MANAGER ========");
            System.out.printf("%-30s %-30s\n", "1. List Courses", "6. Edit Course");
            System.out.printf("%-30s %-30s\n", "2. Course by Id", "7. Delete Course");
            System.out.printf("%-30s %-30s\n", "3. List all Students", "8. Add Student");
            System.out.printf("%-30s %-30s\n", "4. Get Student by Id", "9. Update Student");
            System.out.printf("%-30s %-30s\n", "5. Add Course", "10. Delete Student");
            System.out.println("                      0. Exit");
            System.out.print("Your chose: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid selection. Please chose again.");
                continue;
            }
            scanner.nextLine();
            int id;
            switch (choice) {
                case 1:// Show Course
                    displayDetailCourse();
                    break;
                case 2:// Show Course by Id
                    System.out.print("Enter course id: ");
                    id = scanner.nextInt();
                    displayCoursebyId(id);
                    break;
                case 3: // List all student
                    displayStudent();
                    break;
                case 4: // List student via course Id
                    addData();
                    System.out.print("Enter course id: ");
                    id = scanner.nextInt();
                    displayStudentbyId(id);
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
