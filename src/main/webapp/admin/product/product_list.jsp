<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container py-5">
    <h1 class="text-center mb-4">Product management</h1>
    <div class="d-flex align-items-center justify-content-center">
        <a href="${pageContext.request.contextPath}/admin/manage_product/new" class="me-4">New product</a>
    </div>
    <hr>
    <div class="d-flex align-items-center justify-content-center">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Index</th>
                <th>Image</th>
                <th>Name</th>
                <th>Category Name</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="product" items="${productList}" varStatus="iterationCount">
                <jsp:useBean id="product" type="com.tom.entity.Product"></jsp:useBean>
                <tr>
                    <td>${iterationCount.index + 1}</td>
                    <td>
                        <img src="data:image/jpg;base64, ${product.base64Image}" alt="" style="width: 45px; height: 45px;">
                    </td>
                    <td class="col-3">${product.name}</td>
                    <td>${product.category.name}</td>
                    <td>${product.price}</td>
                    <td class=" " style="width: 20%">
                        <div class="d-flex">
                            <a class="btn btn-primary me-3"
                               href="${pageContext.request.contextPath}/admin/manage_product/edit?productId=${product.productId}">Edit</a>
                            <button onclick="confirmDelete(${product.productId}, '${product.name}')"
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

<form id="deleteProductForm" action="${pageContext.request.contextPath}/admin/manage_product/delete" method="post">
    <input id="deleteProduct" type="hidden" name="productId">
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
    const deleteProductForm = document.getElementById('deleteProductForm');
    const deleteProduct = document.getElementById('deleteProduct')

    function confirmDelete(id, name) {
        deleteProduct.value = id;


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
                deleteProductForm.submit()
            }
        });
    }
</script>