<%-- 
    Document   : VD3
    Created on : May 13, 2019, 3:22:47 PM
    Author     : Admin
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Load data</h1>
        <form action="">
            Username: <input type="text" name="txtUsername"> <br>
            Password: <input type="text" name="txtPassword"> <br>
            Lastname: <input type="text" name="txtLastname"> <br>
            Admin: <input type="text" name="txtAdmin"> <br>
            <input type="submit" value="Insert" name="action">
        </form>
        
        <c:catch var="err">
            <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                               url="jdbc:sqlserver://localhost:1433; databaseName=java4_demo_slide5_sql_vd1"
                               user="thaidhph06986"
                               password="dht24111997"
                               var="con"/>
            <c:if test="${not empty param.txtUsername}">
                <sql:update dataSource="${con}" var="update">
                    Insert into users(username, pass, lastname, isAdmin) values (?,?,?,?)
                    <sql:param value="${param.txtUsername}"/>
                    <sql:param value="${param.txtPassword}"/>
                    <sql:param value="${param.txtLastname}"/>
                    <sql:param value="${param.txtAdmin}"/>
                </sql:update>
            </c:if>

            <c:if test="${update > 0}">
                Thêm mới thành công
            </c:if>
        </c:catch>
                
        <c:if test="${not empty err}">
            Lỗi: <c:out value="${err}"/>
        </c:if>
    </body>
</html>
