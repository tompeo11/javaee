package com.tom.controller.frontend;

import com.tom.entity.Product;
import com.tom.service.CategoryService;
import com.tom.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products/*")

public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(){
        super();
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        if (action.startsWith("/detail")) {
            detailProduct(request, response);
        } else {
            listProduct(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        List<Product> productList;

        if (categoryId == null ) {
            productList = productService.getAllProducts();
        }else {
            productList = productService.getByCategory(Integer.valueOf(categoryId));
            request.setAttribute("currentCategory", categoryService.getById(Integer.parseInt(categoryId)));
        }

        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/product_list.jsp");
        dispatcher.forward(request, response);
    }

    private void detailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = productService.getById(productId);
        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/product_detail.jsp");
        dispatcher.forward(request, response);
    }

}
