<%-- 
    Document   : Index
    Created on : May 7, 2019, 8:29:55 PM
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
        <h1>Máy tính tổng</h1>
        <form method="get">
            <% 
                String so1 = request.getParameter("a");
                String so2 = request.getParameter("b");
                
                if (so1 == null) {
                    so1 = "";
                }
                if (so2 == null) {
                    so2 = "";
                }
            %>

            Số 1: <input type="text" name="a" value="<%= so1 %>"> <br>
            Số 2: <input type="text" name="b" value="<%= so2 %>"> <br>
            <input type="submit" value="Tổng">
        </form>
        
        <% 
            if (so1 != "" && so2 != "") {
        %>
            <br>
            <h1>Kết Quả</h1>
            <jsp:useBean id="s" class="Controller.Sum"/>
            <jsp:setProperty name="s" property="*"/>
            <h3>Tổng của 2 số là: <jsp:getProperty name="s" property="sum"/></h3>
        <%
            }
        %>
        </form>
    </body>
</html>
