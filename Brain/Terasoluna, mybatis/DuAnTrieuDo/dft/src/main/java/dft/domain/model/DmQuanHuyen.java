package dft.domain.model;

import java.io.Serializable;


/**
 * A DmQuanHuyen.
 */
public class DmQuanHuyen implements Serializable {
    // id
    private int id;
    // Mã
    private String ma;
    // Tên
    private String ten;
    // Mổ tả
    private String moTa;
    // Mã tỉnh
    private String maTinh;
    // Cấp
    private String cap;
    // Trạng thái
    private int trangThai;

    public DmQuanHuyen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "DmQuanHuyen{" +
                "id=" + id +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", moTa='" + moTa + '\'' +
                ", maTinh='" + maTinh + '\'' +
                ", cap='" + cap + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
