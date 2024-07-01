package vn.edu.likelioon.models;

public class Student {
    int id;
    String name;
    int age;
    String courseName;
    int courseId;

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

    public int getAge() {
        return age;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
