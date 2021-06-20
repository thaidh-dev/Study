<%-- 
    Document   : TaiKhoan
    Created on : Aug 4, 2019, 10:10:51 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin tài khoản Mendover</title>
        <link rel="stylesheet" href="View/CSS/TaiKhoan.css">
	<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="View/JS/TrangChu.js"></script>
	<link rel="icon" type="image/png" href="Hinh/favicon.png">
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
                                    <c:if test="${sessionScope.USER.chucVu eq true}">
                                        <a href="QuanLySanPham.jsp"><input type="button" value="Quản lý"></a> <br>
                                    </c:if>
                                    <input type="submit" value="Đăng xuất" name="submit">
                                </form>
                            </div>
                        </li>
                    </div>
                </ul>
            </div>

            <div id="menu">
                <a href="TrangChu.jsp"><input type="image" src="Hinh/logo.png"></a>

                <ul>
                    <li><a href="">Liên hệ</a></li>
                    <li class="tang0">
                        <a href="">Tin tức <span class="dropdown"></span></a>
                        <ul class="tang1">
                            <li><a href="">Bất động sản</a></li>
                            <li><a href="">Tin nổi bật</a></li>
                        </ul>
                    </li>
                    <li class="tang0">
                        <a href="">Sản phẩm <span class="dropdown"></span></a>
                        <ul class="tang1">
                            <li class="tang2">
                                <a href="">Nhà ở</a>
                                <span>></span>
                                <ul>
                                    <li><a href="">Mẫu nhà phố đẹp</a></li>
                                    <li><a href="">Căn hộ Pearl Plaza</a></li>
                                    <li><a href="">Khu căn hộ SSG Tower</a></li>
                                    <li><a href="">Times City Park Hill</a></li>
                                </ul>
                            </li>
                            <li class="tang2">
                                <a href="">Căn hộ</a>
                                <span>></span>
                                <ul>
                                    <li><a href="">Mẫu nhà phố đẹp</a></li>
                                    <li><a href="">Căn hộ Pearl Plaza</a></li>
                                    <li><a href="">Khu căn hộ SSG Tower</a></li>
                                    <li><a href="">Times City Park Hill</a></li>
                                </ul>
                            </li>
                            <li><a href="">Chung cư</a></li>
                            <li><a href="">Văn phòng</a></li>
                            <li><a href="">Nhà ở dự án</a></li>
                            <li><a href="">Loại khác</a></li>
                        </ul>
                    </li>
                    <li><a href="">Giới thiệu</a></li>
                    <li><a href="">Trang chủ</a></li>
                </ul>
            </div>

            <div id="banner">
                <img src="Hinh/slider_bg_list.png" alt="">
                <div class="bannerCon">
                    <p>Quản lý tài khoản</p>
                    <ul>
                        <li>Trang chủ</li>
                        <li><span class="thoi"></span></li>
                        <li style="color: #caa87f">Quản lý tài khoản</li>
                    </ul>
                    <div class="dangNhap">
                        <form action="TaiKhoan" method="post">
                            <span class="titleDangNhap">THÔNG TIN TÀI KHOẢN</span> <br>
                            <input type="hidden" value="${sessionScope.USER.id}" name="txtId">
                            <input type="text" placeholder="Họ tên:*" required class="text" name="txtHoTen" value="${sessionScope.USER.hoTen}" maxlength="50"> <br>
                            <input type="email" placeholder="Email:*" data-validation="email" required class="text" name="txtEmail" value="${sessionScope.USER.email}" maxlength="50"> <br>
                            <input type="hidden" value="${sessionScope.USER.matKhau}" name="txtMatKhau">
                            <input type="submit" value="SỬA" class="submit" name="submit">
                            <a href="DoiMatKhau.jsp"><input type="button" value="ĐỔI MẬT KHẨU" class="submit"></a> <br>
                            <input type="submit" value="HỦY TÀI KHOẢN" class="submit" name="submit">
                        </form>
                    </div>
                </div>	
            </div>

            <div id="trenFooter">
                <ul>
                    <li class="x active"><img src="Hinh/logo.png" alt=""></li>
                    <li class="y">
                        <img src="Hinh/icon1.png" alt="">
                        Tầng 6, 266 Đội Cấn, Ba Đình , Hà Nội, Việt Nam
                    </li>
                    <li class="y">
                        <img src="Hinh/icon2.png" alt="">
                        1800 6750
                    </li>
                    <li class="y">
                        <img src="Hinh/icon3.png" alt="">
                        support@sapo.vn
                    </li>
                </ul>
                <ul>
                    <li class="x">Tài khoản</li>
                    <li>Trang chủ</li>
                    <li>Giới thiệu</li>
                    <li>Sản phẩm</li>
                    <li>Tin tức</li>
                    <li>Liên hệ</li>
                </ul>
                <ul>
                    <li class="x">Chính sách</li>
                    <li>Trang chủ</li>
                    <li>Giới thiệu</li>
                    <li>Sản phẩm</li>
                    <li>Tin tức</li>
                    <li>Liên hệ</li>
                </ul>
                <ul>
                    <li class="x">Điều khoản</li>
                    <li>Trang chủ</li>
                    <li>Giới thiệu</li>
                    <li>Sản phẩm</li>
                    <li>Tin tức</li>
                    <li>Liên hệ</li>
                </ul>
                <ul>
                    <li class="x">Hướng dẫn</li>
                    <li>Trang chủ</li>
                    <li>Giới thiệu</li>
                    <li>Sản phẩm</li>
                    <li>Tin tức</li>
                    <li>Liên hệ</li>
                </ul>
            </div>

            <div id="footer">
                <p>Bản quyền thuộc về <span style="font-weight: bold">Avent Team</span> | Cung cấp bởi <span class="white">Sapo</span></p>
                <ul>
                    <li>Liên hệ</li>
                    <li>Tin tức</li>
                    <li>Sản phẩm</li>
                    <li>Giới thiệu</li>
                    <li>Trang chủ</li>
                </ul>
            </div>
        </div>
    </body>
</html>
