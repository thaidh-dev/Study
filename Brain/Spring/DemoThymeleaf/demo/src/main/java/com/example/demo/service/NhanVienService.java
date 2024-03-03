package com.example.demo.service;

import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public void delete(int id) {
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(id);
        optionalNhanVien.ifPresent(nhanVien -> nhanVienRepository.delete(nhanVien));
    }
}
