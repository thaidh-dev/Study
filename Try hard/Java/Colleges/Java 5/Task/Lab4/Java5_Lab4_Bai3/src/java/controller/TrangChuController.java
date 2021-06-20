package controller;

import dao.SanPhamDAO;
import dto.SanPhamDTO;
import model.SanPham;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("")
public class TrangChuController {
    @RequestMapping
    public String showTrangChu() {
        return "TrangChu";
    }

    @ModelAttribute("sanPham")
    public List<SanPhamDTO> getAllSanPham() {
        SanPhamDAO spDAO = new SanPhamDAO();
        return spDAO.getALlSanPham();
    }

    @RequestMapping(params = "btnSua")
    public String sua(@RequestParam("idHidden") int id, Model model) {
        SanPham sp = new SanPhamDAO().getById(id);

        model.addAttribute("spSua", sp);

        return "TrangChu";
    }

    @RequestMapping(params = "btnThem")
    public String them(
            @RequestParam("txtTenSanPham") String tenSanPham,
            @RequestParam("txtGiaCu") BigDecimal giaCu,
            @RequestParam("txtGiamGia") int giamGia) {
        SanPham sp = new SanPham();
        sp.setTenSanPham(tenSanPham);
        sp.setGiaCu(giaCu);
        sp.setGiamGia(giamGia);

        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.saveOrUpdate(sp);

        return "redirect:/";
    }

    @RequestMapping(params = "btnCapNhat")
    public String capNhat(
            @RequestParam("txtId") int id,
            @RequestParam("txtTenSanPham") String tenSanPham,
            @RequestParam("txtGiaCu") BigDecimal giaCu,
            @RequestParam("txtGiamGia") int giamGia) {
        SanPhamDAO spDAO = new SanPhamDAO();
        SanPham sp = spDAO.getById(id);
        sp.setTenSanPham(tenSanPham);
        sp.setGiaCu(giaCu);
        sp.setGiamGia(giamGia);

        spDAO.saveOrUpdate(sp);

        return "redirect:/";
    }

    @RequestMapping(params = "btnXoa")
    public String xoa(@RequestParam("idHidden") int id) {
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.delete(id);

        return "redirect:/";
    }
}

