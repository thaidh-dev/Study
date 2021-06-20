<%-- 
    Document   : Index
    Created on : Aug 5, 2019, 7:55:49 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/SetDataSource.tld" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                           url="jdbc:sqlserver://localhost:1433;databaseName=java4_assignment"
                           user="thaidhph06986"
                           pass="dht24111997"
                           var="con"/>

        <sql:query dataSource="${con}" var="rs">
            select * from nguoi_dung
        </sql:query>
    </body>
</html>
