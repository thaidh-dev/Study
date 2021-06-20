<%-- 
    Document   : Error
    Created on : May 7, 2019, 10:29:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error</h1>
        <a href="index.jsp">Back</a> <br>
        <b style="color: red"><%= exception.toString() %></b>
    </body>
</html>
