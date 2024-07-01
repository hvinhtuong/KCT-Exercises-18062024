package vn.edu.likelioon.models;

public class Student {
    private int id;
    private String name;
    private int age;
    private String courseName;
    private int courseId;

    public Student(int id, String name, int age, String courseName, int courseId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courseName = courseName;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getCourseId() {
        return courseId;
    }
}
