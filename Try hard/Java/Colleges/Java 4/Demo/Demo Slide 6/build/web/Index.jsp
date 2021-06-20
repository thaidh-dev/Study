<%-- 
    Document   : Index
    Created on : May 14, 2019, 9:54:13 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/HelloTag.tld" prefix="my" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Simple custom tag</h1>
        <my:HelloTag/>
        <my:CustomTagAttribute ten="thai" kieungay="dd/MM/yyyy"/>
    </body>
</html>
