<%-- 
    Document   : Demo 3
    Created on : May 11, 2019, 2:28:34 PM
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
        <form>
            Số 1: <input type="text" name="num1" value="${param.num1}"> <br>
            Số 2: <input type="text" name="num2" value="${param.num2}"> <br>
            <c:catch var="er">
                <c:if test="${not empty param.num1 and not empty param.num2}">
                    <c:set var="kq" value="${param.num1 / param.num2}"/>
                    Kết quả: <c:out value="${kq}"/> <br>
                </c:if>
            </c:catch>

            <input type="submit" value="Chia">
            <c:if test="${not empty er}">
                Error: <c:out value="${er}"/>
            </c:if>
        </form>
    </body>
</html>
