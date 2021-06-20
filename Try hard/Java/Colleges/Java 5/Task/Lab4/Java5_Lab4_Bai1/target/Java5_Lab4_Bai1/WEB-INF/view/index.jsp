<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>

<body>
    <div id="body">
        <h2>Hello World!</h2>
        <p>${applicationScope.soAnh}</p>

        <c:forEach var="i" items="${requestScope.photo}">
            <div class="khungAnh">
                <img src="${pageContext.request.contextPath}/resources/images/${i}" alt="">
                <p>${i}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
