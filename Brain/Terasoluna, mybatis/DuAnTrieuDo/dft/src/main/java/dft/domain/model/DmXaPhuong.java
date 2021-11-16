package dft.domain.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class DmXaPhuong implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String ma;
    private String ten;
    private String moTa;
    private String maTinh;
    private String maHuyen;
    private String cap;
    private Integer trangThai;
    private ZonedDateTime ngayTao;
    private Integer nguoiTaoId;
    private ZonedDateTime ngaySua;
    private Integer nguoiSuaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public ZonedDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(ZonedDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getNguoiTaoId() {
        return nguoiTaoId;
    }

    public void setNguoiTaoId(Integer nguoiTaoId) {
        this.nguoiTaoId = nguoiTaoId;
    }

    public ZonedDateTime getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(ZonedDateTime ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Integer getNguoiSuaId() {
        return nguoiSuaId;
    }

    public void setNguoiSuaId(Integer nguoiSuaId) {
        this.nguoiSuaId = nguoiSuaId;
    }
}
