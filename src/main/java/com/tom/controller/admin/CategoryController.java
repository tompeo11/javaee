package com.tom.controller.admin;

import com.tom.entity.Category;
import com.tom.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage_category/*")
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;

    public CategoryController(){
        super();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        if (action.startsWith("/new")) {
            addCategory(request, response);
            return;
        } else if (action.startsWith("/edit")) {
            Integer id = Integer.valueOf(request.getParameter("categoryId"));
            editCategory(request, response, id);
        } else {
            listCategory(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        switch (action){
            case "/insert":
                createCategory(request, response);
                break;
            case "/update":
                updateCategory(request, response);
                break;
            case "/delete":
                deleteCategory(request, response);
                break;
            default:
                listCategory(request, response);
        }
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/category_list.jsp");
        dispatcher.forward(request, response);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        request.setAttribute("category", category);
        request.setAttribute("mode", "create");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/category_form.jsp");
        dispatcher.forward(request, response);
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        Category category = categoryService.getById(id);
        request.setAttribute("category", category);
        request.setAttribute("mode", "update");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/category_form.jsp");

        dispatcher.forward(request, response);
    }

    private void createCategory (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");

        Category category = new Category(name);

        if (categoryService.getByName(name) != null){
            request.getSession().setAttribute("error","Name is exists");
            response.sendRedirect(request.getContextPath() + "/admin/manage_category/new");
            return;
        }

        request.getSession().setAttribute("success","Created success");
        categoryService.createCategory(category);

        response.sendRedirect("manage_category");;
    }

    private void updateCategory (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("categoryId"));
        String name = request.getParameter("name");

        Category category = new Category(name);
        category.setCategoryId(id);

        if (categoryService.getByName(name) != null && categoryService.getByName(name).getCategoryId() != id){
            request.getSession().setAttribute("error","Email is exists");
            response.sendRedirect(request.getContextPath() + "/admin/manage_category/edit?categoryId=" + id);
            return;
        }

        request.getSession().setAttribute("success","Updated success");
        categoryService.updateCategory(category);

        response.sendRedirect("manage_category");
    }

    private void deleteCategory (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("categoryId"));

        request.getSession().setAttribute("success","Deleted success");
        categoryService.deleteById(id);

        response.sendRedirect("manage_category");
    }
}
