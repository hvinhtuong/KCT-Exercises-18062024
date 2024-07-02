package vn.edu.likelion.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private String name;
    private LocalDate birthDate;
    private String idNumber;
    private String studentId;
    private LocalDate enrollmentDate;
    private Classroom classroom;

    public Student(String name, LocalDate birthDate, String idNumber, String studentId, LocalDate enrollmentDate, Classroom classroom) {
        this.name = name;
        this.birthDate = birthDate;
        this.idNumber = idNumber;
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
        this.classroom = classroom;
    }

    public Student(String name, LocalDate birthDate, String idNumber, Classroom classroom) {
        this.name = name;
        this.birthDate = birthDate;
        this.idNumber = idNumber;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Mã học viên: " + studentId + ", Tên: " + name + ", Ngày sinh: " + birthDate.format(formatter) + ", CCCD: " + idNumber;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}