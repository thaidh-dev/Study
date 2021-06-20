<%-- 
    Document   : Index
    Created on : Jul 31, 2019, 8:06:53 PM
    Author     : Admin
--%>

<%@page import="DAO.KhachHangDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Khách hàng</h1>
        <form action="Controller" method="get">
            <input type="hidden" name="txtMaKhachHang" value="${param.maKH}">
            Họ tên: <input type="text" name="txtHoTen" value="${param.ten}" required autofocus> <br>
            Email: <input type="email" name="txtEmail" value="${param.email}" required autofocus data-validation="email"> <br>
            Mật khẩu: <input type="password" name="txtMatKhau" value="${param.matKhau}" required autofocus> <br>
            Điện thoại: <input type="text" name="txtSDT" value="${param.sdt}" required autofocus> <br>
            <input type="submit" name="submit" value="Thêm">
            <button type="submit" name="submit" value="Sửa">Sửa</button>
        </form>
        
        <br>

        <form action="Controller">
            <input type="text" name="text" required autofocus>
            <input type="submit" value="Tìm" name="submit">
        </form>

        <table border="1">
            <thead>
                <tr>
                    <td>Mã khách hàng</td>
                    <td>Họ tên</td>
                    <td>Email</td>
                    <td>Mật khẩu</td>
                    <td>Số điện thoại</td>
                    <td></td>
                    <td></td>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach var="a" items="<%= KhachHangDAO.select()%>">
                    <c:if test="${applicationScope.A ne null}">
                        <c:set var="x" value="true"/>
                        <c:forEach var="b" items="${applicationScope.A}">
                            <c:if test="${x == true}">
                                <c:if test="${b.maKhachHang == a.maKhachHang}">
                                    <tr style="background: yellowgreen; font-weight: bold">
                                    <c:set var="x" value="false"/>    
                                </c:if>
                                <c:if test="${b.maKhachHang != a.maKhachHang}">
                                    <tr>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${applicationScope.A eq null}">
                        <tr>
                    </c:if>
                            <td>${a.maKhachHang}</td>
                            <td>${a.ten}</td>
                            <td>${a.email}</td>
                            <td>${a.matKhau}</td>
                            <td>${a.sdt}</td>

                            <form action="Controller">
                                <input type="hidden" value="${a.maKhachHang}" name="maKH">
                                <input type="hidden" value="${a.ten}" name="ten">
                                <input type="hidden" value="${a.email}" name="email">
                                <input type="hidden" value="${a.matKhau}" name="matKhau">
                                <input type="hidden" value="${a.sdt}" name="sdt">
                                <td><input type="submit" value="Chọn" name="submit"></td>
                                <td><input type="submit" value="Xóa" name="submit"></td>
                            </form>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
