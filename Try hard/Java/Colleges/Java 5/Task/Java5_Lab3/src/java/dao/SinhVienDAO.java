package dao;

import helper.HibernateHelper;
import model.SinhVien;
import org.hibernate.query.Query;

import java.util.List;

public class SinhVienDAO {
    HibernateHelper<SinhVien> h = new HibernateHelper<>();

    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> lstSinhVien = h.query("from SinhVien");
        return  lstSinhVien;
    }

    public void saveOrUpdate(SinhVien sv) {
        h.saveOrUpdate(sv);
    }

    public SinhVien getById(int id) {
        SinhVien sv = h.get("model.SinhVien", id);
        return sv;
    }

    public void delete(int id) {
        h.delete("model.SinhVien", id);
    }
}
