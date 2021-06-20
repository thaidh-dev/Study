<%-- 
    Document   : Index
    Created on : May 1, 2019, 9:19:24 AM
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
        <div>
            <form method="post">
                <input type="checkbox" name="chk" value="A">A <br>
                <input type="checkbox" name="chk" value="B">B <br>
                <input type="checkbox" name="chk" value="C">C <br>
                <input type="checkbox" name="chk" value="D">D <br>
                <input type="checkbox" name="chk" value="E">E <br>
                <input type="submit" value="Bấm">
            </form>
            
            <%
                String chk[] = request.getParameterValues("chk");
                if (chk.length != 0) {
                    for (int i = 0; i < chk.length; i++) {
                        out.println("Item selected: "+chk[i] + "<br>");
                    }
                }
            %>
        </div>
    </body>
</html>
