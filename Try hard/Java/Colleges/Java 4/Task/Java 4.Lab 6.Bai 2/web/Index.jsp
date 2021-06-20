<%-- 
    Document   : Index
    Created on : Jul 8, 2019, 5:41:42 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Welcome.tld" prefix="a" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="lol" value="7"/>
        <a:Welcome name="Thái" pattern="dd/MM/yyyy" so="${lol+3}">
            đéo hiểu kiểu lồn j
        </a:Welcome>

        <p>${so}</p>
    </body>
</html>
