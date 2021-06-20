package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class DoiMatKhau_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Đổi mật khẩu Mendover</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"View/CSS/DoiMatKhau.css\">\n");
      out.write("\t<script src=\"//code.jquery.com/jquery-3.4.1.min.js\"></script>\n");
      out.write("        <script src=\"View/JS/TrangChu.js\"></script>\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" href=\"Hinh/favicon.png\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <ul class=\"left\">\n");
      out.write("                    <div class=\"anhTenMuiTen\">\n");
      out.write("                        <li class=\"an\" style=\"line-height: 0px\"><img src=\"Hinh/userIcon.jpg\" alt=\"\"></li>\n");
      out.write("                        <li class=\"an\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.getHoTen()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("                        <li class=\"an\">\n");
      out.write("                            <span></span>\n");
      out.write("                            <div id=\"dangXuat\">\n");
      out.write("                                <form action=\"DangNhap\" method=\"post\">\n");
      out.write("                                    <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.chucVu eq true}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                                        <a href=\"QuanLySanPham.jsp\"><input type=\"button\" value=\"Quản lý\"></a> <br>\n");
      out.write("                                    </c:if>\n");
      out.write("                                    <input type=\"submit\" value=\"Đăng xuất\" name=\"submit\">\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("                    </div>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"menu\">\n");
      out.write("                <a href=\"TrangChu.jsp\"><input type=\"image\" src=\"Hinh/logo.png\"></a>\n");
      out.write("\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"\">Liên hệ</a></li>\n");
      out.write("                    <li class=\"tang0\">\n");
      out.write("                        <a href=\"\">Tin tức <span class=\"dropdown\"></span></a>\n");
      out.write("                        <ul class=\"tang1\">\n");
      out.write("                            <li><a href=\"\">Bất động sản</a></li>\n");
      out.write("                            <li><a href=\"\">Tin nổi bật</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"tang0\">\n");
      out.write("                        <a href=\"\">Sản phẩm <span class=\"dropdown\"></span></a>\n");
      out.write("                        <ul class=\"tang1\">\n");
      out.write("                            <li class=\"tang2\">\n");
      out.write("                                <a href=\"\">Nhà ở</a>\n");
      out.write("                                <span>></span>\n");
      out.write("                                <ul>\n");
      out.write("                                    <li><a href=\"\">Mẫu nhà phố đẹp</a></li>\n");
      out.write("                                    <li><a href=\"\">Căn hộ Pearl Plaza</a></li>\n");
      out.write("                                    <li><a href=\"\">Khu căn hộ SSG Tower</a></li>\n");
      out.write("                                    <li><a href=\"\">Times City Park Hill</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"tang2\">\n");
      out.write("                                <a href=\"\">Căn hộ</a>\n");
      out.write("                                <span>></span>\n");
      out.write("                                <ul>\n");
      out.write("                                    <li><a href=\"\">Mẫu nhà phố đẹp</a></li>\n");
      out.write("                                    <li><a href=\"\">Căn hộ Pearl Plaza</a></li>\n");
      out.write("                                    <li><a href=\"\">Khu căn hộ SSG Tower</a></li>\n");
      out.write("                                    <li><a href=\"\">Times City Park Hill</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li><a href=\"\">Chung cư</a></li>\n");
      out.write("                            <li><a href=\"\">Văn phòng</a></li>\n");
      out.write("                            <li><a href=\"\">Nhà ở dự án</a></li>\n");
      out.write("                            <li><a href=\"\">Loại khác</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                    <li><a href=\"\">Giới thiệu</a></li>\n");
      out.write("                    <li><a href=\"\">Trang chủ</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"banner\">\n");
      out.write("                <img src=\"Hinh/slider_bg_list.png\" alt=\"\">\n");
      out.write("                <div class=\"bannerCon\">\n");
      out.write("                    <p>Đổi mật khẩu</p>\n");
      out.write("                    <ul>\n");
      out.write("                        <li>Trang chủ</li>\n");
      out.write("                        <li><span class=\"thoi\"></span></li>\n");
      out.write("                        <li style=\"color: #caa87f\">Đổi mật khẩu</li>\n");
      out.write("                    </ul>\n");
      out.write("                    <div class=\"dangNhap\">\n");
      out.write("                        <form action=\"DoiMatKhau\" method=\"post\">\n");
      out.write("                            <span class=\"titleDangNhap\">ĐỔI MẬT KHẨU</span> <br>\n");
      out.write("                            <input type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"txtId\">\n");
      out.write("                            <input type=\"password\" placeholder=\"Mật khẩu cũ:*\" class=\"text\" name=\"txtMatKhauCu\" required maxlength=\"5\"> <br>\n");
      out.write("                            <span class=\"sai\"></span>\n");
      out.write("                            <input type=\"password\" placeholder=\"Mật khẩu mới:*\" class=\"text\" name=\"txtMatKhauMoi\" required maxlength=\"20\"> <br>\n");
      out.write("                            <span class=\"sai\"></span>\n");
      out.write("                            <input type=\"password\" placeholder=\"Xác nhận mật khẩu:*\" class=\"text\" name=\"txtXacNhan\" required maxlength=\"20\"> <br>\n");
      out.write("                            <span class=\"sai\"></span>\n");
      out.write("                            <input type=\"submit\" value=\"ĐỔI\" class=\"submit\" name=\"submit\">\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\t\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"trenFooter\">\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"x active\"><img src=\"Hinh/logo.png\" alt=\"\"></li>\n");
      out.write("                    <li class=\"y\">\n");
      out.write("                        <img src=\"Hinh/icon1.png\" alt=\"\">\n");
      out.write("                        Tầng 6, 266 Đội Cấn, Ba Đình , Hà Nội, Việt Nam\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"y\">\n");
      out.write("                        <img src=\"Hinh/icon2.png\" alt=\"\">\n");
      out.write("                        1800 6750\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"y\">\n");
      out.write("                        <img src=\"Hinh/icon3.png\" alt=\"\">\n");
      out.write("                        support@sapo.vn\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"x\">Tài khoản</li>\n");
      out.write("                    <li>Trang chủ</li>\n");
      out.write("                    <li>Giới thiệu</li>\n");
      out.write("                    <li>Sản phẩm</li>\n");
      out.write("                    <li>Tin tức</li>\n");
      out.write("                    <li>Liên hệ</li>\n");
      out.write("                </ul>\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"x\">Chính sách</li>\n");
      out.write("                    <li>Trang chủ</li>\n");
      out.write("                    <li>Giới thiệu</li>\n");
      out.write("                    <li>Sản phẩm</li>\n");
      out.write("                    <li>Tin tức</li>\n");
      out.write("                    <li>Liên hệ</li>\n");
      out.write("                </ul>\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"x\">Điều khoản</li>\n");
      out.write("                    <li>Trang chủ</li>\n");
      out.write("                    <li>Giới thiệu</li>\n");
      out.write("                    <li>Sản phẩm</li>\n");
      out.write("                    <li>Tin tức</li>\n");
      out.write("                    <li>Liên hệ</li>\n");
      out.write("                </ul>\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"x\">Hướng dẫn</li>\n");
      out.write("                    <li>Trang chủ</li>\n");
      out.write("                    <li>Giới thiệu</li>\n");
      out.write("                    <li>Sản phẩm</li>\n");
      out.write("                    <li>Tin tức</li>\n");
      out.write("                    <li>Liên hệ</li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"footer\">\n");
      out.write("                <p>Bản quyền thuộc về <span style=\"font-weight: bold\">Avent Team</span> | Cung cấp bởi <span class=\"white\">Sapo</span></p>\n");
      out.write("                <ul>\n");
      out.write("                    <li>Liên hệ</li>\n");
      out.write("                    <li>Tin tức</li>\n");
      out.write("                    <li>Sản phẩm</li>\n");
      out.write("                    <li>Giới thiệu</li>\n");
      out.write("                    <li>Trang chủ</li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
