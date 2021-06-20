<%@page import="DAO.SanPhamDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.SanPham"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Mendover</title>
        <link rel="icon" type="image/png" href="Hinh/favicon.png">
        <link rel="stylesheet" href="View/CSS/QuanLySanPham.css">
	<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="View/JS/TrangChu.js"></script>
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
                <div class="bodyLeft">
                    <ul>
                        <li class="danhMuc">
                            <img src="Hinh/bar.png" alt="" width="25px" height="20px">
                            <span>DANH MỤC</span>
                        </li>
                        <li><a href="">Quản lý sản phẩm</a></li>
                        <li><a href="">Quản lý khách hàng</a></li>
                        <li class="trang">
                            <form action="QuanLySanPham" method="post">
                                <c:if test="${applicationScope.TRANG != 1}">
                                    <input type="submit" value="<" name="phanTrang">
                                </c:if>
                                    
                                <c:if test="${applicationScope.TONGSOTRANG <= 5 }">
                                    <c:forEach var="i" begin="1" end="${applicationScope.TONGSOTRANG}" step="1">
                                        <c:if test="${i == applicationScope.TRANG}">
                                            <input type="button" value="${i}" class="trangDuocChon">
                                        </c:if>
                                        <c:if test="${i != applicationScope.TRANG}">
                                            <input type="submit" value="${i}" name="phanTrang">
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${applicationScope.TONGSOTRANG > 5}">
                                    <c:if test="${applicationScope.TRANG > 2 and applicationScope.TRANG < (applicationScope.TONGSOTRANG - 1)}">
                                        <input type="submit" value="${applicationScope.TRANG - 2}" name="phanTrang">
                                        <input type="submit" value="${applicationScope.TRANG - 1}" name="phanTrang">
                                        <input type="button" value="${applicationScope.TRANG}" class="trangDuocChon">
                                        <input type="submit" value="${applicationScope.TRANG + 1}" name="phanTrang">
                                        <input type="submit" value="${applicationScope.TRANG + 2}" name="phanTrang">
                                    </c:if>
                                        
                                    <c:if test="${applicationScope.TRANG == 1 || applicationScope.TRANG == 2}">
                                        <c:forEach var="i" begin="1" end="5" step="1">
                                            <c:if test="${i == applicationScope.TRANG}">
                                                <input type="button" value="${i}" class="trangDuocChon">
                                            </c:if>
                                            <c:if test="${i != applicationScope.TRANG}">
                                                <input type="submit" value="${i}" name="phanTrang">
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                                
                                    <c:if test="${applicationScope.TRANG == (applicationScope.TONGSOTRANG - 1) || applicationScope.TRANG == applicationScope.TONGSOTRANG}">
                                        <c:forEach var="i" begin="${applicationScope.TONGSOTRANG - 4}" end="${applicationScope.TONGSOTRANG}" step="1">
                                            <c:if test="${i == applicationScope.TRANG}">
                                                <input type="button" value="${i}" class="trangDuocChon">
                                            </c:if>
                                            <c:if test="${i != applicationScope.TRANG}">
                                                <input type="submit" value="${i}" name="phanTrang">
                                            </c:if>
                                        </c:forEach>
                                     </c:if>
                                </c:if>
                                                
                                <c:if test="${applicationScope.TRANG ne applicationScope.TONGSOTRANG}">
                                    <input type="submit" value=">" name="phanTrang">
                                </c:if>
                            </form>
                        </li>
                    </ul>
                </div>
                
                <div class="bodyRight">
                    <div class="top">
                        <div class="topLeft">
                            <form action="">
                                <span>Tìm kiếm: </span>
                                <input type="text">
                                <input type="submit" value="Tìm kiếm" name="submit" class="tim">
                            </form>
                        </div>
                        <div class="topRight">
                            <a href="Them_Sua_SanPham.jsp">Thêm sản phẩm</a>
                        </div>
                    </div>
                    <hr>
                    <div class="center">
                        <%
                            SanPhamDAO spDAO = new SanPhamDAO();
                            String trangString = String.valueOf(application.getAttribute("TRANG"));
                            int trangInt = Integer.parseInt(trangString);
                        %>
                        <c:forEach var="item" items="<%= spDAO.showSanPham(trangInt * 7 - 6, trangInt * 7)%>">
                            <div class="sanPham">
                                <div class="sanPhamLeft">
                                    <img src="${item.getHinh()}" alt="" width="260px" height="200px">
                                </div>
                                <div class="sanPhamRight">
                                    <p class="tenSanPham">${item.getTenSanPham()}</p>
                                    <p class="gia">${item.getGia()}<u>đ</u></p>
                                    <p class="soLuongSanPham">Số lượng: ${item.getSoLuong()}</p>
                                    <form action="QuanLySanPham" method="post">
                                        <input type="hidden" name="maSanPham" value="${item.maSanPham}">
                                        <input type="hidden" name="tenSanPham" value="${item.getTenSanPham()}">
                                        <input type="hidden" name="gia" value="${item.getGia()}">
                                        <input type="hidden" name="hinh" value="${item.getHinh()}">
                                        <input type="hidden" name="soLuong" value="${item.getSoLuong()}">
                                        <input type="submit" value="SỬA" name="submit" class="sua">
                                        <input type="submit" value="XÓA" name="submit" class="xoa">
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
