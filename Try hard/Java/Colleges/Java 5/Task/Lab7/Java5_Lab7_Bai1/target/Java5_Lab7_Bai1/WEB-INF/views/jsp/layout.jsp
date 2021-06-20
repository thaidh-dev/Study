<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Layout</title>
</head>
<body>
    <div>
        <header>
            <h1>Shooping mail</h1>
        </header>
        <nav>
            <jsp:include page="menu.jsp"/>
        </nav>

        <article>
<%--            <jsp:include page="about.jsp"/>--%>
            <jsp:include page="${param.view}"/>
        </article>

        <aside>CONTROL PANEL</aside>
        <footer>FOOTER</footer>
    </div>
</body>
</html>
