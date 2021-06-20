package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ghi_nhan")
public class GhiNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ho_so")
    private int idHoSo;
    
    @Column(name = "loai_ghi_nhan")
    private boolean loaiGhiNhan;
    
    @Column(name = "ly_do")
    private String lyDo;
    
    @Column(name = "ngay_ghi_nhan")
    private LocalDate ngayGhiNhan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    //<editor-fold defaultstate="collapsed" desc="hàm tạo">

    public GhiNhan() {
    }

    public GhiNhan(boolean loaiGhiNhan, String lyDo, LocalDate ngayGhiNhan) {
        this.loaiGhiNhan = loaiGhiNhan;
        this.lyDo = lyDo;
        this.ngayGhiNhan = ngayGhiNhan;
    }

    public GhiNhan(boolean loaiGhiNhan, String lyDo, LocalDate ngayGhiNhan, NhanVien nhanVien) {
        this.loaiGhiNhan = loaiGhiNhan;
        this.lyDo = lyDo;
        this.ngayGhiNhan = ngayGhiNhan;
        this.nhanVien = nhanVien;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdHoSo() {
        return idHoSo;
    }

    public void setIdHoSo(int idHoSo) {
        this.idHoSo = idHoSo;
    }

    public boolean isLoaiGhiNhan() {
        return loaiGhiNhan;
    }

    public void setLoaiGhiNhan(boolean loaiGhiNhan) {
        this.loaiGhiNhan = loaiGhiNhan;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public LocalDate getNgayGhiNhan() {
        return ngayGhiNhan;
    }

    public void setNgayGhiNhan(LocalDate ngayGhiNhan) {
        this.ngayGhiNhan = ngayGhiNhan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    //</editor-fold>
}
