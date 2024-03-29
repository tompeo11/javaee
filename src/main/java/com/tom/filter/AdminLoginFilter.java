package com.tom.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminLoginFilter extends HttpFilter implements Filter {
    public AdminLoginFilter() {
        super();
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        boolean isLoggedIn = session != null && session.getAttribute("email") != null;

        String loginURI = request.getContextPath() + "/admin/login";

        boolean isLoginRequest = request.getRequestURI().equals(loginURI);

        if (isLoggedIn || isLoginRequest) {
            chain.doFilter(req, res);
        }else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
