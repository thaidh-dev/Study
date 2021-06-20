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
import object.Sach;

/**
 *
 * @author Thanh
 */
public class SachDAO implements Inf_DAO<Sach> {

    public List thongKe(String sql, Object... args) {
        ResultSet rs = JdbcHelper.executeQuery(sql, args);

        List lst = new ArrayList();
        try {
            while (rs.next()) {
                Object row[] = {rs.getString(1), rs.getString(2)};
                lst.add(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lst;
    }

    public List thongKeChuDe() {
        String sql = "{call sp_thong_ke_sach_theo_chu_de}";
        return thongKe(sql);
    }

    public List thongKeNXB() {
        String sql = "{call sp_thong_ke_sach_theo_NXB}";
        return thongKe(sql);
    }

    public List thongKeTacGia() {
        String sql = "{call sp_thong_ke_sach_theo_tac_gia}";
        return thongKe(sql);
    }

    public List thongKeNgonNgu() {
        String sql = "{call sp_thong_ke_sach_theo_ngon_ngu}";
        return thongKe(sql);
    }

    public List<Sach> thongKeGiaTien(int min, Integer... max) {
        String sql;
        List<Sach> lst;

        if (max.length == 0) {
            sql = "SELECT *, dbo.fn_loang_ngoang_vl(tacgia) tentg FROM Sach where giatien >= ?";
            lst = select(sql, min);
        } else {
            sql = "SELECT *, dbo.fn_loang_ngoang_vl(tacgia) tentg FROM Sach where giatien >= ? and giatien <= ?";
            lst = select(sql, min, max[0]);
        }
        return lst;
    }

    public List<Sach> thongKeSoLuongMoiDauSach(boolean selected) {
        String sql;
        if (selected == true) {
            sql = "SELECT *, dbo.fn_loang_ngoang_vl(tacgia) tentg FROM Sach where soluong <= 5";
        } else {
            sql = "SELECT *, dbo.fn_loang_ngoang_vl(tacgia) tentg FROM Sach";
        }
        return select(sql);
    }

    public List<Sach> lietKeSachTheoChuDe(String tenCD) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,Sach.MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach INNER JOIN ChuDe ON ChuDe.MaCD = Sach.MaCD WHERE ChuDe.TenCD = ?";
        return select(sql, tenCD);
    }

    public List<Sach> lietKeSachTheoNXB(String tenNXB) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',Sach.MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach INNER JOIN NhaXuatBan ON NhaXuatBan.MaNXB = Sach.MaNXB WHERE Nhaxuatban.TenNXB = ?";
        return select(sql, tenNXB);
    }

    public List<Sach> lietKeSachTheoTacGia(String tenTacGia) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach where masach in (select dbo.fn_thong_ke_tac_gia(?, tacgia, masach) from sach)";
        return select(sql, tenTacGia);
    }

    public List<Sach> lietKeSachTheoNgonNgu(String ngonNgu) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach where ngonngu = ?";
        return select(sql, ngonNgu);
    }

    @Override
    public void insert(Sach sach) {
        String SQL = "INSERT INTO Sach (TieuDe, TacGia, MaNXB, NamXB, MaCD, NgonNgu, SoTrang, SoLuong, GiaTien) VALUES (?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(SQL, sach.getTieuDe(), sach.getTacGia(), sach.getMaNXB(), sach.getNXB(), sach.getMaCD(), sach.getNgonNgu(), sach.getSoTrang(), sach.getSoLuong(), sach.getGiaTien());
    }

    @Override
    public void update(Sach sach) {
        String SQL = "UPDATE Sach SET TieuDe = ?, TacGia=?, MaNXB=?, NamXB=?, MaCD=?, NgonNgu=?, SoTrang=?, SoLuong=?, GiaTien=? WHERE MaSach=?";
        JdbcHelper.executeUpdate(SQL, sach.getTieuDe(), sach.getTacGia(), sach.getMaNXB(), sach.getNXB(), sach.getMaCD(), sach.getNgonNgu(), sach.getSoTrang(), sach.getSoLuong(), sach.getGiaTien(), sach.getMaSach());
    }

    @Override
    public void delete(Integer ID) {
        String SQL = "DELETE FROM Sach WHERE MaSach = ?";
        JdbcHelper.executeUpdate(SQL, ID);
    }

    @Override
    public List<Sach> select() {
        String SQL = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach";
        return select(SQL);
    }

    public List<Sach> checkBox() {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach WHERE Sach.SoLuong > 0";
        return select(sql);
    }

    @Override
    public Sach findID(Integer ID) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach WHERE MaCD=?";
        List<Sach> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Sach findMaTG(String ID) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach WHERE Sach.TacGia= ? ";
        List<Sach> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Sach findMaNXB(Integer ID) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach WHERE Sach.MaNXB=?";
        List<Sach> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Sach> selectByKeyword(String Key) {
        String sql = "SELECT MaSach, TieuDe, TacGia, dbo.fn_loang_ngoang_vl(TacGia) AS 'TenTG',MaNXB,NamXB,dbo.Sach.MaCD,NgonNgu,SoTrang,SoLuong,GiaTien FROM dbo.Sach INNER JOIN dbo.ChuDe ON ChuDe.MaCD = Sach.MaCD WHERE dbo.ChuDe.TenCD LIKE ?";
        return select(sql, "%" + Key + "%");
    }

    @Override
    public List<Sach> select(String sql, Object... args) {
        List<Sach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Sach sach = readFromResultSet(rs);
                    list.add(sach);
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

    public List<Sach> caideogi(String ma) {
//        String sql = "{CALL sp_select_Sach (?)} ";
        String sql = "SELECT SoLuong FROM Sach WHERE MaSach in (?)";
        return selectSL(sql, ma);
    }

    public List<Sach> selectSL(String sql, Object... arg) {
        List<Sach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, arg);
                while (rs.next()) {
                    Sach sach = readRs(rs);
                    list.add(sach);
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

    // <editor-fold defaultstate="collapsed" desc="ĐỌC DỮ LIỆU TỪ RS ">
    public Sach readRs(ResultSet rs) throws SQLException {
        Sach sach = new Sach();
        String SL = rs.getString("SoLuong");
        sach.setSoLuong(SL);
        return sach;
    }
    // </editor-fold>

    @Override
    public Sach readFromResultSet(ResultSet rs) {
        try {
            Sach sach = new Sach();
            sach.setMaSach(rs.getString("MaSach"));
            sach.setTieuDe(rs.getString("TieuDe"));
            sach.setTacGia(rs.getString("TacGia"));
            sach.setMaNXB(rs.getInt("MaNXB"));
            sach.setNXB(rs.getInt("NamXB"));
            sach.setMaCD(rs.getInt("MaCD"));
            sach.setNgonNgu(rs.getString("NgonNgu"));
            sach.setSoTrang(rs.getInt("SoTrang"));
            sach.setSoLuong(rs.getString("SoLuong"));
            sach.setGiaTien(rs.getInt("GiaTien"));
            sach.setTenTG(rs.getString("TenTG"));

            return sach;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
