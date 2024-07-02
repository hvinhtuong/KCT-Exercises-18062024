package vn.edu.likelion.models;

import vn.edu.likelion.services.AbsentStudentManagement;

import java.util.ArrayList;
import java.util.List;

public class AbsentStudentManager implements AbsentStudentManagement {
    private List<AbsentStudent> absentStudents = new ArrayList<>();

     /*
      * addAbsentStudent
      */
    @Override
    public void addAbsentStudent(AbsentStudent absentStudent) {
        absentStudents.add(absentStudent);
    }

     /*
      * getAbsentStudents
      */
    @Override
    public List<AbsentStudent> getAbsentStudents() {
        return absentStudents;
    }

}
