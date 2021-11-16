<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa thông tin danh sách thôn xóm</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Chỉnh sửa danh sách xã phường</h1>
    <form:form action="${pageContext.request.contextPath}/dmxaphuong/update" modelAttribute="dmXaPhuong" method="post">
        <table class="table">
            <tr>
                <td>Mã</td>
                <td>
                    <form:input path="ma"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Tên</td>
                <td>
                    <form:input path="ten"/>
                </td>
            </tr>
            <tr>
                <td>Mô tả</td>
                <td>
                    <form:input path="moTa"/>
                </td>
            </tr>
            <tr>
                <td>Mã Tỉnh</td>
                <td>
                    <form:input path="maTinh"/>
                </td>
            </tr>
            <tr>
                <td>Mã Huyện</td>
                <td>
                    <form:input path="maHuyen"/>
                </td>
            </tr>
            <tr>
                <td>Cấp</td>
                <td>
                    <form:input path="cap"/>
                </td>
            </tr>
            <tr>
                <td>Trạng thái</td>
                <td>
                    <form:input path="trangThai"/>
                </td>
            </tr>
<%--            <tr>--%>
<%--                <td>Ngày tạo</td>--%>
<%--                <td>--%>
<%--                    <form:input path="ngayTao"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>người tạo Id</td>--%>
<%--                <td>--%>
<%--                    <form:input path="nguoiTaoId"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Ngày sửa</td>--%>
<%--                <td>--%>
<%--                    <form:input path="ngaySua"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Người sửa Id</td>--%>
<%--                <td>--%>
<%--                    <form:input path="nguoiSuaId"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
            <tr>
                <td></td>
                <td>
                    <form:button type="submit">Update</form:button>
                </td>
            </tr>

        </table>
    </form:form>
</div>
</body>
</html>
