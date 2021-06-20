<%-- 
    Document   : Demo 6
    Created on : May 11, 2019, 2:49:48 PM
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
        <h1>forEach - forTokens</h1>
        
        <c:set var="lang" value="Core Java:Servlet;JSP:Spring;Struts:EJB" scope="page"/>
        <%! String[] names = {"Fpoly", "HCM", "Ha Noi", "Da Nang", "Tay Nguyen"}; %>
        
        <b>forEach:</b> <br>
        <c:forEach var="item" items="<%=names%>">
            <c:out value="${item}"/> <br>
        </c:forEach>
            
        <br>
        <b>forTokens:</b>
        <br>
        <c:forTokens items="${lang}" delims=":;" var="java">
            <c:out value="${java}"/> <br>
        </c:forTokens>
            
        <br>
        <b>Counter</b>
        <br>
        <c:forEach begin="1" end="10" step="1" var="counter">
            <c:out value="${counter}"/>
        </c:forEach>
    </body>
</html>
