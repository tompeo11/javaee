<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<%@include file="/admin/layout/header.jsp" %>

<body class="d-flex flex-column vh-100">
<jsp:include page="/admin/layout/navbar.jsp"/>


<div class="container flex-grow-1">
    <form id="loginForm" class="d-flex flex-column align-items-center mt-5 py-3 rounded bg-primary-subtle" method="post"
          action="${pageContext.request.contextPath}/admin/login">

<%--        <c:if test="${not empty sessionScope.error}">--%>
<%--            <div class="alert alert-danger" role="alert">--%>
<%--                    ${sessionScope.error}--%>
<%--                <%session.removeAttribute("error");%>--%>
<%--            </div>--%>
<%--        </c:if>--%>

        <h1>Admin login</h1>

        <div style="width: 350px;">
            <h2 class="text-center"></h2>
            <div class="form-floating mb-3">
                <input type="email" name="email" class="form-control" id="floatingInput" placeholder="email" value="sa@sa.com">
                <label for="floatingInput">Email address</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control" id="floatingPassword"
                       placeholder="password" value="sa">
                <label for="floatingPassword">Email address</label>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary btn-lg mt-4 w-50">Login</button>
            </div>
        </div>
    </form>
</div>

<%@include file="/admin/layout/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>

<script type="text/javascript">
    $('#loginForm').validate({
        rules: {
            email: {required: true, email: true},
            password: {required: true},
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass("is-invalid").removeClass("is-valid");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).addClass("is-valid").removeClass("is-invalid");
        },
        errorPlacement: function (error, element) {
            // error.insertAfter(element);
        }
    })
</script>

</body>
</html>