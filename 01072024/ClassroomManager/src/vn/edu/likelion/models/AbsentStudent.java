package vn.edu.likelion.models;

import java.time.LocalDate;

public class AbsentStudent {
    private Student student;
    private int classRoom;
    private String reason;
    private LocalDate dateExit;

    public AbsentStudent(Student student, int classRoom, String reason, LocalDate dateExit) {
        this.student = student;
        this.classRoom = classRoom;
        this.reason = reason;
        this.dateExit = dateExit;
    }

    public Student getStudent() {
        return student;
    }

    public String getReason() {
        return reason;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public LocalDate getDateExit() {
        return dateExit;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public void setDateExit(LocalDate dateExit) {
        this.dateExit = dateExit;
    }
}