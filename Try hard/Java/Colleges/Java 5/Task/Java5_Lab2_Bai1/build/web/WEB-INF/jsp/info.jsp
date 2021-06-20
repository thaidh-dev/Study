<%-- 
    Document   : info
    Created on : Aug 27, 2019, 9:52:21 AM
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
        <h1>Hello World!</h1>
        <h2>Thông tin sinh viên</h2>
        <p>Họ tên: ${param.ten}</p>
        <p>Điểm: ${param.diem}</p>
        <c:if test="${param.nganh eq 'udpm'}">
            <p>Ngành: Ứng dụng phần mềm</p>
        </c:if>
        <c:if test="${param.nganh eq 'tktw'}">
            <p>Ngành: Thiết kế trang web</p>
        </c:if>
    </body>
</html>
