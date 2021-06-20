<%-- 
    Document   : GioHang
    Created on : Jul 20, 2019, 3:08:08 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng Mendover</title>
	<link rel="icon" type="image/png" href="Hinh/favicon.png">
        <link rel="stylesheet" href="View/CSS/GioHang.css">
	<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="View/JS/TrangChu.js"></script>
        <script src="View/JS/GioHang.js"></script>
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
                                    <a href="TaiKhoan.jsp"><input type="button" value="Tài khoản"></a> <br>
                                    <input type="submit" value="Đăng xuất" name="submit">
                                </form>
                            </div>
                        </li>
                    </div>                
                </ul>
                <ul class="right">
                    <li class="gio">
                        <div>
                            <input type="image" src="Hinh/giỏ.png">
                            <a href="GioHang?submit=gioHang">Giỏ hàng: <span>(<span id="soLuong">${sessionScope.SoLuong}</span>) sản phẩm</span></a>
                        </div>
                    </li>
                    <li style="border-left: 1px solid white" class="find">
                        <input type="image" src="Hinh/kính lúp.png" class="btnKinhLup">
                        <div class="hidden">
                            <input type="text" placeholder="Tìm kiếm" class="text">
                            <input type="image" src="Hinh/kính lúp.png">
                        </div>
                    </li>
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
                    <p>Giỏ hàng</p>
                    <ul>
                        <li>Trang chủ</li>
                        <li><span class="thoi"></span></li>
                        <li style="color: #caa87f">Giỏ hàng</li>
                    </ul>
                    
                    <c:if test="${sessionScope.SoLuong == 0}">
                        <p class="koCoSp">Không có sản phẩm nào trong giỏ hàng. Quay lại <a href="TrangChu.jsp">cửa hàng</a> để tiếp tục mua sắm.</p>
                    </c:if>
                    <c:if test="${sessionScope.SoLuong != 0}">
                        <div class="bangGioHang">
                            <div class="bangGioHang2">
                                <table cellpadding="0" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <td>HÌNH ẢNH</td>
                                            <td>THÔNG TIN SẢN PHẨM</td>
                                            <td>ĐƠN GIÁ</td>
                                            <td>SỐ LƯỢNG</td>
                                            <td>THÀNH TIỀN</td>
                                            <td>XÓA</td>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:set var="hang" value="${sessionScope.GIOHANG}"/>
                                        <c:forEach var="cot" items="${hang.getCacSanPham()}">
                                            <c:set var="sp" value="${cot.getSanPham()}"/>
                                                <tr>
                                                    <td style="padding: 40px"><img src="${sp.getHinh()}" alt="" width="100px" height="80px"></td>
                                                    <td>${sp.getTenSanPham()}</td>
                                                    <td style="color: #bda87f" class="gia">${sp.getGia()}<u>đ</u></td>
                                                    <td><input type="number" min="1" value="${cot.getSoLuongDaMua()}" class="number" onchange="tinhThanhTien()" onKeyDown="return false"></td>
                                                    <td style="color: #bda87f" class="thanhTien"></td>
                                                    <td><input type="image" src="Hinh/xọt rác.jpg" width="50px" height="50px" onclick="xoa(this)"></td>
                                                </tr>
                                        </c:forEach>
                                    </tbody>

                                    <tfoot>
                                        <form action="GioHang">
                                            <tr>
                                                <td colspan="6">
                                                    <input type="submit" value="TIẾP TỤC MUA HÀNG" class="inputFootTableLeft" name="submit">
                                                    <input type="submit" value="XÓA GIỎ HÀNG" class="inputFootTableRight" name="submit">
                                                </td>
                                            </tr>
                                        </form>
                                    </tfoot>
                                </table>

                                <div class="thanhToan">
                                    <span>TỔNG TIỀN THANH TOÁN</span
                                    ><span id="tongTien" style="color: #bda87f; border-left: none"></span>
                                    <form action="GioHang">
                                        <input type="submit" value="THANH TOÁN" name="submit">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <hr>
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
            </div>

            
        </div>
    </body>
    

</html>
