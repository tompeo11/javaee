package com.tom.service;

import com.tom.dao.ProductDAO;
import com.tom.entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public List<Product> getAllProducts(){
        return productDAO.getAll();
    }

    public String createProduct(Product product){
        Product existProduct = this.productDAO.getByName(product.getName());

        if (existProduct != null){
            return "The product name is already exists";
        }

        productDAO.create(product);
        return null;
    }

    public String updateProduct(Product product){
        Product existProduct = this.productDAO.getByName(product.getName());

        if (existProduct != null && existProduct.getProductId() != product.getProductId()) {
            return "The product name already exist";
        }

        productDAO.update(product);
        return null;
    }

    public Product getById(int id){
        return productDAO.getById(id);
    }

    public Product getByName(String name){
        return productDAO.getByName(name);
    }

    public List<Product> getByCategory(Integer id){
        return productDAO.getByCategory(id);
    }


    public void deleteById(int id){
        productDAO.deleteById(id);
    }
}
