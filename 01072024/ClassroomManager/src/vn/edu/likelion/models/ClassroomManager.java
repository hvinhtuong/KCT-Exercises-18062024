package vn.edu.likelion.models;

import vn.edu.likelion.services.ClassroomManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassroomManager implements ClassroomManagement {
    private List<Classroom> classrooms = new ArrayList<>();

    @Override
    public void addClassroom(Classroom classroom) {
        classrooms.add(classroom);
    }

    @Override
    public Classroom getClassroomById(String classroomId) {
        return classrooms.stream()
                .filter(classroom -> classroom.getClassroomId() == Integer.parseInt(classroomId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classrooms;
    }

     /*
      * isClassroomFull - Check max student in class for start
      */
    public boolean isClassroomFull(Classroom classroom) {
        return classroom.getStudents().size() >= 4;
    }

     /*
      * startClassroom - Start a class
      */
    public void startClassroom(String classroomId) {
        Classroom classroom = getClassroomById(classroomId);
        if (classroom != null && !isClassroomFull(classroom)) {
            classroom.setStatus("Đang học");
            classroom.setStartDate(LocalDate.now());
            for (Student student : classroom.getStudents()) {
                student.setEnrollmentDate(LocalDate.now());
            }
            System.out.println("Lớp học " + classroomId + " đã bắt đầu.");
        } else {
            System.out.println("Lớp học chưa đủ học viên hoặc không tồn tại.");
        }
    }

    public void startClassroomIfFull(String classroomId) {
        Classroom classroom = getClassroomById(classroomId);
        if (classroom != null && classroom.isFull() && classroom.getStatus().equals("Chưa bắt đầu")) {
            classroom.setStatus("Đang học");
            classroom.setStartDate(LocalDate.now());
            for (Student student : classroom.getStudents()) {
                student.setEnrollmentDate(LocalDate.now());
            }
            System.out.println("*Chú ý: Lớp học " + classroomId + " đã đủ học viên và đã bắt đầu!!!");
        }
    }


}
