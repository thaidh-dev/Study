package DAO;

import Helper.HibernateHelper;
import hibernate.entity.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    static HibernateHelper<NhanVien> h = new HibernateHelper();

    public static List<NhanVien> selectAll() {
        String sql = "from NhanVien";
        List<NhanVien> lstNhanVien = h.query(sql);
        return lstNhanVien;
    }

    public static List<NhanVien> find(String ten) {
        String sql = "from NhanVien where tenNhanVien = ?";
        List<NhanVien> lstNhanVien = h.query(sql, ten);
        return lstNhanVien;
    }
    
    public static void delete(String entity, int id) {
        h.delete(entity, id);
    }

    public static void update(NhanVien nv) {
        h.update(nv);
    }
    
    public static NhanVien getByID(int id) {
        NhanVien nv = h.get("hibernate.entity.NhanVien", id);
        return nv;
    }
    
    public static void insert(NhanVien nv) {
        h.insert(nv);
    }
}
