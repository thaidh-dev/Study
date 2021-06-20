package controller;

import dao.SinhVienDAO;
import javax.servlet.http.HttpServletRequest;
import model.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("form")
public class SinhVienController {
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        //model.addAttribute("lstSV", SinhVienDAO.selectAllSinhVien());
        
        return "Form";
    }
    
    @RequestMapping(params = "btnThem")
    public String them(
            @RequestParam("hoTen") String hoTen,
            @RequestParam("diem") double diem,
            @RequestParam("nganh") String nganh) {

        SinhVien sv = new SinhVien();
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(nganh);
        
        SinhVienDAO.them(sv);
        return "Form";
    }
    
    @RequestMapping(params = "btnCapNhat")
    public String capNhat(
            @RequestParam("id") int id,
            @RequestParam("hoTen") String hoTen,
            @RequestParam("diem") double diem,
            @RequestParam("nganh") String nganh) {

        SinhVien sv = new SinhVien();
        sv.setId(id);
        sv.setHoTen(hoTen);
        sv.setDiem(diem);
        sv.setNganh(nganh);
        
        SinhVienDAO.capNhat(sv);
        
        return "Form";
    }
    
    @RequestMapping(params = "btnXoa")
    public String xoa(@RequestParam("idXoa") int id) {
        SinhVienDAO.xoa(id);
        return "Form";
    }
}
