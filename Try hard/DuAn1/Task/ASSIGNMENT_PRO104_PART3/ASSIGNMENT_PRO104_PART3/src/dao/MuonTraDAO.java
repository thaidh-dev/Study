/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.DateHelper;
import helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.MuonTra;
import object.Sach;

/**
 *
 * @author Thanh
 */
public class MuonTraDAO {


    public void insertPM(MuonTra MT) {
        String SQL = "INSERT INTO PhieuMuon ( MaPM, TheDG, NgayHen, MaNV) VALUES (?,?,?,?)";
        JdbcHelper.executeUpdate(SQL, MT.getMaPM(), MT.getTheDG(), MT.getNgayHen(), MT.getMaNV());
    }

    public void insertPMCT(MuonTra MT) {
        String SQL = "EXEC dbo.sp_insert_pmct @chuoi_ma_sach = ? , @ma_phieu = ?";
        JdbcHelper.executeUpdate(SQL, MT.getMaSach(), MT.getMaPM());
    }

    public void updatePM(MuonTra MT) {
        String SQL = "UPDATE PhieuMuon SET NgayHen = ? WHERE MaPM = ?";
        JdbcHelper.executeUpdate(SQL, MT.getNgayHen(), MT.getMaPM());
    }

    public void traSach(MuonTra MT) {
        String SQL = "EXEC dbo.sp_traSach @mapm = ?";
        JdbcHelper.executeUpdate(SQL, MT.getMaPM());
    }

    public void deletePM(int MaPM) {
        String SQL = "DELETE FROM PhieuMuon WHERE MaPM = ?";
        JdbcHelper.executeUpdate(SQL, MaPM);
    }

    public void deletePMCT(int MaPM) {
        String SQL = "EXEC sp_delete_pmct @mapm = ?";
        JdbcHelper.executeUpdate(SQL, MaPM);
    }

