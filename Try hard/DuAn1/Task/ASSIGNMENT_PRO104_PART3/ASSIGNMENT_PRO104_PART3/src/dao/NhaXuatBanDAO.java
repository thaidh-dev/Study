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
import object.NhaXuatBan;

/**
 *
 * @author Thanh
 */
public class NhaXuatBanDAO implements Inf_DAO<NhaXuatBan> {

    @Override
    public void insert(NhaXuatBan NXB) {
        String sql = "INSERT INTO NhaXuatBan (TenNXB, SDT, DiaChi) VALUES (?,?,?)";
        JdbcHelper.executeUpdate(sql, NXB.getTenNXB(), NXB.getSDT(), NXB.getDiaChi());
    }

    @Override
    public void update(NhaXuatBan NXB) {
        String sql = "UPDATE NhaXuatBan SET TenNXB = ?, SDT=?, DiaChi=? WHERE MaNXB = ?";
        JdbcHelper.executeUpdate(sql, NXB.getTenNXB(), NXB.getSDT(), NXB.getDiaChi(), NXB.getMaNXB());
    }

    @Override
    public void delete(Integer ID) {
        String sql = "DELETE FROM dbo.NhaXuatBan WHERE MaNXB = ?";
        JdbcHelper.executeUpdate(sql, ID);
    }

    @Override
    public List<NhaXuatBan> select() {
        String sql = "SELECT * FROM NhaXuatBan";
        return select(sql);
    }

    @Override
    public NhaXuatBan findID(Integer ID) {
        String sql = "SELECT * FROM NhaXuatBan WHERE MaNXB=?";
        List<NhaXuatBan> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<NhaXuatBan> selectByKeyword(String Key) {
        String sql = "SELECT * FROM NhaXuatBan WHERE TenNXB LIKE ?";
        return select(sql, "%" + Key + "%");
    }

    @Override
    public List<NhaXuatBan> select(String sql, Object... args) {
        List<NhaXuatBan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    NhaXuatBan NXB = readFromResultSet(rs);
                    list.add(NXB);
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
    public NhaXuatBan readFromResultSet(ResultSet rs) {
        try {
            NhaXuatBan NXB = new NhaXuatBan();
            NXB.setMaNXB(rs.getInt("MaNXB"));
            NXB.setTenNXB(rs.getString("TenNXB"));
            NXB.setSDT(rs.getString("SDT"));
            NXB.setDiaChi(rs.getString("DiaChi"));
            return NXB;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
