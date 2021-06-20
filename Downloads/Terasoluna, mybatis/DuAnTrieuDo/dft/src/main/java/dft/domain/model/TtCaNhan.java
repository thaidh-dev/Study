package dft.domain.model;

public class TtCaNhan {
    private Long id;
    private String maYTeCaNhan;
    private String hoTen;
    private DMGioiTinh gioiTinh;
    private Integer ngaySinh;
    private DmTinhTP ttTinh;
    private DmQuanHuyen ttHuyen;
    private DmXaPhuong ttXa;
    private DmThonXom ttThonXom;
    private DmTinhTP htTinh;
    private DmQuanHuyen htHuyen;
    private DmXaPhuong htXa;
    private DmThonXom htThonXom;
    private boolean trangThai;

    //<editor-fold defaultstate="collapsed" desc="set và get">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaYTeCaNhan() {
        return maYTeCaNhan;
    }

    public void setMaYTeCaNhan(String maYTeCaNhan) {
        this.maYTeCaNhan = maYTeCaNhan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public DMGioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(DMGioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Integer ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public DmTinhTP getTtTinh() {
        return ttTinh;
    }

    public void setTtTinh(DmTinhTP ttTinh) {
        this.ttTinh = ttTinh;
    }

    public DmQuanHuyen getTtHuyen() {
        return ttHuyen;
    }

    public void setTtHuyen(DmQuanHuyen ttHuyen) {
        this.ttHuyen = ttHuyen;
    }

    public DmXaPhuong getTtXa() {
        return ttXa;
    }

    public void setTtXa(DmXaPhuong ttXa) {
        this.ttXa = ttXa;
    }

    public DmThonXom getTtThonXom() {
        return ttThonXom;
    }

    public void setTtThonXom(DmThonXom ttThonXom) {
        this.ttThonXom = ttThonXom;
    }

    public DmTinhTP getHtTinh() {
        return htTinh;
    }

    public void setHtTinh(DmTinhTP htTinh) {
        this.htTinh = htTinh;
    }

    public DmQuanHuyen getHtHuyen() {
        return htHuyen;
    }

    public void setHtHuyen(DmQuanHuyen htHuyen) {
        this.htHuyen = htHuyen;
    }

    public DmXaPhuong getHtXa() {
        return htXa;
    }

    public void setHtXa(DmXaPhuong htXa) {
        this.htXa = htXa;
    }

    public DmThonXom getHtThonXom() {
        return htThonXom;
    }

    public void setHtThonXom(DmThonXom htThonXom) {
        this.htThonXom = htThonXom;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
//</editor-fold>
}
