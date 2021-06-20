package assignment;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

public class QuanLySinhVien extends SinhVien {

    public QuanLySinhVien() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    public void taoDoiTuong() {
        f = new JInternalFrame("Quản lý sinh viên");
        f.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        fileChooser = new JFileChooser(new File("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image"));
        
        lblTieuDe = new JLabel("<html><p style = \"font-family: tahoma; font-size: 30px; color: blue\">Quản lý sinh viên</p></html>");
        
        panelThan = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelLeft = new JPanel(new GridBagLayout());
        panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        lblMa = new JLabel("Mã SV:");
        lblTen = new JLabel("Họ tên:");
        lblEmail = new JLabel("Email:");
        lblSDT = new JLabel("SĐT");
        lblGioiTinh = new JLabel("Giới tính:");
        lblDiaChi = new JLabel("Địa chỉ:");
        lblAnh = new JLabel();
        lblImg = new JLabel("Img");
        
        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSDT = new JTextField(20);
        
        buttonGroup = new ButtonGroup();
        radioButtonNam = new JRadioButton("Nam");
        radioButtonNu = new JRadioButton("Nữ");
        
        textArea = new JTextArea();
        scrollPane1TextArea = new JScrollPane(textArea);
        
        panelRight = new JPanel(new GridBagLayout());
        panelButton = new JPanel(new GridLayout(2, 2, 10, 10));
        
        btnNew = new JButton("New", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\new.png"));
        btnDelete = new JButton("Delete", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\delete.png"));
        btnSave = new JButton("Save", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\save.png"));
        btnUpdate = new JButton("Update", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\update.png"));
        btnImg = new JButton("Chọn ảnh");
        
        table = new JTable(model);
        scrollPaneTable = new JScrollPane(table);
    }
    
    public void taoDang() {
        scrollPane1TextArea.setPreferredSize(new Dimension(250, 100));
        scrollPane1TextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1TextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        lblAnh.setPreferredSize(new Dimension(200, 200));
        lblAnh.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        
        btnNew.setIconTextGap(10);
        btnUpdate.setIconTextGap(10);
        btnDelete.setIconTextGap(10);
        btnSave.setIconTextGap(10);
        
        scrollPaneTable.setPreferredSize(new Dimension(700, 200));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(0);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(0);
        
        f.setClosable(true);
    }
    
    public void add() {
        buttonGroup.add(radioButtonNam);
        buttonGroup.add(radioButtonNu);
        panelRadio.add(radioButtonNam);
        panelRadio.add(radioButtonNu);
        
        JComponent mangPanelLeft[] = {lblMa, lblTen, lblEmail, lblSDT, lblGioiTinh, lblDiaChi, lblImg, txtMa, txtTen, txtEmail, txtSDT, panelRadio, scrollPane1TextArea, btnImg};
        addChung(panelLeft, gbc, mangPanelLeft, 7, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        JComponent mangButton[] = {btnNew, btnDelete, btnSave, btnUpdate};
        addChung(panelButton, null, mangButton, 2, 2, null, 0);
        
        JComponent mangPanelRight[] = {lblAnh, panelButton};
        addChung(panelRight, gbc, mangPanelRight, 2, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        JComponent mangThan[] = {panelLeft, panelRight};
        addChung(panelThan, null, mangThan, 1, 2, null, 0);
        
        JComponent mangF[] = {lblTieuDe, panelThan, scrollPaneTable};
        addChung(f, gbc, mangF, 3, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        f.pack();
        f.setLocation(570, 10);
        f.setVisible(true);
    }
    
    public void event() {
        f.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                KhungTo.menuQuanLySinhVien.setEnabled(true);
            }
            
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                fillToTable();
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiemTraSinhVien();
                if (sai == 0 && trong == 0) {
                    try {
                        pst = con.prepareStatement("insert into student values(?, ?, ?, ?, ?, ?, ?)");
                        pst.setString(1, txtMa.getText());
                        pst.setString(2, txtTen.getText());
                        pst.setString(3, txtEmail.getText());
                        pst.setString(4, txtSDT.getText());
                        pst.setBoolean(5, radioButtonNam.isSelected() ? true : false);
                        pst.setString(6, textArea.getText());
                        ImageIcon imageIcon = (ImageIcon) lblAnh.getIcon();
                        pst.setString(7, imageIcon.getDescription());
                        pst.executeUpdate();
                        fillToTable();
                        JOptionPane.showMessageDialog(f, "Lưu thành công");
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
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
                kiemTraSinhVien();
                if (sai == 0 && trong == 0) {
                    try {
                        table.getSelectedRow();
                        pst = con.prepareStatement("update student set ma_sv = ?, ten = ?, email = ?, sdt = ?, gioi_tinh = ?, dia_chi = ?, hinh = ? where ma_sv = ?");
                        pst.setString(1, txtMa.getText());
                        pst.setString(2, txtTen.getText());
                        pst.setString(3, txtEmail.getText());
                        pst.setString(4, txtSDT.getText());
                        pst.setBoolean(5, radioButtonNam.isSelected() ? true : false);
                        pst.setString(6, textArea.getText());
                        ImageIcon imageIcon = (ImageIcon) lblAnh.getIcon();
                        pst.setString(7, imageIcon.getDescription());
                        pst.setString(8, txtMa.getText());
                        pst.executeUpdate();
                        fillToTable();
                        JOptionPane.showMessageDialog(f, "Update thành công");
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(f, "Bạn hãy chọn 1 dòng trong bảng trước");
                    }
                }
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                SinhVien sv = list.get(index);
                txtMa.setText(sv.ma);
                txtTen.setText(sv.ten);
                txtEmail.setText(sv.email);
                txtSDT.setText(sv.sdt);
                if (sv.gioiTinh == true) {
                    radioButtonNam.setSelected(true);
                }
                else {
                    radioButtonNu.setSelected(true);
                }
                textArea.setText(sv.diaChi);
                lblAnh.setIcon(new ImageIcon(sv.hinh));
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMa.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Không để trống ô mã sinh viên");
                }
                else {
                    try {
                        int index = table.getSelectedRow();
                        SinhVien sv = list.get(index);
                        pst = con.prepareStatement("delete from student where ma_sv = ?");
                        pst.setString(1, sv.ma);
                        pst.executeUpdate();
                        fillToTable();
                        lamMoi();
                        JOptionPane.showMessageDialog(f, "Xóa thành công");
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(f, "Phải xóa bên quản lý điểm trước");
                    }
                }
            }
        });
        
        btnImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int traVe = fileChooser.showOpenDialog(f);
                if (traVe == JFileChooser.APPROVE_OPTION) {
                    try {
                        lblAnh.setIcon(new ImageIcon(fileChooser.getSelectedFile().getPath()));
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }
}
