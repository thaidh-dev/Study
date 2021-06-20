package lab.pkg2;

import java.awt.*;
import java.sql.*;
import javax.swing.JComponent;

public abstract class Khung {
    static Connection con;
    static PreparedStatement pst;
    static PreparedStatement pst2;
    static ResultSet rs1;
    static ResultSet rs2;
    static ResultSet rs3;
    static ResultSet rs4;
    static CallableStatement cst;
    static boolean truongPhong = true;
    static String loggingAcc;
    int sai, trong;
    
    public abstract void taoDoiTuong();
    public abstract void taoDang();
    public abstract void add();
    public abstract void event();
    
    public void addChung(Container c, GridBagConstraints gbc, JComponent mang[], int row, int col, Insets insets, int anchor) {
        int z = 0;
        if (gbc == null) {
            for (int x = 0; x < col; x++) {
                for (int y = 0; y < row; y++) {
                    c.add(mang[z]);
                    z++;
                }
            }
        }
        else {
            for (int x = 0; x < col; x++) {
                for (int y = 0; y < row; y++) {
                    gbc.gridx = x;
                    gbc.gridy = y;
                    gbc.insets = insets;
                    gbc.anchor = anchor;
                    c.add(mang[z], gbc);
                    z++;
                }
            }
        }
    }

    public void lienKetSQL() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=PolyPro", "thaidh", "dht24111997");
            System.out.println("Kết nối SQl thành công");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
