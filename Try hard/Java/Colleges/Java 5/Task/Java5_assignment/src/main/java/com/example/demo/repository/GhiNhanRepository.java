package com.example.demo.repository;

import com.example.demo.entity.GhiNhan;
import com.example.demo.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GhiNhanRepository extends JpaRepository<GhiNhan, Integer> {
    public List<GhiNhan> findAllByNhanVien(NhanVien nv);
}
