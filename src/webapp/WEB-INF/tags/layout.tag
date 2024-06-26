<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="title" %>
<!DOCTYPE html>
<html class="h-100">
<head>
    <title>${title}</title>
        <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
      integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <%-- bootstrap --%>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
</head>
<body class="bg-light h-100">
    <%-- justify-content : space-between --%>
    <div class="container-fluid d-flex flex-column justify-content-between align-items-center h-100">
        <main class="container flex-grow-1 d-flex flex-column">
            <jsp:include page="/WEB-INF/tags/header.jsp" />
            <div class="container flex-grow-1 d-flex flex-column" style="min-width: 75%;width: fit-content;">
                <jsp:doBody />
            </div>
        </main>

        <jsp:include page="/WEB-INF/tags/footer.jsp" />
    </div>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.min.js"></script>
</body>
</html>
