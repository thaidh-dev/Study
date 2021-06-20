package dao;

import helper.HibernateHelper;
import model.SinhVien;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class SinhVienDAO {
    HibernateHelper<SinhVien> h = new HibernateHelper<>();

    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> lstSinhVien = h.query("from SinhVien");
        return lstSinhVien;
    }

    public List<SinhVien> getSinhVienDiemCaoNhat() {
        List<SinhVien> lstSinhVien = h.query("from SinhVien where diem = (select max(diem) from SinhVien)");
        return lstSinhVien;
    }

    public SinhVien getById(int id) {
        SinhVien sv = h.get("model.SinhVien", id);
        return sv;
    }

    public void saveOrUpdate(SinhVien sv) {
        h.saveOrUpdate(sv);
    }

    public void xoa(int id) {
        h.delete("model.SinhVien", id);
    }

    public String xepLoai(double diem) {
        if (diem >= 0 && diem < 3) {
            return "Yếu";
        }
        if (diem >= 3 && diem < 5) {
            return "Trung bình";
        }
        if (diem >= 5 && diem < 8) {
            return "Khá";
        }
        if (diem >= 8 && diem <= 10) {
            return "Giỏi";
        }
        return null;
    }
}
