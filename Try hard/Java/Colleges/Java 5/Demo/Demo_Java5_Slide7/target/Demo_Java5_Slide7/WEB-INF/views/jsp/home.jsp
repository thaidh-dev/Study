<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giao diện đa ngôn ngữ</title>
    </head>

    <body>
        <h1>Spring MVC internationalization</h1>
        Language :
        <a href="?language=en">English</a>|
        <a href="?language=vi_VN">Viet Nam</a>|
        <a href="?language=fr_FR">France</a>
        <h2>hello :  <spring:message code="hello" text="default text" /></h2>

        Current Locale : ${pageContext.response.locale}
    </body>
</html>