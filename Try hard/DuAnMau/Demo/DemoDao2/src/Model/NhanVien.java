package Model;

public class NhanVien {
    private String taiKhoan, ten, matKhau;

    public NhanVien() {
    }

    public NhanVien(String taiKhoan, String ten, String matKhau) {
        this.taiKhoan = taiKhoan;
        this.ten = ten;
        this.matKhau = matKhau;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
