<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <div>
        <header>
            <h1>Quản trị web</h1>
        </header>
        <nav>
            <a href="admin/student">Quản lý sinh viên</a>
            <a href="home/major"></a>
        </nav>
        <div>
            <jsp:include page="${param.view}"/>
        </div>
        <footer>FOOTER</footer>
    </div>
</body>
</html>
