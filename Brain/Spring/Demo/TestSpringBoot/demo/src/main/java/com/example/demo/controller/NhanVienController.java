package com.example.demo.controller;

import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/getAllNhanVien")
    public ResponseEntity<List<NhanVien>> getAllPhongBan() {
        return ResponseEntity.ok(nhanVienService.getAllNhanVien());
    }

    @PostMapping("/insertNhanVien")
    public void insertNhanVien() {

    }
}
