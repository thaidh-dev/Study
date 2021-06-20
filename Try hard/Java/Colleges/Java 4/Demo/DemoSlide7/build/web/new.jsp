<%-- 
    Document   : new
    Created on : Jul 31, 2019, 10:15:53 AM
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
        <h1>New Customer</h1>
        <form action="ControllerCustomer">
            Ma KH: <input type="text" name="txtMaKH" value=""> <br>
            Mat Khau: <input type="password" name="txtMatKhau" value=""> <br>
            Ho ten: <input type="text" name="txtHotTen" value=""> <br>
            Email: <input type="text" name="txtEmail" value=""> <br>
            So DT: <input type="text" name="txtSoDT" value=""> <br>
            <input type="submit" name="action" value="Insert">
        </form>
    </body>
</html>
