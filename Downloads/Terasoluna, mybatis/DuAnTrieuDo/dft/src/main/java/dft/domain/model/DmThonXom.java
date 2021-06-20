package dft.domain.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Danh mục thôn xóm
 */
public class DmThonXom implements Serializable {
    /* Thuộc tính: Id thôn, xóm*/
    private int id;

    /* Thuộc tính: mã thôn, xóm*/
    private String ma;

    /* Thuộc tính: tên thôn, xóm*/
    private String ten;

    /* Thuộc tính: mô tả thôn, xóm*/
    private String moTa;

    /* Thuộc tính: mã tỉnh*/
    private String maTinh;

    /* Thuộc tính: mã huyện*/
    private String maHuyen;

    /* Thuộc tính: mã xã*/
    private String maXa;

    /* Thuộc tính: trạng thái hoạt động của thôn xóm*/
    private int trangThai;

    public DmThonXom() {
    }

    public DmThonXom(int id, String ma, String ten, String moTa, String maTinh, String maHuyen, String maXa, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
        this.maTinh = maTinh;
        this.maHuyen = maHuyen;
        this.maXa = maXa;
        this.trangThai = trangThai;
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

    public String getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getMaXa() {
        return maXa;
    }

    public void setMaXa(String maXa) {
        this.maXa = maXa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
