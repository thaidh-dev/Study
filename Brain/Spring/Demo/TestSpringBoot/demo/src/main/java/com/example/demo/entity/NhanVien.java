package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "email")
    private String email;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "luong")
    private BigDecimal luong;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong_ban", nullable = true)
//    sử dụng 1 trong 2: @JsonIgnore or @JsonProperty
//    @JsonIgnore is used at field level to mark a property or list of properties to be ignored.
//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) is for the REST APIs section below to ignore the property when serializing it to JSON string,
//    due to library is a LAZY association which can throw LazyInitializationException if it is uninitialized in a non-transactional context
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PhongBan phongBan;
}
