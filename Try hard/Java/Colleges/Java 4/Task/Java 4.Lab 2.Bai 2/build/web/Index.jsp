<%-- 
    Document   : Index
    Created on : May 2, 2019, 8:36:05 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Products"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Products</h1>
        <table border="1">
            <tr>
                <th>Ma san pham</th>
                <th>Ten san pham</th>
                <th>Gia</th>
            </tr>
            
            <%
                Products listSP = new Products();
                List<Product> list = listSP.showProduct("");
                
                for (Product sp : list) {
                    out.print("<form action=\"ControllerCartBean\" method=\"post\">");
                    out.print("<tr>"
                            + "<td>" + sp.getCode() + "</td>"
                            + "<td>" + sp.getName() + "</td>"
                            + "<td>" + sp.getPrice() + "</td>"
                            + "<td><input type=\"submit\" value=\"Add to Cart\" name=\"action\"></td>"
                            + "<input type=\"hidden\" name=\"txtCode\" value='" + sp.getCode() + "'>"
                            + "<input type=\"hidden\" name=\"txtName\" value='" + sp.getName() + "'>"
                            + "<input type=\"hidden\" name=\"txtPrice\" value='" + sp.getPrice() + "'>"
                            + "</tr>");
                    out.print("</form>");
                }
            %>
        </table>
        
        <form action="ControllerCartBean" method="post">
            <input type="submit" value="View Cart" name="action">
        </form>
    </body>
</html>
