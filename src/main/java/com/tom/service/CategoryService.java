package com.tom.service;

import com.tom.dao.CategoryDAO;
import com.tom.entity.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories(){
        return categoryDAO.getAll();
    }

    public String createCategory(Category category){
        Category existCategory = this.categoryDAO.getByName(category.getName());

        if (existCategory != null){
            return "The category name is already exists";
        }

        categoryDAO.create(category);
        return null;
    }

    public String updateCategory(Category category){
        Category existCategory = this.categoryDAO.getByName(category.getName());

        if (existCategory != null && existCategory.getCategoryId() != category.getCategoryId()) {
            return "The category name already exist";
        }

        categoryDAO.update(category);
        return null;
    }

    public Category getById(int id){
        return categoryDAO.getById(id);
    }

    public Category getByName(String name){
        return categoryDAO.getByName(name);
    }

    public void deleteById(int id){
        categoryDAO.deleteById(id);
    }
}
