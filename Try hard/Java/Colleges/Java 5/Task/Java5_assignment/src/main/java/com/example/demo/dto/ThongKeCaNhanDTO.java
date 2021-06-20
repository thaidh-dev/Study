package com.example.demo.dto;

public class ThongKeCaNhanDTO {
    private int idThongKeCaNhan;
    private String tenNhanVien;
    private int tongThanhTich;
    private int tongKyLuat;
    private int diemThuong;

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdThongKeCaNhan() {
        return idThongKeCaNhan;
    }

    public void setIdThongKeCaNhan(int idThongKeCaNhan) {
        this.idThongKeCaNhan = idThongKeCaNhan;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getTongThanhTich() {
        return tongThanhTich;
    }

    public void setTongThanhTich(int tongThanhTich) {
        this.tongThanhTich = tongThanhTich;
    }

    public int getTongKyLuat() {
        return tongKyLuat;
    }

    public void setTongKyLuat(int tongKyLuat) {
        this.tongKyLuat = tongKyLuat;
    }

    public int getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        this.diemThuong = diemThuong;
    }

    //</editor-fold>
}
