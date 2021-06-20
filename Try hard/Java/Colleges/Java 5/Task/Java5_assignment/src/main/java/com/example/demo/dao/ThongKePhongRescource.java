package com.example.demo.dao;

import com.example.demo.dto.ThongKePhongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TrangChu")
public class ThongKePhongRescource {
    @Autowired
    EntityManager em;

    @GetMapping("/ThongKeThanhTichPhong")
    public List<ThongKePhongDTO> get() {
        String sql = "select ten_phong, \n" +
                "\tdbo.fn_thuong_phat_2(id_phong_ban, 1) thuong, \n" +
                "\tdbo.fn_thuong_phat_2(id_phong_ban, 0) phat,\n" +
                "\tdbo.fn_thuong_phat_2(id_phong_ban, 1) - dbo.fn_thuong_phat_2(id_phong_ban, 0) diem_thuong\n" +
                "from phong_ban";

        List<Object[]> lstTKPDTO = em.createNativeQuery(sql).getResultList();
        List<ThongKePhongDTO> lstTKP = new ArrayList<>();

        for (Object[] o : lstTKPDTO) {
            ThongKePhongDTO tk = new ThongKePhongDTO();
            tk.setTenPhong(String.valueOf(o[0]));
            tk.setTongThanhTich(Integer.parseInt(String.valueOf(o[1])));
            tk.setTongKyLuat(Integer.parseInt(String.valueOf(o[2])));
            tk.setDiemThuong(Integer.parseInt(String.valueOf(o[3])));

            lstTKP.add(tk);
        }

        return lstTKP;
    }
}
