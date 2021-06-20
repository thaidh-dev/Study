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
public class NhaXuatBan implements Serializable {

    private int maNXB;
    private String tenNXB;
    private String SDT;
    private String diaChi;

    public NhaXuatBan() {
    }

    @Override
    public String toString() {
        return tenNXB ;
    }

    public NhaXuatBan(int maNXB, String tenNXB, String SDT, String diaChi) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.SDT = SDT;
        this.diaChi = diaChi;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

}
