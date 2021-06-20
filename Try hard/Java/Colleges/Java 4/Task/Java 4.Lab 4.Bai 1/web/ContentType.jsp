<%-- 
    Document   : ContentType
    Created on : May 6, 2019, 9:24:38 AM
    Author     : Admin
--%>

<%@page import="java.util.Date"%>
<%@page contentType="application/msword" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hôm nay là: </h1> <%= new Date()%>
    </body>
</html>
