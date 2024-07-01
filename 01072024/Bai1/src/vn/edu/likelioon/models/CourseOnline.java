package vn.edu.likelioon.models;

public class CourseOnline extends Course{
    private String platform;
    private String period;

    public CourseOnline(int courseId, String courseName, String mentorName, int credit, String platform, String period) {
        super(courseId, courseName, mentorName, credit);
        this.platform = platform;
        this.period = period;
    }

    public String getPlatform() {
        return platform;
    }
    public String getPeriod() {
        return period;
    }
}
