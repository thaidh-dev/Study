package Helper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class JDBCHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433; databaseName=EMPDB";
    private static String user = "thaidhph06986";
    private static String pass = "dht24111997";
    
    static {
        try {
            Class.forName(driver);
        } 
        catch (Exception e) {
            DialogHelper.message(null, "Lỗi nạp Driver", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static PreparedStatement preparedStatement(boolean T_SQL, String sql, Object...args) {
        try {
            Connection con = DriverManager.getConnection(dburl, user, pass);
            PreparedStatement pst;
            
            if (T_SQL == true) {
                pst = con.prepareCall(sql);
            }
            else {
                pst = con.prepareStatement(sql);
            }
            
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1, args[i]);
            }
            
            return pst;
        } 
        catch (Exception e) {
            DialogHelper.message(null, "Lỗi kết nối SQL", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static ResultSet executeQuery(boolean T_SQL, String sql, Object...args) {
        PreparedStatement pst = null;
            
        try {
            if (T_SQL == true) {
                pst = preparedStatement(true, sql, args);
            }
            else {
                pst = preparedStatement(false, sql, args);
            }

            return pst.executeQuery();
        } 
        catch (Exception ex) {
        }
        return null;
    }
    
    public static void executeUpdate(boolean T_SQL, String sql, Object...args) {
        PreparedStatement pst = null;
            
        try {
            if (T_SQL == true) {
                pst = preparedStatement(true, sql, args);
            }
            else {
                pst = preparedStatement(false, sql, args);
            }
            
            pst.executeUpdate();
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
            try {
                if (pst != null) {
                    pst.getConnection().close();
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
