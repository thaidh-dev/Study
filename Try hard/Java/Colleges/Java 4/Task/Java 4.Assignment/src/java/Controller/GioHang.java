package Controller;

import Model.CacSanPham;
import Model.NguoiDung;
import Model.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GioHang extends HttpServlet {
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");

            String submit = request.getParameter("submit");
        
            if (submit != null) {
                HttpSession session = request.getSession(true);
                
                if (submit.equalsIgnoreCase("MUA NGAY")) {
                    NguoiDung sessionUser = (NguoiDung) session.getAttribute("USER");
                    
                    if (sessionUser == null) {
                        response.sendRedirect("DangNhap.jsp");
                    }
                    else {
                        String gioHang = "GIOHANG" + sessionUser.getId();
                        
                        int maSanPham = Integer.parseInt(request.getParameter("maSanPham"));
                        String tenSanPham = request.getParameter("tenSanPham");
                        String gia = request.getParameter("gia");
                        String hinh = request.getParameter("hinh");
                        SanPham sanPham = new SanPham(maSanPham, tenSanPham, gia, hinh);

                        Model.GioHang sessionGioHang = (Model.GioHang) session.getAttribute(gioHang);

                        if (sessionGioHang == null) {
                            CacSanPham cacSanPham = new CacSanPham(sanPham, 1);
                            List<CacSanPham> lstCacSanPham = new ArrayList<>();
                            lstCacSanPham.add(cacSanPham);

                            sessionGioHang = new Model.GioHang(lstCacSanPham);
                        }
                        else {
                            List<CacSanPham> lstCacSanPham = sessionGioHang.getCacSanPham();
                            boolean daCo = false;

                            for (CacSanPham csp : lstCacSanPham) {
                                if (csp.getSanPham().getMaSanPham() == sanPham.getMaSanPham()) {
                                    csp.setSoLuongDaMua(csp.getSoLuongDaMua() + 1);
                                    daCo = true;
                                }
                            }

                            if (daCo == false) {
                                CacSanPham cacSanPham = new CacSanPham(sanPham, 1);
                                lstCacSanPham.add(cacSanPham);
                            }

                            sessionGioHang.setCacSanPham(lstCacSanPham);
                        }

                        session.setAttribute(gioHang, sessionGioHang);
                        session.setAttribute("GIOHANG", session.getAttribute(gioHang));

                        session.setAttribute("SoLuong", (int) session.getAttribute("SoLuong") + 1);

                        response.sendRedirect("TrangChu.jsp");
                    }
                }
                else if (submit.equalsIgnoreCase("gioHang")) {
                    NguoiDung sessionUser = (NguoiDung) session.getAttribute("USER");

                    if (sessionUser == null) {
                        response.sendRedirect("DangNhap.jsp");
                    }
                    else {
                        response.sendRedirect("GioHang.jsp");
                    }
                }
                else if (submit.equalsIgnoreCase("Thanh toán")) {
                }
                else if (submit.equalsIgnoreCase("Tiếp tục mua hàng")) {
                    NguoiDung sessionUser = (NguoiDung) session.getAttribute("USER");
                    
                    if (sessionUser == null) {
                        response.sendRedirect("DangNhap.jsp");
                    }
                    else {
                        response.sendRedirect("TrangChu.jsp");
                    }
                }
                else if (submit.equalsIgnoreCase("Xóa giỏ hàng")) {
                    NguoiDung sessionUser = (NguoiDung) session.getAttribute("USER");
                    
                    if (sessionUser == null) {
                        response.sendRedirect("DangNhap.jsp");
                    }
                    else {
                        String gioHang = "GIOHANG" + sessionUser.getId();
                    
                        Model.GioHang sessionGioHang = (Model.GioHang) session.getAttribute(gioHang);
                        List<CacSanPham> lstCacSanPham = sessionGioHang.getCacSanPham();

                        int s = lstCacSanPham.size();
                        for (int i = 0; i < s; i++) {
                            lstCacSanPham.remove(0);
                        }

                        sessionGioHang.setCacSanPham(lstCacSanPham);
                        session.setAttribute(gioHang, sessionGioHang);

                        int x = 0;
                        for (CacSanPham csp : lstCacSanPham) {
                            x += csp.getSoLuongDaMua();
                        }
                        session.setAttribute("SoLuong", x);

                        response.sendRedirect("GioHang.jsp");
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
