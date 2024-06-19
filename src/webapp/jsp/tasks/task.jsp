<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ctag" %>


<ctag:layout title="Task detail">
    <h1>Task detail</h1>
    <c:out value="${task}" />
</ctag:layout>
