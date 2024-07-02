package vn.edu.likelion.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private static int nextClassroomId = 1;
    private int classroomId;
    private List<Student> students;
    private Teacher teacher;
    private String status;
    private LocalDate startDate;

    public Classroom(int classroomId, List<Student> students, Teacher teacher, String status, LocalDate startDate) {
        this.classroomId = classroomId;
        this.students = students;
        this.teacher = teacher;
        this.status = status;
        this.startDate = startDate;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Classroom(Teacher teacher) {
        this(Classroom.nextClassroomId++, new ArrayList<>(), teacher, "Chưa bắt đầu", null);
    }

    public boolean isFull() {
        return (students.size() == 1);
    }

    public void addStudent(Student student) {
        if (!isFull()) {
            students.add(student);
        } else {
            throw new IllegalStateException("Lớp học đã đầy!");
        }
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}