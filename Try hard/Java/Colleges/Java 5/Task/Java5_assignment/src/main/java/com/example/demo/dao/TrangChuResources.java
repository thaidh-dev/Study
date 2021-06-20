package com.example.demo.dao;

import com.example.demo.dto.ThanhTichCaoNhatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TrangChuResources {
    @Autowired
    EntityManager em;

    @GetMapping("/TrangChu")
    public List<ThanhTichCaoNhatDTO> get() {
        String sql = "select ten_nhan_vien, ten_phong, diem_thuong from\n" +
                "(\n" +
                "\tselect ten_nhan_vien, ten_phong, \n" +
                "\t\tdbo.fn_thuong_phat(id_nhan_vien, 1) - dbo.fn_thuong_phat(id_nhan_vien, 0) diem_thuong\n" +
                "\tfrom nhan_vien\n" +
                "\tinner join phong_ban on phong_ban.id_phong_ban = nhan_vien.id_phong_ban\n" +
                ") as tbl\n" +
                "where tbl.[diem_thuong] = (\n" +
                "\tselect max(\n" +
                "\t\tdbo.fn_thuong_phat(id_nhan_vien, 1) - dbo.fn_thuong_phat(id_nhan_vien, 0)\n" +
                "\t) \n" +
                "\tfrom nhan_vien\n" +
                ")";

        List<Object[]> lstTTCNDTO = em.createNativeQuery(sql).getResultList();
        List<ThanhTichCaoNhatDTO> lstTTCN = new ArrayList<>();

        for (Object[] o : lstTTCNDTO) {
            ThanhTichCaoNhatDTO tt = new ThanhTichCaoNhatDTO();
            tt.setTenNhanVien(String.valueOf(o[0]));
            tt.setTenPhong(String.valueOf(o[1]));
            tt.setDiemThuong(Integer.parseInt(String.valueOf(o[2])));

            lstTTCN.add(tt);
        }

        return lstTTCN;
    }
}
