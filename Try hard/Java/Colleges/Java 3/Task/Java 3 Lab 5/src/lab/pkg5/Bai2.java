package lab.pkg5;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Bai2 extends Student{
    public static void main(String[] args) {
        Bai2 a = new Bai2();
        a.taoDoiTuong();
        a.taoDang();
        a.add();
        a.event();
        a.lienKetSql();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 2");
        gbc = new GridBagConstraints();
        list = new ArrayList<>();
        
        panel1 = new JPanel(new GridBagLayout());
        panel2 = new JPanel(new GridBagLayout());
        panel3 = new JPanel(new FlowLayout());
        panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        lblTieuDe = new JLabel("<html><p style=\"color: blue; font-size: 30px\">Quản lý Users</p></html>");
        lblMa = new JLabel("Mã SV:");
        lblTen = new JLabel("Họ tên:");
        lblEmail = new JLabel("Email:");
        lblSDT = new JLabel("Số ĐT:");
        lblGioiTinh = new JLabel("Giới tính:");
        lblDiaChi = new JLabel("Địa chỉ:");
        
        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSDT = new JTextField(20);
        
        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        buttonGroup = new ButtonGroup();
        
        textAreaDiaChi = new JTextArea();
        scrollPane = new JScrollPane(textAreaDiaChi);
        
        btnAdd = new JButton("Add", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3 Lab 5\\image\\add.png"));
        btnDelete = new JButton("Delete", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3 Lab 5\\image\\delete.png"));
        btnUpdate = new JButton("Update", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3 Lab 5\\image\\update.png"));
        btnTim = new JButton("Tìm", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3 Lab 5\\image\\save.png"));
        btnDau = new JButton("|<");
        btnCuoi = new JButton(">|");
        btnTien = new JButton(">");
        btnLui = new JButton("<");
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        btnAdd.setIconTextGap(10);
        btnUpdate.setIconTextGap(10);
        btnDelete.setIconTextGap(10);
        btnTim.setIconTextGap(10);
        btnAdd.setPreferredSize(new Dimension(120, 35));
        btnUpdate.setPreferredSize(new Dimension(120, 35));
        btnDelete.setPreferredSize(new Dimension(120, 35));
        btnTim.setPreferredSize(new Dimension(120, 35));
        
        txtMa.setMargin(new Insets(0, 10, 0, 0));
        txtTen.setMargin(new Insets(0, 10, 0, 0));
        txtSDT.setMargin(new Insets(0, 10, 0, 0));
        txtEmail.setMargin(new Insets(0, 10, 0, 0));
        textAreaDiaChi.setMargin(new Insets(10, 10, 10, 0));
        
        scrollPane.setPreferredSize(new Dimension(350, 100));
    }
    
    public void add() {
        f.add(lblTieuDe);
        
        JComponent mangPanel1[] = {lblMa, lblTen, lblEmail, lblSDT, txtMa, txtTen, txtEmail, txtSDT, btnAdd, btnDelete, btnUpdate, btnTim};
        addChung(panel1, gbc, mangPanel1, 4, 3, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        buttonGroup.add(radNu);
        buttonGroup.add(radNam);
        panel3.add(radNam);
        panel3.add(radNu);
        JComponent mangPanel2[] = {lblGioiTinh, lblDiaChi, panel3, scrollPane};
        addChung(panel2, gbc, mangPanel2, 2, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        JComponent mangPanel4[] = {btnDau, btnLui, btnTien, btnCuoi};
        addChung(panel4, gbc, mangPanel4, 1, 4, null, 0);
        
        JComponent mangF[] = {lblTieuDe, panel1, panel2, panel4};
        addChung(f, gbc, mangF, 4, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);

        f.pack();
        f.setDefaultCloseOperation(3);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void event() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent mang[] = {txtMa, txtTen, txtEmail, txtSDT, null, textAreaDiaChi};
                kiemTra();
                if (sai == 0 && trong == 0) {
                    thucHienSql("insert into student values(?, ?, ?, ?, ?, ?)", 6, mang, "Add thành công", "Lỗi thêm dữ liệu");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent mang[] = {txtMa};
                thucHienSql("delete from student where ma_sv = ?", 1, mang, "Xóa thành công", "Không xóa được do mã sinh viên không đúng");
                try {
                    pst = con.prepareStatement("select * from student");
                    rs = pst.executeQuery();
                    duaLenJTextField();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }            
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent mang[] = {txtMa, txtTen, txtEmail, txtSDT, null, textAreaDiaChi, txtMa};
                String updateSql = "update student set ma_sv = ?, ho_ten = ?, email = ?, sdt = ?, gioi_tinh = ?, dia_chi = ? where ma_sv = ?";
                kiemTra();
                if (sai == 0 && trong == 0) {
                    thucHienSql(updateSql, 7, mang, "Update thành công", "Không update được do không đúng mã sinh viên hoặc nhiều lý do khác");
                }
            }
        });
        
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int khong = 0;
                try {
                    pst = con.prepareStatement("select * from student");
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        if (rs.getString(1).equals(txtMa.getText())) {
                            pst = con.prepareStatement("select * from student where ma_sv = ?");
                            pst.setString(1, txtMa.getText());
                            rs = pst.executeQuery();
                            duaLenJTextField();
                            khong = 0;
                            break;
                        }
                        else {
                            khong++;
                        }
                    }
                    if (khong > 0) {
                        JOptionPane.showMessageDialog(f, "Không tìm thấy");
                    }
                } 
                catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(f, "Không tìm thấy");
                }
            }
        });
        
        btnDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("select * from student");
                    rs = pst.executeQuery();
                    duaLenJTextField();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnCuoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("select * from student", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = pst.executeQuery();
                    rs.last();
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
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("select * from student", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        if (txtMa.getText().equals(rs.getString(1))) {
                            if (rs.next()) {
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
                            }
                            else {
                                JOptionPane.showMessageDialog(f, "Đã ở cuối danh sách");
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnLui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pst = con.prepareStatement("select * from student", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        if (txtMa.getText().equals(rs.getString(1))) {
                            if (rs.previous()) {
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
                            }
                            else {
                                JOptionPane.showMessageDialog(f, "Đã ở đầu danh sách");
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}
