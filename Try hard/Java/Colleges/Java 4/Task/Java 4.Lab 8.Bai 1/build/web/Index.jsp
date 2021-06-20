<%-- 
    Document   : Index
    Created on : Aug 2, 2019, 4:26:29 PM
    Author     : Admin
--%>

<%@page import="DAO.NhanVienDAO"%>
<%@page import="DAO.PhongBanDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nhân viên</h1>
        <form action="" method="get">
            <input type="hidden" value="${param.maNhanVien}" name="txtMaNhanVien">
            Tên nhân viên: <input type="text" required autofocus value="${param.tenNhanVien}" name="txtTenNhanVien"> <br>
            Email: <input type="email" required autofocus data-validation="email" value="${param.email}" name="txtEmail"> <br>
            <input type="hidden" value="${param.maPhong}" name="txtMaPhong">
            Phòng ban: <select name="txtTenPhong" id="">
                <c:set var="lstPhongBan" value="<%= PhongBanDAO.selectAll() %>"/>
                <c:forEach var="p" items="${lstPhongBan}">
                    <c:if test="${p.tenPhong == param.tenPhong}">
                        <option selected="selected">${p.tenPhong}</option>
                    </c:if>
                    <c:if test="${p.tenPhong != param.tenPhong}">
                        <option>${p.tenPhong}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Thêm" name="submit">
            <input type="submit" value="Sửa" name="submit">
        </form>
        
        <br>
        
        <form action="Controller" method="post">
            <input type="text" required autofocus name="txtTim">
            <input type="submit" value="Tìm" name="submit">
        </form>
        <br>
        <table border="1">
            <tr>
                <td>Mã nhân viên</td>
                <td>Tên nhân viên</td>
                <td>Email</td>
                <td>Tên phòng</td>
                <td></td>
                <td></td>
            </tr>

            <c:set var="lstNhanVien" value="<%= NhanVienDAO.selectAll() %>"/>
            <c:forEach var="nv" items="${lstNhanVien}">
                <c:if test="${applicationScope.B ne null}">
                    <c:set var="x" value="true"/>
                    <c:forEach var="app" items="${applicationScope.B}">
                        <c:if test="${x == true}">
                            <c:if test="${app.maNhanVien == nv.maNhanVien}">
                                <tr style="background: yellowgreen; font-weight: bold">
                                <c:set var="x" value="false"/>
                            </c:if>
                            <c:if test="${app.maNhanVien != nv.maNhanVien}">
                                <tr>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${applicationScope.B eq null}">
                    <tr>
                </c:if>
                    <td>${nv.maNhanVien}</td>
                    <td>${nv.tenNhanVien}</td>
                    <td>${nv.email}</td>
                    <td>${nv.phongBan.tenPhong}</td>
                    <form action="Controller" method="get">
                        <input type="hidden" name="maNhanVien" value="${nv.maNhanVien}">
                        <input type="hidden" name="tenNhanVien" value="${nv.tenNhanVien}">
                        <input type="hidden" name="email" value="${nv.email}">
                        <input type="hidden" name="maPhong" value="${nv.phongBan.maPhong}">
                        <input type="hidden" name="tenPhong" value="${nv.phongBan.tenPhong}">
                        <td><input type="submit" value="Chọn" name="submit"></td>
                        <td><input type="submit" value="Xóa" name="submit"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
