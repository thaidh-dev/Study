package assignment;

import java.awt.*;
import java.sql.*;
import javax.swing.JComponent;

public class Method {
    static Connection con;
    static PreparedStatement pst;
    static ResultSet rs;

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

    public void lienKetSql() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName = FPL_dao_tao", "thaidhph06986", "dht24111997");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
