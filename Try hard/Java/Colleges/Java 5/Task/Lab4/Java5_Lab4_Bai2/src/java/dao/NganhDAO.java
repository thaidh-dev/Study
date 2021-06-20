package dao;

import helper.HibernateHelper;
import model.Nganh;
import model.SinhVien;

import java.util.List;

public class NganhDAO {
    HibernateHelper<Nganh> h = new HibernateHelper<>();

    public List<Nganh> getAllNganh() {
        List<Nganh> lstNganh = h.query("from Nganh");
        return lstNganh;
    }

    public Nganh getById(int id) {
        Nganh n = h.get("model.Nganh", id);
        return n;
    }
}
