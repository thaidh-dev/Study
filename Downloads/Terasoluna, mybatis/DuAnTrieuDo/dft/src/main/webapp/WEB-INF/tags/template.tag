<!DOCTYPE html>
<%@tag description="Simple Template" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="title" required="false" rtexprvalue="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/javascript/js.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${title}</title>
    <base href="${pageContext.servletContext.contextPath}/">
    <!-- head -->
    <jsp:invoke fragment="head"/>
    <!-- /head -->
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a href="http://localhost:8080" class="navbar-brand">
        <img src="${pageContext.request.contextPath}/resources/app/images/logo.jpg" alt="" width="40px"
             class="rounded-circle">Trang chủ
    </a>
    <ul class="navbar-nav">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/Tinh" class="nav-link">Tỉnh</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/QuanHuyen/" class="nav-link">Quận huyện</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/dmxaphuong/list" class="nav-link">Xã phường</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/dm-thonxom/list" class="nav-link">Thôn xóm</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/ttCaNhan/" class="nav-link">Thông tin cá nhân</a></li>
    </ul>
</nav>
<div id="sidebar">
    <div class="toggle-btn" onclick="tggleSidebar()">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <ul>
        <li>Thông tin cá nhân</li>
        <li>Thống kê</li>
        <li>Kêt quả</li>
    </ul>
</div>
<div style="background: #e9ecef; width: 100%; height: 80px; position: relative;">

</div>
<div class="row col-md-12" style=" padding-top: 10px;
	; background: #ffffff; ">
    <div style="width: 80%; margin-left: 20%; height: 450px; overflow: auto;">
        <!-- page content -->
        <jsp:invoke fragment="content"/>
        <!-- page content -->
    </div>
</div>
<div class="footer">
    <div class="container">
        <p style="text-align: center; color: #b4acac;">Bản dự án demo</p>
    </div>
</div>
</body>
<footer>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/javascript/js.js"></script>
    <!-- footer -->
    <jsp:invoke fragment="footer"/>
    <!-- /footer -->
</footer>
</html>
