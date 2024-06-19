<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/jsp/template/head.jsp">
    <jsp:param name="pageTitle" value="User not found" />
</jsp:include>

<h1>User not found</h1>

<p>User with id <strong>${userId}</strong> not found</p>

<button onclick="history.go(-1)" class="btn btn-primary">
Back</button>
<jsp:include page="/jsp/template/footer.jsp" />
