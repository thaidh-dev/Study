<%-- 
    Document   : Welcome
    Created on : May 4, 2019, 10:47:53 AM
    Author     : Admin
--%>

<%@page import="Model.TaiKhoan"%>
<%@page import="java.util.List"%>
<%@page import="Model.CheckLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <span style="color: yellowgreen">${sessionScope.USER}</span></h1>
        <h2>Welcome to my site</h2>
        <form action="Search">
            Name: <input type="text" name="text"> <br>
            <input type="submit" name="x" value="Search">
        </form>
        <%
            String x = request.getParameter("lol");
            if (x != null) {
                CheckLogin c = new CheckLogin();
                c.lienKetSql();
                String ten = (String) session.getAttribute("TEN");
                List<TaiKhoan> lst = c.tim(ten);
                for (TaiKhoan tk : lst) {
                    out.print("<table>");
                    out.print("<tr>"
                        + "<td>" + tk.getUsername() + "</td>"
                        + "<td>" + tk.getTen() + "</td>"
                        + "</tr>");
                    out.print("</table>");
                }
            }
        %>
    </body>
</html>
