<%-- 
    Document   : Aptech
    Created on : May 7, 2019, 3:10:55 PM
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
        <%
            out.println(request.getParameter("title"));
            out.println(request.getParameter("Uname"));
        %>
    </body>
</html>
