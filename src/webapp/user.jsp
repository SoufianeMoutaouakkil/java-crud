<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Users List</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Users List -- (user)</h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>

<h1>User sample</h1>
<c:out value="${userSample}" />
<h1>Path Info</h1>
<c:out value="${path}" />
</body>
</html>