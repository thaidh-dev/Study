package com.example.demo.repository;

import com.example.demo.entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongBanRepository extends JpaRepository<PhongBan, Integer> {
    PhongBan findAllByTenPhong(String tenPhong);
}
