<%-- 
    Document   : Display
    Created on : May 5, 2019, 3:13:42 PM
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
        <%=
            "Hello " + application.getAttribute("MyName") + "<br>" +
            "Welcome to my site!"
        %>
    </body>
</html>
