package com.tom.controller.admin;

import com.tom.entity.User;
import com.tom.service.UserService;
import com.tom.utilities.CryptoUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/admin/manage_user/*")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UserController(){
        super();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        if (action.startsWith("/new")) {
            addUser(request, response);
            return;
        } else if (action.startsWith("/edit")) {
            Integer id = Integer.valueOf(request.getParameter("userId"));
            editUser(request, response, id);    
        } else {
            listUser(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        switch (action){
            case "/insert":
                createUser(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            default:
                listUser(request, response);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> userList = userService.getAllUsers();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_list.jsp");
        dispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.setAttribute("user", user);
        request.setAttribute("mode", "create");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_form.jsp");
        dispatcher.forward(request, response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        UserService userService = new UserService();
        User user = userService.getById(id);
        request.setAttribute("user", user);
        request.setAttribute("mode", "update");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_form.jsp");

        dispatcher.forward(request, response);
    }

    private void createUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        User user = new User(email, fullName, CryptoUtil.hashPassword(password, getServletContext().getInitParameter("salt")));

        String message = userService.createUser(user);

        if (message != null) {
            request.getSession().setAttribute("error",message);
        }

        request.getSession().setAttribute("success","Add user success");

        response.sendRedirect("manage_user");
    }

    private void updateUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("userId"));
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        User user = new User(email, fullName, CryptoUtil.hashPassword(password, getServletContext().getInitParameter("salt")));
        user.setUserId(id);

        if (userService.getByEmail(email) != null && userService.getByEmail(email).getUserId() != id){
            request.getSession().setAttribute("error","Email da ton tai");
            response.sendRedirect(request.getContextPath() + "/admin/manage_user/edit?userId=" + id);
            return;
        }

        request.getSession().setAttribute("success","update thanh cong");
        userService.updateUser(user);

        response.sendRedirect("manage_user");
    }

    private void deleteUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("userId"));


        request.getSession().setAttribute("success","delete thanh cong");
        userService.deleteById(id);

        response.sendRedirect("manage_user");
    }
}
