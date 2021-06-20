package demodao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSingleton {
    
    private static JDBCSingleton jdbc;
    
    private JDBCSingleton() {
    }

    public static JDBCSingleton getInstance() {
        if (jdbc == null) {
            jdbc = new JDBCSingleton();
        }
        return jdbc;
    }

    private static Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EMPDB", "thaidhph06986", "dht24111997");
    }

 // Thêm dữ liệu vào db
    public int create(String username, String pass) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int cnt = 0;

        try {
            c = this.getConnection();

            ps = c.prepareStatement("insert into accounts(username,pass) values(?,?)");
            ps.setString(1, username);
            ps.setString(2, pass);

            cnt = ps.executeUpdate();
        } 
        catch (Exception e) {
            System.out.print(e.toString());
        } 
        finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return cnt;
    }

 //Hiển dữ liệu
    public void display(String id) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cnt = 0;

        try {
            con = this.getConnection();
            ps = con.prepareStatement("select * from accounts where username = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Ten " + rs.getString("username") + " va mat khau " + rs.getString("pass"));
                cnt++;
            }

            if (cnt == 0) {
                System.out.println("Khong co tai khoan nao ton tai");
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        } 
        finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
