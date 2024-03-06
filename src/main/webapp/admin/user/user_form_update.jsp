<%@include file="/admin/layout/header.jsp"%>
</head>

<body>
<%@include file="/admin/layout/navbar.jsp"%>



<div class="container py-5">
    <h1 class="text-center mb-4">Update user</h1>
    <hr class="mx-auto" style="width: 50%">
    <div class="d-flex flex-column align-items-center py-5">
        <form id="userForm" style="width: 350px"; action="${pageContext.request.contextPath}/admin/manage_user/update" method="post">

            <jsp:useBean id="user" scope="request" type="com.tom.entity.User"></jsp:useBean>

            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                        ${sessionScope.error}
                    <%session.removeAttribute("error");%>
                </div>
            </c:if>

            <input type="hidden" name="userId" value="${user.userId}">
            <div class="form-floating mb-3">
                <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email" value="${user.email}">
                <label for="inputEmail">Email address</label>
                <div class="invalid-feedback">Email is required</div>
            </div>

            <div class="form-floating mb-3">
                <input type="text" name="fullName" class="form-control" id="inputFullName" placeholder="FullName" value="${user.fullName}">
                <label for="inputFullName">Full Name</label>
                <div class="invalid-feedback">Fullname is required</div>
            </div>

            <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
                <label for="inputPassword">Password</label>
                <div class="invalid-feedback">Password is required</div>
            </div>

            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width: 30%">Save</button>
                <a href="${pageContext.request.contextPath}/admin/manage_user" class="btn btn-primary btn-lg mt-4" style="width: 30%">Cancel</a>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/admin/layout/footer.jsp"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>

<script type="text/javascript">
    $(function() {
        $('#userForm').validate({
            rules: {
                email: {required: true, email: true},
                fullName: {required: true, minlength: 2},
                password: {required: true, minlength: 2}
            },
            highlight: function (element, errorClass, validClass){
                $(element).addClass("is-invalid").removeClass("is-valid");
            },
            unhighlight: function (element, errorClass, validClass){
                $(element).addClass("is-valid").removeClass("is-invalid");
            },
            errorPlacement: function (error, element){
                // error.insertAfter(element);
            }
        })
        console.log(123);
    });
</script>
</body>

</html>