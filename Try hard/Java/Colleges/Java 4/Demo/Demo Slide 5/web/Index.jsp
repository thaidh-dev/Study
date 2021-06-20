<%-- 
    Document   : Index
    Created on : May 11, 2019, 2:11:41 PM
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
        <h1>Calculator</h1>
        <form>
            Số 1: <input type="text" name="txt1" value="${param.txt1}"> <br>
            Số 2: <input type="text" name="txt2" value="${param.txt2}"> <br>
            <input type="submit" value="Cộng"> 
            <h2>Tổng 2 số: ${param.txt1 + param.txt2}</h2>
        </form>
    </body>
</html>
