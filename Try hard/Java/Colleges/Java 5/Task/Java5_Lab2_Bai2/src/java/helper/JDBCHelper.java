package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433; databaseName=java5_lab2_bai2";
    private static String user = "thaidh";
    private static String pass = "dht24111997";
    
    static {
        try {
            Class.forName(driver);
        } 
        catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public static PreparedStatement prepareStatement(String sql, boolean T_SQL, Object...args) {
        PreparedStatement pst = null;
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            
            if (T_SQL == true) {
                pst = con.prepareCall(sql);
            }
            else {
                pst = con.prepareStatement(sql);
            }
            
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1, args[i]);
            }
            
            
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        return pst;
    }
    
    public static ResultSet executeQuery(String sql, boolean T_SQL, Object...args) {
        try {
            PreparedStatement pst = prepareStatement(sql, T_SQL, args);
            ResultSet rs = pst.executeQuery();
            return rs;
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    public static void executeUpdate(String sql, boolean T_SQL, Object...args) {
        PreparedStatement pst = null;
        try {
            pst = prepareStatement(sql, T_SQL, args);
            pst.executeUpdate();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                pst.getConnection().close();
            } 
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
