package com.example.demo.repository;

import com.example.demo.entity.GhiNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GhiNhanRepository extends JpaRepository<GhiNhan, Integer> {
}
