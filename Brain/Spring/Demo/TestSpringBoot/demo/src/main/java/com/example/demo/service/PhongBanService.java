package com.example.demo.service;

import com.example.demo.entity.PhongBan;
import com.example.demo.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    PhongBanRepository phongBanRepository;

    public List<PhongBan> getAllPhongBan() {
        return phongBanRepository.findAll();
    }
}
