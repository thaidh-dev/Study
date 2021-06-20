<%-- 
    Document   : search
    Created on : Jul 31, 2019, 10:19:43 AM
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
        Welcome ${sessionScope.USER}
        <h1>Search</h1>
        <form action="ControllerCustomer">
            Ten KH: <input type="text" name="txtTenKH" value="">
            <input type="submit" name="action" value="Search">
        </form>
        <br>
        <table border="1">
            <tr>
                <td>MaKH</td><td>HoTen</td><td>MatKhau</td><td>Email</td>
                <td>SoDT</td>
                <td>Delete</td>
            </tr>
            <c:forEach var="rows" items="${listKH}">
                <form action="ControllerCustomer">
                    <tr>
                        <td>${rows.maKhachHang}</td>
                        <td>${rows.hoTen}</td>
                        <td>${rows.matKhau}</td>
                        <td>${rows.email}</td>
                        <td>${rows.dienThoai}</td>
                        <td>
                            <input type="hidden" name="txtMaKH" value="${rows.maKhachHang}">
                            <input type="submit" name="action" value="Delete">
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </body>
</html>
