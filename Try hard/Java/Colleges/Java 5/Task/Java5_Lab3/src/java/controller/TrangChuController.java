package controller;

import dao.*;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("form")
public class TrangChuController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showTrangChu() {
        return "TrangChu";
    }

    @ModelAttribute("nganh")
    public List<Nganh> getAllNganh() {
        NganhDAO ndDAO = new NganhDAO();
        return ndDAO.getAllNganh();
    }

    @ModelAttribute("sinhVien")
    public List<SinhVien> getAllSinhVien() {
        SinhVienDAO svDAO = new SinhVienDAO();
        return svDAO.getAllSinhVien();
    }

    @RequestMapping(params = "btnSua")
    public String sua(
            @RequestParam("idSinhVien") int idSinhVien,
            @RequestParam("hoTen") String hoTen,
            @RequestParam("diem") double diem,
            @RequestParam("idNganh") int idNganh,
            Model model) {
        Nganh n = new Nganh();
        n.setIdNganh(idNganh);

        SinhVien sv = new SinhVien();
        sv.setIdSinhVien(idSinhVien);
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(n);

        model.addAttribute("sinhVienSua", sv);
        return "TrangChu";
    }

    @RequestMapping(params = "btnThem")
    public String them(
            @RequestParam("hoTen") String hoTen,
            @RequestParam("diem") double diem,
            @RequestParam("idNganh") int idNganh) {

        Nganh n = new NganhDAO().getById(idNganh);

        SinhVien sv = new SinhVien();
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(n);

        SinhVienDAO svDAO = new SinhVienDAO();
        svDAO.saveOrUpdate(sv);

        return "redirect:/form";
    }

    @RequestMapping(params = "btnCapNhat")
    public String capNhat(
            @RequestParam("idSinhVien") int idSinhVien,
            @RequestParam("hoTen") String hoTen,
            @RequestParam("diem") double diem,
            @RequestParam("idNganh") int idNganh) {

        Nganh n = new NganhDAO().getById(idNganh);

        SinhVienDAO svDAO = new SinhVienDAO();
        SinhVien sv = svDAO.getById(idSinhVien);
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(n);

        svDAO.saveOrUpdate(sv);

        return "redirect:/form";
    }

    @RequestMapping(params = "btnXoa")
    public String xoa(@RequestParam("idSinhVien") int idSinhVien) {
        SinhVienDAO svDAO = new SinhVienDAO();
        svDAO.delete(idSinhVien);

        return "redirect:/form";
    }
}
