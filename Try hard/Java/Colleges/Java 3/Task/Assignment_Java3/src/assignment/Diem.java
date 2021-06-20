package assignment;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class Diem extends Method {
    static JInternalFrame f;
    JLabel lblTieuDe, lblMaDeTim, lblTen, lblMa, lblEnglish, lblTin, lblGDTC, lblDTB, lblMax, lblTxtTen, lblDTBInfo;
    JButton btnSearch, btnNew, btnSave, btnDelete, btnUpdate, btnDau, btnLui, btnTien, btnCuoi;
    JTable table;
    JScrollPane scrollPane;
    JPanel panelTop, panelLeft, panelRight, panelBot, PanelLeftTrai, PanelLeftPhai;
    JTextField txtMa, txtEnglish, txtTin, txtGDTC, txtCbo;
    String tenCot[] = {"Mã SV", "Họ tên", "Tiếng anh", "Tin học", "GDTC", "Điểm TB"};
    DefaultTableModel model = new DefaultTableModel(tenCot, 0);
    GridBagConstraints gbc;
    String id, ma, ten;
    String regexMa = "[0-9a-z]+";
    double english, tin, gdtc, dtb;
    int sai, trong;
    List<Diem> list = new ArrayList<>();
    List<String> listCbo = new ArrayList();
    JComboBox cboSearch;
    
    public void kiemTraDiem() {
        trong = 0;
        sai = 0;
        JTextComponent a[] = {txtMa, txtEnglish, txtTin, txtGDTC};
        for (int i = 0; i < a.length; i++) {
            if (a[i].getText().isEmpty()) {
                trong++;
            }
            else {
                if (i > 0) {
                    try {
                        Double.parseDouble(a[i].getText());
                        if (Double.parseDouble(a[i].getText()) < 0 || Double.parseDouble(a[i].getText()) > 10) {
                            sai++;
                            JOptionPane.showMessageDialog(f, "Điểm phải từ 0 đến 10");
                        }
                    }
                    catch (Exception ex) {
                        sai++;
                        JOptionPane.showMessageDialog(f, "Nhập sai điểm");
                    }
                }
                else {
                    if (!txtMa.getText().matches(regexMa)) {
                        sai++;
                        JOptionPane.showMessageDialog(f, "Nhập sai mã");
                    }
                }
            }
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(f, "Không để trống ô");
        }
    }
    
    public void fillToTable() {
        try {
            list.clear();
            pst = con.prepareStatement("select top 3 id, student.ma_sv, ten, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) as 'DTB' from grade inner join student on student.ma_sv = grade.ma_sv order by 'DTb' desc");
            rs = pst.executeQuery();
            while (rs.next()) {
                Diem d = new Diem();
                d.id = rs.getString(1);
                d.ma = rs.getString(2);
                d.ten = rs.getString(3);
                d.english = Double.parseDouble(rs.getString(4));
                d.tin = Double.parseDouble(rs.getString(5));
                d.gdtc = Double.parseDouble(rs.getString(6));
                d.dtb = Double.parseDouble(rs.getString(7));
                list.add(d);
            }
            
            pst = con.prepareStatement("select ma_sv from grade");
            rs = pst.executeQuery();
            while (rs.next()) {
                listCbo.add(rs.getString(1));
            }
            
            cboSearch.setModel(new DefaultComboBoxModel(listCbo.toArray()));
            cboSearch.setSelectedItem("");
            
            model.setRowCount(0);
            for (Diem diem : list) {
                Object row[] = {diem.ma, diem.ten, diem.english, diem.tin, diem.gdtc, diem.dtb};
                model.addRow(row);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void dauCuoi(int a) {
        try {
            pst = con.prepareStatement("select ten, student.ma_sv, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) from grade inner join student on student.ma_sv = grade.ma_sv", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            if (a == 0) {
                rs.first();
            }
            else {
                rs.last();
            }
            lblTxtTen.setText(rs.getString(1));
            txtMa.setText(rs.getString(2));
            txtEnglish.setText(rs.getString(3));
            txtTin.setText(rs.getString(4));
            txtGDTC.setText(rs.getString(5));
            lblDTBInfo.setText(""+Double.parseDouble(rs.getString(6)));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void tienLui(int a) {
        try {
            pst = con.prepareStatement("select ten, student.ma_sv, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) from grade inner join student on student.ma_sv = grade.ma_sv", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equals(txtMa.getText())) {
                    if (a == 1) {
                        boolean n = rs.next();
                        if (n == false) {
                            JOptionPane.showMessageDialog(f, "Đã ở cuối danh sách");
                        }
                    }
                    else {
                        boolean p = rs.previous();
                        if (p == false) {
                            JOptionPane.showMessageDialog(f, "Đã ở đầu danh sách");
                        }
                    }
                    lblTxtTen.setText(rs.getString(1));
                    txtMa.setText(rs.getString(2));
                    txtEnglish.setText(rs.getString(3));
                    txtTin.setText(rs.getString(4));
                    txtGDTC.setText(rs.getString(5));
                    lblDTBInfo.setText(""+Double.parseDouble(rs.getString(6)));
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void lamMoi() {
        JTextComponent a[] = {txtMa, txtEnglish, txtTin, txtGDTC};
        for (int i = 0; i < a.length; i++) {
            a[i].setText("");
        }
        lblDTBInfo.setText("");
        lblTxtTen.setText("");
        cboSearch.setSelectedItem("");
    }
}
