<%-- 
    Document   : Index
    Created on : May 5, 2019, 3:27:13 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post">
            Nhập tuổi: <input type="text" name="txt">
            <input type="submit" value="Kiểm tra" name="btn">
        </form>
        
        <%
            String tuoi = request.getParameter("txt");
            if (tuoi != null) {
                try {
                    int t = Integer.parseInt(tuoi);
                    if (t >= 0) {
                        if (t > 18) {
                            out.println("Đủ tuổi trưởng thành");
                        }
                        else {
                            out.println("Chưa đủ tuổi trưởng thành");
                        }
                    }
                    else {
                        out.println("Tuổi không thể là số âm");
                    }
                }
                catch (Exception ex) {
                    out.println("Chỉ được nhập số");
                }
            }
        %>
    </body>
</html>
