package DAO;

import Helper.*;
import hibernate.entity.*;
import java.util.List;

public class PhongBanDAO {
    static HibernateHelper<PhongBan> h = new HibernateHelper<>();
    
    public static List<PhongBan> selectAll() {
        String sql = "from PhongBan";
        List<PhongBan> lstPhongBan = h.query(sql);
        return lstPhongBan;
    }
    
    public static List<PhongBan> selectByName(String tenPhong) {
        String sql = "from PhongBan where tenPhong = ?";
        List<PhongBan> lstPhongBan = h.query(sql, tenPhong);
        return lstPhongBan;
    }
}
