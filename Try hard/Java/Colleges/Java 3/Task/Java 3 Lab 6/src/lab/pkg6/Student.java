package lab.pkg6;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class Student {
    String id, name, address, parentName, phone, standard, date;
    String regexName = "[a-zA-z ]+";
    String regexAddress = "[\\w ]+";
    String regexPhone = "\\d+";
    JFrame f;
    JScrollPane scrollPaneTable, scrollPaneTextArea;
    JTable table;
    JPanel panelRight, panelTop, panelBot;
    JLabel lblName, lblAddress, lblParentName, lblContactNo, lblStandard;
    JTextField txtName, txtParentName, txtContactNo;
    JTextArea textArea;
    JComboBox cboStandard;
    JButton btnNew, btnNext, btnInsert, btnPrevious, btnEdit, btnDelete, btnUpdate, btnExit;
    GridBagConstraints gbc;
    DefaultTableModel model;
    String tenCot[] = {"Name", "Standard"};
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    List<Student> list = new ArrayList<>();
    int sai, trong;
    String indexID;
    
    public void kiemTra() {
        sai = 0;
        trong = 0;
        
        JTextComponent mangTxt[] = {txtName, textArea, txtParentName, txtContactNo};
        String mangRegex[] = {regexName, regexAddress, regexName, regexPhone};
        String note[] = {"tên", "địa chỉ", "tên cha hoặc mẹ", "số điện thoại"};
        for (int i = 0; i < mangTxt.length; i++) {
            if (mangTxt[i].getText().isEmpty()) {
                trong++;
            }
            else {
                if (!mangTxt[i].getText().matches(mangRegex[i])) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Sai "+note[i]);
                }
            }
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(f, "Không để trống ô");
        }
    }
    
    public void fillToTable() {
        model.setRowCount(0);
        for (Student student : list) {
            Object row[] = {student.name, student.standard};
            model.addRow(row);
        }
    }
    
    public void lienKetSql() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName = student", "thaidhph06986", "dht24111997");
            pst = con.prepareStatement("select * from student");
            rs = pst.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.id = rs.getString(1);
                student.name = rs.getString(2);
                student.address = rs.getString(3);
                student.parentName = rs.getString(4);
                student.phone = rs.getString(5);
                student.standard = rs.getString(6);
                list.add(student);
                cboStandard.addItem(student.standard);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void setEdiable(boolean a) {
        if (a == false) {
            txtName.setEditable(false);
            textArea.setEditable(false);
            txtParentName.setEditable(false);
            txtContactNo.setEditable(false);
            cboStandard.setEnabled(false);
        }
        else {
            txtName.setEditable(true);
            txtParentName.setEditable(true);
            txtContactNo.setEditable(true);
            textArea.setEditable(true);
            cboStandard.setEnabled(true);
        }
    }
    
    public void tienLui(int a, String b) {
        try {
            table.getSelectedRow();
            pst = con.prepareStatement("select * from student", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (indexID.equals(rs.getString("id"))) {
                    if (a == 1) {
                        rs.next();
                    }
                    else {
                        rs.previous();
                    }
                    indexID = rs.getString(1);
                    txtName.setText(rs.getString(2));
                    textArea.setText(rs.getString(3));
                    txtParentName.setText(rs.getString(4));
                    txtContactNo.setText(rs.getString(5));
                    cboStandard.setSelectedItem(rs.getString(6));
                    break;
                }
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(f, "Đã ở " + b + " bảng");
        }
        catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
        }
    }
    
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
}
