<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang chủ</title>
</head>
<body>
    <div>
        <h1>Hello world</h1>
        <a href="?language=en">English</a> |
        <a href="?language=vi">Việt Nam</a>

        <p><s:message code="trangChu" text="default text"/></p>
        <p><s:message code="thongTin" text="default text"/></p>
    </div>
</body>
</html>
