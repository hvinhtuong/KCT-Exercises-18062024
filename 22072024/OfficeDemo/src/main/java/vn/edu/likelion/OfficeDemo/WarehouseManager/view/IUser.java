package vn.edu.likelion.OfficeDemo.WarehouseManager.view;

import vn.edu.likelion.OfficeDemo.WarehouseManager.models.Users;

public interface IUser {
    void addUser(String name, String pass, int role);
    void removeUser(int id);
    void updateUser(String oldName, String newName);
    Users getUserByUsername(String username);
    boolean getEmptyUser();
    void getAllUser();
}
