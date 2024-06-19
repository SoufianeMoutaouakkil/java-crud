<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    int year = calendar.get(Calendar.YEAR);
    request.setAttribute("year", year);
%>

<footer class="footer">
    <div class="container py-2">
        <span class="text-muted">&copy; SM-DEV <c:out value="${year}" /></span>
    </div>
</footer>
