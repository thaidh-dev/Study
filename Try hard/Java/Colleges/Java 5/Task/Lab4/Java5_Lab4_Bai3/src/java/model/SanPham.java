package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "gia_cu")
    private BigDecimal giaCu;

    @Column(name = "giam_gia")
    private int giamGia;

    //<editor-fold defaultstate="collapsed" desc="constructor">

    public SanPham() {
    }

    public SanPham(String tenSanPham, BigDecimal giaCu, int giamGia) {
        this.tenSanPham = tenSanPham;
        this.giaCu = giaCu;
        this.giamGia = giamGia;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get và set">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public BigDecimal getGiaCu() {
        giaCu = giaCu.setScale(0);
        return giaCu;
    }

    public void setGiaCu(BigDecimal giaCu) {
        this.giaCu = giaCu;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    //</editor-fold>
}
