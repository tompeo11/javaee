package com.tom.controller.frontend;

import com.tom.entity.Category;
import com.tom.entity.Product;
import com.tom.service.CategoryService;
import com.tom.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;
    private ProductService productService;

    public HomeServlet(){
        super();
        categoryService = new CategoryService();
        productService = new ProductService();

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        List<Product> productList = productService.getAllProducts();

        request.setAttribute("productList", productList);



            RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/index.jsp");
        dispatcher.forward(request, response);
    }

}