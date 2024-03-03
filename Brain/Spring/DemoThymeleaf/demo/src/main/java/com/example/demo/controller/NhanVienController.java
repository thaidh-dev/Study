package com.example.demo.controller;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("employees", nhanVienService.getAllNhanVien());
        return "index";
    }

    @GetMapping("/add")
    public String addNewEmployee(Model model) {
        NhanVien nhanVien = new NhanVien();
        model.addAttribute("employee", nhanVien);
        return "newemployee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id) {
        nhanVienService.delete(id);
        return "redirect:/";
    }
}
