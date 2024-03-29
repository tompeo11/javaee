package com.tom.dao;

import com.tom.entity.Category;
import com.tom.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends JpaDAO<Product>{
    public ProductDAO() {
        super(Product.class);
    }

    @Override
    public Product create(Product product) {
        return super.create(product);
    }

    @Override
    public Product getById(Object id) {
        return super.getById(id);
    }

    @Override
    public Product update(Product product) {
        return super.update(product);
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return super.getAll();
    }

    @Override
    public long getTotalRecord() {
        return super.getTotalRecord();
    }


    public Product getByName(String name) {
        return super.findOneWithHQL("Product.HQL.getByName", "name", name);
    }

    public List<Product> getByCategory(Integer categoryId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categoryId", categoryId);

        return super.getByNamedQueryWithParams("Product.HQL.getByCategory", params);
    }
}
