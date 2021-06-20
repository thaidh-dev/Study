/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author Thanh
 */
public class TacGia {

    private int maTG;
    private String TacGia;
    private Date ngaySinh;
    private boolean gioiTinh;

    public TacGia() {
    }

    public TacGia(int maTG, String TacGia, Date ngaySinh, boolean gioiTinh) {
        this.maTG = maTG;
        this.TacGia = TacGia;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

}