    public boolean checkMaPM(String MaPM) throws SQLException {
        String sql = "SELECT * FROM dbo.PhieuMuon WHERE MaPM = ?";

        ResultSet rs = JdbcHelper.executeQuery(sql, MaPM.trim());

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public boolean checkMaDG(String MaDG) throws SQLException {
        String sql = "SELECT * FROM dbo.DocGia WHERE TheDG = ?";

        ResultSet rs = JdbcHelper.executeQuery(sql, MaDG.trim());

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public boolean checkTheDGtoPM(String MaDG) throws SQLException {
        String sql = "SELECT * FROM dbo.PhieuMuon WHERE TheDG = ?";

        ResultSet rs = JdbcHelper.executeQuery(sql, MaDG.trim());

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public boolean checkPMtoPMCT(String MaDG) throws SQLException {
        String sql = "SELECT * FROM dbo.PMCT WHERE MaPM = ?";

        ResultSet rs = JdbcHelper.executeQuery(sql, MaDG.trim());

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public MuonTra findMaPM(Integer ID) {
        String sql = "SELECT DISTINCT PhieuMuon.MaPM, DocGia.TheDG ,dbo.DocGia.TenDG , dbo.fn_select_pmct(PhieuMuon.MaPM) AS 'TieuDe', dbo.PhieuMuon.NgayMuon,dbo.PhieuMuon.NgayHen,dbo.PhieuMuon.NgayTra, NhanVien.MaNV, dbo.NhanVien.TenNV, dbo.PMCT.TrangThai  FROM dbo.PhieuMuon INNER JOIN dbo.DocGia ON DocGia.TheDG = PhieuMuon.TheDG INNER JOIN dbo.PMCT ON PMCT.MaPM = PhieuMuon.MaPM INNER JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuMuon.MaNV WHERE PhieuMuon.TheDG = ?";
        List<MuonTra> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<MuonTra> select() {
        String sql = "SELECT DISTINCT PhieuMuon.MaPM, DocGia.TheDG ,dbo.DocGia.TenDG , dbo.fn_select_pmct(PhieuMuon.MaPM) AS 'TieuDe', dbo.PhieuMuon.NgayMuon,dbo.PhieuMuon.NgayHen,dbo.PhieuMuon.NgayTra, NhanVien.MaNV, dbo.NhanVien.TenNV, dbo.PMCT.TrangThai  FROM dbo.PhieuMuon INNER JOIN dbo.DocGia ON DocGia.TheDG = PhieuMuon.TheDG INNER JOIN dbo.PMCT ON PMCT.MaPM = PhieuMuon.MaPM INNER JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuMuon.MaNV";
        return select(sql);
    }

    public List<MuonTra> daTra() {
        String sql = "SELECT DISTINCT PhieuMuon.MaPM, DocGia.TheDG ,dbo.DocGia.TenDG , dbo.fn_select_pmct(PhieuMuon.MaPM) AS 'TieuDe', dbo.PhieuMuon.NgayMuon,dbo.PhieuMuon.NgayHen,dbo.PhieuMuon.NgayTra, NhanVien.MaNV, dbo.NhanVien.TenNV, dbo.PMCT.TrangThai  FROM dbo.PhieuMuon INNER JOIN dbo.DocGia ON DocGia.TheDG = PhieuMuon.TheDG INNER JOIN dbo.PMCT ON PMCT.MaPM = PhieuMuon.MaPM INNER JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuMuon.MaNV WHERE TrangThai = 1 AND dbo.PhieuMuon.NgayTra <= dbo.PhieuMuon.NgayHen";
        return select(sql);
    }

    public List<MuonTra> chuaTra() {
        String sql = "SELECT DISTINCT PhieuMuon.MaPM, DocGia.TheDG ,dbo.DocGia.TenDG , dbo.fn_select_pmct(PhieuMuon.MaPM) AS 'TieuDe', dbo.PhieuMuon.NgayMuon,dbo.PhieuMuon.NgayHen,dbo.PhieuMuon.NgayTra, NhanVien.MaNV, dbo.NhanVien.TenNV, dbo.PMCT.TrangThai  FROM dbo.PhieuMuon INNER JOIN dbo.DocGia ON DocGia.TheDG = PhieuMuon.TheDG INNER JOIN dbo.PMCT ON PMCT.MaPM = PhieuMuon.MaPM INNER JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuMuon.MaNV WHERE TrangThai = 0";
        return select(sql);
    }

    public List<MuonTra> quaHan() {
        String sql = "SELECT DISTINCT PhieuMuon.MaPM, DocGia.TheDG ,dbo.DocGia.TenDG , dbo.fn_select_pmct(PhieuMuon.MaPM) AS 'TieuDe', dbo.PhieuMuon.NgayMuon,dbo.PhieuMuon.NgayHen,dbo.PhieuMuon.NgayTra, NhanVien.MaNV, dbo.NhanVien.TenNV, dbo.PMCT.TrangThai  FROM dbo.PhieuMuon INNER JOIN dbo.DocGia ON DocGia.TheDG = PhieuMuon.TheDG INNER JOIN dbo.PMCT ON PMCT.MaPM = PhieuMuon.MaPM INNER JOIN dbo.NhanVien ON NhanVien.MaNV = PhieuMuon.MaNV WHERE dbo.PhieuMuon.NgayTra > dbo.PhieuMuon.NgayHen";
        return select(sql);
    }

    public List<MuonTra> select(String sql, Object... args) {
        List<MuonTra> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    MuonTra MT = readFromResultSet(rs);
                    list.add(MT);
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

    public List<MuonTra> selectByTheDG(int Key) {
        String sql = "SELECT TenDG FROM DocGia WHERE TheDG = ?";
        return layTenDG(sql, Key);
    }

    public List<MuonTra> layTenDG(String sql, Object... args) {
        List<MuonTra> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    MuonTra MT = rsTenDG(rs);
                    list.add(MT);
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

    public MuonTra rsTenDG(ResultSet rs) {
        try {
            MuonTra MT = new MuonTra();
            MT.setTenDG(rs.getString("TenDG"));
            return MT;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MuonTra> autoMaPM() {
        String sql = "SELECT TOP 1 MaPM FROM dbo.PhieuMuon ORDER BY MaPM DESC";
        return layMaPM(sql);
    }

    public List<MuonTra> layMaPM(String sql, Object... args) {
        List<MuonTra> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    MuonTra MT = rsMaPM(rs);
                    list.add(MT);
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

    public MuonTra rsMaPM(ResultSet rs) {
        try {
            MuonTra MT = new MuonTra();
            MT.setMaPM(rs.getInt(1));
            return MT;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MuonTra readFromResultSet(ResultSet rs) {
        try {
            MuonTra MT = new MuonTra();
            MT.setTheDG(rs.getInt("TheDG"));
            MT.setTenDG(rs.getString("TenDG"));
            MT.setMaPM(rs.getInt("MaPM"));
            MT.setTenSach(rs.getString("TieuDe"));
            MT.setNgayMuon(rs.getDate("NgayMuon"));
            MT.setNgayHen(rs.getDate("NgayHen"));
            MT.setNgayTra(rs.getDate("NgayTra"));
            MT.setMaNV(rs.getString("MaNV"));
            MT.setTenNV(rs.getString("TenNV"));
            MT.setTrangThai(rs.getBoolean("TrangThai"));
            return MT;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
