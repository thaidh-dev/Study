package com.controller;

import com.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
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
    public String kiemLoi(@Validated @ModelAttribute("sinhVien") SinhVien sv, BindingResult br) {
//        Date ns = sv.getNgaySinh();
//        Date now = new Date();
//        long muoiTamNam = 567648000000L;
//        if (ns.getTime() - now.getTime() < muoiTamNam) {
//            br.rejectValue("ngaySinh", "sinhVien", "Nhân viên chưa đủ 18 tuổi");
//        }

        return "index";
    }


}
