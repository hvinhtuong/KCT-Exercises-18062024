package vn.edu.likelion.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private static int nextClassroomId = 1;
    private int classroomId;
    private List<Student> students;
    private List<Teacher> teachers;
    private String status;
    private LocalDate startDate;

    public Classroom(int classroomId, List<Student> students, List<Teacher> teachers, String status, LocalDate startDate) {
        this.classroomId = classroomId;
        this.students = students;
        this.teachers = teachers;
        this.status = status;
        this.startDate = startDate;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeacher() {
        return teachers;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Classroom(Teacher teacher1, Teacher teacher2) {
        this.classroomId = nextClassroomId++;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.teachers.add(teacher1);
        this.teachers.add(teacher2);
        this.status = "Chưa bắt đầu";
        this.startDate = null;
        teacher1.addTeachingClass(this);
        teacher2.addTeachingClass(this);
    }

    // Check maximum student in class
    public boolean isFull() {
        return (students.size() == 10);
    }

    // Add student
    public void addStudent(Student student) {
        if (!isFull()) {
            students.add(student);
        } else {
            throw new IllegalStateException("Lớp học đã đầy!");
        }
    }

    // Remove Student
    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setTeacher(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
