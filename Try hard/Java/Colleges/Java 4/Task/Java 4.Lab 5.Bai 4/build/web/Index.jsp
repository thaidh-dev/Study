<%-- 
    Document   : Index
    Created on : May 13, 2019, 8:15:56 PM
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
        <h1>Load data!</h1>
        <form action="">
            Username: <input type="text" name="txtName" value="${param.txtName}"> <br>
            Password: <input type="text" name="txtPass" value="${param.txtPass}"> <br>
            Lastname: <input type="text" name="txtLastName" value="${param.txtLastName}"> <br>
            Is admin: <input type="text" name="txtAdmin" value="${param.txtAdmin}"> <br>
            <input type="submit" value="Insert">
        </form>
        
        <c:catch var="err">
            <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                               url="jdbc:sqlserver://localhost:1433; databaseName=java4_demo_slide5_sql_vd1"
                               user="thaidhph06986"
                               password="dht24111997"
                               var="con"/>

            <c:if test="${not empty param.txtName and not empty param.txtPass and not empty param.txtLastName and not empty param.txtAdmin}">
                <sql:update dataSource="${con}" var="i">
                    insert into users values (?,?,?,?)
                    <sql:param value="${param.txtName}"/>
                    <sql:param value="${param.txtPass}"/>
                    <sql:param value="${param.txtLastName}"/>
                    <sql:param value="${param.txtAdmin}"/>
                </sql:update>
            </c:if>

            <sql:query dataSource="${con}" var="s">
                select * from users
            </sql:query>
            <c:set var="count" value="0"/>

            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Tên tài khoản</th>
                    <th>Mật khẩu</th>
                    <th>Tên của bạn</th>
                    <th>Là admin?</th>
                </tr>
                <c:forEach var="row" items="${s.rows}">
                    <tr>
                        <c:set var="count" value="${count+1}"/>
                        <td>${count}</td>
                        <c:if test="${row.username.contains('a')}">
                            <td style="color: red">${row.username}</td>
                        </c:if>
                        <c:if test="${!row.username.contains('a')}">
                            <td>${row.username}</td>
                        </c:if>
                        <td>${row.pass}</td>
                        <td>${row.lastname}</td>
                        <td>${row.isAdmin}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:catch>
                        
        <c:if test="${not empty err}">
            Lỗi: ${err}
        </c:if>        
    </body>
</html>
