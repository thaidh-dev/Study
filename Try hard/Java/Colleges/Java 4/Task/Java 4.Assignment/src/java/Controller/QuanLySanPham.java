package Controller;

import DAO.SanPhamDAO;
import Model.NguoiDung;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class QuanLySanPham extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            ServletContext application = request.getServletContext();
            
            HttpSession session = request.getSession(true);
            NguoiDung sessionUser = (NguoiDung) session.getAttribute("USER");
            
            if (sessionUser == null) {
                response.sendRedirect("DangNhap.jsp");
            }
            else {
                String phanTrang = request.getParameter("phanTrang");

                if (phanTrang != null) {
                    String trang = String.valueOf(application.getAttribute("TRANG"));

                    if (trang == null) {
                        response.sendRedirect("DangNhap.jsp");
                    }
                    else {
                        if (phanTrang.equals("<")) {
                            application.setAttribute("TRANG", Integer.parseInt(trang) - 1);
                        }
                        else if (phanTrang.equals(">")) {
                            application.setAttribute("TRANG", Integer.parseInt(trang) + 1);
                        }
                        else {
                            application.setAttribute("TRANG", request.getParameter("phanTrang"));
                        }
                        response.sendRedirect("QuanLySanPham.jsp");
                    }
                }

                String submit = request.getParameter("submit");

                if (submit != null) {
                    if (submit.equals("XÓA")) {
                        String maSanPham = request.getParameter("maSanPham");
                        SanPhamDAO.delete(Integer.parseInt(maSanPham));

                        int a = SanPhamDAO.tongSoTrang();
                        int b = Integer.parseInt(String.valueOf(application.getAttribute("TONGSOTRANG")));
                        if (a < b) {
                            application.setAttribute("TRANG", a);
                        }
                        application.setAttribute("TONGSOTRANG", SanPhamDAO.tongSoTrang());
                        response.sendRedirect("QuanLySanPham.jsp");
                    }
                    else if (submit.equals("SỬA")) {
                        String maSanPham = request.getParameter("maSanPham");
                        String tenSanPham = request.getParameter("tenSanPham");
                        String gia = request.getParameter("gia");
                        String hinh = request.getParameter("hinh");
                        String soLuong = request.getParameter("soLuong");

                        String url = "Them_Sua_SanPham.jsp?submit=SỬA&maSanPham=" + maSanPham +
                                "&tenSanPham=" + tenSanPham +
                                "&gia=" + gia +
                                "&hinh=" + hinh +
                                "&soLuong=" + soLuong;
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
