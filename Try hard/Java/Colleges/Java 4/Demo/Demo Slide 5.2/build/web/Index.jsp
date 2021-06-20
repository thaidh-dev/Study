<%-- 
    Document   : Index
    Created on : May 11, 2019, 2:22:03 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Core tag</h1>
        <c:set var="num1" value="20" scope="page"/>
        <c:set var="num2" value="30" scope="page"/>
        <c:if test="${num1 < num2}">
            <c:out value="${num1 - num2}"/>
        </c:if>
    </body>
</html>
