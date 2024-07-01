package vn.edu.likelioon.models;

public class Course {
    private int courseId;
    private String courseName;
    private String mentorName;
    private int credit;

    public Course(int courseId, String courseName, String mentorName, int credit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.mentorName = mentorName;
        this.credit = credit;
    }

    public int getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getMentorName() {
        return mentorName;
    }
    public int getCredit() {
        return credit;
    }
}
