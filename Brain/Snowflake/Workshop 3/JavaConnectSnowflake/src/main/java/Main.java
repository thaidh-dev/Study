import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("user", "ThaiDH");
        properties.put("password", "&Dht24111997");
        properties.put("account", "yg79731.ap-southeast-1");
        properties.put("db", "DEMO_DB");
        properties.put("schema", "PUBLIC");

        String url = "jdbc:snowflake://yg79731.ap-southeast-1.snowflakecomputing.com";

        try {
            Connection cnx = DriverManager.getConnection(url, properties);
            Statement sql = cnx.createStatement();
            ResultSet results = sql.executeQuery("select top 7 * from LANGDESC order by factor_code desc");

            while (results.next()) {
                System.out.println(
                        results.getString(1) + "       " +
                        results.getString(2) + "       " +
                        results.getString(3)
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
