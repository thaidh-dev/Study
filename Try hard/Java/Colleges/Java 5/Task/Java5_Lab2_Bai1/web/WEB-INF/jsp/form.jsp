<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p>${5+5}</p>
        <form action="info.htm" method="post">
            <div>
                <label for="">Họ tên</label>
                <input type="text" name="ten" required>
            </div>
            <div>
                <label for="">Điểm</label>
                <input type="text" name="diem" required pattern="(?:[0-9]|[0-9],[1-9]|10)">
            </div>
            <div>
                <label for="">Chuyên ngành</label>
                <input type="radio" name="nganh" value="udpm" required> Ứng dụng phần mềm
                <input type="radio" name="nganh" value="tktw" required> Thiết kế trang web
            </div>
            <hr>
            <input type="submit" value="Lưu" name="btnLuu">
        </form>
    </body>
</html>
