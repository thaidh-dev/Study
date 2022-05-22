package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ghi_nhan")
public class GhiNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "loai_ghi_nhan")
    private boolean loaiGhiNhan;
    
    @Column(name = "ly_do")
    private String lyDo;
    
    @Column(name = "ngay_ghi_nhan")
    private LocalDate ngayGhiNhan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien", nullable = false)
    private NhanVien nhanVien;
}
