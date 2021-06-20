<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <form action="info.htm">
            <div>
                <label for="">Họ tên</label>
                <input type="text" required name="ten">
            </div>
            <div>
                <label for="">Điểm trung bình</label>
                <input type="text" required pattern="(?:[0-9]|[0-9],[1-9]|10)" name="diem">
                <span>${requestScope.saiDiem}</span>
            </div>
            <div>
                <label for="">Chuyên ngành</label>
                <input type="radio" value="udpm" name="nganh" required>Ứng dụng phầm mềm
                <input type="radio" value="tktw" name="nganh">Thiết kế trang web
            </div>
            <hr>
            <div>
                <input type="submit" value="Lưu">
            </div>
        </form>
    </body>
</html>
