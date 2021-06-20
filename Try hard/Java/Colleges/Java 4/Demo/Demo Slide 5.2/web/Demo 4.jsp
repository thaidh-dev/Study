<%-- 
    Document   : Demo 4
    Created on : May 11, 2019, 2:39:31 PM
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
        <h1>Choose ... when</h1>
        <form>
            Number: <input type="text" name="num" value="${param.num}"> <br>
            <input type="submit" value="Submit">
            <c:choose>
                <c:when test="${empty param.num}">
                    ...
                </c:when>
                <c:when test="${param.num % 2 == 0}">
                    ${param.num} là số chẵn
                </c:when>
                <c:when test="${param.num % 2 != 0}">
                    ${param.num} là số lẻ
                </c:when>
            </c:choose>
        </form>
    </body>
</html>
