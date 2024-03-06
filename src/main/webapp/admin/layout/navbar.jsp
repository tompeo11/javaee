<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <a href="<c:url value="/admin"/>">
            <img src="<c:url value="/images/logo.jpg"/>" width="" alt="logo image">
        </a>

        <div>
            <a class="text-decoration-none" style="font-weight: bold; font-size: larger" href="${pageContext.request.contextPath}/">Home</a>
            <a class="text-decoration-none" style="font-weight: bold; font-size: larger" href="${pageContext.request.contextPath}/admin/">Admin</a>
        </div>

        <div>
            Welcome, Admin
            <span class="mx-3"> | </span>

            <input type="button" value="Logout">
        </div>
    </div>

    <div class="d-flex align-items-center justify-content-center">
        <a href="${pageContext.request.contextPath}/admin/manage_user">User</a>
        <span class="mx-3"> | </span>
        <a href="#">Categories</a>
        <span class="mx-3"> | </span>
        <a href="#">Books</a>
        <span class="mx-3"> | </span>
        <a href="#">Customers</a>
        <span class="mx-3"> | </span>
        <a href="#">Reviews</a>
        <span class="mx-3"> | </span>
        <a href="#">Orders</a>
    </div>
</div>