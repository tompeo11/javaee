<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="currentPage" value="${pageContext.request.servletPath}"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mt-3">
    <h4 class="gradient-text"><i class="fa-regular fa-code fa-2xs"></i> Management</h4>
</div>

<div class="list-group mt-3">
    <a href="${contextPath}/admin" class="list-group-item list-group-item-action ${currentPage eq '/admin' ? 'active' : ''}">Dashboard</a>
    <a href="${contextPath}/admin/manage_user/" class="list-group-item list-group-item-action ${currentPage eq '/admin/users' ? 'active' : ''}">Users</a>
    <a href="${contextPath}/admin/manage_category/" class="list-group-item list-group-item-action ${currentPage eq '/categories/' ? 'active' : ''}">Categories</a>
    <a href="${contextPath}/admin/manage_product/" class="list-group-item list-group-item-action ${currentPage eq '/products/' ? 'active' : ''}">Products</a>
</div>


<div class="mt-3">
    <h4>Quick actions</h4>
</div>

<div class="list-group mt-3">
    <a href="${contextPath}/admin/manage_user/new" class="list-group-item list-group-item-action">New User</a>
    <a href="${contextPath}/admin/manage_category/new" class="list-group-item list-group-item-action">New Category</a>
    <a href="${contextPath}/admin/manage_product/new" class="list-group-item list-group-item-action">New Product</a>
</div>