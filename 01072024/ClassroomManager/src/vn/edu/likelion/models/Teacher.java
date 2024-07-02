package vn.edu.likelion.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private static int nextTeacherId = 1;
    private String teacherId;
    private String name;
    private List<Classroom> teachingClasses; // List class of Teacher

    // Constructor
    public Teacher(String name) {
        this.teacherId = "GV" + nextTeacherId++;
        this.name = name;
        this.teachingClasses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<Classroom> getTeachingClasses() {
        return teachingClasses;
    }

    public void addTeachingClass(Classroom classroom) {
        teachingClasses.add(classroom);
    }

    @Override
    public String toString() {
        return "Mã giáo viên: " + teacherId + ", Tên: " + name;
    }
}
