<%-- 
    Document   : Index
    Created on : May 8, 2019, 8:27:59 AM
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
        <table border="1px" cellPadding="0px" cellSpacing="0px">
            <tr>
                <th>Concept</th>
                <th>Expression</th>
                <th>Result</th>
            </tr>
            <tr>
                <td>Cộng</td>
                <td>${"${5+5}"}</td>
                <td>${5+5}</td>
            </tr>
            <tr>
                <td>Trừ</td>
                <td>${"${"}5-5}</td>
                <td>${5-5}</td>
            </tr>
            <tr>
                <td>Nhân</td>
                <td>${"${"}5*5}</td>
                <td>${5*5}</td>
            </tr>
            <tr>
                <td>Chia</td>
                <td>${"sssds{"}5/5}</td>
                <td>${5/5}</td>
            </tr>
        </table>
    </body>
</html>
