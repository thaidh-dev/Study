package com.example.demo.dao;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.GhiNhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TrangChu")
public class GhiNhanResource {
    @Autowired
    private GhiNhanRepository ghiNhanRepository;

    @PostMapping("/QuanLyGhiNhan")
    public List<GhiNhanDTO> getGhiNhanByNhanVien(@RequestBody NhanVienDTO nvDTO) {
        NhanVien nv = new NhanVien();
        nv.setIdNhanVien(nvDTO.getIdNhanVien());
        nv.setTenNhanVien(nvDTO.getTenNhanVien());

        List<GhiNhan> lstGhiNhan = ghiNhanRepository.findAllByNhanVien(nv);
        List<GhiNhanDTO> lstGhiNhanDTO = new ArrayList<>();

        for (GhiNhan gn : lstGhiNhan) {
            GhiNhanDTO gnDTO = new GhiNhanDTO();
            gnDTO.setIdHoSo(gn.getIdHoSo());
            gnDTO.setLoaiGhiNhan(gn.isLoaiGhiNhan());
            gnDTO.setLyDo(gn.getLyDo());
            gnDTO.setNgayGhiNhan(gn.getNgayGhiNhan());
            gnDTO.setNhanVienDTO(nvDTO);

            lstGhiNhanDTO.add(gnDTO);
        }

        return lstGhiNhanDTO;
    }

    @PostMapping("/QuanLyGhiNhan/Them")
    public void themGhiNhan(@RequestBody GhiNhanDTO gnDTO) {
        ghiNhanRepository.save(chuyenGNThanhGNDTO(gnDTO));
    }

    @PutMapping("/QuanLyGhiNhan")
    public void suaGhiNhan(@RequestBody GhiNhanDTO gnDTO) {
        ghiNhanRepository.save(chuyenGNThanhGNDTO(gnDTO));
    }

    @DeleteMapping("/QuanLyGhiNhan/{idNhanVien}")
    public void xoaGhiNhan(@PathVariable int idNhanVien) {
        ghiNhanRepository.deleteById(idNhanVien);
    }

    private GhiNhan chuyenGNThanhGNDTO(GhiNhanDTO gnDTO) {
        NhanVien nv = new NhanVien();
        nv.setIdNhanVien(gnDTO.getNhanVienDTO().getIdNhanVien());
        nv.setTenNhanVien(gnDTO.getNhanVienDTO().getTenNhanVien());

        GhiNhan gn = new GhiNhan();
        gn.setIdHoSo(gnDTO.getIdHoSo());
        gn.setLoaiGhiNhan(gnDTO.isLoaiGhiNhan());
        gn.setLyDo(gnDTO.getLyDo());
        gn.setNgayGhiNhan(gnDTO.getNgayGhiNhan());
        gn.setNhanVien(nv);

        return gn;
    }
}
