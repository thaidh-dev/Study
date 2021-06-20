<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/ttCaNhanCSS/ttCaNhanCSS.css">
</head>
<body>
<div>
    <div>
        <form:form action="${pageContext.request.contextPath}/ttCaNhan/" modelAttribute="ttCaNhanDTO" method="post">
            <form:input path="hoTen" placeholder="Họ tên"/>
            <form:errors path="hoTen" cssClass="errors_message"/>
            <br>

            <form:input path="maYTeCaNhan" placeholder="Mã y tế cá nhân"/>
            <form:errors path="maYTeCaNhan" cssClass="errors_message"/>
            <br>

            <form:input path="ngaySinh" placeholder="Ngày sinh"/>
            <form:errors path="ngaySinh" cssClass="errors_message"/>
            <br>

            <form:radiobutton path="gioiTinh.id" value="1" checked="true" name="gt"/> Nam
            <form:radiobutton path="gioiTinh.id" value="2" name="gt"/> Nữ
            <br>

            Tỉnh thành phố: <form:select path="ttTinh.id" items="${lstDmTinhs}" itemLabel="ten" itemValue="id" class="cboTinh"/>
            Quận huyện: <form:select path="ttHuyen.id" items="${lstDmQuanHuyens}" itemLabel="ten" itemValue="id" class="cboQuanHuyen"/>
            Xã phường: <form:select path="ttXa.id" class="cboXaPhuong" items="${lstDmXaPhuongs}" itemValue="id" itemLabel="ten"/>
            Thôn xóm: <form:select path="ttThonXom.id" class="cboThonXom" items="${lstDmThonXoms}" itemValue="id" itemLabel="ten"/>

            <br>
            <form:errors path="ttTinh" cssClass="errors_message"/>
            <form:errors path="ttHuyen" cssClass="errors_message"/>
            <c:if test="${ttCaNhanDTO.ttHuyen.id != 0}">
                <form:errors path="ttXa" cssClass="errors_message"/>
            </c:if>
            <c:if test="${ttCaNhanDTO.ttXa.id != null}">
                <form:errors path="ttThonXom" cssClass="errors_message"/>
            </c:if>

            <br>
            <button name="btnLuu">Lưu</button>
            <button name="btnHuyThem">Hủy</button>
        </form:form>
        <%--            <span class="errors_message">${xoaError}</span>--%>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/resources/app/js/ttCaNhanJS/ttCaNhanJS.js"></script>
</html>
