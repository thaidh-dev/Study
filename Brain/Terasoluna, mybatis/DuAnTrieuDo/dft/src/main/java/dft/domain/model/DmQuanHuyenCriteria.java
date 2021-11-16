package dft.domain.model;

import java.io.Serializable;


/**
 * A DmQuanHuyen.
 */
public class DmQuanHuyenCriteria implements Serializable {

    // Mã
    private String ma;
    // Tên
    private String ten;
    // Cấp
    private String cap;


    public DmQuanHuyenCriteria() {
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

}
