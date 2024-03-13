package com.tom.dao;

import com.tom.entity.Category;

import java.util.List;

public class CategoryDAO extends JpaDAO<Category>{
    public CategoryDAO() {
        super(Category.class);
    }

    @Override
    public Category create(Category category) {
        return super.create(category);
    }

    @Override
    public Category getById(Object id) {
        return super.getById(id);
    }

    @Override
    public Category update(Category category) {
        return super.update(category);
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
    }

    @Override
    public List<Category> getAll() {
        return super.getAll();
    }

    @Override
    public long getTotalRecord() {
        return super.getTotalRecord();
    }


    public Category getByName(String name) {
        return super.findOneWithHQL("Category.HQL.getByName", "name", name);
    }
}
