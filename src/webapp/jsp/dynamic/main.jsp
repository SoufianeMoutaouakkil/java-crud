<%@ page import="java.io.File" %>
<%
    String name = request.getParameter("name");
    if (name != null) {
        out.println("File name: " + name);
        // import dynamic file
        String path = "/jsp/dynamic/" + name + ".jsp";
        // check if file exists
        if (!new File(getServletContext().getRealPath(path)).exists()) {
            out.println("File not found");
            // return;
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.include(request, response);
        }
    } else if (request.getParameter("name") == null) {
        out.println("File name: Empty");
    }
%>
<jsp:include page="/jsp/dynamic/form.jsp" />
<%

    out.println("<h5>var from form.jsp: " + request.getAttribute("formVar") + "</h5>");
%>