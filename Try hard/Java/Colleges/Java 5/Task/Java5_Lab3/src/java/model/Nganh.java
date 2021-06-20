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
    private List<SinhVien> lstSinhVien;

    public Nganh() {
    }

    public Nganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    //<editor-fold defaultstate="collapsed" desc="get và set">
    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public int getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }

    public List<SinhVien> getLstSinhVien() {
        return lstSinhVien;
    }

    public void setLstSinhVien(List<SinhVien> lstSinhVien) {
        this.lstSinhVien = lstSinhVien;
    }
    //</editor-fold>
}
