<%-- 
    Document   : index
    Created on : May 7, 2019, 10:07:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Calculator</h1>
        <form action="index.jsp">
            Số 1: <input type="text" name="btn1"> <br>
            Số 2: <input type="text" name="btn2"> <br>
            <input type="submit" value="Chia">
        </form>

        <%
            String btn1 = request.getParameter("btn1");
            String btn2 = request.getParameter("btn2");
            if (btn1 != null && btn2 != null) {
                double a = Double.parseDouble(btn1);
                double b = Double.parseDouble(btn2);
                double ketQua = a/b;
                out.println(a + " : "+ b + " = " + ketQua);
            }
        %>
    </body>
</html>
