package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NhanVienDTO {
    private int idNhanVien;
    private String tenNhanVien;
    private boolean gioiTinh;
    private LocalDate ngaySinh;
    private String email;
    private String sdt;
    private BigDecimal luong;
    private String ghiChu;
    private PhongBanDTO phongBan;

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public BigDecimal getLuong() {
        return luong;
    }

    public void setLuong(BigDecimal luong) {
        this.luong = luong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public PhongBanDTO getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBanDTO phongBan) {
        this.phongBan = phongBan;
    }

//</editor-fold>
}
