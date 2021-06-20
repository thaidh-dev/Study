package Controller;

import DAO.NguoiDungDAO;
import Model.NguoiDung;
import javax.servlet.http.*;

public class TaiKhoan extends HttpServlet {
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
                        String txtId = request.getParameter("txtId");
                        String txtHoTen = request.getParameter("txtHoTen");
                        String txtEmail = request.getParameter("txtEmail");
                        String txtMatKhau = request.getParameter("txtMatKhau");

                        NguoiDung nd = new NguoiDung();
                        nd.setId(Integer.parseInt(txtId));
                        nd.setHoTen(txtHoTen);
                        nd.setEmail(txtEmail);
                        nd.setMatKhau(txtMatKhau);
                        
                        NguoiDungDAO.update(nd);

                        session.setAttribute("USER" + txtId, txtHoTen);
                        session.setAttribute("USER", nd);

                        response.sendRedirect("TaiKhoan.jsp");
                    }
                    else if (submit.equals("HỦY TÀI KHOẢN")) {
                        String txtId = request.getParameter("txtId");
                        String txtHoTen = request.getParameter("txtHoTen");
                        String txtEmail = request.getParameter("txtEmail");
                        String txtMatKhau = request.getParameter("txtMatKhau");

                        NguoiDung nd = new NguoiDung();
                        nd.setId(Integer.parseInt(txtId));
                        nd.setHoTen(txtHoTen);
                        nd.setEmail(txtEmail);
                        nd.setMatKhau(txtMatKhau);
                        nd.setTrangThai(true);

                        DangNhap.ndDAO.huyTaiKhoan(nd);
                        
                        session.setAttribute("USER" + txtId, null);
                        session.setAttribute("USER", null);
                        response.sendRedirect("TrangChu.jsp");
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
