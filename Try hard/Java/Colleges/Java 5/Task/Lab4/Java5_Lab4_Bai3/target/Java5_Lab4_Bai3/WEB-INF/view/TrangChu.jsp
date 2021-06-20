<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <div>
        <div>
            <form action="">
                <input type="hidden" value="${spSua.id}" name="txtId">
                Tên sản phẩm: <input type="text" name="txtTenSanPham" value="${spSua.tenSanPham}"> <br>
                Giá cũ: <input type="text" name="txtGiaCu" value="${spSua.giaCu}"> <br>
                Giảm giá: <input type="text" name="txtGiamGia" value="${spSua.giamGia}"> <br>
                <hr>
                <input type="submit" value="Thêm" name="btnThem">
                <input type="submit" value="Cập nhật" name="btnCapNhat">
            </form>
        </div>

        <div>
            <table border="1" style="width: 100%;">
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá cũ</th>
                    <th>Giảm giá</th>
                    <th>Giá mới</th>
                </tr>

                <c:forEach var="sp" items="${sanPham}">
                    <tr>
                        <td>${sp.tenSanPham}</td>
                        <td>${sp.giaCu}</td>
                        <td>${sp.giamGia}</td>
                        <td>${sp.giaMoi}</td>
                        <td>
                            <form action="">
                                <input type="hidden" value="${sp.id}" name="idHidden">
                                <input type="submit" value="Sửa" name="btnSua">
                                <input type="submit" value="Xóa" name="btnXoa">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
