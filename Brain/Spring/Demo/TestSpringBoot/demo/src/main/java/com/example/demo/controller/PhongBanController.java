package com.example.demo.controller;

import com.example.demo.entity.PhongBan;
import com.example.demo.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhongBanController {
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping("/getAllPhongBan")
    public ResponseEntity<List<PhongBan>> getAllPhongBan() {
        return ResponseEntity.ok(phongBanService.getAllPhongBan());
    }

    @PostMapping("/insertPhongBan")
    public ResponseEntity<PhongBan> insertPhongBan(@RequestBody PhongBan phongBan) {
        return ResponseEntity.ok(phongBanService.insertPhongBan(phongBan));
    }
}
