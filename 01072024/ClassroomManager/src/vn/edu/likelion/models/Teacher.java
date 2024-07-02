package vn.edu.likelion.models;

public class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Tên: " + name;
    }
}
