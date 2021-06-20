package assignment;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class QuanLyDiemSinhVien extends Diem {

    public QuanLyDiemSinhVien() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    public void taoDoiTuong() {
        f = new JInternalFrame("Quản lý điểm sinh viên");
        gbc = new GridBagConstraints();
        lblTieuDe = new JLabel("<html><p style = \"font-size: 30px; font-family: tahoma; color: blue\">Quản lý điểm sinh viên</p></html>");
        
        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        lblMaDeTim = new JLabel("Mã SV cần tìm");
        cboSearch = new JComboBox();
        btnSearch = new JButton("Search", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\search.jpg"));
        txtCbo = (JTextField) cboSearch.getEditor().getEditorComponent();
        
        panelLeft = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        PanelLeftTrai = new JPanel(new GridBagLayout());
        PanelLeftPhai = new JPanel(new GridLayout(2, 1, 0, 10));
        lblTen = new JLabel("Họ tên SV:");
        lblMa = new JLabel("Mã SV:");
        lblEnglish = new JLabel("Tiếng anh:");
        lblTin = new JLabel("Tin học:");
        lblGDTC = new JLabel("GDTC:");
        lblDTB = new JLabel("Điểm TB:");
        lblDTBInfo = new JLabel();
        lblTxtTen = new JLabel();
        txtMa = new JTextField(20);
        txtEnglish = new JTextField(20);
        txtTin = new JTextField(20);
        txtGDTC = new JTextField(20);
        
        panelRight = new JPanel(new GridLayout(4, 1, 0, 10));
        btnNew = new JButton("New", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\new.png"));
        btnSave = new JButton("Save", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\Save.png"));
        btnDelete = new JButton("Delete", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\Delete.png"));
        btnUpdate = new JButton("Update", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\Update.png"));
        
        panelBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnDau = new JButton("|<");
        btnLui = new JButton("<");
        btnTien = new JButton(">");
        btnCuoi = new JButton(">|");
        
        lblMax = new JLabel("<html><p style = \"font-family: tahoma; font-size: 15px; color: blue\">3 sinh viên có điểm cao nhất</p></html>");
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        f.setClosable(true);
        
        cboSearch.setEditable(true);
        cboSearch.setMaximumRowCount(7);
        cboSearch.setPreferredSize(new Dimension(250, 25));

        panelTop.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("", Font.BOLD, 15), Color.black));
        panelLeft.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        
        lblDTBInfo.setForeground(Color.blue);
        lblDTBInfo.setFont(new Font("", Font.BOLD, 20));
        lblDTBInfo.setHorizontalAlignment(JLabel.CENTER);
        
        btnSearch.setIconTextGap(10);
        btnNew.setIconTextGap(10);
        btnSave.setIconTextGap(10);
        btnDelete.setIconTextGap(10);
        btnUpdate.setIconTextGap(10);
        
        table.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(0);
        table.getColumnModel().getColumn(3).setPreferredWidth(0);
        table.getColumnModel().getColumn(4).setPreferredWidth(0);
        table.getColumnModel().getColumn(5).setPreferredWidth(0);
    }
    
    public void add() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        f.add(lblTieuDe, gbc);

        panelTop.add(lblMaDeTim);
        panelTop.add(cboSearch);
        panelTop.add(btnSearch);
        
        gbc.gridy = 1;
        f.add(panelTop, gbc);

        gbc.gridwidth = 1;
        JComponent mangPanelLeftTrai[] = {lblTen, lblMa, lblEnglish, lblTin, lblGDTC, lblTxtTen, txtMa, txtEnglish, txtTin, txtGDTC};
        addChung(PanelLeftTrai, gbc, mangPanelLeftTrai, 5, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        PanelLeftPhai.add(lblDTB);
        PanelLeftPhai.add(lblDTBInfo);
        
        panelLeft.add(PanelLeftTrai);
        panelLeft.add(PanelLeftPhai);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        f.add(panelLeft, gbc);
        
        JComponent mangPanelRight[] = {btnNew, btnSave, btnDelete, btnUpdate};
        addChung(panelRight, null, mangPanelRight, 4, 1, null, 0);
        
        gbc.gridx = 1;
        f.add(panelRight, gbc);
        
        JComponent mangPanelBot[] = {btnDau, btnLui, btnTien, btnCuoi};
        addChung(panelBot, null, mangPanelBot, 1, 4, null, 0);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        f.add(panelBot, gbc);
        
        gbc.gridy = 4;
        f.add(lblMax, gbc);
        
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        f.add(scrollPane, gbc);
        
        f.pack();
        f.setLocation(10, 10);
        f.setVisible(true);
    }
    
    public void event() {
        txtCbo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                List<String> a = new ArrayList<>();
                for (int i = 0; i < listCbo.size(); i++) {
                    if (listCbo.get(i).startsWith(txtCbo.getText())) {
                        a.add(listCbo.get(i));
                    }
                }
                if (a.size() > 0) {
                    String b = txtCbo.getText();
                    cboSearch.setModel(new DefaultComboBoxModel(a.toArray()));
                    cboSearch.setSelectedItem(b);
                    cboSearch.showPopup();
                }
                else {
                    cboSearch.hidePopup();
                }
            }
        });
        
        f.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                fillToTable();
            }
            
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                KhungTo.menuQuanLyDiemSinhVien.setEnabled(true);
            }
        });
        
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lamMoi();
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiemTraDiem();
                if (trong == 0 && sai == 0) {
                    try {
                        pst = con.prepareStatement("update grade set tieng_anh = ?, tin = ?, gdtc = ? where ma_sv = ?");
                        pst.setString(1, txtEnglish.getText());
                        pst.setString(2, txtTin.getText());
                        pst.setString(3, txtGDTC.getText());
                        pst.setString(4, txtMa.getText());
                        int update = pst.executeUpdate();
                        if (update > 0) {
                            listCbo.clear();
                            fillToTable();
                            JOptionPane.showMessageDialog(f, "Update thành công");
                        }
                        else {
                            JOptionPane.showMessageDialog(f, "Không có mã sinh viên nào là '" + txtMa.getText() + "'");
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtMa.getText().isEmpty()) {
                    try {
                        pst = con.prepareStatement("delete from grade where ma_sv = ?");
                        pst.setString(1, txtMa.getText());
                        int delete = pst.executeUpdate();
                        if (delete > 0) {
                            listCbo.clear();
                            fillToTable();
                            lamMoi();
                            JOptionPane.showMessageDialog(f, "Xóa thành công");
                        }
                        else {
                            JOptionPane.showMessageDialog(f, "Không có mã sinh viên nào là '" + txtMa.getText() + "'");
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(f, "Không để trống ô mã sinh viên");
                }
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                Diem d = list.get(index);
                lblTxtTen.setText(d.ten);
                txtMa.setText(d.ma);
                txtEnglish.setText(String.valueOf(d.english));
                txtTin.setText(String.valueOf(d.tin));
                txtGDTC.setText(String.valueOf(d.gdtc));
                lblDTBInfo.setText(String.valueOf(d.dtb));
            }
        });
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtCbo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Không để trống ô tìm kiếm");
                }
                else {
                    try {
                        pst = con.prepareStatement("select ten, student.ma_sv, tieng_anh, tin, gdtc, round((tieng_anh + tin + gdtc)/3, 2) from grade inner join student on student.ma_sv = grade.ma_sv where student.ma_sv = ?");
                        pst.setString(1, (String) cboSearch.getSelectedItem());
                        rs = pst.executeQuery();
                        rs.next();
                        lblTxtTen.setText(rs.getString(1));
                        txtMa.setText(rs.getString(2));
                        txtEnglish.setText(rs.getString(3));
                        txtTin.setText(rs.getString(4));
                        txtGDTC.setText(rs.getString(5));
                        lblDTBInfo.setText(""+Double.parseDouble(rs.getString(6)));
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(f, "Không có mã sinh viên nào là '" + txtCbo.getText() + "'");
                    }
                }
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiemTraDiem();
                if (sai == 0 && trong == 0) {
                    try {
                        pst = con.prepareStatement("insert into grade values (?, ?, ?, ?)");
                        pst.setString(1, txtMa.getText());
                        pst.setString(2, txtEnglish.getText());
                        pst.setString(3, txtTin.getText());
                        pst.setString(4, txtGDTC.getText());
                        pst.executeUpdate();
                        listCbo.clear();
                        fillToTable();
                        JOptionPane.showMessageDialog(f, "Thêm mới thành công");
                    }
                    catch (SQLException ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(f, "Lỗi thêm mới do trùng mã sinh viên hoặc không có mã sinh viên " + "'" + txtMa.getText() + "'" + " trong danh sách sinh viên");
                    }
                }
            }
        });
        
        btnCuoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dauCuoi(1);
            }
        });
        
        btnDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dauCuoi(0);
            }
        });
        
        btnTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tienLui(1);
            }
        });
        
        btnLui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tienLui(0);
            }
        });
    }
}
