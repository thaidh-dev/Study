<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:template title="Danh sách Xã phường">
    <jsp:attribute name="content">
        <div id="criteriaPart">
            <form:form action="${pageContext.request.contextPath}/dmxaphuong/list" method="get"
                       modelAttribute="searchCriteriaForm">
                <form:input path="ten"/>
                <form:button>Search</form:button>
                <br>
            </form:form>
        </div>
<c:choose>
    <c:when test="${page != null && page.totalPages != 0}">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <th>STT</th>
            <th>Mã xã phường</th>
            <th>Tên</th>
            <th>Mô tả xã phường</th>
            <th>Mã tỉnh</th>
            <th>Mã huyện</th>
            <th>Cấp</th>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Người tạo id</th>
            <th>Ngày sửa</th>
            <th>Người sửa id</th>
            <th>Edit</th>
            <th>Delete</th>
            </thead>
            <tbody>
            <c:forEach items="${page.content}" var="QH" varStatus="rowStatus">
            <tr>
                <td>${(page.number * page.size) + rowStatus.count}</td>
                <td>${QH.ma }</td>
                <td>${QH.ten }</td>
                <td>${QH.moTa }</td>
                <td>${QH.maTinh }</td>
                <td>${QH.maHuyen }</td>
                <td>${QH.cap }</td>
                <td>${QH.trangThai}</td>
                <td>${QH.ngayTao }</td>
                <td>${QH.nguoiTaoId }</td>
                <td>${QH.ngaySua }</td>
                <td>${QH.nguoiSuaId }</td>
                <td>
                    <a href="${pageContext.request.contextPath}/dmxaphuong/edit/${QH.id }">Edit</a></td>
                <td>
                    <a href="${pageContext.request.contextPath}/dmxaphuong/delete/${QH.id}"
                       onclick="
                                    if (!(confirm('Bạn có chắc chắn muốn xóa????')))
                                        return false ">Delete</a></td>
            </tr>
        </c:forEach>
            </tbody>
        </table>
        <div class="paginationPart">
            <t:pagination page="${page}" disabledHref="#" pathTmpl="${pageContext.request.contextPath}/dmxaphuong/list"
                          outerElementClass="pagination" criteriaQuery="${f:query(searchCriteriaForm)}"
                          maxDisplayCount="10"
                          enableLinkOfCurrentPage="true"/>
        </div>
        <div>
            <fmt:formatNumber value="${page.totalElements}"/> results
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

