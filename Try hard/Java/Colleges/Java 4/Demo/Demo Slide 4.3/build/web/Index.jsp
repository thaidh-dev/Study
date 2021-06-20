<%-- 
    Document   : Index
    Created on : May 7, 2019, 7:10:07 PM
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
        <jsp:useBean id="msg" class="Controller.SimpleBean"/>
        Init message (getProperty): <jsp:getProperty name="msg" property="message"/> <br>
        (Scriptlet): <%= msg.getMessage() %> <br>
        Set message - setProperty: <jsp:setProperty name="msg" property="message" value="I am bean"/>
        <jsp:getProperty name="msg" property="message"/> - Scriptlet: <% msg.setMessage("I am Scriptlet"); %>
        <%= msg.getMessage() %>
    </body>
</html>
