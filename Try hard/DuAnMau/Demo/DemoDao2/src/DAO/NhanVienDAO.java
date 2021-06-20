package DAO;

import Model.NhanVien;
import Helper.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NhanVienDAO implements DAO<NhanVien> {
    private List<NhanVien> list = new ArrayList<>(); 

    @Override
    public List<NhanVien> fillToTable() {
        ResultSet rs;
        String sql = "exec sp_select_nhan_vien";
        rs = JDBCHelper.executeQuery(true, sql);

        try {
            list.clear();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(nv);
            }

            rs.getStatement().getConnection().close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return list;
    }

    @Override
    public void insert(NhanVien t) {
        try {
            ResultSet rs = find("select * from nhan_vien where tai_khoan = ?", t.getTaiKhoan());
            
            if (rs.next() == false) {
                String sql = "insert into nhan_vien values (?, ?, ?)";
                
                JDBCHelper.executeUpdate(false, sql, t.getTaiKhoan(), t.getTen(), t.getMatKhau());
                DialogHelper.message(null, "Thêm mới thành công", "Insert", JOptionPane.DEFAULT_OPTION);
            }
            else {
                DialogHelper.message(null, "Trùng tên tài khoản", "Trùng tài khoản", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(NhanVien t) {
        NhanVien nv = showDetail();
        String sql = "update nhan_vien set ten = ?, mat_khau = ? where tai_khoan = ?";
        
        JDBCHelper.executeUpdate(false, sql, t.getTen(), t.getMatKhau(), nv.getTaiKhoan());
        DialogHelper.message(null, "Cập nhật thành công", "Update", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void delete() {
        NhanVien nv = showDetail();
        String sql = "delete nhan_vien where tai_khoan = ?";
        
        JDBCHelper.executeUpdate(false, sql, nv.getTaiKhoan());
        DialogHelper.message(null, "Xóa thành công", "Delete", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public ResultSet find(String sql, Object...args) {
        ResultSet rs = JDBCHelper.executeQuery(true, sql, args);
        return rs;
    }

    @Override
    public NhanVien showDetail() {
        NhanVien nv = list.get(2);
        return nv;
    }

    @Override
    public void clear() {
    }
}
