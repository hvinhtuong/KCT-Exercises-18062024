package vn.edu.likelion.services;

import vn.edu.likelion.models.Student;

import java.util.List;

public interface StudentManagement {
    void addStudent(Student student);           // Register Student to class
    void removeStudent(String studentId);       // Delete absent student
    void updateStudent(Student student);        // Edit Student information
    Student getStudentById(String studentId);   // Get Student via Id
    List<String> getAllStudents();
}
