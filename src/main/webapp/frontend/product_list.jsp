<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="mt-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/products/categoryId=${category.categoryId}">${currentCategory.name}</a></li>
        </ol>
    </nav>
</div>
<h1>${currentCategory.name}</h1>
<div class="row row-cols-1 row-cols-md-4 g-4 mt-1">
    <c:forEach var="product" items="${productList}" varStatus="iterationCount">
        <jsp:useBean id="product" type="com.tom.entity.Product"/>
        <div class="col">
            <div class="card h-100">
                <img src="data:image/jpg;base64, ${product.base64Image}" class="card-img-top" style="height: 150px" alt="${product.name}">
                <div class="card-body d-flex flex-column">
                    <h5 class="title-ellipsis gradient-text">${product.name}</h5>
                    <p class="text-ellipsis mb-3">${product.description}</p>
                    <div class="mt-auto">
                        <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$" var="formattedPrice"/>
                        <p class="text-warning">${formattedPrice}</p>
<%--                        <a href="${contextPath}/ebook/products/detail?productId=${product.productId}" class="btn btn-primary w-100">Details</a>--%>
                        <button onclick="view(${product.productId}, '${product.name}', '${product.description}', '${product.price}', '${product.base64Image}', '${product.category.name}')"
                                class="btn btn-primary">
                            Details
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>



<script type="text/javascript">
    function view(id, name, description, price, base64Image, categoryName) {
        Swal.fire({
            title: "Details!",
            html: "Name: " + name + "<br>" +
                  "Category name: " + categoryName + "<br>" +
                  "Description: " + description + "<br>" +
                  "Price: $" + price,
            imageUrl: "data:image/jpg;base64, " + base64Image,
            imageWidth: 150,
            imageHeight: 250,
            imageAlt: "Custom image"
        });
    }
</script>