<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="product" scope="request" type="com.tom.entity.Product"/>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/blitzer/jquery-ui.css"/>
</head>

<%@include file="/frontend/layout/header.jsp" %>

<body class="d-flex flex-column vh-100">
<jsp:include page="/frontend/layout/navbar.jsp"/>

<div class="container flex-grow-1">
    <div class="row">
        <div class="col">
            <div class="mt-3">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/products?categoryId=${product.category.categoryId}">${product.category.name}</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
                    </ol>
                </nav>
            </div>

            <h3 class="liner mb-3">
                Chi tiết sản phẩm
            </h3>

            <div class="card mb-3">
                <div class="row g-0">
                    <div class="col-md-4 p-3">
                        <img src="data:image/jpg;base64, ${product.base64Image}" class="card-img-top" alt="${product.name}" style="height: 250px; width: 185px;">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body d-flex flex-column">
                            <h4 class="gradient-text">Name: ${product.name}</h4>
                            <p class="mb-3">Description: ${product.description}</p>

                            <div class="mt-auto">
                                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$" var="formattedPrice"/>
                                <h3 class="text-warning mb-3">Price: ${formattedPrice}</h3>
                                <a href="${contextPath}/products" class="btn btn-success w-100">Go back</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/frontend/layout/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>

<script type="text/javascript">
    initValidate()
</script>

</body>
</html>