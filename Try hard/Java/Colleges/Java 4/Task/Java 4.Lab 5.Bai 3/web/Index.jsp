<%-- 
    Document   : Index
    Created on : May 13, 2019, 7:46:36 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Load data</h1>
        
        <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://localhost:1433; databaseName=java4_demo_slide5_sql_vd1"
                           user="thaidhph06986"
                           password="dht24111997"
                           var="con"/>
        
        <sql:query dataSource="${con}" var="rs">
            select * from users
        </sql:query>
        <c:set var="count" value="0"/>
            
        <table border="1">
            <tr>
                <th>STT</th>
                <th>Tên tài khoản</th>
                <th>Mật khẩu</th>
                <th>Tên của bạn</th>
            </tr>
            
            <c:forEach var="dong" items="${rs.rows}">
                <tr>
                    <c:set var="count" value="${count+1}"/>
                    <td>${count}</td>
                    <c:if test="${dong.username.startsWith('a')}">
                        <td style="color: red">${dong.username}</td>
                    </c:if>
                    <c:if test="${!dong.username.startsWith('a')}">
                        <td>${dong.username}</td>
                    </c:if>
                    <td>${dong.pass}</td>
                    <td>${dong.lastname}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
