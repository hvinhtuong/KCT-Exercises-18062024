package vn.edu.likelion.OfficeDemo.CV;

import java.util.ArrayList;
import java.util.List;

public class Information {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private List<String> skills;
    private List<String> experiences;
    private List<String> educations;
    private List<String> certificates;

    // Constructor
    public Information() {
        skills = new ArrayList<>();
        experiences = new ArrayList<>();
        educations = new ArrayList<>();
        certificates = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getExperiences() {
        return experiences;
    }

    public List<String> getEducations() {
        return educations;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    // Không cần setters cho các List vì chúng đã được khởi tạo và có thể thêm phần tử trực tiếp
}
