package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeDAO {
    public static List soLuongPhieuMuonTheoThang(int nam) {
        String sql = "{call sp_so_pm_theo_thang (?)}";
        ResultSet rs = JdbcHelper.executeQuery(sql, nam);

        List lst = readFromResultSet(rs, 2);
        
        return lst;
    }
    
    public static List danhSachPhieuMUonTheoThang(int thang, int nam) {
        String sql = "{call sp_danh_sach_pm_theo_thang (?, ?)}";
        ResultSet rs = JdbcHelper.executeQuery(sql, thang, nam);
        
        List lst = readFromResultSet(rs, 4);
        
        return lst;
    }
    
    public static List danhSachPhieuMuonQuaHan() {
        String sql = "{call sp_select_PM_qua_han}";
        ResultSet rs = JdbcHelper.executeQuery(sql);
        
        List lst = readFromResultSet(rs, 5);
        
        return lst;
    }
    
    public static List cacSachDaMuonQuaHan(int maPM) {
        String sql = "select tieude from pmct inner join sach on pmct.MaSach = sach.MaSach where mapm = ?";
        ResultSet rs = JdbcHelper.executeQuery(sql, maPM);
        
        List lst = readFromResultSet(rs, 1);
        
        return lst;
    }
    
    public static List soLuotMuonTungDauSach(int thang, int nam) {
        String sql = "{call sp_so_luot_muon_tung_dau_sach (?, ?)}";
        ResultSet rs = JdbcHelper.executeQuery(sql, thang, nam);
        
        List lst = readFromResultSet(rs, 4);
        
        return lst;
    }
    
    public static List soDauSachDuocMuonTheoThang(int nam) {
        String sql = "{call sp_so_dau_sach_muon_theo_thang (?)}";
        ResultSet rs = JdbcHelper.executeQuery(sql, nam);
        
        List lst = readFromResultSet(rs, 2);
        
        return lst;
    }
    
    public static List readFromResultSet(ResultSet rs, int soCot) {
        List lst = new ArrayList();
    
        try {
            while (rs.next()) {
                Object row[] = new Object[soCot];
                for (int i = 0; i < soCot; i++) {
                    row[i] = rs.getObject(i+1);
                }
                lst.add(row);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return lst;
    }
}
