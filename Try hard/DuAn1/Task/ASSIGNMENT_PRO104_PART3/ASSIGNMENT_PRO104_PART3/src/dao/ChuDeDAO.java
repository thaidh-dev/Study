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
import object.ChuDe;

/**
 *
 * @author Thanh
 */
public class ChuDeDAO implements Inf_DAO<ChuDe> {

    @Override
    public void insert(ChuDe CD) {
        String sql = "INSERT INTO ChuDe (TenCD) VALUES (?)";
        JdbcHelper.executeUpdate(sql, CD.getTenCD());
    }

    @Override
    public void update(ChuDe CD) {
        String sql = "UPDATE ChuDe SET TenCD = ? WHERE MaCD = ?";
        JdbcHelper.executeUpdate(sql, CD.getTenCD(), CD.getMaCD());
    }

    @Override
    public void delete(Integer ID) {
        String sql = "DELETE FROM ChuDe WHERE MaCD=?";
        JdbcHelper.executeUpdate(sql, ID);
    }

    @Override
    public List<ChuDe> select() {
        String sql = "SELECT * FROM ChuDe";
        return select(sql);
    }
    
    public List<ChuDe> checkBox() {
        String sql = "SELECT ChuDe.MaCD,dbo.ChuDe.TenCD, COUNT(dbo.Sach.MaSach) FROM dbo.ChuDe INNER JOIN dbo.Sach ON Sach.MaCD = ChuDe.MaCD GROUP BY ChuDe.MaCD ,dbo.ChuDe.TenCD HAVING COUNT(dbo.Sach.MaSach) > 0";
        return select(sql);
    }

    @Override
    public ChuDe findID(Integer ID) {
        String sql = "SELECT * FROM ChuDe WHERE MaCD=?";
        List<ChuDe> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<ChuDe> selectByKeyword(String Key) {
        String sql = "SELECT * FROM dbo.Sach INNER JOIN dbo.ChuDe ON ChuDe.MaCD = Sach.MaCD WHERE dbo.ChuDe.TenCD LIKE ?";
        return select(sql, "%" + Key + "%");
    }

    @Override
    public List<ChuDe> select(String sql, Object... args) {
        List<ChuDe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    ChuDe CD = readFromResultSet(rs);
                    list.add(CD);
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
    public ChuDe readFromResultSet(ResultSet rs) {
        try {
            ChuDe CD = new ChuDe();
            CD.setMaCD(rs.getInt("MaCD"));
            CD.setTenCD(rs.getString("TenCD"));
            return CD;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
