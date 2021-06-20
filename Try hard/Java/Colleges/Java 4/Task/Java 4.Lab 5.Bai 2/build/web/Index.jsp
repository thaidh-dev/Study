<%-- 
    Document   : Index
    Created on : May 12, 2019, 3:37:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
        %>
        <h1>Calculator</h1>
        <form>
            Số 1: <input type="text" name="txt1" value="${param.txt1}"> <br>
            Số 2: <input type="text" name="txt2" value="${param.txt2}"> <br>
            <input type="submit" value="Tổng" name="submit">
        </form>
            
            <%
                String submit = request.getParameter("submit");
                if (submit != null) {
                    if (submit.equals("Tổng")) {
            %>
                        <c:if test="${param.txt1 != '' and param.txt2 ne ''}">
                            <h2>Kết Quả</h2>
                            <h3>Tổng 2 số: ${param.txt1 + param.txt2}</h3>
                        </c:if>
                        <c:if test="${param.txt1 == '' and param.txt2 eq ''}">
                            <h2>Hãy nhập số vào các ô</h2>
                        </c:if>
            <%
                    }
                }
            %>
    </body>
</html>
