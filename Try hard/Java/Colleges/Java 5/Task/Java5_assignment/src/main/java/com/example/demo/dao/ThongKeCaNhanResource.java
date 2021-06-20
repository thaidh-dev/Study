package com.example.demo.dao;

import com.example.demo.dto.ThongKeCaNhanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TrangChu")
public class ThongKeCaNhanResource {
    @Autowired
    EntityManager em;

    @GetMapping("/ThongKeThanhTichCaNhan")
    public List<ThongKeCaNhanDTO> get() {
        String sql = "select ten_nhan_vien, \n" +
                "\tdbo.fn_thuong_phat(id_nhan_vien, 1) thuong, \n" +
                "\tdbo.fn_thuong_phat(id_nhan_vien, 0) phat,\n" +
                "\tdbo.fn_thuong_phat(id_nhan_vien, 1) - dbo.fn_thuong_phat(id_nhan_vien, 0) diem_thuong\n" +
                "from nhan_vien";

        List<ThongKeCaNhanDTO> lstTKCNDTO = new ArrayList<>();
        List<Object[]> lstTKCN = em.createNativeQuery(sql).getResultList();

        for (Object[] o : lstTKCN) {
            ThongKeCaNhanDTO tk = new ThongKeCaNhanDTO();
            tk.setTenNhanVien(String.valueOf(o[0]));
            tk.setTongThanhTich(Integer.parseInt(String.valueOf(o[1])));
            tk.setTongKyLuat(Integer.parseInt(String.valueOf(o[2])));
            tk.setDiemThuong(Integer.parseInt(String.valueOf(o[3])));

            lstTKCNDTO.add(tk);
        }


        return lstTKCNDTO;
    }
}
