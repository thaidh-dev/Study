package Model;

public class CacSanPham {
    private SanPham sanPham;
    private int soLuongDaMua;

    public CacSanPham() {
    }

    public CacSanPham(SanPham sanPham, int soLuongDaMua) {
        this.sanPham = sanPham;
        this.soLuongDaMua = soLuongDaMua;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuongDaMua() {
        return soLuongDaMua;
    }

    public void setSoLuongDaMua(int soLuongDaMua) {
        this.soLuongDaMua = soLuongDaMua;
    }
}
