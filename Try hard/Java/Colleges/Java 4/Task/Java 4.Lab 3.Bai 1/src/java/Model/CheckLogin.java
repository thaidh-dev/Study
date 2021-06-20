package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class CheckLogin {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void lienKetSql() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=java4_lab3_bai1", "thaidhph06986", "dht24111997");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean check(String user, String pass) {
        try {
            pst = con.prepareStatement("select * from tai_khoan where username = ? and pass = ?");
            pst.setString(1, user);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            boolean dungTaiKhoan = rs.next();
            
            if (dungTaiKhoan == true) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public String ten(String user) {
        try {
            pst = con.prepareStatement("select ten from tai_khoan where username = ?");
            pst.setString(1, user);
            rs = pst.executeQuery();    
            rs.next();
            return rs.getString(1);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<TaiKhoan> tim(String ten) {
        try {
            List<TaiKhoan> lst = new ArrayList<>();
            pst = con.prepareStatement("select * from tai_khoan where ten = ?");
            pst.setString(1, ten);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                lst.add(tk);
            }
            
            return lst;
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void destroy() {
        try {
            con.close();
            pst.close();
            rs.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
