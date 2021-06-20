package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DAO.SanPhamDAO;
import Model.SanPham;

public final class TrangChu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_if_test.release();
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Mendover</title>\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"Hinh/favicon.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"View/CSS/TrangChu.css\">\n");
      out.write("\t<script src=\"//code.jquery.com/jquery-3.4.1.min.js\"></script>\n");
      out.write("        <script src=\"View/JS/TrangChu.js\"></script>    \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <ul class=\"left\">\n");
      out.write("                    ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                </ul>\n");
      out.write("                <ul class=\"right\">\n");
      out.write("                    <li class=\"gio\">\n");
      out.write("                        <div>\n");
      out.write("                            <input type=\"image\" src=\"Hinh/giỏ.png\">\n");
      out.write("                            <a href=\"GioHang?submit=gioHang\">Giỏ hàng: <span>(<span id=\"soLuong\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER eq null ? 0 : sessionScope.SoLuong}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>) sản phẩm</span></a>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                    <li style=\"border-left: 1px solid white\" class=\"find\">\n");
      out.write("                        <input type=\"image\" src=\"Hinh/kính lúp.png\" class=\"btnKinhLup\">\n");
      out.write("                        <div class=\"hidden\">\n");
      out.write("                            <input type=\"text\" placeholder=\"Tìm kiếm\" class=\"text\">\n");
      out.write("                            <input type=\"image\" src=\"Hinh/kính lúp.png\">\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"menu\">\n");
      out.write("                <a href=\"\"><input type=\"image\" src=\"Hinh/logo.png\"></a>\n");
      out.write("\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"\">Liên hệ</a></li>\n");
      out.write("                    <li class=\"tang0\">\n");
      out.write("                        <a href=\"\">Tin tức <span class=\"dropdown\"></span></a>\n");
      out.write("                        <ul class=\"tang1\">\n");
      out.write("                                <li><a href=\"\">Bất động sản</a></li>\n");
      out.write("                                <li><a href=\"\">Tin nổi bật</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"tang0\">\n");
      out.write("                        <a href=\"\">Sản phẩm <span class=\"dropdown\"></span></a>\n");
      out.write("                        <ul class=\"tang1\">\n");
      out.write("                                <li class=\"tang2\">\n");
      out.write("                                    <a href=\"\">Nhà ở</a>\n");
      out.write("                                    <span>></span>\n");
      out.write("                                    <ul>\n");
      out.write("                                        <li><a href=\"\">Mẫu nhà phố đẹp</a></li>\n");
      out.write("                                        <li><a href=\"\">Căn hộ Pearl Plaza</a></li>\n");
      out.write("                                        <li><a href=\"\">Khu căn hộ SSG Tower</a></li>\n");
      out.write("                                        <li><a href=\"\">Times City Park Hill</a></li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </li>\n");
      out.write("                                <li class=\"tang2\">\n");
      out.write("                                    <a href=\"\">Căn hộ</a>\n");
      out.write("                                    <span>></span>\n");
      out.write("                                    <ul>\n");
      out.write("                                        <li><a href=\"\">Mẫu nhà phố đẹp</a></li>\n");
      out.write("                                        <li><a href=\"\">Căn hộ Pearl Plaza</a></li>\n");
      out.write("                                        <li><a href=\"\">Khu căn hộ SSG Tower</a></li>\n");
      out.write("                                        <li><a href=\"\">Times City Park Hill</a></li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </li>\n");
      out.write("                                <li><a href=\"\">Chung cư</a></li>\n");
      out.write("                                <li><a href=\"\">Văn phòng</a></li>\n");
      out.write("                                <li><a href=\"\">Nhà ở dự án</a></li>\n");
      out.write("                                <li><a href=\"\">Loại khác</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </li>\n");
      out.write("                    <li><a href=\"\">Giới thiệu</a></li>\n");
      out.write("                    <li><a href=\"\">Trang chủ</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"banner\">\n");
      out.write("                <img src=\"Hinh/banner1.png\" alt=\"\" id=\"bannerImg\">\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"btnBanner\"><span>1</span></li>\n");
      out.write("                    <li class=\"btnBanner\"><span>2</span></li>\n");
      out.write("                    <li class=\"btnBanner\"><span>3</span></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"duoiBanner\">\n");
      out.write("                <div>\n");
      out.write("                    <div><a href=\"\"><img src=\"Hinh/layer-46.png\" alt=\"\"></a></div>\n");
      out.write("                    <div><a href=\"\"><img src=\"Hinh/layer-45.png\" alt=\"\"></a></div>\n");
      out.write("                    <div><a href=\"\"><img src=\"Hinh/layer-47.png\" alt=\"\"></a></div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"trenSanPhamBanChay\">\n");
      out.write("                <img src=\"Hinh/chấm.png\" alt=\"\">\n");
      out.write("                <a href=\"\">SẢN PHẨM BÁN CHẠY</a>\n");
      out.write("                <img src=\"Hinh/chấm.png\" alt=\"\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"banChay\">\n");
      out.write("                ");
 SanPhamDAO spDAO = new SanPhamDAO();
      out.write("\n");
      out.write("                ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setVar("item");
      _jspx_th_c_forEach_0.setItems( spDAO.showSanPham(1, 6));
      int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("                    <div class=\"sanPham\">\n");
            out.write("                        <img src=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getHinh()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" alt=\"\">\n");
            out.write("                        <p class=\"tenBanChay\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getTenSanPham()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("</p>\n");
            out.write("                        <p style=\"font-size: 15px; color: #f4304c\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getGia()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("₫</p>\n");
            out.write("                        <form action=\"GioHang\" class=\"xoay\" method=\"get\">\n");
            out.write("                            <input type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getMaSanPham()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" name=\"maSanPham\">\n");
            out.write("                            <input type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getTenSanPham()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" name=\"tenSanPham\">\n");
            out.write("                            <input type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getGia()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" name=\"gia\">\n");
            out.write("                            <input type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getHinh()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" name=\"hinh\">\n");
            out.write("                            <input type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.getSoLuong()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" name=\"soLuong\" class=\"soLuongHangTrongKho\">\n");
            out.write("                            <input type=\"submit\" value=\"MUA NGAY\" name=\"submit\" class=\"muaNgay\">\n");
            out.write("                            <input type=\"submit\" value=\"CHI TIẾT\">\n");
            out.write("                        </form>\n");
            out.write("                    </div>\n");
            out.write("                ");
            int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_forEach_0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_forEach_0.doFinally();
        _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
      }
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"xemThem\">\n");
      out.write("                <button>XEM THÊM</button>\n");
      out.write("                <img src=\"Hinh/bìa xem thêm.png\" alt=\"\">\n");
      out.write("                <div id=\"khuyenMai\">\n");
      out.write("                    <div class=\"khuyenMaiCon\">\n");
      out.write("                        <img src=\"Hinh/phone.png\" alt=\"\">\n");
      out.write("                        <p style=\"font-weight: bold; color: #ecd3a1\">MIỄN PHÍ VẬN CHUYỂN</p>\n");
      out.write("                        <p>Chúng tôi vận chuyển miễn phí với đơn hàng trên 1.000.000 đ</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"khuyenMaiCon\">\n");
      out.write("                        <img src=\"Hinh/quà.png\" alt=\"\">\n");
      out.write("                        <p style=\"font-weight: bold; color: #ecd3a1\">KHUYẾN MẠI CUỐI TUẦN</p>\n");
      out.write("                        <p>Giảm giá tới 30% vào các ngày thứ 7 và chủ nhật hàng tuần</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"khuyenMaiCon\">\n");
      out.write("                        <img src=\"Hinh/khiên.png\" alt=\"\">\n");
      out.write("                        <p style=\"font-weight: bold; color: #ecd3a1\">HỖ TRỢ ĐỔI TRẢ</p>\n");
      out.write("                        <p>Hỗ trợ miễn phí đổi trả sản phẩm trong 30 ngày đầu tiên từ khi mua hàng</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"nhanHieu\">\n");
      out.write("                    <img src=\"Hinh/partler5.png\" alt=\"\">\n");
      out.write("                    <img src=\"Hinh/partler6.png\" alt=\"\">\n");
      out.write("                    <img src=\"Hinh/partler7.png\" alt=\"\">\n");
      out.write("                    <img src=\"Hinh/partler9.png\" alt=\"\">\n");
      out.write("                </div>\n");
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
      out.write("\t\t</ul>\n");
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
      out.write("            </div>\t\t\n");
      out.write("\t</div>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER ne null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                        <div class=\"anhTenMuiTen\">\n");
        out.write("                            <li class=\"an\" style=\"line-height: 0px\"><img src=\"Hinh/userIcon.jpg\" alt=\"\"></li>\n");
        out.write("                            <li class=\"an\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.getHoTen()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</li>\n");
        out.write("                            <li class=\"an\">\n");
        out.write("                                <span></span>\n");
        out.write("                                <div id=\"dangXuat\">\n");
        out.write("                                    <form action=\"DangNhap\" method=\"post\">\n");
        out.write("                                        ");
        if (_jspx_meth_c_if_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                                        <a href=\"TaiKhoan.jsp\"><input type=\"button\" value=\"Tài khoản\"></a> <br>\n");
        out.write("                                        <input type=\"submit\" value=\"Đăng xuất\" name=\"submit\">\n");
        out.write("                                    </form>\n");
        out.write("                                </div>\n");
        out.write("                            </li>\n");
        out.write("                        </div>\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.chucVu eq true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                            <a href=\"QuanLySanPham.jsp\"><input type=\"button\" value=\"Quản lý\"></a> <br>\n");
        out.write("                                        ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER eq null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                        <li class=\"hien\" style=\"border-left: 1px solid white\"><a href=\"DangNhap.jsp\">Đăng nhập</a></li>\n");
        out.write("                        <li class=\"hien\"><a href=\"DangKy.jsp\">Đăng ký</a></li>\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }
}
