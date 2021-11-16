package dft.domain.dto.dmThonXom;

public class DmThonXomDTO {
    private int id;
    private String ma;
    private String ten;
    private String moTa;
    private String tinh;
    private String huyen;
    private String xa;
    private String trangThai;

    public DmThonXomDTO() {
    }

    public DmThonXomDTO(int id,String ma, String ten, String moTa, String tinh, String huyen, String xa, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
        this.tinh = tinh;
        this.huyen = huyen;
        this.xa = xa;
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

    public String getMoTaThon() {
        return moTa;
    }

    public void setMoTaThon(String moTaThon) {
        this.moTa = moTaThon;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getHuyen() {
        return huyen;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
