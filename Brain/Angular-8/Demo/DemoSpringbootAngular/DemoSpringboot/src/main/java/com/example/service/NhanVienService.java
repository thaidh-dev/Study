package com.example.service;

import com.example.entity.NhanVien;
import com.example.repository.NhanVienRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TrangChu")
@CrossOrigin("http://localhost:4200")
public class NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @GetMapping(value = "/NhanVien", params = {"pageNumber", "pageSize", "orderBy", "sortBy"})
    public List<NhanVien> getAllNhanVien(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("orderBy") boolean orderBy,
            @RequestParam("sortBy") String sortBy
    ) {
        Page<NhanVien> pageable = nhanVienRepository.findAll(PageRequest.of(pageNumber, pageSize, orderBy ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return pageable.getContent();

//        return nhanVienRepository.findAllByVaiTro(vaiTro);
    }
}
