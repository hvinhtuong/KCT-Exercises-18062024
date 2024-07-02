import vn.edu.likelion.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static Scanner scanner = new Scanner(System.in);
    static ClassroomManager classroomManager = new ClassroomManager();
    static StudentManager studentManager = new StudentManager(classroomManager);
    static List<Teacher> teachers = new ArrayList<>();
    static AbsentStudentManager absentStudentManager = new AbsentStudentManager();

    public static void main(String[] args) {

        // Add Teacher
        teachers.add(new Teacher("Chu Văn Biên"));
        teachers.add(new Teacher("Trần Sỹ Hùng"));
        teachers.add(new Teacher("Cao Bá Quát"));

        // Add Classroom
        Classroom classroom1 = new Classroom(teachers.get(0), teachers.get(1));
        Classroom classroom2 = new Classroom(teachers.get(1), teachers.get(2));
        Classroom classroom3 = new Classroom(teachers.get(0), teachers.get(2));
        classroomManager.addClassroom(classroom1);
        classroomManager.addClassroom(classroom2);
        classroomManager.addClassroom(classroom3);

        int count = 1;
        int choice;
        do {
            System.out.println("\n                 ===== QUẢN LÝ LỚP HỌC =====");
            System.out.printf("%-30s %-30s\n", "1. Đăng ký học viên", "5. Danh sách lớp học hiện có");
            System.out.printf("%-30s %-30s\n", "2. Sửa thông tin học viên", "6. Bắt đầu một lớp học");
            System.out.printf("%-30s %-30s\n", "3. Xem học viên từng lớp", "7. Thêm học viên bỏ học");
            System.out.printf("%-30s %-30s\n", "4. Xem tất cả học viên", "8. Danh sách học viên bỏ học");
            System.out.printf("%-30s %-30s\n", "", "9. Danh sách học viên từng giáo viên");
            System.out.println("                        0. Thoát ");
            System.out.print("Chọn chức năng: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: //Register student
                        System.out.print("Chọn lớp học (1 hoặc 2 hoặc 3): ");

                        int classroomChoice;
                        try {
                            classroomChoice = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                            scanner.nextLine();
                            break;
                        }
                        scanner.nextLine();

                        Classroom selectedClassroom = null;
                        if (classroomChoice == 1) {
                            selectedClassroom = classroom1;
                        } else if (classroomChoice == 2) {
                            selectedClassroom = classroom2;
                        } else if (classroomChoice == 3) {
                            selectedClassroom = classroom3;
                        }

                        if (selectedClassroom == null) {
                            System.out.println("Lựa chọn không hợp lệ.");
                            break;
                        }
                        // Add student
                        if (!selectedClassroom.isFull()) {
                            System.out.print("Nhập tên học viên: ");
                            String name = scanner.nextLine();
                            LocalDate birthDate = null;
                            boolean validBirthDate = false;

                            while (!validBirthDate) {
                                System.out.print("Nhập ngày sinh (dd-MM-yyyy): ");
                                String birthDateStr = scanner.nextLine();
                                try {
                                    birthDate = LocalDate.parse(birthDateStr, formatter);
                                    if (studentManager.isValidAge(birthDate)) {
                                        validBirthDate = true;
                                    } else {
                                        System.out.println("Tuổi học viên phải từ 18 đến 20. Vui lòng nhập lại.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng dd-MM-yyyy.");
                                }
                            }

                            String idNumber;
                            while (true) {
                                System.out.print("Nhập CCCD: ");
                                idNumber = scanner.nextLine();
                                if (idNumber.matches("\\d+")) {
                                    break;
                                } else {
                                    System.out.println("CCCD không hợp lệ. Vui lòng nhập lại (chỉ chứa số).");
                                }
                            }
                            if (validBirthDate) {
                                Student student = new Student(name, birthDate, idNumber, selectedClassroom);
                                try {
                                    studentManager.addStudent(student);
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Không thể thêm học viên do ngày sinh không hợp lệ.");
                            }
                        } else {
                            System.out.println("Lớp học đã đầy!");
                        }
                        break;

                    case 2: //Update student infor
                        System.out.print("Nhập mã học viên cần sửa: ");
                        String studentIdToUpdate = scanner.nextLine();
                        Student studentToUpdate = studentManager.getStudentById(studentIdToUpdate);
                        if (studentToUpdate != null) {
                            System.out.print("Nhập tên mới (hoặc Enter để bỏ qua): ");
                            String newName = scanner.nextLine();
                            if (!newName.isEmpty()) {
                                studentToUpdate.setName(newName);
                            }

                            System.out.print("Nhập ngày sinh mới (dd-MM-yyyy) (hoặc Enter để bỏ qua): ");
                            String newBirthDateStr = scanner.nextLine();
                            if (!newBirthDateStr.isEmpty()) {
                                LocalDate newBirthDate = LocalDate.parse(newBirthDateStr, formatter);
                                studentToUpdate.setBirthDate(newBirthDate);
                            }

                            System.out.print("Nhập CCCD mới (hoặc Enter để bỏ qua): ");
                            String newIdNumber = scanner.nextLine();
                            if (!newIdNumber.isEmpty()) {
                                studentToUpdate.setIdNumber(newIdNumber);
                            }

                            try {
                                studentManager.updateStudent(studentToUpdate);
                                System.out.println("Cập nhật thông tin học viên thành công.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Không tìm thấy học viên.");
                        }
                        break;
                    case 3: //Get student by class
                        count = 1;
                        System.out.print("Nhập mã lớp học: ");
                        String classroomIdToView = scanner.nextLine();
                        Classroom classroomToView = classroomManager.getClassroomById(classroomIdToView);
                        if (classroomToView != null) {
                            System.out.println("Danh sách học viên lớp " + classroomIdToView + ":");
                            List<Student> studentsInClass = classroomToView.getStudents();
                            if (studentsInClass.isEmpty()) {
                                System.out.println("Lớp học chưa có học viên.");
                            } else {
                                for (Student student : studentsInClass) {
                                    System.out.println(count++ + " - " + student);
                                }
                            }
                        } else {
                            System.out.println("Không tìm thấy lớp học.");
                        }
                        break;
                    case 4: // List all student
                        List<String> studentInfo = studentManager.getAllStudents();
                        if (studentInfo.isEmpty()) {
                            System.out.println("Chưa có học viên nào.");
                        } else {
                            System.out.println("Danh sách tất cả học viên:");
                            count = 1;
                            for (String info : studentInfo) {
                                System.out.println("STT: " + count++ + " - "+ info);
                            }
                        }
                        break;
                    case 5: //List class
                        List<Classroom> allClassrooms = classroomManager.getAllClassrooms();
                        if (allClassrooms.isEmpty()) {
                            System.out.println("Chưa có lớp học nào.");
                        } else {
                            System.out.println("Danh sách lớp học hiện có:");
                            for (Classroom classroom : allClassrooms) {
                                int soLuongHocVien = classroom.getStudents().size();
                                // Check date null
                                String startDateInfo = (classroom.getStartDate() != null && soLuongHocVien != 0)
                                        ? classroom.getStartDate().format(formatter)
                                        : "Chưa có";
                                // Check no student no start class
                                String endIfEmpty = soLuongHocVien == 0 ? "Chưa bắt đầu" : classroom.getStatus();
                                System.out.println(" - Lớp: " + classroom.getClassroomId() +
                                        ", Giáo viên 1 : " + classroom.getTeacher().getFirst().getName() +
                                        ", Giáo viên 2 : " + classroom.getTeacher().getLast().getName() +
                                        ", Trạng thái: " + endIfEmpty +
                                        ", Số lượng học viên: " + soLuongHocVien + " / 10" +
                                        ", Ngày bắt đầu: " + startDateInfo);
                            }
                        }
                        break;
                    case 6: // Start a class manual
                        System.out.print("Nhập mã lớp học cần bắt đầu: ");
                        String classroomIdToStart = scanner.nextLine();
                        classroomManager.startClassroom(classroomIdToStart);
                        break;
                    case 7: // Add absent student
                        System.out.print("Nhập mã học viên bỏ học: ");
                        String studentId = scanner.nextLine();
                        Student studentAbsent = studentManager.getStudentById(studentId);
                        if (studentAbsent != null) {
                            System.out.print("Nhập lý do bỏ học: ");
                            String reason = scanner.nextLine();
                            LocalDate dateExit = LocalDate.now();

                            // Remove absent student from class
                            for (Classroom classroom : classroomManager.getAllClassrooms()) {
                                if (classroom.getStudents().contains(studentAbsent)) {
                                    AbsentStudent absentStudent = new AbsentStudent(studentAbsent, classroom.getClassroomId(), reason, dateExit);
                                    absentStudentManager.addAbsentStudent(absentStudent);
                                    classroom.removeStudent(studentAbsent);
                                    break;
                                }
                            }
                            studentManager.removeStudent(studentId);
                            System.out.println("Đã thêm học viên " + studentAbsent.getName() + " - "
                                    + studentAbsent.getStudentId() + " vào danh sách bỏ học.");
                        } else {
                            System.out.println("Không tìm thấy học viên.");
                        }
                        break;
                    case 8: // Show list Absent Student
                        count = 1;
                        List<AbsentStudent> absentStudents = absentStudentManager.getAbsentStudents();
                        if (absentStudents.isEmpty()) {
                            System.out.println("Chưa có học viên bỏ học.");
                        } else {
                            System.out.println("Danh sách học viên bỏ học:");
                            for (AbsentStudent absentStudent : absentStudents) {
                                System.out.println(count++ + "- Tên: " + absentStudent.getStudent().getName()
                                        + " - Mã học viên: " + absentStudent.getStudent().getStudentId()
                                        + " - Mã lớp: " + absentStudent.getClassRoom()
                                        + " - Lý do: " + absentStudent.getReason()
                                        + " - Ngày bỏ học: " + absentStudent.getDateExit().format(formatter));
                            }
                        }
                        break;
                    case 9: // Students of each Teacher
                        for (Teacher teacher : teachers) {
                            System.out.println("\nDanh sách học viên của giáo viên: " + teacher.getName() + ": ");
                            List<Student> studentsOfTeacher = new ArrayList<>();
                            for (Classroom classroom : teacher.getTeachingClasses()) {
                                studentsOfTeacher.addAll(classroom.getStudents());
                            }
                            if (studentsOfTeacher.isEmpty()) {
                                System.out.println("Chưa có học viên nào.");
                            } else {
                                for (Student student : studentsOfTeacher) {
                                    System.out.println("- " + student);
                                }
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Thoát chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                scanner.nextLine();
            }
        } while (true);
    }
}
