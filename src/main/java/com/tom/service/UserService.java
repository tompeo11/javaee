package com.tom.service;

import com.tom.dao.UserDAO;
import com.tom.entity.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    public List<User> getAllUsers(){
        return userDAO.getAll();
    }

    public String createUser(User user){
        if (userDAO.getByEmail(user.getEmail().trim()) != null){
            return "Email is exists";
        }

        userDAO.create(user);
        return null;
    }

    public void updateUser(User user){
        userDAO.update(user);
    }

    public User getById(int id){
        return userDAO.getById(id);
    }

    public User getByEmail(String email){
        return userDAO.getByEmail(email);
    }

    public void deleteById(int id){
        userDAO.deleteById(id);
    }

    public String checkLogin(String email, String password) {
        boolean success = userDAO.checkLogin(email, password);

        if (success) {
            return null;
        }

        return "Login fail";
    }

}
