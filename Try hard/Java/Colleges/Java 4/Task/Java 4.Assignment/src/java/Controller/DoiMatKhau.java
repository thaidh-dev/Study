package Controller;

import DAO.NguoiDungDAO;
import Model.NguoiDung;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DoiMatKhau extends HttpServlet {
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
                    if (submit.equals("ĐỔI")) {
                        String txtId = request.getParameter("txtId");
                        String txtMatKhauCu = request.getParameter("txtMatKhauCu");
                        String txtMatKhauMoi = request.getParameter("txtMatKhauMoi");
                        String txtXacNhan = request.getParameter("txtXacNhan");

                        NguoiDungDAO ndDAO = new NguoiDungDAO();
                        NguoiDung nd = ndDAO.selectById(Integer.parseInt(txtId));

                        if (txtMatKhauCu.equals(nd.getMatKhau())) {
                            if (txtMatKhauMoi.equals(txtXacNhan)) {
                                NguoiDungDAO.updateMatKhau(txtXacNhan, Integer.parseInt(txtId));
                                response.sendRedirect("TaiKhoan.jsp");
                            }
                            else {
                                RequestDispatcher rd = request.getRequestDispatcher("DoiMatKhau.jsp?saiXacNhan=true");
                                rd.forward(request, response);
                            }
                        }
                        else {
                            RequestDispatcher rd = request.getRequestDispatcher("DoiMatKhau.jsp?saiMatKhauCu=true");
                            rd.forward(request, response);
                        }
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
