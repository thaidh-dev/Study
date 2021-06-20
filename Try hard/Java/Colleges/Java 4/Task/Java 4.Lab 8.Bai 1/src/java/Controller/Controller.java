package Controller;

import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import hibernate.entity.NhanVien;
import hibernate.entity.PhongBan;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            
            String submit = request.getParameter("submit");
            
            if (submit != null) {
                if (submit.equals("Tìm")) {
                    String txtTim = request.getParameter("txtTim");
                    List<NhanVien> lstNhanVien = NhanVienDAO.find(txtTim);
                    
                    ServletContext application = request.getServletContext();
                    application.setAttribute("B", lstNhanVien);
                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Chọn")) {
                    String maNhanVien = request.getParameter("maNhanVien");
                    String tenNhanVien = request.getParameter("tenNhanVien");
                    String email = request.getParameter("email");
                    String maPhong = request.getParameter("maPhong");
                    String tenPhong = request.getParameter("tenPhong");
                    
                    PhongBan pb = new PhongBan();
                    pb.setMaPhong(Integer.parseInt(maPhong));
                    pb.setTenPhong(tenPhong);
                    NhanVien nv = new NhanVien(Integer.parseInt(maNhanVien), pb, tenNhanVien, email);
                    List<NhanVien> lstNhanVien = new ArrayList<>();
                    lstNhanVien.add(nv);
                    
                    ServletContext application = request.getServletContext();
                    application.setAttribute("B", lstNhanVien);
                    
                    String url = "Index.jsp?tenNhanVien=" + tenNhanVien +
                            "&email=" + email +
                            "&tenPhong=" + tenPhong;
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
                else if (submit.equals("Xóa")) {
                    String maNhanVien = request.getParameter("maNhanVien");
                    
                    int id = Integer.parseInt(maNhanVien);
                    
                    NhanVienDAO.delete("hibernate.entity.NhanVien", id);
                    
                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Sửa")) {
                    String maNhanVien = request.getParameter("txtMaNhanVien");
                    String tenNhanVien = request.getParameter("txtTenNhanVien");
                    String email = request.getParameter("txtEmail");
                    String tenPhong = request.getParameter("txtTenPhong");

                    int maNhanVienInt = Integer.parseInt(maNhanVien);
                    
                    List<PhongBan> pb = PhongBanDAO.selectByName(tenPhong);
                    NhanVien nv = NhanVienDAO.getByID(maNhanVienInt);
                    nv.setTenNhanVien(tenNhanVien);
                    nv.setEmail(email);
                    nv.setPhongBan(pb.get(0));
                    
                    NhanVienDAO.update(nv);
                    
                    response.sendRedirect("Index.jsp");
                }
                else if (submit.equals("Thêm")) {
                    String tenNhanVien = request.getParameter("txtTenNhanVien");
                    String email = request.getParameter("txtEmail");
                    String tenPhong = request.getParameter("txtTenPhong");
                    
                    List<PhongBan> pb = PhongBanDAO.selectByName(tenPhong);
                    NhanVien nv = new NhanVien();
                    nv.setTenNhanVien(tenNhanVien);
                    nv.setEmail(email);
                    nv.setPhongBan(pb.get(0));
                    
                    NhanVienDAO.insert(nv);
                    
                    response.sendRedirect("Index.jsp");
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
