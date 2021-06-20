/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author Thanh
 */
public class Sach implements Serializable {

    private String maSach;
    private String tieuDe;
    private String tacGia;
    private int maNXB;
    private int NXB;
    private int maCD;
    private String ngonNgu;
    private int soTrang;
    private String soLuong;
    private int giaTien;
    private String tenTG;

    public Sach() {
    }

    public Sach(String maSach, String tieuDe, String tacGia, int maNXB, int NXB, int maCD, String ngonNgu, int soTrang, String soLuong, int giaTien) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.maNXB = maNXB;
        this.NXB = NXB;
        this.maCD = maCD;
        this.ngonNgu = ngonNgu;
        this.soTrang = soTrang;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    @Override
    public String toString() {
        return tieuDe ;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public int getNXB() {
        return NXB;
    }

    public void setNXB(int NXB) {
        this.NXB = NXB;
    }

    public int getMaCD() {
        return maCD;
    }

    public void setMaCD(int maCD) {
        this.maCD = maCD;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

}
