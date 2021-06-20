package dto;

import model.Nganh;

public class SinhVienDTO {
    private int idSinhVien;
    private String hoTen;
    private double diem;
    private Nganh nganh;
    private String xepLoai;
    private String ongVang;

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdSinhVien() {
        return idSinhVien;
    }

    public void setIdSinhVien(int idSinhVien) {
        this.idSinhVien = idSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public String getOngVang() {
        return ongVang;
    }

    public void setOngVang(String ongVang) {
        this.ongVang = ongVang;
    }


    //</editor-fold>

}
