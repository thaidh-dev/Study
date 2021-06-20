<%-- 
    Document   : MyErrorPage
    Created on : May 6, 2019, 9:32:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sorry an exception occured</h1>
        <h2>The exception is: <%= exception %></h2>
    </body>
</html>
