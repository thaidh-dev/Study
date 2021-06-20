<%-- 
    Document   : index
    Created on : Jul 8, 2019, 12:00:57 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="than">
            <form action="Search" method="get">
                <h1>Danh bạ điện thoại</h1>
                <div class="lain">
                    <label>Nhập tên thuê bao</label>
                    <input type="text" name="txt">
                </div>
                <div class="lain">
                    <label>Nhập số điện thoại</label>
                    <input type="text" name="txt">
                </div>
                <div class="lain">
                    <label>Địa chỉ</label>
                    <input type="text" name="txt">
                </div>
                <input type="submit" value="Tra cứu" name="x">
                <input type="submit" value="Delete" name="x">
                <br>
                <br>
                <%
                    String x = request.getParameter("x");
                    if (x != null && x.equals("empty")) {
                %>
                <h2 style="color: red; font-family: tahoma; font-weight: bold; font-size: 20px">Bạn hãy điền thông tin vào các ô</h2>
                <%
                    }
                %>
            </form>
        </div>
    </body>
</html>
