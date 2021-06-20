/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import helper.DateHelper;
import java.util.Date;

/**
 *
 * @author Thanh
 */
public class MuonTra {

    private int theDG;
    private String tenDG;
    private int maPM;
    private String maSach;
    private String tenSach;
    private Date ngayMuon = DateHelper.now();
    private Date ngayTra;
    private Date ngayHen;
    private boolean trangThai;
    private String maNV;
    private String tenNV;

    public MuonTra() {
    }

    public MuonTra(int theDG, String tenDG, int maPM, String maSach, String tenSach, Date ngayTra, Date ngayHen, boolean trangThai, String maNV, String tenNV) {
        this.theDG = theDG;
        this.tenDG = tenDG;
        this.maPM = maPM;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayTra = ngayTra;
        this.ngayHen = ngayHen;
        this.trangThai = trangThai;
        this.maNV = maNV;
        this.tenNV = tenNV;
    }

    public int getTheDG() {
        return theDG;
    }

    public void setTheDG(int theDG) {
        this.theDG = theDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    

}
