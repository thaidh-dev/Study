package lab.pkg5;

import java.awt.*;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.text.*;

public class Student {
    JFrame f;
    JLabel lblTieuDe, lblMa, lblTen, lblEmail, lblSDT, lblGioiTinh, lblDiaChi;
    JTextField txtMa, txtTen, txtEmail, txtSDT;
    JRadioButton radNam, radNu;
    ButtonGroup buttonGroup;
    JTextArea textAreaDiaChi;
    JScrollPane scrollPane;
    JButton btnAdd, btnDelete, btnUpdate, btnTim, btnDau, btnCuoi, btnTien, btnLui;
    JPanel panel1, panel2, panel3, panel4;
    GridBagConstraints gbc;
    List<Student> list;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String regexMa = "[\\w]+";
    String regexEmail = "\\w+@\\w+(\\.\\w+){1,2}";
    String regexSDT = "\\d+";
    String regexTen = "[a-zA-Z ]+";
    String regexDiaChi = "[0-9a-zA-z ]+";
    int sai, trong;
    
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
    
    public void kiemTra() {
        sai = 0;
        trong = 0;
        JTextComponent mang[] = {txtMa, txtTen, txtEmail, txtSDT, textAreaDiaChi};
        String note[] = {"mã", "tên", "email", "số điện thoại", "địa chỉ"};
        String regex[] = {regexMa, regexTen, regexEmail, regexSDT, regexDiaChi};
        
        for (int i = 0; i < mang.length; i++) {
            
            if (mang[i].getText().isEmpty()) {
                trong++;
            }
            else {
                if (!mang[i].getText().matches(regex[i])) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Nhập sai " + note[i]);
                }
            }
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(f, "Không để trống ô");
        }
    }
    
    public void lienKetSql() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-94HOG3QG\\SA: 1433; databaseName = java3_lab5_bai2", "thaidhph06986", "dht24111997");
            pst = con.prepareStatement("select * from student");
            rs = pst.executeQuery();
            duaLenJTextField();
        }
        catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(f, "Lỗi liên kết");
        }
    }
    
    public void duaLenJTextField() {
        try {
            while (rs.next()) {
                txtMa.setText(rs.getString(1));
                txtTen.setText(rs.getString(2));
                txtEmail.setText(rs.getString(3));
                txtSDT.setText(rs.getString(4));
                if (rs.getBoolean(5)) {
                    radNam.setSelected(true);
                }
                else {
                    radNu.setSelected(true);
                }
                textAreaDiaChi.setText(rs.getString(6));
                break;
            }
        } 
        catch (Exception ex) {
        }
    }
    
    public void thucHienSql(String cauLenhSql, int thamSo, JTextComponent mang[], String thanhCong, String sai) {
        try {
            pst = con.prepareStatement(cauLenhSql);
            for (int i = 1; i <= thamSo; i++) {
                if (i == 5) {
                    if (radNam.isSelected()) {
                        pst.setBoolean(5, true);
                    }
                    else {
                        pst.setBoolean(5, false);
                    }
                }
                else {
                    pst.setString(i, mang[i-1].getText());
                }
            }
            int a = pst.executeUpdate();
            if (a == 0) {
                JOptionPane.showMessageDialog(f, sai);
            }
            else {
                JOptionPane.showMessageDialog(f, thanhCong);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(f, sai);
        }
    }
}

