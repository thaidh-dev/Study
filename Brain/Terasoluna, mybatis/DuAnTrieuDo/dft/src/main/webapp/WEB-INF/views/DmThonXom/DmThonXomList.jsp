<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
<l:template title="Danh sách Thôn Xóm">
    <jsp:attribute name="content">
        <div id="criteriaPart">
            <form:form action="${pageContext.request.contextPath}/dm-thonxom/list" method="get"
                       modelAttribute="searchCriteriaForm">
                <label>Tìm kiếm thôn, xóm theo tên hoặc mã</label>
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
                        <th>Mã Thôn/Xóm</th>
                        <th>Tên Thôn/Xóm</th>
                        <th>Mô tả Thôn/Xóm</th>
                        <th>Tỉnh/TP</th>
                        <th>Huyện/Quận</th>
                        <th>Xã/Phường</th>
                        <th>Trạng thái</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${page.content}" var="ThonXom" varStatus="rowStatus">
                        <tr>
                            <td>${(page.number * page.size) + rowStatus.count}</td>
                            <td>${ThonXom.ma }</td>
                            <td>${ThonXom.ten }</td>
                            <td>${ThonXom.moTa }</td>
                            <td>${ThonXom.maTinh }</td>
                            <td>${ThonXom.maHuyen }</td>
                            <td>${ThonXom.maXa}</td>
                            <td>${ThonXom.trangThai}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/dm-thonxom/edit/${ThonXom.id }">Edit</a></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/dm-thonxom/delete/${ThonXom.id}"
                                   onclick="
                                            if (!(confirm('Bạn có chắc chắn muốn xóa????')))
                                                    return false ">Delete</a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="paginationPart">
                    <t:pagination page="${page}"
                                  disabledHref="#"
                                  pathTmpl="${pageContext.request.contextPath}/dm-thonxom/list"
                                  outerElementClass="pagination"
                                  criteriaQuery="${f:query(searchCriteriaForm)}"
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
