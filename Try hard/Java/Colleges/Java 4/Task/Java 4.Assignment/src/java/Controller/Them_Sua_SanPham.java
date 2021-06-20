package Controller;

import DAO.SanPhamDAO;
import Model.NguoiDung;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class Them_Sua_SanPham extends HttpServlet {
    JFileChooser fileChooser = new JFileChooser();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            
            HttpSession session = request.getSession(true);
            NguoiDung sessionUser = (NguoiDung) session.getAttribute("USER");
            
            if (sessionUser == null) {
                response.sendRedirect("DangNhap.jsp");
            }
            else {
                String submit = request.getParameter("submit");

                if (submit != null) {
                    if (submit.equals("SỬA")) {
                        String maSanPham = request.getParameter("maSanPham");
                        String tenSanPham = request.getParameter("tenSanPham");
                        String gia = request.getParameter("gia");
                        String hinh = request.getParameter("hinh");
                        String soLuong = request.getParameter("soLuong");

                        SanPhamDAO.update(Integer.parseInt(maSanPham), tenSanPham, gia, hinh, Integer.parseInt(soLuong));

                        response.sendRedirect("QuanLySanPham.jsp");
                    }
                    else if (submit.equals("THÊM")) {
                        String tenSanPham = request.getParameter("tenSanPham");
                        String gia = request.getParameter("gia");
                        String hinh = request.getParameter("hinh");
                        String soLuong = request.getParameter("soLuong");

                        SanPhamDAO.insert(tenSanPham, gia, hinh, Integer.parseInt(soLuong));

                        response.sendRedirect("QuanLySanPham.jsp");
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
}
