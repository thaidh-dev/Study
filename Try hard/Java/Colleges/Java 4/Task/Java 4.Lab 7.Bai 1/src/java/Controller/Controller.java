package Controller;

import DAO.KhachHangDAO;
import fpoly.entity.KhachHang;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            
            String submit = request.getParameter("submit");
            if (submit != null) {
                if (submit.equals("Tìm")) {
                    String text = request.getParameter("text");
                    List<KhachHang> lstKhachHang = KhachHangDAO.find(text, text);
                    
                    ServletContext application = request.getServletContext();
                    application.setAttribute("A", lstKhachHang);
                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Thêm")) {
                    String txtHoTen = request.getParameter("txtHoTen");
                    String txtEmail = request.getParameter("txtEmail");
                    String txtMatKhau = request.getParameter("txtMatKhau");
                    String txtSDT = request.getParameter("txtSDT");
                    
                    KhachHangDAO.insert(txtHoTen, txtEmail, txtMatKhau, txtSDT);
                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Sửa")) {
                    String txtMaKhachHang = request.getParameter("txtMaKhachHang");
                    String txtHoTen = request.getParameter("txtHoTen");
                    String txtEmail = request.getParameter("txtEmail");
                    String txtMatKhau = request.getParameter("txtMatKhau");
                    String txtSDT = request.getParameter("txtSDT");
                    
                    KhachHangDAO.update(Integer.parseInt(txtMaKhachHang), txtHoTen, txtEmail, txtMatKhau, txtSDT);
                    
                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Xóa")) {
                    String maKH = request.getParameter("maKH");
                    int m = Integer.parseInt(maKH);
                    KhachHangDAO.delete(m);
                    
                    ServletContext application = request.getServletContext();
                    application.setAttribute("A", null);

                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Chọn")) {
                    String maKH = request.getParameter("maKH");
                    String ten = request.getParameter("ten");
                    String email = request.getParameter("email");
                    String matKhau = request.getParameter("matKhau");
                    String sdt = request.getParameter("sdt");
                    
                    String url = "Index.jsp?submit=Sửa&maKH=" + maKH + 
                            "&ten=" + ten +
                            "&email=" + email +
                            "&matKhau=" + matKhau +
                            "&sdt=" + sdt;
                    
                    KhachHang kh = new KhachHang(Integer.parseInt(maKH), ten, email, matKhau, sdt);
                    List<KhachHang> lstKhachHang = new ArrayList<>();
                    lstKhachHang.add(kh);
                    
                    ServletContext application = request.getServletContext();
                    application.setAttribute("A", lstKhachHang);

                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
        } 
        catch (Exception ex) {
            System.out.println(ex);
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
