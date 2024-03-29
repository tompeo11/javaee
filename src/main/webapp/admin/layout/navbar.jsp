<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <a href="<c:url value="/admin"/>">
            <img src="<c:url value="/images/logo.jpg"/>" width="" alt="logo image">
        </a>

        <div>
            <a class="text-decoration-none" style="font-weight: bold; font-size: larger" href="${pageContext.request.contextPath}/">Home</a>
<%--            <a class="text-decoration-none" style="font-weight: bold; font-size: larger" href="${pageContext.request.contextPath}/admin/">Admin</a>--%>
        </div>

        <div>
            <form class="d-flex align-items-center" action="${pageContext.request.contextPath}/admin/logout" method="post">
                <c:if test="${not empty sessionScope.email}">
                    Welcome, ${sessionScope.email}
                    <span class="mx-3"> | </span>
                    <button class="btn" type="submit">Logout</button>
                </c:if>

<%--                <c:if test="${empty sessionScope.email}">--%>
<%--                    <div class="d-flex justify-content-end align-items-center vw-50">--%>
<%--                        <a href="${pageContext.request.contextPath}/admin/logout" class="btn btn-outline-success" type="button">Logout</a>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
            </form>
        </div>
    </div>
</div>