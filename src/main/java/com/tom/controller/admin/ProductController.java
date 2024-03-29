package com.tom.controller.admin;

import com.tom.entity.Category;
import com.tom.entity.Product;
import com.tom.service.CategoryService;
import com.tom.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet("/admin/manage_product/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100)

public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;
    private CategoryService categoryService;


    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        if (action.startsWith("/new")) {
            addProduct(request, response);
        } else if (action.startsWith("/edit")) {
            Integer id = Integer.valueOf(request.getParameter("productId"));
            editProduct(request, response, id);
        } else {
            listProduct(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "/list" : request.getPathInfo();

        switch (action){
            case "/insert":
                createProduct(request, response);
                break;
            case "/update":
                updateProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            default:
                listProduct(request, response);
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        List<Product> productList = productService.getAllProducts();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_list.jsp");
        dispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        request.setAttribute("product", product);
        request.setAttribute("mode", "create");

        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_form.jsp");
        dispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        ProductService productService = new ProductService();
        Product product = productService.getById(id);
        request.setAttribute("product", product);
        request.setAttribute("mode", "update");

        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_form.jsp");
        dispatcher.forward(request, response);
    }

    private void createProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(String.valueOf(request.getParameter("price")));

        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = this.categoryService.getById(categoryId);

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);

        Part filePart = request.getPart("image");

        if (filePart != null && filePart.getSize() > 0) {
            byte[] imageBytes = new byte[(int)filePart.getSize()];
            InputStream inputStream = filePart.getInputStream();
            inputStream.read(imageBytes);
            inputStream.close();

            product.setImage(imageBytes);
        }else {
            InputStream inputStream = getServletContext().getResourceAsStream("/images/img_default.png");
            byte[] imageBytes = new byte[inputStream.available()];
            inputStream.read(imageBytes);
            product.setImage(imageBytes);
            inputStream.close();
        }

        if (productService.getByName(name) != null){
            request.getSession().setAttribute("error","Name is exists");
            response.sendRedirect(request.getContextPath() + "/admin/manage_product/new");
            return;
        }

        request.getSession().setAttribute("success","Created success");
        productService.createProduct(product);

        response.sendRedirect("manage_product");;
    }

    private void updateProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("productId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(String.valueOf(request.getParameter("price")));

        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = this.categoryService.getById(categoryId);

        Product updateProduct = productService.getById(id);
        updateProduct.setName(name);
        updateProduct.setCategory(category);
        updateProduct.setDescription(description);
        updateProduct.setPrice(price);

        Part filePart = request.getPart("image");

        if (filePart != null && filePart.getSize() > 0) {
            byte[] imageBytes = new byte[(int)filePart.getSize()];
            InputStream inputStream = filePart.getInputStream();
            inputStream.read(imageBytes);
            inputStream.close();

            updateProduct.setImage(imageBytes);
        }

        if (productService.getByName(name) != null && productService.getByName(name).getProductId() != id){
            request.getSession().setAttribute("error","Name is exists");
            response.sendRedirect(request.getContextPath() + "/admin/manage_product/edit?productId=" + id);
            return;
        }

        request.getSession().setAttribute("success","Updated success");
        productService.updateProduct(updateProduct);

        response.sendRedirect("manage_product");
    }

    private void deleteProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.valueOf(request.getParameter("productId"));

        request.getSession().setAttribute("success","Deleted success");
        productService.deleteById(id);

        response.sendRedirect("manage_product");
    }
}
