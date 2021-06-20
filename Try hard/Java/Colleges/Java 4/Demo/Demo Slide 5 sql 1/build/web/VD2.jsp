<%-- 
    Document   : VD2
    Created on : May 13, 2019, 3:14:11 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
            Lastname: <input type="text" name="txtLastName" value="${param.txtLastName}"> <br>
            <input type="submit" value="Search" name="action">
        </form>
        <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://localhost:1433; databaseName=java4_demo_slide5_sql_vd1"
                           user="thaidhph06986"
                           password="dht24111997"
                           var="con"/>
        <sql:query dataSource="${con}" var="rs">
            select * from users where lastname like ?
            <sql:param value="%${param.txtLastName}%"/>
        </sql:query>
            <table border="1">
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                </tr>
                <c:set var="count" value="0"/>
                <c:forEach var="dong" items="${rs.rows}">
                    <tr>
                        <c:set var="count" value="${count+1}"/>
                        <td>${count}</td>
                        <td>${dong.username}</td>
                        <td>${dong.pass}</td>
                        <td>${dong.lastname}</td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
