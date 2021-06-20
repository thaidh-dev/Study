package dft.domain.dto.ttCaNhanDTO;

import dft.domain.model.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class TtCaNhanDTO {
    private Long id;

    @NotBlank(message = "Vui lòng nhập mã y tế cá nhân")
    @Pattern(regexp = "( *|[A-Z0-9]{0,13})", message = "Mã y tế chỉ chứa chữ hoa, số và không quá 13 ký tự")
    private String maYTeCaNhan;

    @NotBlank(message = "Vui lòng nhập họ tên")
    @Pattern(regexp = "[a-zA-Z \\u0080-\\u9fff]*", message = "Vui lòng nhập tên không chứa các ký tự đặc biệt")
    @Size(max = 255, message = "Vui lòng nhập họ tên không quá 255 ký tự")
    private String hoTen;

    private DMGioiTinh gioiTinh;

    @NotBlank(message = "Vui lòng nhập ngày sinh")
    @Pattern(regexp = " *((([0-2][0-9])|(3[0-1]))-((0[0-9])|(1[0-2]))-\\d{4})*",
            message = "Vui lòng nhập ngày sinh thep định dạng dd-MM-yyyy")
    private String ngaySinh;

    @NotNull(message = "Vui lòng chọn 1 tỉnh")
    private DmTinhTP ttTinh;

    @NotNull(message = "Vui lòng chọn 1 huyện")
    private DmQuanHuyen ttHuyen;

    @NotNull(message = "Vui lòng chọn 1 xã")
    private DmXaPhuong ttXa;

    @NotNull(message = "Vui lòng chọn 1 thôn")
    private DmThonXom ttThonXom;

    //<editor-fold defaultstate="collapsed" desc="get và set">
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

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
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
    //</editor-fold>

}
