<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <div>
        <header>
            <h1>Shooping mail</h1>
        </header>
        <nav>
            <jsp:include page="user-layout/menu.jsp"/>
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
