<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/jsp/template/head.jsp">
    <jsp:param name="pageTitle" value="Invalid user id" />
</jsp:include>

<h1>Invalid user id</h1>

<p>User id <strong>${userId}</strong> is invalid</p>

<button onclick="history.go(-1)" class="btn btn-primary">
Back</button>
<jsp:include page="/jsp/template/footer.jsp" />
