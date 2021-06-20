package Model;

public class TaiKhoan {
    private String username, pass, ten;
    private boolean isAdmin;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String pass, String ten, boolean isAdmin) {
        this.username = username;
        this.pass = pass;
        this.ten = ten;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
