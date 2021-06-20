<%-- 
    Document   : info
    Created on : Aug 20, 2019, 4:21:05 PM
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
        <h1>USER INFO</h1>
        <ul>
            <li>Username: ${requestScope.uid}</li>
            <li>Password: ${requestScope.pwd}</li>
        </ul>
    </body>
</html>
