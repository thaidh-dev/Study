package dao;

import helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SinhVien;

public class SinhVienDAO {
    public static List<SinhVien> selectAllSinhVien() {
        String sql = "select * from sinh_vien";
        return select(sql);
    }
    
    public static void them(SinhVien sv) {
        String sql = "insert into sinh_vien(ho_ten, diem, nganh) values (?, ?, ?)";
        JDBCHelper.executeUpdate(sql, false, sv.getHoTen(), sv.getDiem(), sv.getNganh());
    }
    
    public static void capNhat(SinhVien sv) {
        String sql = "update sinh_vien set ho_ten = ?, diem = ?, nganh = ? where id = ?";
        JDBCHelper.executeUpdate(sql, false, sv.getHoTen(), sv.getDiem(), sv.getNganh(), sv.getId());
    }
    
    public static void xoa(int id) {
        String sql = "delete from sinh_vien where id = ?";
        JDBCHelper.executeUpdate(sql, false, id);
    }
    
    public static List<SinhVien> select(String sql, Object...args) {
        List<SinhVien> lst = new ArrayList<>();
        
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, false, args);
           
            while (rs.next()) {
                SinhVien sv = readFromRS(rs);
                lst.add(sv);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return lst;
    }
    
    public static SinhVien readFromRS(ResultSet rs) {
        try {
            SinhVien sv = new SinhVien();
            sv.setId(rs.getInt("id"));
            sv.setHoTen(rs.getString("ho_ten"));
            sv.setDiem(rs.getDouble("diem"));
            sv.setNganh(rs.getString("nganh"));
            
            return sv;
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
}
