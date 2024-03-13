<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="d-flex flex-column flex-shrink-0 p-3 float-start h-100" style="width: 280px;">
    <ul class="nav nav-pills flex-column mb-auto">
        <c:forEach var="category" items="${categoryList}" varStatus="status">
            <a href="product?categoryId=${category.categoryId}" class="nav-link link-dark d-flex align-items-center" aria-current="page"></a>
        </c:forEach>
    </ul>
</div>