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
import object.DocGia;

/**
 *
 * @author Thanh
 */
public class DocGiaDAO implements Inf_DAO<DocGia> {

    @Override
    public void insert(DocGia t) {
        String SQL = "INSERT INTO DocGia (TenDG) VALUES (?)";
        JdbcHelper.executeUpdate(SQL, t.getTenDG());
    }

    public void update(DocGia t) {
        String SQL = "UPDATE DocGia SET TenDG = ? WHERE TheDG = ?";
        JdbcHelper.executeUpdate(SQL, t.getTenDG(), t.getTheDG());
    }

    @Override
    public void delete(Integer ID) {
        String SQL = "DELETE FROM DocGia WHERE TheDG = ?";
        JdbcHelper.executeUpdate(SQL, ID);
    }

    @Override
    public List<DocGia> select() {
        String sql = "SELECT * FROM DocGia";
        return select(sql);
    }

    @Override
    public DocGia findID(Integer ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocGia> selectByKeyword(String Key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocGia> select(String sql, Object... args) {
        List<DocGia> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DocGia DG = readFromResultSet(rs);
                    list.add(DG);
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
    public DocGia readFromResultSet(ResultSet rs) {
        try {
            DocGia DG = new DocGia();
            DG.setTheDG(rs.getInt("TheDG"));
            DG.setTenDG(rs.getString("TenDG"));
            return DG;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
