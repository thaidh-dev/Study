<%-- 
    Document   : newjsp
    Created on : May 4, 2019, 9:00:07 AM
    Author     : Admin
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%= "Hôm nay là " + new Date() %>
        
        <form method="post">
            Nhập n: <input type="text" name="txt">
            <input type="submit" name="btn">
        </form>
        <% 
            String a = request.getParameter("txt");
            if (a != null) {
                int x = Integer.parseInt(a);
                for (int i = 0; i <= x; i++) {
                    out.println(i + "<br>");
                }
            }
        %>
        
        <%! 
            public void lol() {
                System.out.println("sđs");
            }
        %>
    
    </body>
</html>
