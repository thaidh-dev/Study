<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <h1>Hello World!</h1>
    <form action="form" method="get">
        <c:if test="${sinhVienSua != null}">
            <form:hidden path="sinhVienSua.idSinhVien" name="idSinhVien"/>
            Họ tên: <form:input path="sinhVienSua.hoTen" name="hoTen" required="true" />
            Điểm: <form:input path="sinhVienSua.diem" name="diem" pattern="(?:[0-9]|[0-9].[1-9]|10)" reqired="true"/>
            Chuyên ngành:
            <form:select path="nganh" name="idNganh">
                <c:forEach var="n" items="${nganh}">
                    <c:if test="${n.idNganh == sinhVienSua.nganh.idNganh}">
                        <form:option value="${n.idNganh}" label="${n.tenNganh}" selected="true"/>
                    </c:if>
                    <c:if test="${n.idNganh != sinhVienSua.nganh.idNganh}">
                        <form:option value="${n.idNganh}" label="${n.tenNganh}"/>
                    </c:if>
                </c:forEach>
            </form:select>
        </c:if>

        <c:if test="${sinhVienSua == null}">
            Họ tên: <input type="text" required name="hoTen">
            Điểm: <input pattern="(?:[0-9]|[0-9].[1-9]|10)" type="text" required="" name="diem">
            <form:select path="nganh" items="${nganh}" itemLabel="tenNganh" itemValue="idNganh" name="idNganh"/>
        </c:if>

        <br>
        <input type="submit" value="Thêm" name="btnThem">
        <input type="submit" value="Cập nhật" name="btnCapNhat">
    </form>

    <br>
    <table border="1" style="width: 100%">
        <c:forEach var="sv" items="${sinhVien}">
            <tr>
                <td>${sv.hoTen}</td>
                <td>${sv.diem}</td>
                <td>${sv.nganh.tenNganh}</td>
                <td>
                    <form action="form">
                        <input type="hidden" value="${sv.idSinhVien}" name="idSinhVien">
                        <input type="hidden" value="${sv.hoTen}" name="hoTen">
                        <input type="hidden" value="${sv.diem}" name="diem">
                        <input type="hidden" value="${sv.nganh.idNganh}" name="idNganh">
                        <input type="submit" value="Sửa" name="btnSua"> |
                        <input type="submit" value="Xóa" name="btnXoa" onclick="if(!confirm('Bạn có chắc chắn muốn xóa ko?')) return true">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
