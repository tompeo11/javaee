<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container py-5">
    <h1 class="text-center mb-4">
        <c:if test="${mode == 'create'}">
            Create new category
            <c:set var="action" value="${pageContext.request.contextPath}/admin/manage_category/insert"></c:set>
        </c:if>
        <c:if test="${mode == 'update'}">
            Update category
            <c:set var="action" value="${pageContext.request.contextPath}/admin/manage_category/update"></c:set>
        </c:if>
    </h1>
    <hr class="mx-auto" style="width: 50%">
    <div class="d-flex flex-column align-items-center py-5">
        <form id="categoryForm" style="width: 350px" ; action="${action}" method="post">
            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                        ${sessionScope.error}
                    <%session.removeAttribute("error");%>
                </div>
            </c:if>

            <input type="hidden" name="categoryId" value="${category.categoryId}">

            <div class="form-floating mb-3">
                <input type="text" name="name" class="form-control" id="inputName" placeholder="name"
                       value="${category.name}">
                <label for="inputName">Name</label>
                <div class="invalid-feedback">Name is required</div>
            </div>

            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary btn-lg mt-4 me-4" style="width: 30%">Save</button>
                <a href="${pageContext.request.contextPath}/admin/manage_category" class="btn btn-primary btn-lg mt-4"
                   style="width: 30%">Cancel</a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    function initValidate() {
        $('#categoryForm').validate({
            rules: {
                name: {required: true, minlength: 2},
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