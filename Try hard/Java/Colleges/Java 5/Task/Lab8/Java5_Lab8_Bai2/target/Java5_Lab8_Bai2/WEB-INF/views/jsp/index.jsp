<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Index</title>
    <style>
        *[id$=errors]{
            color:red;
            font-style: italic;
        }
    </style>
</head>
<body>
<div>
    <form:form action="kiemLoi" modelAttribute="sinhVien" method="post">
        <h1>Quản lý sinh viên</h1>
        <h3>Vui lòng sửa các lỗi sau đây</h3>

        <div>
            <label>Họ tên</label>
            <form:input type="text" name="txtHoTen" path="tenSinhVien"/>
            <form:errors path="tenSinhVien"/>
                <%--                <span id="tenSinhVien.errors">dsds</span>--%>
        </div>

        <div>
            <label>Ngày sinh</label>
            <form:input type="text" path="ngaySinh"/>
            <form:errors path="ngaySinh"/>
        </div>

        <div>
            <label>Điểm</label>
            <form:input type="text" name="txtDiem" path="diem"/>
            <form:errors path="diem"/>
        </div>

        <div>
            <label>Chuyên ngành</label>
            <form:radiobuttons path="nganh.idNganh" items="${nganh}" itemValue="idNganh" itemLabel="tenNganh"/> <br>
            <form:errors path="nganh"/>
                <%--                dùng sai mục đích, đáng lẽ phải dùng comboxbox--%>
        </div>

        <input type="submit" value="Kiểm lỗi" name="btnKiemLoi">
        <h4>${dung}</h4>
    </form:form>
</div>
</body>
</html>
