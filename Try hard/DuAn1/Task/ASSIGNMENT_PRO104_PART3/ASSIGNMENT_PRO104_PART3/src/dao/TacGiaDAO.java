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
import object.TacGia;

/**
 *
 * @author Thanh
 */
public class TacGiaDAO implements Inf_DAO<TacGia> {

    @Override
    public void insert(TacGia TG) {
        String sql = "INSERT INTO TacGia (TenTG, GioiTinh, NgaySinh ) VALUES (?,?,?)";
        JdbcHelper.executeUpdate(sql, TG.getTacGia(), TG.isGioiTinh(), TG.getNgaySinh());
    }

    @Override
    public void update(TacGia TG) {
        String sql = "UPDATE TacGia SET TenTG = ?, GioiTinh=?, NgaySinh=? WHERE MaTG = ?";
        JdbcHelper.executeUpdate(sql, TG.getTacGia(), TG.isGioiTinh(), TG.getNgaySinh(), TG.getMaTG());
    }

    @Override
    public void delete(Integer ID) {
        String sql = "DELETE FROM TacGia WHERE MaTG=?";
        JdbcHelper.executeUpdate(sql, ID);
    }

    @Override
    public List<TacGia> select() {
        String sql = "SELECT * FROM TacGia";
        return select(sql);
    }

    @Override
    public TacGia findID(Integer ID) {
        String sql = "SELECT * FROM TacGia WHERE MaTG=?";
        List<TacGia> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<TacGia> selectByKeyword(String Key) {
        String sql = "SELECT * FROM TacGia WHERE TenTG LIKE ?";
        return select(sql, "%" + Key + "%");
    }

    @Override
    public List<TacGia> select(String sql, Object... args) {
        List<TacGia> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    TacGia TG = readFromResultSet(rs);
                    list.add(TG);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public TacGia readFromResultSet(ResultSet rs) {
        try {
            TacGia TG = new TacGia();
            TG.setMaTG(rs.getInt("MaTG"));
            TG.setTacGia(rs.getString("TenTG"));
            TG.setGioiTinh(rs.getBoolean("GioiTinh"));
            TG.setNgaySinh(rs.getDate("NgaySinh"));
            return TG;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
