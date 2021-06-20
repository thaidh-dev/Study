<%-- 
    Document   : Forward
    Created on : May 7, 2019, 3:04:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String test = request.getParameter("companyID");
            System.out.println(test);
            if (test == null) {
        %>
        <jsp:forward page="Aptech.jsp">
            <jsp:param name="title" value="Aptech"/>
            <jsp:param name="Uname" value="JSP course"/>
        </jsp:forward>
        <%
            }
            else {
                out.println("Sorry, no match value found");
            }
        %>
    </body>
</html>
