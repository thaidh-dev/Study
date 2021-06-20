package com.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;

public class SinhVien {
    @NotBlank(message="Vui lòng điền tên sinh viên")
    private String tenSinhVien;

    @NotNull(message="Vui lòng nhập điểm")
    @DecimalMin(value = "0", message = "Vui lòng nhập điểm từ 0 đến 10")
    @DecimalMax(value = "10", message = "Vui lòng nhập điểm từ 0 đến 10")
    private Double diem;

    @NotNull(message="Vui lòng chọn 1 ngành")
    private Nganh nganh;

    @NotNull(message = "Vui lòng nhập ngày sinh")
    @DateTimeFormat(pattern = "dd-MM-yyyy") // chỉ dành cho thẻ form:form của riêng nó
    @Past(message = "Ngày không tồn tại")
    private Date ngaySinh;

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

    public Nganh getNganh() {
        return nganh;
    }

    public void setNganh(Nganh nganh) {
        this.nganh = nganh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

//</editor-fold>
}
