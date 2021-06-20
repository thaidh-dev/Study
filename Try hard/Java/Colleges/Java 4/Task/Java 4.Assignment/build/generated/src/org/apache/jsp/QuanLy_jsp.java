package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class QuanLy_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Quản lý Mendover</title>\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"Hinh/favicon.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"View/CSS/QuanLy.css\">\n");
      out.write("\t<script src=\"//code.jquery.com/jquery-3.4.1.min.js\"></script>\n");
      out.write("        <script src=\"View/JS/TrangChu.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <ul class=\"left\">\n");
      out.write("                    <div class=\"anhTenMuiTen\">\n");
      out.write("                        <li class=\"an\" style=\"line-height: 0px\"><img src=\"Hinh/userIcon.jpg\" alt=\"\"></li>\n");
      out.write("                        <li class=\"an\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.USER}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("                        <li class=\"an\">\n");
      out.write("                            <span></span>\n");
      out.write("                            <div id=\"dangXuat\">\n");
      out.write("                                <form action=\"DangNhap\" method=\"post\">\n");
      out.write("                                    <input type=\"submit\" value=\"Đăng xuất\" name=\"submit\">\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("                    </div>                \n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div id=\"body\">\n");
      out.write("                <div class=\"bodyLeft\">\n");
      out.write("                    <ul>\n");
      out.write("                        <li class=\"danhMuc\">\n");
      out.write("                            <img src=\"Hinh/bar.png\" alt=\"\" width=\"25px\" height=\"20px\">\n");
      out.write("                            <span>DANH MỤC</span>\n");
      out.write("                        </li>\n");
      out.write("                        <li><a href=\"\">Quản lý sản phẩm</a></li>\n");
      out.write("                        <li><a href=\"\">Quản lý khách hàng</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"bodyRight\">\n");
      out.write("                    <div class=\"top\">\n");
      out.write("                        <div class=\"topLeft\">\n");
      out.write("                            <form action=\"\">\n");
      out.write("                                <span>Tìm kiếm:</span>\n");
      out.write("                                <input type=\"text\">\n");
      out.write("                                <input type=\"submit\" value=\"Tìm kiếm\" name=\"submit\">\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"topRight\">\n");
      out.write("                            <a href=\"\">Thêm sản phẩm</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <hr>\n");
      out.write("                    <div class=\"center\">\n");
      out.write("                        <p>ádsds</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
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
