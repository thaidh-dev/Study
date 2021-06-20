<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
    <style>
        .failed {
            color: red;
        }

        .loggedOut {
            color: greenyellow;
        }
    </style>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
        <c:if test="${param.error != null}">
            <i class="failed">Nhập sai tên đăng nhập và mật khẩu</i>
        </c:if>
        <c:if test="${param.logout != null}">
            <p class="loggedOut">Bạn vừa đăng xuất khỏi tài khoản</p>
        </c:if>

        <p>User name: <input type="text" name="username"></p>
        <p>Password: <input type="password" name="password"></p>
        <input type="submit" value="Login">
    </form:form>
</body>
</html>
