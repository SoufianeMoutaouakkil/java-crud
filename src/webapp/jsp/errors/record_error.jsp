<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ctag" %>


<ctag:layout title="Error occured">
    <h1>Oops! An error occured</h1>
    <p>${error}</p>
    <button class="btn btn-primary" onclick="handleBack()">Back</button>
</ctag:layout>

<script>
    function handleBack() {
        let backUrl = "${backUrl}";
        if (backUrl) {
            window.location.href = backUrl;
        } else {
            window.history.back();
        }
    }
</script>
