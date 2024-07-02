package vn.edu.likelion.models;

import vn.edu.likelion.services.StudentManagement;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentManager implements StudentManagement {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ClassroomManager classroomManager;
    private List<Student> students = new ArrayList<>();

    public StudentManager(ClassroomManager classroomManager) {
        this.classroomManager = classroomManager;
    }

    @Override
    public void addStudent(Student student) {
        if (isValidAge(student.getBirthDate()) && !isDuplicateIdNumber(student.getIdNumber())) {
            student.setStudentId(generateStudentId());
            students.add(student);

            Classroom classroom = student.getClassroom();
            if (classroom != null) {
                classroom.addStudent(student);
            }
            System.out.println("Đăng ký thành công. Mã học viên: " + student.getStudentId());

            // Check full then begin
            if (classroom != null && classroom.isFull()) {
                classroomManager.startClassroomIfFull(String.valueOf(classroom.getClassroomId()));
            }

        } else {
            System.out.println("Đăng ký thất bại. Kiểm tra lại tuổi và CCCD.");
        }
    }

    /*
     * removeStudent - Remove student from class
     */
    @Override
    public void removeStudent(String studentId) {
        for (Student c: students) {
            if (c.getStudentId().equals(studentId)) {
                students.remove(c);
                return;
            }
        }
        System.out.println("Không tìm thấy học viên có id: " + studentId);
    }

     /*
      * updateStudent - Update Student information
      */
    @Override
    public void updateStudent(Student updatedStudent) {
        Student existingStudent = getStudentById(updatedStudent.getStudentId());
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setBirthDate(updatedStudent.getBirthDate());
            existingStudent.setIdNumber(updatedStudent.getIdNumber());
        } else {
            throw new IllegalArgumentException("Học viên không tồn tại!");
        }
    }

     /*
      * getStudentById - Get Student via Id
      */
    @Override
    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    /*
     * getAllStudents - Get all students
     */
    @Override
    public List<String> getAllStudents() {
        List<String> studentInfo = new ArrayList<>();
        for (Student student : students) {
            Classroom classroom = student.getClassroom();
            String startDateInfo = (classroom != null && classroom.getStartDate() != null)
                    ? classroom.getStartDate().format(formatter) : "Chưa có";
            studentInfo.add(student + ", Lớp: " + (classroom != null ? classroom.getClassroomId() : "Chưa có lớp") +
                    ", Ngày bắt đầu: " + startDateInfo);
        }
        return studentInfo;
    }


    /*
     * isValidAge - Check age from 18-20
     */
    public boolean isValidAge(LocalDate birthDate) {
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears() >= 18 && period.getYears() <= 20;
    }

     /*
      * isDuplicateIdNumber - Check duplicate CCCD
      */
    private boolean isDuplicateIdNumber(String idNumber) {
        for (Student student : students) {
            if (student.getIdNumber().equals(idNumber)) {
                return true;
            }
        }
        return false;
    }

     /*
      * generateStudentId - Auto Generate Student ID
      */
    private String generateStudentId() {
        return "HV" + (students.size() + 1);
    }
}

