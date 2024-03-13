<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container py-5">
    <h1 class="text-center mb-4">Category management</h1>
    <div class="d-flex align-items-center justify-content-center">
        <a href="${pageContext.request.contextPath}/admin/manage_category/new" class="me-4">New category</a>
    </div>
    <hr>
    <div class="d-flex align-items-center justify-content-center">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Index</th>
                <th>Id</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="category" items="${categoryList}" varStatus="iterationCount">
                <jsp:useBean id="category" type="com.tom.entity.Category"></jsp:useBean>
                <tr>
                    <td>${iterationCount.index + 1}</td>
                    <td>${category.categoryId}</td>
                    <td>${category.name}</td>
                    <td class=" " style="width: 20%">
                        <div class="d-flex">
                            <a class="btn btn-primary me-3"
                               href="${pageContext.request.contextPath}/admin/manage_category/edit?categoryId=${category.categoryId}">Edit</a>
                            <button onclick="confirmDelete(${category.categoryId}, '${category.name}')"
                                    class="btn btn-danger">
                                Delete
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<form id="deleteCategoryForm" action="${pageContext.request.contextPath}/admin/manage_category/delete" method="post">
    <input id="deleteCategory" type="hidden" name="categoryId">
</form>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<c:if test="${not empty sessionScope.success}">
    <script type="text/javascript">
        Swal.fire({
            title: 'Success!',
            text: '${sessionScope.success}',
            icon: 'success',
            confirmButtonText: 'Close'
        })
    </script>
    <%session.removeAttribute("success");%>
</c:if>

<script type="text/javascript">
    const deleteCategoryForm = document.getElementById('deleteCategoryForm');
    const deleteCategory = document.getElementById('deleteCategory')

    function confirmDelete(id, name) {
        deleteCategory.value = id;


        Swal.fire({
            title: "Are you sure delete " + name,
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {
                deleteCategoryForm.submit()
            }
        });
    }
</script>