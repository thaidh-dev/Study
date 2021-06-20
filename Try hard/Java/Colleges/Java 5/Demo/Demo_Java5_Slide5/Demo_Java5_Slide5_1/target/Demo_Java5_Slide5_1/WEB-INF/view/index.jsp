<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<body>
    <h2>Hello World!</h2>
    <div>
        <h1>${company.name}</h1>
        <h1>${company.logo}</h1>
        <h1>${company.slogan}</h1>
        <hi>${sendMail}</hi>
        <form action="send">
            <input type="submit" value="Send" name="send">
        </form>
    </div>

</body>

</html>
