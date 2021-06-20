package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nganh")
public class Nganh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nganh")
    private int idNganh;

    @Column(name = "ten_nganh")
    private String tenNganh;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nganh")
    List<SinhVien> lstSinhVien;

    //<editor-fold defaultstate="collapsed" desc="hàm tạo">
    public Nganh() {
    }

    public Nganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public List<SinhVien> getLstSinhVien() {
        return lstSinhVien;
    }

    public void setLstSinhVien(List<SinhVien> lstSinhVien) {
        this.lstSinhVien = lstSinhVien;
    }

    //</editor-fold>
}
