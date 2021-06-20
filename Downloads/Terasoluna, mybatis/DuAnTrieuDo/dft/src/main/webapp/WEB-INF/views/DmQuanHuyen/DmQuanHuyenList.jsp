<%--
  Created by IntelliJ IDEA.
  User: Viet
  Date: 09/10/2019
  Time: 05:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:template title="Danh sách Quận Huyện">
    <jsp:attribute name="content">
        <div id="criteriaPart">
            <form:form action="${pageContext.request.contextPath}/QuanHuyen" method="get"
                       modelAttribute="searchCriteriaForm">
                <form:input path="ten" />
                <form:button>Search</form:button>
                <br>
            </form:form>
        </div>
<c:choose>
    <c:when test="${page != null && page.totalPages != 0}">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <th>STT</th>
                <th>Mã</th>
                <th>Tên</th>
                <th>Mô tả</th>
                <th>Mã tỉnh</th>
                <th>Cấp</th>
                <th>Trạng thái</th>
                <th></th>
            </thead>
            <tbody>
         <c:forEach items="${page.content}" var="ThonXom" varStatus="rowStatus">
            <tr>
                <td>${(page.number * page.size) + rowStatus.count}</td>
                <td>${ThonXom.ma}</td>
                <td>${ThonXom.ten}</td>
                <td>${ThonXom.moTa}</td>
                <td>${ThonXom.maTinh}</td>
                <td>${ThonXom.cap}</td>
                <td>${ThonXom.trangThai}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/QuanHuyen/Sua/${ThonXom.id}">Sửa</a>
                    <a href="${pageContext.request.contextPath}/QuanHuyen/Xoa/${ThonXom.id}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
            </tbody>
        </table>
        <div class="paginationPart">
            <t:pagination page="${page}" disabledHref="#" pathTmpl="${pageContext.request.contextPath}/QuanHuyen"
                          outerElementClass="pagination" criteriaQuery="${f:query(searchCriteriaForm)}"  maxDisplayCount="10"
                          enableLinkOfCurrentPage="true"/>
        </div>
        <div>
            <fmt:formatNumber value="${page.totalElements}" /> results
        </div>
        <div>
                ${f:h(page.number + 1) } /
                ${f:h(page.totalPages)} Pages
        </div>
</c:when>
<c:otherwise>
    <h4 align="center">Không có kết quả tìm kiếm</h4>
</c:otherwise>
</c:choose>
    </jsp:attribute>
</l:template>

