<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    session.invalidate();
%>

<c:set
    var="reqLocale"
    value="${pageContext.request.locale}"
/>
<c:set
    var="parLocale"
    value="${param.locale}"
/>

<c:set
    var="locale"
    value="${not empty parLocale ? parLocale : null}"
/>

<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="translate.text" />

<!DOCTYPE html>
<html>
<head>
    <title>Apache Commons Lang Tests</title>
</head>
<body>
    <h1>Testing translate.text</h1>
    <h4>Locale: <c:out value="${locale}" /></h4>
    <h4>Request Locale: <c:out value="${reqLocale}" /></h4>
    <h4>Parameter Locale: <c:out value="${parLocale}" /></h4>
    <p>
        <%
            String[] items = {"item1", "item2"};
            pageContext.setAttribute("items", items);
        %>
        <c:forEach var="item" items="${items}">
            <c:out value="${item}" /> <br>
            <fmt:message key="label.uppercase" />: ${fn:toUpperCase(item)} <br>
        </c:forEach>
    </p>
</body>
</html>
