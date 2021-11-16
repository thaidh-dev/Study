<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/app/css/ttCaNhanCSS/ttCaNhanCSS.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<div>
    <div>
        <form:form action="${pageContext.request.contextPath}/ttCaNhan/" method="post">
            <button name="btnThem">Thêm</button>
        </form:form>

        <form:form action="${pageContext.request.contextPath}/ttCaNhan/" method="post" modelAttribute="ttCaNhanDTO">
            Tỉnh: <form:select path="ttTinh.id" items="${lstDmTinhs}" itemLabel="ten" itemValue="id" class="cboTinh"/>
            Quận huyện: <form:select path="ttHuyen.id" items="${lstDmQuanHuyens}" itemLabel="ten" itemValue="id" class="cboQuanHuyen"/>
            Xã phường: <form:select path="ttXa.id" class="cboXaPhuong" items="${lstDmXaPhuongs}" itemValue="id" itemLabel="ten"/>
            Thôn xóm: <form:select path="ttThonXom.id" class="cboThonXom" items="${lstDmThonXoms}" itemValue="id" itemLabel="ten"/>
            <button name="btnHienThi">Hiển thị</button>

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
            <t:pagination page="${page}" disabledHref="#" pathTmpl="${pageContext.request.contextPath}/ttCaNhan/"
                          criteriaQuery="&btnHienThi=&ttTinh.id=${ttCaNhanDTO.ttTinh.id}&ttHuyen.id=${ttCaNhanDTO.ttHuyen.id}&ttXa.id=${ttCaNhanDTO.ttXa.id}&ttThonXom.id=${ttCaNhanDTO.ttThonXom.id}"
                          maxDisplayCount="10"
                          enableLinkOfCurrentPage="true"
                          activeClass="phanTrangActive" outerElementClass="phanTrang"
            />

            <c:if test="${page != null}">
                <br>
                <span>Tổng số kết quả: ${page.totalElements}</span>
                <br>
                <span>Tổng số trang: ${page.totalPages}</span>
                <%--            <span>${page.number}</span> // (trang được chọn) - 1--%>
            </c:if>
        </form:form>
    </div>

    <div>
        <table border="1">
            <tr>
                <th>STT</th>
                <th>Họ tên</th>
                <td>Mã y tế cá nhân</td>
                <th>Nam</th>
                <th>Nữ</th>
                <th>Địa chỉ</th>
            </tr>

            <c:forEach items="${page.content}" var="t" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${t.hoTen}</td>
                    <td>${t.maYTeCaNhan}</td>
                    <td>${t.gioiTinh.id == 1 ? t.ngaySinh : ''}</td>
                    <td>${t.gioiTinh.id == 2 ? t.ngaySinh : ''}</td>
                    <td>${t.ttThonXom.ten} - ${t.ttXa.ten} - ${t.ttHuyen.ten} - ${t.ttTinh.ten}</td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/ttCaNhan/" method="post" modelAttribute="ttCaNhanDTO">
                            <input type="hidden" value="${t.id}" name="txtId">
                            <form:hidden path="ttTinh.id"/>
                            <form:hidden path="ttHuyen.id"/>
                            <form:hidden path="ttXa.id"/>
                            <form:hidden path="ttThonXom.id"/>
                            <input type="hidden" value="${page.number}" name="trangDangChon">

                            <button name="btnSua">Sửa</button> |
                            <button name="btnXoa">Xóa</button>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/resources/app/js/ttCaNhanJS/ttCaNhanJS.js"></script>
</html>
