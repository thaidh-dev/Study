package Controller;

import DAO.NguoiDungDAO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DangKy extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            
            String submit = request.getParameter("submit");
            
            if (submit != null) {
                if (submit.equals("ĐĂNG KÝ")) {
                    String txtHoTen = request.getParameter("txtHoTen");
                    String txtEmail = request.getParameter("txtEmail");
                    String txtMatKhau = request.getParameter("txtMatKhau");
                    
                    NguoiDungDAO.insert(txtHoTen, txtEmail, txtMatKhau);
                    response.sendRedirect("TrangChu.jsp");
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
