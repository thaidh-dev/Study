<%-- 
    Document   : KetQua
    Created on : May 7, 2019, 7:44:45 PM
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
        <h1>Kết Quả</h1>
        <jsp:useBean id="calc" class="Controller.CalcBean"/>
        <jsp:setProperty name="calc" property="*"/>
        <b>Tổng của 2 số là: </b>
        <jsp:getProperty name="calc" property="sum"/>
    </body>
</html>
