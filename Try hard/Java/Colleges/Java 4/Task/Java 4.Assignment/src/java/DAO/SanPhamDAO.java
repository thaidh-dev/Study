package DAO;

import Helper.JDBCHelper;
import Model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO implements Info_DAO<SanPham> {
    public List<SanPham> showSanPham(int from, int to) {
        List<SanPham> lstSanPham = select("exec sp_phan_trang ?, ?", from, to);
        return lstSanPham;
    }
    
    public static int tongSoTrang() {
        int tongSoTrang = 0;
        try {
            String sql = "exec sp_tong_so_trang";
            ResultSet rs = JDBCHelper.executeQuery(sql, true);
            rs.next();
            tongSoTrang = rs.getInt(1);
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return tongSoTrang;
    }
    
    public static void delete(int ma) {
        String sql = "delete from san_pham where ma_san_pham = ?";
        JDBCHelper.executeUpdate(sql, false, ma);
    }
    
    public static void update(int maSanPham, String tenSanPham, String gia, String hinh, int soLuong) {
        String sql = "update san_pham set ten_san_pham = ?, gia = ?, hinh = ?, so_Luong = ? where ma_san_pham = ?";
        JDBCHelper.executeUpdate(sql, false, tenSanPham, gia, hinh, soLuong, maSanPham);
    }
    
    public static void insert(String tenSanPham, String gia, String hinh, int soLuong) {
        String sql = "insert into san_pham(ten_san_pham, gia, hinh, so_luong) values (?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql, false, tenSanPham, gia, hinh, soLuong);
    }
    
    @Override
    public List<SanPham> select(String sql, Object...args) {
        List<SanPham> lstSanPham = new ArrayList<>();
        
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, true, args);
            while (rs.next()) {
                SanPham sp = readFromRS(rs);
                lstSanPham.add(sp);
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return lstSanPham;
    }
    
    @Override
    public SanPham readFromRS(ResultSet rs) {
        try {
            SanPham sp = new SanPham(rs.getInt("ma_san_pham"), rs.getString("ten_san_pham"), rs.getString("gia"), rs.getString("hinh"), rs.getInt("so_luong"));
            return sp;
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
