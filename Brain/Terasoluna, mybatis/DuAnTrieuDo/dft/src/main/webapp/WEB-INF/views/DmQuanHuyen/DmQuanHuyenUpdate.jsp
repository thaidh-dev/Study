<%--
  Created by IntelliJ IDEA.
  User: Viet
  Date: 09/10/2019
  Time: 05:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:template title="Sửa Quận Huyện">
    <jsp:attribute name="content">
   <h2>Sửa quận huyện</h2>
    <form:form  action="${pageContext.request.contextPath}/QuanHuyen/Sua" method="post" modelAttribute="DmQuanHuyen">
        <form:hidden path="id"/>
        <form:label path="ma">Mã</form:label>
        <form:input path="ma"/><br>
        <form:label path="ten">Tên</form:label>
        <form:input path="ten"/><br>
        <form:label path="moTa">Mô tả</form:label>
        <form:input path="moTa"/><br>
        <form:label path="maTinh">Tỉnh</form:label>
        <form:select path="maTinh" itemValue="id" itemLabel="ten"  items="${litsTinhTP_Selects}"/><br>
        <form:label path="cap">Cấp</form:label>
        <form:input path="cap"/><br>
        <form:label path="trangThai">Trạng thái</form:label>
        <form:radiobutton path="trangThai" value="1"/>
        <form:radiobutton path="trangThai" value="0"/><br>
        <form:button>Sửa</form:button>
    </form:form>
    </jsp:attribute>
</l:template>