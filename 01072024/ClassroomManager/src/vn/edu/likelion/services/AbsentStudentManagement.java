package vn.edu.likelion.services;

import vn.edu.likelion.models.AbsentStudent;

import java.util.List;

public interface AbsentStudentManagement {
    void addAbsentStudent(AbsentStudent absentStudent);
    List<AbsentStudent> getAbsentStudents();
}