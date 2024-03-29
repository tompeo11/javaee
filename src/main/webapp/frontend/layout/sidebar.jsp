<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="d-flex flex-column flex-shrink-0 p-3 float-start h-100" style="width: 280px;">
    <h3>Category</h3>
    <ul class="nav nav-pills flex-column mb-auto">
        <a href="http://localhost:8080/ebook/products" class="list-group-item list-group-item-action
                      <c:if test="${currentCategory.categoryId == null}">bg-primary-subtle</c:if>">
            <img src="<c:url value="/images/products.jfif"/>" alt="all" class="me-2" style="width: 50px; height: 50px;">
            All
        </a>
        <c:forEach var="category" items="${categoryList}" varStatus="status">
            <jsp:useBean id="category" type="com.tom.entity.Category"></jsp:useBean>
            <li class="nav-item mb-3
                      <c:if test="${category.categoryId == currentCategory.categoryId}">bg-primary-subtle</c:if>">
                <a href="products?categoryId=${category.categoryId}" class="nav-link link-dark d-flex align-items-center" aria-current="page">
                    <c:choose>
                        <c:when test="${fn:toLowerCase(category.name) == 'led'}">
                            <img src="<c:url value="/images/led.jfif"/>" alt="led" class="me-2" style="width: 50px; height: 50px;">
                        </c:when>
                        <c:when test="${fn:toLowerCase(category.name) == 'pin'}">
                            <img src="<c:url value="/images/pin.jfif"/>" alt="pin" class="me-2" style="width: 50px; height: 50px;">
                        </c:when>
                        <c:when test="${fn:toLowerCase(category.name) == 'lcd'}">
                            <img src="<c:url value="/images/lcd.jfif"/>" alt="lcd" class="me-2" style="width: 50px; height: 50px;">
                        </c:when>
                        <c:when test="${fn:toLowerCase(category.name) == 'relay'}">
                            <img src="<c:url value="/images/relay.jfif"/>" alt="relay" class="me-2" style="width: 50px; height: 50px;">
                        </c:when>
                        <c:otherwise>
                            <img src="<c:url value="/images/book.svg"/>" alt="other">
                        </c:otherwise>
                    </c:choose>

                    <c:out value="${category.name}"/>
                    
                </a>
            </li>
            
            <c:if test="${status.last}">
                <hr>
            </c:if>
            
        </c:forEach>
    </ul>
</div>