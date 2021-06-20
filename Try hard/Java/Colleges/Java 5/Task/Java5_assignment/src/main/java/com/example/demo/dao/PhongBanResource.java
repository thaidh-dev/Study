package com.example.demo.dao;

import com.example.demo.dto.PhongBanDTO;
import com.example.demo.entity.PhongBan;
import com.example.demo.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TrangChu")
public class PhongBanResource {
    @Autowired
    private PhongBanRepository phongBanRepository;

    @GetMapping("/QuanLyPhongBan")
    public List<PhongBanDTO> getAllPhongBan() {
        List<PhongBan> lstPB = phongBanRepository.findAll();
        List<PhongBanDTO> lstPBDTO = new ArrayList<>();

        for (PhongBan pb : lstPB) {
            PhongBanDTO pbDTO = new PhongBanDTO();
            pbDTO.setIdPhongBan(pb.getIdPhongBan());
            pbDTO.setTenPhong(pb.getTenPhong());
            lstPBDTO.add(pbDTO);
        }

        return lstPBDTO;
    }

    @GetMapping("/QuanLyPhongBan/{tenPhong}")
    public PhongBanDTO getByTenPhong(@PathVariable String tenPhong) {
        PhongBan pb = phongBanRepository.findAllByTenPhong(tenPhong);
        if (pb != null) {
            PhongBanDTO pbDTO = new PhongBanDTO();
            pbDTO.setIdPhongBan(pb.getIdPhongBan());
            pbDTO.setTenPhong(pb.getTenPhong());

            return pbDTO;
        }

        return null;
    }

    @PostMapping("/QuanLyPhongBan")
    public void themPhongBan(@RequestBody PhongBan pb) {
        phongBanRepository.save(pb);
    }

    @PutMapping("/QuanLyPhongBan")
    public void suaPhongBan(@RequestBody PhongBan pb) {
        phongBanRepository.save(pb);
    }

    @DeleteMapping("/QuanLyPhongBan/{idPhongBan}")
    public void xoaPhongBan(@PathVariable int idPhongBan) {
        phongBanRepository.deleteById(idPhongBan);
    }
}
