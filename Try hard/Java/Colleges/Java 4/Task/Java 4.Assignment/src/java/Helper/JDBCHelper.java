package Helper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433; databaseName=java4_assignment";
    private static String user = "thaidh";
    private static String pass = "dht24111997";
    
    static {
        try {
            Class.forName(driver);
        } 
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    
    private static PreparedStatement prepareStatement(String sql, boolean T_SQL, Object...args) {
        PreparedStatement pst = null;
        
        try {
            Connection con = DriverManager.getConnection(dburl, user, pass);
            
            if (T_SQL == false) {
                pst = con.prepareStatement(sql);
            }
            else {
                pst = con.prepareCall(sql);
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
        PreparedStatement pst = null;
        
        if (T_SQL == false) {
            pst = prepareStatement(sql, false, args);
        }
        else {
            pst = prepareStatement(sql, true, args);
        }
        
        try {
            return pst.executeQuery();
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void executeUpdate(String sql, boolean T_SQL, Object...args) {
        PreparedStatement pst = null;
        
        if (T_SQL == false) {
            pst = prepareStatement(sql, false, args);
        }
        else {
            pst = prepareStatement(sql, true, args);
        }

        try {
            pst.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        finally {
            try {
                pst.getConnection().close();
            } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
