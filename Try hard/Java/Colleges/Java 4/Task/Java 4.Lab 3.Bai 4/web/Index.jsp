<%-- 
    Document   : Index
    Created on : May 5, 2019, 3:22:54 PM
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
        <%!
            public int sum(int a, int b, int c) {
                return a+b+c;
            }
        %>
        
        <%=
            "Kết quả = " + sum(5, 6, 7)
        %>
    </body>
</html>
