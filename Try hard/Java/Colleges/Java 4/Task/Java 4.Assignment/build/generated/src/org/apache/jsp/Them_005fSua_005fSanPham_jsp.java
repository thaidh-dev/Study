package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Them_005fSua_005fSanPham_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Quản lý Mendover</title>\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"Hinh/favicon.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"View/CSS/Them_Sua_SanPham.css\">\n");
      out.write("        <script src=\"//code.jquery.com/jquery-3.4.1.min.js\"></script>\n");
      out.write("        <script src=\"View/JS/TrangChu.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"header\">\n");
      out.write("            <ul class=\"left\">\n");
      out.write("                <div class=\"anhTenMuiTen\">\n");
      out.write("                    <li class=\"an\" style=\"line-height: 0px\"><img src=\"Hinh/userIcon.jpg\" alt=\"\"></li>\n");
      out.write("                    <li class=\"an\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER.getHoTen()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("                    <li class=\"an\">\n");
      out.write("                        <span></span>\n");
      out.write("                        <div id=\"dangXuat\">\n");
      out.write("                            <form action=\"DangNhap\" method=\"post\">\n");
      out.write("                                <input type=\"submit\" value=\"Đăng xuất\" name=\"submit\">\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                </div>                \n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"body\">\n");
      out.write("            <div class=\"sanPham\">\n");
      out.write("                <form action=\"Them_Sua_San_Pham\" method=\"post\">\n");
      out.write("                    <div class=\"sanPhamLeft\">\n");
      out.write("                        <button type=\"submit\" name=\"submit\" value=\"Ảnh\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.hinh}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"\" width=\"470px\" height=\"355px\"></button>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"sanPhamRight\">\n");
      out.write("                        <input type=\"hidden\" name=\"maSanPham\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.maSanPham}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <input type=\"hidden\" name=\"hinh\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.hinh}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <div class=\"txt\">\n");
      out.write("                            <label for=\"\">Tên sản phẩm:</label>\n");
      out.write("                            <input type=\"text\" name=\"tenSanPham\" required autofocus value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.tenSanPham}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"> <br>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"txt\">\n");
      out.write("                            <label for=\"\">Giá sản phẩm:</label>\n");
      out.write("                            <input type=\"text\" name=\"gia\" required autofocus value=\"");
      out.print( request.getParameter("gia").replaceAll(",", ""));
      out.write("\" pattern=\"[0-9]*\"> <br>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"txt\">\n");
      out.write("                            <label for=\"\">Số lượng sản phẩm:</label>\n");
      out.write("                            <input type=\"number\" name=\"soLuong\" min=\"0\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.soLuong}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"> <br>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"nut\">\n");
      out.write("                            <input type=\"submit\" value=\"SỬA\" name=\"submit\" class=\"sua\">\n");
      out.write("                            <input type=\"reset\" value=\"Reset\" class=\"xoa\">\n");
      out.write("                        </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
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
