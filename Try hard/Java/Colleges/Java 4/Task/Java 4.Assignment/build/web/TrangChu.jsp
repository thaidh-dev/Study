<%-- 
    Document   : TrangChu
    Created on : Jul 12, 2019, 2:52:05 PM
    Author     : Admin
--%>

<%@page import="DAO.SanPhamDAO"%>
<%@page import="Model.SanPham"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mendover</title>
        <link rel="icon" type="image/png" href="Hinh/favicon.png">
        <link rel="stylesheet" href="View/CSS/TrangChu.css">
	<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="View/JS/TrangChu.js"></script>    
    </head>
    <body>
        <div>
            <div id="header">
                <ul class="left">
                    <c:if test="${sessionScope.USER ne null}">
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
                    </c:if>
                    <c:if test="${sessionScope.USER eq null}">
                        <li class="hien" style="border-left: 1px solid white"><a href="DangNhap.jsp">Đăng nhập</a></li>
                        <li class="hien"><a href="DangKy.jsp">Đăng ký</a></li>
                    </c:if>
                </ul>
                <ul class="right">
                    <li class="gio">
                        <div>
                            <input type="image" src="Hinh/giỏ.png">
                            <a href="GioHang?submit=gioHang">Giỏ hàng: <span>(<span id="soLuong">${sessionScope.USER eq null ? 0 : sessionScope.SoLuong}</span>) sản phẩm</span></a>
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
                <a href=""><input type="image" src="Hinh/logo.png"></a>

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
                <img src="Hinh/banner1.png" alt="" id="bannerImg">
                <ul>
                    <li class="btnBanner"><span>1</span></li>
                    <li class="btnBanner"><span>2</span></li>
                    <li class="btnBanner"><span>3</span></li>
                </ul>
            </div>

            <div id="duoiBanner">
                <div>
                    <div><a href=""><img src="Hinh/layer-46.png" alt=""></a></div>
                    <div><a href=""><img src="Hinh/layer-45.png" alt=""></a></div>
                    <div><a href=""><img src="Hinh/layer-47.png" alt=""></a></div>
                </div>
            </div>

            <div id="trenSanPhamBanChay">
                <img src="Hinh/chấm.png" alt="">
                <a href="">SẢN PHẨM BÁN CHẠY</a>
                <img src="Hinh/chấm.png" alt="">
            </div>

            <div id="banChay">
                <% SanPhamDAO spDAO = new SanPhamDAO();%>
                <c:forEach var="item" items="<%= spDAO.showSanPham(1, 6)%>">
                    <div class="sanPham">
                        <img src="${item.getHinh()}" alt="">
                        <p class="tenBanChay">${item.getTenSanPham()}</p>
                        <p style="font-size: 15px; color: #f4304c">${item.getGia()}₫</p>
                        <form action="GioHang" class="xoay" method="get">
                            <input type="hidden" value="${item.getMaSanPham()}" name="maSanPham">
                            <input type="hidden" value="${item.getTenSanPham()}" name="tenSanPham">
                            <input type="hidden" value="${item.getGia()}" name="gia">
                            <input type="hidden" value="${item.getHinh()}" name="hinh">
                            <input type="hidden" value="${item.getSoLuong()}" name="soLuong" class="soLuongHangTrongKho">
                            <input type="submit" value="MUA NGAY" name="submit" class="muaNgay">
                            <input type="submit" value="CHI TIẾT">
                        </form>
                    </div>
                </c:forEach>
            </div>

            <div id="xemThem">
                <button>XEM THÊM</button>
                <img src="Hinh/bìa xem thêm.png" alt="">
                <div id="khuyenMai">
                    <div class="khuyenMaiCon">
                        <img src="Hinh/phone.png" alt="">
                        <p style="font-weight: bold; color: #ecd3a1">MIỄN PHÍ VẬN CHUYỂN</p>
                        <p>Chúng tôi vận chuyển miễn phí với đơn hàng trên 1.000.000 đ</p>
                    </div>
                    <div class="khuyenMaiCon">
                        <img src="Hinh/quà.png" alt="">
                        <p style="font-weight: bold; color: #ecd3a1">KHUYẾN MẠI CUỐI TUẦN</p>
                        <p>Giảm giá tới 30% vào các ngày thứ 7 và chủ nhật hàng tuần</p>
                    </div>
                    <div class="khuyenMaiCon">
                        <img src="Hinh/khiên.png" alt="">
                        <p style="font-weight: bold; color: #ecd3a1">HỖ TRỢ ĐỔI TRẢ</p>
                        <p>Hỗ trợ miễn phí đổi trả sản phẩm trong 30 ngày đầu tiên từ khi mua hàng</p>
                    </div>
                </div>

                <div id="nhanHieu">
                    <img src="Hinh/partler5.png" alt="">
                    <img src="Hinh/partler6.png" alt="">
                    <img src="Hinh/partler7.png" alt="">
                    <img src="Hinh/partler9.png" alt="">
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
