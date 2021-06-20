<%-- 
    Document   : login
    Created on : Aug 20, 2019, 4:17:54 PM
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
        <h1>LOGIN</h1>
        ${requestScope.message}
        <form action="b.clgt" method="post">
            <div>Username</div>
            <input name="id">
            
            <div>Password</div>
            <input name="password">
            
            <hr>
            <button>Login</button>
        </form>
    </body>
</html>
