<%-- 
    Document   : Index
    Created on : May 5, 2019, 3:13:34 PM
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
        <h1><a href="Display.jsp">Click here to display!</a></h1>
        <% 
            application.setAttribute("MyName", "Thái");
        %>
        <%= application.getContextPath() %> <br>
        <%= application.getServerInfo() %> <br>
        <%= application.getServletContextName() %> <br>
        <%= application.getVirtualServerName() %> <br>
        <%= application.getAttribute("MyName") %>
    </body>
</html>
