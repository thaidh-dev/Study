package com.controller;

import com.model.Nganh;
import com.model.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @ModelAttribute("nganh")
    public List<Nganh> showAllNganh() {
        List<Nganh> lstNganh = new ArrayList<>();
        lstNganh.add(new Nganh(1, "Ứng dụng phần mềm"));
        lstNganh.add(new Nganh(2, "Thiết kế web"));

        return lstNganh;
    }

    @ModelAttribute("sinhVien")
    public SinhVien sinhVien() {
        return new SinhVien();
    }

    @PostMapping("/kiemLoi")
    public String kiemLoi(@ModelAttribute("sinhVien") SinhVien sv, BindingResult br) {
        if (checkTrong(sv, br)) {
            checkDiem(sv.getDiem(), br);
        }

        return "index";
    }

    private boolean checkTrong(SinhVien sv, BindingResult br) {
        boolean isEmpty = false;
        if (sv.getTenSinhVien().isEmpty()) {
            br.rejectValue("tenSinhVien", "sinhVien", "Vui lòng nhập tên");
            isEmpty = true;
        }

        if (sv.getDiem() == null) {
            br.rejectValue("diem", "sinhVien", "Vui lòng nhập điểm");
            isEmpty = true;
        }

        if (sv.getNganh() == null) {
            br.rejectValue("nganh", "sinhVien", "Vui lòng chọn 1 ngành");
            isEmpty = true;
        }

        if (isEmpty) {
            return false;
        }

        return true;
    }

    private boolean checkDiem(double diem, BindingResult br) {
        if (diem < 0 || diem > 10) {
            br.rejectValue("diem", "sinhVien", "Vui lòng nhập điểm từ 0 đến 10");
            return false;
        }

        return true;
    }
}
