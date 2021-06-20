package dft.domain.model;

import java.io.Serializable;


/**
 * A DmTinhTP.
 */
public class DmTinhTP implements Serializable {
    // id
    private int id;
    // Mã
    private String ma;
    // Tên
    private String ten;
    // Cấp
    private String cap;
    // Trạng thái
    private int trangThai;

    public DmTinhTP() {
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
        return "DmTinhTP{" +
                "id=" + id +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", cap='" + cap + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
