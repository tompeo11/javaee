<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="d-flex align-items-center justify-content-between">
<%--        <img src="<%= request.getContextPath() %>/images/gura.jpg" alt="logo image" style="">--%>
    <a href="<c:url value="/"/>">
        <img src="<c:url value="/images/logo.jpg"/>" width="" alt="logo image">
    </a>
<%--        <img src="${pageContext.request.contextPath}/images/logo.jpg" width="" alt="logo image">--%>


        <div>
<%--            <a class="text-decoration-none" style="font-weight: bold; font-size: larger"--%>
<%--               href="${pageContext.request.contextPath}/">Home</a>--%>
            <a class="text-decoration-none" style="font-weight: bold; font-size: larger"
               href="${pageContext.request.contextPath}/admin/">Admin</a>
        </div>


        <div class="d-flex justify-content-end vw-50">
<%--            <div class="position-relative">--%>
<%--                <input class="form-control me-3" name="keyword" type="text" size="50" style="padding-left: 50px"--%>
<%--                       placeholder="Search...">--%>
<%--                <i class="fa-solid fa-magnifying-glass position-absolute" style="top: 25%; left: 12px"></i>--%>
<%--            </div>--%>

<%--            <button class="btn btn-primary me-5">Search</button>--%>

            <a href="#" class="text-decoration-none text-dark">
                <i class="fa-solid fa-cart-shopping fa-2x me-3"></i>
            </a>

            <input class="btn btn-outline-secondary me-3" type="button" value="Login">
            <input class="btn btn-outline-secondary me-3" type="button" value="Sign up">
        </div>
    </div>
</div>