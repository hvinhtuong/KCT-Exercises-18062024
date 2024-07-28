package vn.edu.likelion.OfficeDemo.WarehouseManager.models;

public class Users {
    private int id;
    private String username;
    private String password;
    private int role;
    private int status;

    public Users(int id, String username, String password, int role, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Users(int id, int role, int status) {
        this.id = id;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public int getRole() {
        return role;
    }
    public int getStatus() {
        return status;
    }
}

