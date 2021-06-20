package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Products {
    public List<Product> showProduct(String tenSp) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=java4_lab2_bai2", "thaidhph06986", "dht24111997");
            String sql = "select * from product";
            if (tenSp.length() > 0) {
                sql += " where name like '%" +tenSp+"%'";
            }
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<Product> list = new ArrayList<Product>();
            
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                Float price = rs.getFloat("Price");
                Product sp = new Product(code, name, price);
                list.add(sp);
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
