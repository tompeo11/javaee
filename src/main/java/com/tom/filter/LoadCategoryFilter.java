package com.tom.filter;

import com.tom.entity.Category;
import com.tom.service.CategoryService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebFilter("/*")
public class LoadCategoryFilter extends HttpFilter implements Filter {
    private CategoryService categoryService;
    public LoadCategoryFilter() {
        super();
        categoryService = new CategoryService();
    }


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        List<Category> categoryList = categoryService.getAllCategories();

        request.setAttribute("categoryList", categoryList);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
