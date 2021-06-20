package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "phong_ban")
public class PhongBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phong_ban")
    private int idPhongBan;

    @Column(name = "ten_phong")
    private String tenPhong;

    //<editor-fold defaultstate="collapsed" desc="hàm tạo">

    public PhongBan(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public PhongBan() {
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdPhongBan() {
        return idPhongBan;
    }

    public void setIdPhongBan(int idPhongBan) {
        this.idPhongBan = idPhongBan;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    //</editor-fold>
}
