package com.example.demo.dao;

import com.example.demo.dto.NhanVienDTO;
import com.example.demo.dto.PhongBanDTO;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TrangChu")
public class NhanVienResource {
    @Autowired
    NhanVienRepository nhanVienRepository;

    @GetMapping("/QuanLyNhanVien")
    public List<NhanVienDTO> getAllNhanVien() {
        List<NhanVien> lstNhanVien = nhanVienRepository.findAll();
        List<NhanVienDTO> lstNhanVienDTO = new ArrayList<>();

        for (NhanVien nv : lstNhanVien) {
            NhanVienDTO nvDTO = chuyenNVThanhNVDTO(nv);
//            BeanUtils.copyProperties(nv, nvDTO, "phongBan");
            lstNhanVienDTO.add(nvDTO);
        }

        return lstNhanVienDTO;
    }

    @GetMapping("/QuanLyNhanVien/findByEmail/{email}")
    public NhanVienDTO getByEmail(@PathVariable String email) {
        NhanVien nv = nhanVienRepository.findAllByEmail(email);

        if (nv != null) {
            return chuyenNVThanhNVDTO(nv);
        }

        return null;
    }

    @GetMapping("/QuanLyNhanVien/findBySdt/{sdt}")
    public NhanVienDTO getBySdt(@PathVariable String sdt) {
        NhanVien nv = nhanVienRepository.findAllBySdt(sdt);

        if (nv != null) {
            return chuyenNVThanhNVDTO(nv);
        }

        return null;
    }

    @PostMapping("/QuanLyNhanVien")
    public void themNhanVien(@RequestBody NhanVien nv) {
        nv.setNgaySinh(congThem1Ngay(nv.getNgaySinh()));
        nhanVienRepository.save(nv);
    }

    @PutMapping("/QuanLyNhanVien")
    public void suaNhanVien(@RequestBody NhanVien nv) {
        nhanVienRepository.save(nv);
    }

    @DeleteMapping("/QuanLyNhanVien/{idNhanVien}")
    public void xoaNhanVien(@PathVariable int idNhanVien) {
        nhanVienRepository.deleteById(idNhanVien);
    }

    private NhanVienDTO chuyenNVThanhNVDTO(NhanVien nv) {
        NhanVienDTO nvDTO = new NhanVienDTO();
        PhongBanDTO pbDTO = new PhongBanDTO();

        pbDTO.setIdPhongBan(nv.getPhongBan().getIdPhongBan());
        pbDTO.setTenPhong(nv.getPhongBan().getTenPhong());

        nvDTO.setIdNhanVien(nv.getIdNhanVien());
        nvDTO.setTenNhanVien(nv.getTenNhanVien());
        nvDTO.setGioiTinh(nv.isGioiTinh());
        nvDTO.setNgaySinh(nv.getNgaySinh());
        nvDTO.setEmail(nv.getEmail());
        nvDTO.setSdt(nv.getSdt());
        nvDTO.setLuong(nv.getLuong());
        nvDTO.setGhiChu(nv.getGhiChu());
        nvDTO.setPhongBan(pbDTO);

        return nvDTO;
    }

    private LocalDate congThem1Ngay(LocalDate ld) {
        return ld.plusDays(1);
    }
}
