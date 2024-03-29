<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="container py-5">
    <h1 class="text-center mb-4">
        <c:if test="${mode == 'create'}">
            Create new product
            <c:set var="action" value="${pageContext.request.contextPath}/admin/manage_product/insert"></c:set>
        </c:if>
        <c:if test="${mode == 'update'}">
            Update product
            <c:set var="action" value="${pageContext.request.contextPath}/admin/manage_product/update"></c:set>
        </c:if>
    </h1>
    <hr class="mx-auto" style="width: 50%">
    <div class="d-flex flex-column align-items-center py-5">
        <form id="productForm" style="width: 350px" ; action="${action}" method="post" enctype="multipart/form-data">

            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                        ${sessionScope.error}
                    <%session.removeAttribute("error");%>
                </div>
            </c:if>

            <input type="hidden" name="productId" value="${product.productId}">

            <div class="form-floating mb-3">
                <select name="category" class="form-select" id="selectCategory" aria-label="select category">
                    <option value="" selected>Select a category</option>
                    <c:forEach items="${categoryList}" var="category">
                        <jsp:useBean id="category" type="com.tom.entity.Category"></jsp:useBean>
                        <option value="${category.categoryId}">${category.name}</option>
                    </c:forEach>
                </select>
                <label for="selectCategory">Category</label>
                <div class="invalid-feedback">Category is required</div>
            </div>

            <div class="form-floating mb-3">
                <input type="text" name="name" class="form-control" id="inputName" placeholder="name"
                       value="${product.name}">
                <label for="inputName">Name</label>
                <div class="invalid-feedback">Name is required</div>
            </div>

            <div class="mb-3">
                <label for="inputBookImage" class="form-label">Image</label>
                <input type="file"
                       name="image"
                       onchange="loadFile(event)"
                       class="form-control"
                       id="inputBookImage">
            </div>

            <div class="form-floating mb-3">
                <input type="text" name="price" class="form-control" id="inputPrice" placeholder="price"
                       value="${product.price}">
                <label for="inputPrice">Price</label>
                <div class="invalid-feedback">Price is required</div>
            </div>

            <div class="form-floating mb-3">
                <textarea name="description" rows="5" style="height: 100px;" class="form-control" id="inputDescription" placeholder="Description">${product.description}</textarea>
                <label for="inputDescription">Description</label>
                <div class="invalid-feedback">Description is required</div>
            </div>

            <div class="mb-3">
                <c:if test="${mode == 'update'}">
                    <img id="preview-image" src="data:image/jpg;base64, ${product.base64Image}" style="width: 220px; object-fit: cover"/>
                </c:if>

                <c:if test="${mode == 'create'}">
                    <img id="preview-image" src="${pageContext.request.contextPath}/images/img_default.png" style="width: 220px; object-fit: cover"/>
                </c:if>
            </div>

            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width: 30%">Save</button>
                <a href="${pageContext.request.contextPath}/admin/manage_product" class="btn btn-primary btn-lg mt-4"
                   style="width: 30%">Cancel</a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#inputPublishDate').datepicker();
    })

    function initValidate() {
        $('#productForm').validate({
            rules: {
                name: {required: true},
                description: {required: true},
                price: {required: true, number: true},
                image: {required: true},
                category: {required: true},
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
    }
</script>

<script type="text/javascript">
    function loadFile(event) {
        if (event.target.files.length > 0) {
            var previewImage = document.getElementById('preview-image');
            previewImage.src = URL.createObjectURL(event.target.files[0]);
        }
    }
</script>