<%-- 
    Document   : Them_Sua_SanPham
    Created on : Aug 3, 2019, 11:17:53 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Mendover</title>
        <link rel="icon" type="image/png" href="Hinh/favicon.png">
        <link rel="stylesheet" href="View/CSS/Them_Sua_SanPham.css">
        <script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="View/JS/TrangChu.js"></script>
        <script src="View/JS/Them_Sua_SanPham.js"></script>
    </head>
    <body>
        <div>
            <div id="header">
                <ul class="left">
                    <div class="anhTenMuiTen">
                        <li class="an" style="line-height: 0px"><img src="Hinh/userIcon.jpg" alt=""></li>
                        <li class="an">${sessionScope.USER.getHoTen()}</li>
                        <li class="an">
                            <span></span>
                            <div id="dangXuat">
                                <form action="DangNhap" method="post">
                                    <a href="TaiKhoan.jsp"><input type="button" value="Tài khoản"></a> <br>
                                    <input type="submit" value="Đăng xuất" name="submit">
                                </form>
                            </div>
                        </li>
                    </div>                
                </ul>
            </div>

            <div id="body">
                <div class="sanPham">
                    <div class="sanPhamLeft">
                        <input type="file" accept="image/*" style="display:none" id="btnHinh">
                        <img src="${param.hinh}" width="470px" height="355px" id="hinh">
                    </div>

                    <div class="sanPhamRight">
                        <form action="Them_Sua_SanPham" method="post">
                            <input type="hidden" name="maSanPham" value="${param.maSanPham}">
                            <input type="hidden" name="hinh" value="${param.hinh}" id="hinhHidden">
                            <div class="txt">
                                <label for="">Tên sản phẩm:</label>
                                <input type="text" name="tenSanPham" required autofocus value="${param.tenSanPham}" maxlength="50"> <br>
                            </div>
                            <div class="txt">
                                <label for="">Giá sản phẩm:</label>
                                <% String gia = request.getParameter("gia");%>
                                <input type="text" name="gia" required autofocus value="<%= gia != null ? gia.replaceAll(",", "") : ""%>" pattern="[0-9]*"> <br>
                            </div>
                            <div class="txt">
                                <label for="">Số lượng sản phẩm:</label>
                                <input type="number" name="soLuong" min="0" value="${param.soLuong}"> <br>
                            </div>
                            <div class="nut">
                                <input type="submit" value="<%= gia != null ? "SỬA" : "THÊM"%>" name="submit" class="sua">
                                <input type="reset" value="Reset" class="xoa">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
