package vn.edu.likelioon.models;

public class Course {
    int courseId;
    String courseName;
    String mentorName;
    int credit;

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

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

}
