package lab.pkg5;

import java.sql.*;

public class Bai1 {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-94HOG3QG\\SA: 1433; databaseName = Postgresql10", "thaidhph06986", "dht24111997");
//            String sql = "select * from phieu_mua\n" +
//"inner join khach_hang on khach_hang.ma_khach_hang = phieu_mua.ma_khach_hang";
//            String sql2 = "exec sp_update_tac_gia ?, ?";
//            CallableStatement pst = con.prepareCall(sql);
//            pst.setString(1, "Linda Francis");
//            pst.setString(2, "Dương Thái");

//                String sql = "select (select count(phieu_mua.ma_phieu_mua) from phieu_mua), \n" +
//"	sum(gia*so_luong_da_mua) \n" +
//"from phieu_mua_chi_tiet\n" +
//"inner join san_pham on san_pham.ma_san_pham = phieu_mua_chi_tiet.ma_san_pham";

            String sql = "select ma_khach_hang,\n" +
"		ten_khach_hang,\n" +
"		count(ma_phieu_mua) tong_so_giao_dich, \n" +
"		sum(tong_tien) tong_tien \n" +
"	from dbo.sp_thong_ke_2 ()\n" +
"	group by ma_khach_hang, ten_khach_hang";


            PreparedStatement pst = con.prepareCall(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
            
            con.close();
            System.out.println();

            //System.out.println(pst.executeUpdate());
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + ", " + 
//                                rs.getString(2) + ", " + 
//                                rs.getString(3)
//                        );
//            }
//            System.out.println(con.isClosed());
//            System.out.println(pst.isClosed());
//            System.out.println(rs.isClosed());
//            con.close();
//            pst.close();
//            rs.close();
//            System.out.println(con.isClosed());
//            System.out.println(pst.isClosed());
//            System.out.println(rs.isClosed());
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

// Connection con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-94HOG3QG\\SA: 1433; databaseName = ass1", "thaidhph06986", "dht24111997");
// LAPTOP-94HOG3QG\\SA là cổng nối vào sql


// Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost: 1433; databaseName = ass1", "thaidhph06986", "dht24111997");
