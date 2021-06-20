package Model;

public class NguoiDung {
    private int id;
    private String email;
    private String hoTen;
    private String matKhau;
    private boolean chucVu;
    private boolean khoa;
    private boolean trangThai;

    public NguoiDung() {
    }

    public NguoiDung(int id, String email, String hoTen, String matKhau, boolean chucVu, boolean khoa, boolean trangThai) {
        this.id = id;
        this.email = email;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.khoa = khoa;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }
    
    public boolean isKhoa() {
        return khoa;
    }

    public void setKhoa(boolean khoa) {
        this.khoa = khoa;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
