package assignment;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class SinhVien extends Method {
    static JInternalFrame f;
    JLabel lblTieuDe, lblMa, lblTen, lblEmail, lblSDT, lblGioiTinh, lblDiaChi, lblAnh, lblImg;
    JTextField txtMa, txtTen, txtEmail, txtSDT;
    JPanel panelRadio, panelButton, panelLeft, panelRight, panelThan; 
    JScrollPane scrollPaneTable, scrollPane1TextArea;
    JTable table;
    JTextArea textArea;
    JButton btnNew, btnDelete, btnSave, btnUpdate, btnImg;
    GridBagConstraints gbc;
    String tenCot[] = {"Mã SV", "Họ tên", "Email", "SĐT", "Giới tính", "Địa chỉ", "Hình"};
    DefaultTableModel model = new DefaultTableModel(tenCot, 0);
    ButtonGroup buttonGroup;
    JRadioButton radioButtonNam, radioButtonNu;
    String regexMa = "[0-9a-z]+";
    String regexTen = "[a-zA-Z ]+";
    String regexEmail = "\\w+@\\w+(\\.\\w+){1,2}";
    String regexSDT = "0\\d{9,10}";
    String regexDiaChi = "[0-9a-zA-z \\,]+";
    int sai, trong;
    List<SinhVien> list = new ArrayList<>(); 
    String ma, ten, email, sdt, diaChi, hinh; 
    boolean gioiTinh;
    JFileChooser fileChooser;
    
    public void kiemTraSinhVien() {
        sai = 0;
        trong = 0;
        JTextComponent a[] = {txtMa, txtTen, txtEmail, txtSDT, textArea};
        String regex[] = {regexMa, regexTen, regexEmail, regexSDT, regexDiaChi};
        String b[] = {"mã", "tên", "email", "số điện thoại", "địa chỉ"};
        
        for (int i = 0; i < a.length; i++) {
            if (a[i].getText().isEmpty()) {
                trong++;
            }
            else {
                if (!a[i].getText().matches(regex[i])) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Nhập sai " + b[i]);
                }
            }
        }
        
        if (!radioButtonNam.isSelected() && !radioButtonNu.isSelected()) {
            trong++;
        }
        
        if (lblAnh.getIcon() == null) {
            trong++;
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(f, "Không để trống ô hay ảnh");
        }
    }
    
    public void fillToTable() {
        try {
            list.clear();
            pst = con.prepareStatement("select *  from student");
            rs = pst.executeQuery();
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.ma = rs.getString(1);
                sv.ten = rs.getString(2);
                sv.email = rs.getString(3);
                sv.sdt = rs.getString(4);
                sv.gioiTinh = rs.getBoolean(5);
                sv.diaChi = rs.getString(6);
                sv.hinh = rs.getString(7);
                list.add(sv);
            }
            model.setRowCount(0);
            for (SinhVien sv : list) {
                Object row[] = {sv.ma, sv.ten, sv.email, sv.sdt, sv.gioiTinh, sv.diaChi, sv.hinh};
                model.addRow(row);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void lamMoi() {
        JTextComponent a[] = {txtMa, txtTen, txtEmail, txtSDT, textArea};
        for (int i = 0; i < a.length; i++) {
            a[i].setText("");
        }
        buttonGroup.clearSelection();
        lblAnh.setIcon(null);
    }
}
