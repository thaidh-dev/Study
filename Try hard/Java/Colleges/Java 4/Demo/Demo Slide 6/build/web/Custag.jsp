<%-- 
    Document   : Custag
    Created on : May 14, 2019, 10:19:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/HelloTag.tld" prefix="ph" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <ph:CustomTagAttribute ten="Tu Ech" kieungay="MM/dd/yyyy"/>
        <ph:HelloTag/>
    </body>
</html>
