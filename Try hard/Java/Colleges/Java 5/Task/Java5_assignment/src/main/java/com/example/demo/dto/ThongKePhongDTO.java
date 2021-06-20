package com.example.demo.dto;

public class ThongKePhongDTO {
    private String tenPhong;
    private int tongThanhTich;
    private int tongKyLuat;
    private int diemThuong;

    //<editor-fold defaultstate="collapsed" desc="get và set">
    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
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
