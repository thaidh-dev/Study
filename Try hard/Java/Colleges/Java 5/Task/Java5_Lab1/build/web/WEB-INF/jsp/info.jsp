<%-- 
    Document   : info
    Created on : Aug 26, 2019, 9:50:03 PM
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
        <h1>Hello homie!</h1>
        <h2>Thông tin sinh viên</h2>
        Họ tên: ${param.ten} <br>
        Điểm trung bình: ${param.diem} <br>
        <c:if test="${param.nganh eq 'udpm'}">
            Ngành: Ứng dụng phần mềm
        </c:if>
        <c:if test="${param.nganh eq 'tktw'}">
            Ngành: Thiết kế trang web
        </c:if>
            
    </body>
</html>
