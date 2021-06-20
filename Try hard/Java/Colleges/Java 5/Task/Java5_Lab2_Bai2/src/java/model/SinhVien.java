package model;

public class SinhVien {
    private int id;
    private String hoTen;
    private double diem;
    private String nganh;

    public SinhVien() {
    }

    public SinhVien(int id, String hoTen, double diem, String nganh) {
        this.id = id;
        this.hoTen = hoTen;
        this.diem = diem;
        this.nganh = nganh;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }
    
    
}
