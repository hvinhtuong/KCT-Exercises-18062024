package vn.edu.likelion.services;

import vn.edu.likelion.models.Classroom;

import java.util.List;

public interface ClassroomManagement {
    void addClassroom(Classroom classroom);
    Classroom getClassroomById(String classroomId);
    List<Classroom> getAllClassrooms();
}