<%@ page import="controller.TrangChuController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <div>
            <form action="">
                <input type="hidden" value="${sinhVienSua.idSinhVien}" name="txtIdSinhVien">
                Họ tên: <input type="text" value="${sinhVienSua.hoTen}" name="txtHoTen" required, > <br>
                Điểm: <input type="text" pattern="(?:[0-9]|[0-9].[1-9]|10)" name="txtDiem" value="${sinhVienSua.diem}"> <br>
                Ngành:
                <c:if test="${sinhVienSua == null}">
                    <form:select path="nganh" items="${nganh}" itemValue="idNganh" itemLabel="tenNganh" name="cboNganh"/>
                </c:if>
                <c:if test="${sinhVienSua != null}">
                    <form:select path="nganh" name="cboNganh">
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
                <br>
                <input type="submit" value="Thêm" name="btnThem">
                <input type="submit" value="Cập nhật" name="btnCapNhat">
                <hr>
            </form>
        </div>

        <table border="1" style="width: 100%">
            <tr>
                <th>Họ tên</th>
                <th>Điểm</th>
                <th>Ngành</th>
                <th>Xếp loại</th>
                <th>Ong vàng</th>
            </tr>

            <c:forEach var="sv" items="${sinhVien}">
                <tr>
                    <td>${sv.hoTen}</td>
                    <td>${sv.diem}</td>
                    <td>${sv.nganh.tenNganh}</td>
                    <td>${sv.xepLoai}</td>
                    <td>${sv.ongVang}</td>
                    <td>
                        <form action="">
                            <input type="hidden" value="${sv.idSinhVien}" name="idSinhVienHidden">
                            <input type="hidden" value="${sv.hoTen}" name="hoTenHidden">
                            <input type="hidden" value="${sv.diem}" name="diemHidden">
                            <input type="hidden" value="${sv.nganh.idNganh}" name="idNganhHidden">
                            <input type="submit" value="Sửa" name="btnSua"> |
                            <input type="submit" value="Xóa" name="btnXoa">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
