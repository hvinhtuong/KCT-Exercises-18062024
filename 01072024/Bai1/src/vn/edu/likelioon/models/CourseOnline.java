package vn.edu.likelioon.models;

import vn.edu.likelioon.services.ICourseOnline;

import java.util.ArrayList;
import java.util.List;


public class CourseOnline extends Course{
    static String flatForm;
    static String period;
    static int count;
    static List<CourseOnline> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();

    public CourseOnline(int courseId, String courseName, String mentorName, int credit, String flatForm, String period) {
        super(courseId, courseName, mentorName, credit);
        this.flatForm = flatForm;
        this.period = period;
    }

    /*
     * totalStudent - get Course's total student
     */
    public int totalStudent() {
        return students.size();
    }


    public List<Student> getStudents() {
        return students;
    }

    public String getFlatForm() {
        return flatForm;
    }

    public String getPeriod() {
        return period;
    }
    public void setFlatForm(String flatForm) {
        this.flatForm = flatForm;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    /*
    public void displayDetailCourse(int id) {
        System.out.println("Detail Course " + id + ": ");
        System.out.println("STT | ID |   Course Name   |   Mentor Name   |  Credit  |   Flatform  |  Period  | Status ");
        System.out.println("---------------------------------------------------");
        for (CourseOnline c: courses) {
            if (courses != null) {
                count = 1;
                System.out.printf("%-4d| %-3d| %-15s | %-15s | %-10d| %-12s| %-10s\n",
                        count++, c.getCourseId(), c.getCourseName(), c.getMentorName(), c.getCredit(), c.getFlatForm(), c.getPeriod());
            }
        }
    }

    public void displayStudent(int id) {
        System.out.println("Studen in Course " + id + ": ");
        System.out.println("STT | ID | Student Name | Age |  Course");
        System.out.println("---------------------------------------------------");
        for (Student c: students) {
            if (students != null) {
                count = 1;
                if (c.getCourseId() == id) {
                    System.out.printf("%-3d| %-3d| %-15s | %-3d | %-15s| %-3d\n",
                            count++, c.getId(), c.getName(), c.getAge(), c.getCourseName());
                }
            }
        }
    }
    */

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
}
