<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.SinhVien"%>
<%@page import="java.util.List"%>
<%@page import="dao.SinhVienDAO"%>
<%@page import="helper.JDBCHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="form.htm" method="get">
            <h1>Hello World!</h1>
            <input type="hidden" value="${param.id}" name="id">
            Họ tên: <input type="text" required value="${param.hoTen}" name="hoTen">
            Điểm: <input pattern="(?:[0-9]|[0-9],[1-9]|10)" type="text" required="" value="${param.diem}" name="diem">
            Chuyên ngành: <input type="text" required="" value="${param.nganh}" name="nganh">
            
            <br>
            
            <input type="submit" value="Thêm" name="btnThem">
            <input type="submit" value="Cập nhật" name="btnCapNhat">
            <input type="reset" value="Nhập lại">
        </form>
            
        <br>
        <table border="1" style="width: 100%">
            <% List<SinhVien> lstSV = SinhVienDAO.selectAllSinhVien(); %>
            <c:forEach var="sv" items="<%= lstSV %>">
                <tr>
                    <td>${sv.hoTen}</td>
                    <td>${sv.diem}</td>
                    <td>${sv.nganh}</td>
                    <td><a href="form.htm?id=${sv.id}&hoTen=${sv.hoTen}&diem=${sv.diem}&nganh=${sv.nganh}">Sửa</a></td>
                    <td>
                        <form action="form.htm">
                            <input type="hidden" value="${sv.id}" name="idXoa">
                            <input type="submit" value="Xóa" name="btnXoa">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
