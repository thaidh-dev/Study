package com.model;

public class Nganh {
    private int idNganh;
    private String tenNganh;

    //<editor-fold defaultstate="collapsed" desc="hàm tạo">

    public Nganh() {
    }

    public Nganh(int idNganh, String tenNganh) {
        this.idNganh = idNganh;
        this.tenNganh = tenNganh;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    //</editor-fold>
}
