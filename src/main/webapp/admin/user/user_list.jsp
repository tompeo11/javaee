    <%@include file="../layout/header.jsp"%>
</head>

<body>
    <%@include file="../layout/navbar.jsp"%>

    <div class="container py-5">
        <h1 class="text-center mb-4">User management</h1>
        <div class="d-flex align-items-center justify-content-center">
            <a href="${pageContext.request.contextPath}/admin/manage_user/new" class="me-4">New user</a>
        </div>
        <hr>
        <div class="d-flex align-items-center justify-content-center">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Full name</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="user" items="${userList}" varStatus="iterationCount">
                        <jsp:useBean id="user" type="com.tom.entity.User"></jsp:useBean>
                        <tr>
                            <td>${iterationCount.index + 1}</td>
                            <td>${user.userId}</td>
                            <td>${user.email}</td>
                            <td>${user.fullName}</td>
                            <td class=" " style="width: 20%">
                                <div class="d-flex">
                                    <a class="btn btn-secondary me-3" href="${pageContext.request.contextPath}/admin/manage_user/edit/${user.userId}">Edit</a>
                                    <form action="${pageContext.request.contextPath}/admin/manage_user/delete" method="post">
                                        <input type="hidden" name="userId" value="${user.userId}">
                                        <button type="submit" class="btn btn-primary">Delete</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <c:if test="${not empty sessionScope.success}">
        <script type="text/javascript">
            Swal.fire({
                title: 'Success!',
                text: '${sessionScope.success}',
                icon: 'success',
                confirmButtonText: 'Cool'
            })
        </script>
        <%session.removeAttribute("success");%>
    </c:if>
</body>

    </html>