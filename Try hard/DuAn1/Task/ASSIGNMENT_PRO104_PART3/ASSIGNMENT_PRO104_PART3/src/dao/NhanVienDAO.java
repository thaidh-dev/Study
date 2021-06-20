/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import object.NhanVien;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Thanh
 */
public class NhanVienDAO implements Inf_DAO<NhanVien> {

    String MD5 = DigestUtils.md5Hex("123456");

    @Override
    public void insert(NhanVien NV) {
        String sql = "INSERT INTO NhanVien (MaNV, TenNV, ChucVu, Email, TaiKhoan, MatKhau) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, NV.getMaNV(), NV.getTenNV(), NV.isChucVu(), NV.getEmail(), NV.getTaiKhoan(), MD5);
    }

    @Override
    public void update(NhanVien NV) {
        String sql = "UPDATE NhanVien SET TenNV=? , ChucVu=? ,Email = ?, TaiKhoan=?, MatKhau=? WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, NV.getTenNV(), NV.isChucVu(), NV.getEmail(), NV.getTaiKhoan(), NV.getMatKhau(), NV.getMaNV());
    }

    public void deleteNV(String ID) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, ID);
    }

    public boolean checkTrungMail(String email) throws SQLException {
        String sql = "SELECT * FROM dbo.NhanVien WHERE Email = ?";

        ResultSet rs = JdbcHelper.executeQuery(sql, email.trim());

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public boolean checkTrungTK(String email) throws SQLException {
        String sql = "SELECT * FROM dbo.NhanVien WHERE TaiKhoan = ?";

        ResultSet rs = JdbcHelper.executeQuery(sql, email.trim());

        if (rs.next()) {
            return true;
        }
        return false;
    }

    @Override
    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    @Override
    public NhanVien findID(Integer ID) {
        String sql = " SELECT * FROM NhanVien WHERE MaNV=? ";
        List<NhanVien> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByTaiKhoan(String taiKhoan) {
        String sql = "select * from NhanVien where TaiKhoan = ?";
        List<NhanVien> lstNhanVien = select(sql, taiKhoan);
        return lstNhanVien.size() > 0 ? lstNhanVien.get(0) : null;
    }

    public void updateMatKhau(String matKhau, String maNV) {
        String sql = "update NhanVien set MatKhau = ? where MaNV = ?";
        JdbcHelper.executeUpdate(sql, DigestUtils.md5Hex(matKhau), maNV);
    }

    public void updateAutoMK(String maNV) {
        String sql = "update NhanVien set MatKhau = ? where MaNV = ?";
        JdbcHelper.executeUpdate(sql, MD5, maNV);
    }

    @Override
    public List<NhanVien> selectByKeyword(String Key) {
        String sql = "SELECT * FROM NhanVien WHERE TenNV LIKE ?";
        return select(sql, "%" + Key + "%");
    }

    @Override
    public List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien NV = readFromResultSet(rs);
                    list.add(NV);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public NhanVien readFromResultSet(ResultSet rs) {
        try {
            NhanVien NV = new NhanVien();
            NV.setMaNV(rs.getString("MaNV"));
            NV.setTenNV(rs.getString("TenNV"));
            NV.setChucVu(rs.getBoolean("ChucVu"));
            NV.setTaiKhoan(rs.getString("TaiKhoan"));
            NV.setMatKhau(rs.getString("MatKhau"));
            NV.setEmail(rs.getString("Email"));
            return NV;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Integer ID) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
