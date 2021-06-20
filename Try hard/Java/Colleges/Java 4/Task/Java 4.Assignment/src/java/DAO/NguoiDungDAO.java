package DAO;

import Helper.HibernateHelper;
import Helper.JDBCHelper;
import Hibernate.util.HibernateUtil;
import java.sql.ResultSet;
import java.util.List;
import Model.NguoiDung;
import java.sql.SQLException;
import java.util.ArrayList;

public class NguoiDungDAO implements Info_DAO<NguoiDung> {
    static HibernateHelper<NguoiDung> h = new HibernateHelper<>();
    boolean start;
    Thread t;

    public NguoiDung selectByEmailAndPass(String email, String pass) {
        String sql = "select * from nguoi_dung where email = ? and mat_khau = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql, false, email, pass);

        try {
            if (rs.next() == true) {
                NguoiDung nd = readFromRS(rs);
                if (nd.isKhoa() == false) {
                    if (nd.isTrangThai() == true) {
                        start = false;
                        
                        nd.setTrangThai(false);
                        h.update(nd);
                        
                        try {
                            t.interrupt();
                        }
                        catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }

                    return nd;
                }
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public NguoiDung selectById(int id) {
        String sql = "from NguoiDung where id = ?";
        List<NguoiDung> lstNguoiDung = h.query(sql, id);
        return lstNguoiDung.get(0);
    }
    
    public static void insert(String hoTen, String email, String matKhau) {
        String sql = "insert into nguoi_dung(ho_ten, email, mat_khau) values (?, ?, ?)";
        JDBCHelper.executeUpdate(sql, false, hoTen, email, matKhau);
    }
    
    public static void update(NguoiDung nd) {
        h.update(nd);
    }
    
    public void huyTaiKhoan(NguoiDung nd) {
        nd.setKhoa(false);
        h.update(nd);
        
        t = new Thread() {
            @Override
            public void run() {
                try {
                    start = true;
                    Thread.sleep(20000);
                    if (start == true) {
                        nd.setKhoa(true);
                        h.update(nd);
                    }
                } 
                catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        };

        t.start();
    }
    
    public static void updateMatKhau(String matKhau, int id) {
        String sql = "update nguoi_dung set mat_khau = ? where id = ?";
        JDBCHelper.executeUpdate(sql, false, matKhau, id);
    }
    
    @Override
    public List<NguoiDung> select(String sql, Object... args) {
        List<NguoiDung> lstNguoiDung = new ArrayList<>();
        
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, true, args);
            while (rs.next()) {
                NguoiDung nd = readFromRS(rs);
                lstNguoiDung.add(nd);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return lstNguoiDung;
    }

    @Override
    public NguoiDung readFromRS(ResultSet rs) {
        try {
            NguoiDung nd = new NguoiDung(rs.getInt("id"), rs.getString("email"), rs.getString("ho_ten"), rs.getString("mat_khau"), rs.getBoolean("chuc_vu"), rs.getBoolean("khoa"), rs.getBoolean("trang_thai"));
            return nd;
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
