package Model;

import java.util.List;

public class GioHang {
    private List<CacSanPham> cacSanPham;

    public GioHang() {
    }

    public GioHang(List<CacSanPham> cacSanPham) {
        this.cacSanPham = cacSanPham;
    }

    public List<CacSanPham> getCacSanPham() {
        return cacSanPham;
    }

    public void setCacSanPham(List<CacSanPham> cacSanPham) {
        this.cacSanPham = cacSanPham;
    }
}
