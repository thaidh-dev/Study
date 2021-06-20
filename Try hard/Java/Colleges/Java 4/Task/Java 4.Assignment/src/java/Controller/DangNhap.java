package Controller;

import DAO.NguoiDungDAO;
import DAO.SanPhamDAO;
import javax.servlet.http.*;
import Helper.JDBCHelper;
import Model.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class DangNhap extends HttpServlet {
    String user = "";
    String gioHang = "";
    static NguoiDungDAO ndDAO = new NguoiDungDAO();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
            
            String text[] = request.getParameterValues("text");
            String submit = request.getParameter("submit");
            
            if (submit != null) {
                HttpSession session = request.getSession(true);
                ServletContext application = request.getServletContext();
                
                if (submit.equalsIgnoreCase("Đăng nhập")) {
                    NguoiDung nd = ndDAO.selectByEmailAndPass(text[0], text[1]);

                    if (nd != null) {
                        user = "USER" + nd.getId();
                        gioHang = "GIOHANG" + nd.getId();
                        
                        session.setAttribute(user, nd.getHoTen());
                        session.setAttribute("USER", nd);
                        session.setAttribute("GIOHANG", session.getAttribute(gioHang));

                        if (nd.isChucVu() == true) {
                            application.setAttribute("TRANG", 1);
                            application.setAttribute("TONGSOTRANG", SanPhamDAO.tongSoTrang());
                        }

                        if (session.getAttribute("GIOHANG") == null) {
                            session.setAttribute("SoLuong", 0);
                        }
                        else {
                            Model.GioHang gh = (Model.GioHang) session.getAttribute("GIOHANG");
                            List<CacSanPham> lstCacSanPham = gh.getCacSanPham();
                            
                            int x = 0;
                            for (CacSanPham csp : lstCacSanPham) {
                                x += csp.getSoLuongDaMua();
                            }
                            
                            session.setAttribute("SoLuong", x);
                        }
                        
                        response.sendRedirect("TrangChu.jsp");
                    }
                    else {
                        response.sendRedirect("DangNhap.jsp?sai=true");
                    }
                }
                else if (submit.equalsIgnoreCase("Đăng xuất")) {
                    session.setAttribute("USER", null);
                    response.sendRedirect("TrangChu.jsp");
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
     
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
