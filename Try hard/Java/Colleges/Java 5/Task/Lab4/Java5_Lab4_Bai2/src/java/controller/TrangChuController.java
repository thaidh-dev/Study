package controller;

import dao.NganhDAO;
import dao.SinhVienDAO;
import dto.SinhVienDTO;
import model.Nganh;
import model.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("")
public class TrangChuController {
    @RequestMapping("")
    public String showTrangChu() {
        return "TrangChu";
    }

    @ModelAttribute("sinhVien")
    public List<SinhVienDTO> getAllSinhVien() {
        SinhVienDAO svDAO = new SinhVienDAO();

        List<SinhVien> lstSinhVien = svDAO.getAllSinhVien();
        List<SinhVien> lstSinhVienDiemCaoNhat = svDAO.getSinhVienDiemCaoNhat();
        List<SinhVienDTO> lstSinhVienDTO = new ArrayList<>();

        for (SinhVien sv : lstSinhVien) {
            SinhVienDTO svDTO = new SinhVienDTO();
            svDTO.setIdSinhVien(sv.getIdSinhVien());
            svDTO.setHoTen(sv.getHoTen());
            svDTO.setDiem(sv.getDiem());
            svDTO.setNganh(sv.getNganh());
            svDTO.setXepLoai(xepLoai(sv.getDiem()));

            for (SinhVien svDiemCaoNhat : lstSinhVienDiemCaoNhat) {
                if (sv.getIdSinhVien() == svDiemCaoNhat.getIdSinhVien()) {
                    svDTO.setOngVang("Ong vàng");
                    break;
                }
            }

            lstSinhVienDTO.add(svDTO);
        }

        return lstSinhVienDTO;
    }

    @ModelAttribute("nganh")
    private List<Nganh> getAllNganh() {
        NganhDAO nDAO = new NganhDAO();
        return nDAO.getAllNganh();
    }

    public String xepLoai(double diem) {
        SinhVienDAO svDAO = new SinhVienDAO();
        return svDAO.xepLoai(diem);
    }

    @RequestMapping(params = "btnThem")
    public String them(
            @RequestParam("txtHoTen") String hoTen,
            @RequestParam("txtDiem") double diem,
            @RequestParam("cboNganh") int idNganh) {
        Nganh n = new NganhDAO().getById(idNganh);

        SinhVien sv = new SinhVien();
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(n);

        SinhVienDAO svDAO = new SinhVienDAO();
        svDAO.saveOrUpdate(sv);

        return "redirect:/";
    }

    @RequestMapping(params = "btnXoa")
    public String xoa(@RequestParam("idSinhVienHidden") int idSinhVien) {
        SinhVienDAO svDAO = new SinhVienDAO();
        svDAO.xoa(idSinhVien);

        return "redirect:/";
    }

    @RequestMapping(params = "btnSua")
    public String sua(@RequestParam("idSinhVienHidden") int idSinhVien, Model model) {
        SinhVien sv = new SinhVienDAO().getById(idSinhVien);
        model.addAttribute("sinhVienSua", sv);

        return "TrangChu";
    }

    @RequestMapping(params = "btnCapNhat")
    public String capNhat(
            @RequestParam("txtIdSinhVien") int idSinhVien,
            @RequestParam("txtHoTen") String hoTen,
            @RequestParam("txtDiem") double diem,
            @RequestParam("cboNganh") int idNganh) {
        SinhVienDAO svDAO = new SinhVienDAO();
        Nganh n = new NganhDAO().getById(idNganh);

        SinhVien sv = svDAO.getById(idSinhVien);
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(n);

        svDAO.saveOrUpdate(sv);

        return "redirect:/";
    }
}
