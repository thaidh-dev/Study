package com.example.demo.dto;

import java.time.LocalDate;

public class GhiNhanDTO {
    private int idHoSo;
    private boolean loaiGhiNhan;
    private String lyDo;
    private LocalDate ngayGhiNhan;
    private NhanVienDTO nhanVienDTO;

    //<editor-fold defaultstate="collapsed" desc="get và set">

    public int getIdHoSo() {
        return idHoSo;
    }

    public void setIdHoSo(int idHoSo) {
        this.idHoSo = idHoSo;
    }

    public boolean isLoaiGhiNhan() {
        return loaiGhiNhan;
    }

    public void setLoaiGhiNhan(boolean loaiGhiNhan) {
        this.loaiGhiNhan = loaiGhiNhan;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public LocalDate getNgayGhiNhan() {
        return ngayGhiNhan;
    }

    public void setNgayGhiNhan(LocalDate ngayGhiNhan) {
        this.ngayGhiNhan = ngayGhiNhan;
    }

    public NhanVienDTO getNhanVienDTO() {
        return nhanVienDTO;
    }

    public void setNhanVienDTO(NhanVienDTO nhanVienDTO) {
        this.nhanVienDTO = nhanVienDTO;
    }

    //</editor-fold>
}
