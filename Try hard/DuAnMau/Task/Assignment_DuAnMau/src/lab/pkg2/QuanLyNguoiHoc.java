package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;

public class QuanLyNguoiHoc extends Khung {
    JLabel lblTitle, lblChuyenDe, lblKhoaHoc, lblMa, lblHoTen, lblGioiTinh, lblNgaySinh, lblDienThoai, lblEmail, lblGhiChu, lblTimMaNH, lblTimMaKH;
    JTextField txtMa, txtHoTen, txtNgaySinh, txtDienThoai, txtEmail, txtTimMaNH;
    JTextArea txtGhiChu;
    JComboBox cboChuyenDe, cboKhoaHoc, cboGioiTinh, cboTimMaKH;
    JButton btnInsert, btnUpdate, btnDeleteEditor, btnDeleteRenderer, btnClear, btnTim, btnSave, btnBoQua;
    JRadioButton rdoKo, rdoCo;
    JPanel pnlJFrame, pnlInfo, pnlBtn, pnlTim, pnlRdo, pnlInfoBtnTim, pnlNorth, pnlCenter;
    GridBagConstraints gbc;
    JScrollPane scrGhiChu, scrTbl;
    JTable tblGridView;
    String tenCot[] = {"STT", "Mã", "Họ tên", "Nam", "Nữ", "Ngày ĐK", "Điểm", ""};
    DefaultTableModel model;
    List<Object> list;
    String regexMa = "[0-9]*";
    String regexTen = "[a-zA-Z \\u0080-\\u9fff]+";
    String regexSDT = "0\\d+";
    String regexNgaySinh = "([0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}){1}";
    String regexEmail = "\\w+@\\w+(\\.\\w+){1,2}";
    String regexDiem = "[0-9\\.]*";
    String txtTim;
    ButtonGroup bgr;
    int luu = 0;
    int sai, trong;
    boolean coMa;
    boolean suaDiem = false;
    boolean tim = false;
    int infoDiem;

    public QuanLyNguoiHoc() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();
        bgr = new ButtonGroup();

        pnlJFrame = new JPanel(new BorderLayout(0, 10));
        pnlInfo = new JPanel(new GridBagLayout());
        pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        pnlTim = new JPanel(new GridBagLayout());
        pnlRdo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnlNorth = new JPanel(new BorderLayout(0, 10));
        pnlCenter = new JPanel(new BorderLayout());
        pnlInfoBtnTim = new JPanel(new GridBagLayout());

        cboChuyenDe = new JComboBox();
        cboKhoaHoc = new JComboBox();
        cboGioiTinh = new JComboBox(new String[]{"Nam", "Nữ"});
        cboTimMaKH = new JComboBox();
        
        lblTitle = new JLabel("Quản lý học viên");
        lblChuyenDe = new JLabel("Chuyên đề");
        lblKhoaHoc = new JLabel("Khóa học");
        lblMa = new JLabel("Mã học viên");
        lblHoTen = new JLabel("Họ tên");
        lblGioiTinh = new JLabel("Giới tính");
        lblNgaySinh = new JLabel("Ngày sinh");
        lblDienThoai = new JLabel("Điện thoại");
        lblEmail = new JLabel("Email");
        lblGhiChu = new JLabel("Ghi chú");
        lblTimMaNH = new JLabel("Mã học viên");
        lblTimMaKH = new JLabel("Khóa học đã đăng ký");
        
        txtMa = new JTextField(18);
        txtHoTen = new JTextField(18);
        txtNgaySinh = new JTextField(18);
        txtDienThoai = new JTextField(18);
        txtEmail = new JTextField(18);
        txtTimMaNH = new JTextField(21);
        
        txtGhiChu = new JTextArea();
        scrGhiChu = new JScrollPane(txtGhiChu);
        
        btnInsert = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnClear = new JButton("Làm mới");
        btnSave = new JButton("Lưu");
        btnTim = new JButton("Tìm theo mã");
        btnBoQua = new JButton("Bỏ qua");

        rdoCo = new JRadioButton("Đã nhập điểm");
        rdoKo = new JRadioButton("Chưa nhập điểm");
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model);
        scrTbl = new JScrollPane(tblGridView);
        
        list = new ArrayList();
    }

    @Override
    public void taoDang() {
        lblTitle.setOpaque(true);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("tahoma", Font.BOLD, 14));
        lblTitle.setForeground(Color.white);
        lblTitle.setPreferredSize(new Dimension(580, 30));
        lblTitle.setBackground(Color.darkGray);
        
        pnlBtn.setPreferredSize(new Dimension(580, 30));
        pnlTim.setPreferredSize(new Dimension(505, 70));
        pnlTim.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        
        scrGhiChu.setPreferredSize(new Dimension(580, 50));
        scrGhiChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        scrTbl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        tblGridView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblGridView.setFillsViewportHeight(true);
        tblGridView.setRowHeight(30);
        tblGridView.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblGridView.getColumnModel().getColumn(1).setPreferredWidth(0);
        tblGridView.getColumnModel().getColumn(2).setPreferredWidth(130);
        tblGridView.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblGridView.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblGridView.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblGridView.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblGridView.getColumnModel().getColumn(7).setPreferredWidth(50);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        tblGridView.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(4).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(5).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(6).setCellRenderer(dtcr);
        
        DefaultCellEditor dce = new DefaultCellEditor(new JTextField()) {
            public boolean isCellEditable(EventObject anEvent) {
                return false;
            }
        };
        
        tblGridView.getColumnModel().getColumn(0).setCellEditor(dce);
        tblGridView.getColumnModel().getColumn(1).setCellEditor(dce);
        tblGridView.getColumnModel().getColumn(2).setCellEditor(dce);
        tblGridView.getColumnModel().getColumn(3).setCellEditor(dce);
        tblGridView.getColumnModel().getColumn(4).setCellEditor(dce);
        tblGridView.getColumnModel().getColumn(5).setCellEditor(dce);
        tblGridView.getColumnModel().getColumn(6).setCellEditor(dce);
        
        // Cột 7: xóa
        btnDeleteRenderer = new JButton("Xóa");
        DefaultTableCellRenderer dtcrDelete = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return btnDeleteRenderer;
            }
        };
            
        btnDeleteEditor = new JButton("Xóa");
        DefaultCellEditor dceDelete = new DefaultCellEditor(new JCheckBox()) {
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                btnDeleteEditor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fireEditingStopped();
                    }
                });
                return btnDeleteEditor;
            }
            
            public boolean isCellEditable(EventObject anEvent) {
                if (btnDeleteEditor.isEnabled()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        };
        
        tblGridView.getColumnModel().getColumn(7).setCellRenderer(dtcrDelete);
        tblGridView.getColumnModel().getColumn(7).setCellEditor(dceDelete);
        
        cboChuyenDe.setPreferredSize(txtHoTen.getPreferredSize());
        cboKhoaHoc.setPreferredSize(txtHoTen.getPreferredSize());
        cboGioiTinh.setPreferredSize(txtHoTen.getPreferredSize());
        cboTimMaKH.setPreferredSize(txtTimMaNH.getPreferredSize());
        
        txtMa.setEditable(false);
        txtHoTen.setEditable(false);
        cboGioiTinh.setEnabled(false);
        txtNgaySinh.setEditable(false);
        txtDienThoai.setEditable(false);
        txtEmail.setEditable(false);
        txtGhiChu.setEditable(false);
        
        btnSave.setEnabled(false);
        btnBoQua.setEnabled(false);
    }

    @Override
    public void add() {
        JComponent a[] = {lblChuyenDe, lblKhoaHoc, lblGioiTinh, lblDienThoai, cboChuyenDe, cboKhoaHoc, cboGioiTinh, txtDienThoai, lblMa, lblHoTen, lblNgaySinh, lblEmail, txtMa, txtHoTen, txtNgaySinh, txtEmail};
        addChung(pnlInfo, gbc, a, 4, 4, new Insets(5, 5, 0, 5), 17);
        
        JComponent b[] = {btnInsert, btnUpdate, btnClear, btnSave, btnBoQua};
        addChung(pnlBtn, null, b, 5, 1, null, 0);
        
        JComponent c[] = {lblTimMaNH, lblTimMaKH, txtTimMaNH, cboTimMaKH};
        addChung(pnlTim, gbc, c, 2, 2, new Insets(5, 5, 5, 5), 17);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        pnlTim.add(btnTim, gbc);
        gbc.gridheight = 1;
        
        bgr.add(rdoCo);
        bgr.add(rdoKo);
        pnlRdo.add(rdoCo);
        pnlRdo.add(rdoKo);
        
        JComponent d[] = {lblTitle, pnlInfo, lblGhiChu, scrGhiChu, pnlBtn, pnlTim};
        addChung(pnlInfoBtnTim, gbc, d, 6, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        pnlCenter.add(pnlRdo, BorderLayout.NORTH);
        pnlCenter.add(scrTbl, BorderLayout.CENTER);
        
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlInfoBtnTim, BorderLayout.CENTER);
        
        pnlJFrame.add(pnlNorth, BorderLayout.NORTH);
        pnlJFrame.add(pnlCenter, BorderLayout.CENTER);
    }

    @Override
    public void event() {
        cboChuyenDe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (luu == 1) {
                        addItemKhoaHoc("handk >= getdate()");
                    }
                    else if (luu == 2 && suaDiem == false) {
                        addItemKhoaHoc("");
                    }
                    else if (luu == 2 && suaDiem == true) {
                        addItemKhoaHoc("ngaykg < getdate()");
                    }
                    else {
                        btnDeleteRenderer.setEnabled(true);
                        btnDeleteEditor.setEnabled(true);
                        for (int i = 0; i < tblGridView.getRowCount(); i++) {
                            tblGridView.changeSelection(i, 0, false, false);
                        }
                        addItemKhoaHoc("");
                    }
                }
            }
        });
        
        cboKhoaHoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (tim == false) {
                        eventCboKhoaHoc();
                        txtTimMaNH.setText("");
                        cboTimMaKH.removeAllItems();
                    }
                    else {
                        eventCboKhoaHoc();
                    }
                }
            }
        });
        
        pnlJFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                try {
                    cboChuyenDe.removeAllItems();
                    pst = con.prepareStatement("select tencd from chuyende");
                    rs2 = pst.executeQuery();
                    
                    while (rs2.next()) {
                        cboChuyenDe.addItem(rs2.getString(1));
                    }
                } 
                catch (Exception ex) {
                }
            }
        });
        
        tblGridView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                showDetail();
            }
        });
        
        rdoCo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (luu == 2) {
                        fillToTable(false);
                    }
                    else {
                        fillToTable(true);
                    }
                }
            }
        });

        rdoKo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (luu == 2) {
                        fillToTable(false);
                    }
                    else {
                        fillToTable(true);
                    }
                }
            }
        });
        
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtTimMaNH.getText().isEmpty()) {
                        txtTimMaNH.setText("");
                        cboTimMaKH.removeAllItems();
                        JOptionPane.showMessageDialog(pnlJFrame, "Không để trống ô mã học viên");
                    }
                    else {
                        if (!txtTimMaNH.getText().matches(regexMa)) {
                            txtTimMaNH.setText("");
                            cboTimMaKH.removeAllItems();
                            JOptionPane.showMessageDialog(pnlJFrame, "Mã chỉ chứa kí tự số");
                        }
                        else {
                            pst = con.prepareStatement("select tencd, ngaykgConverted, makh from dbo.fn_select_hocvien() where manh = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            pst.setString(1, txtTimMaNH.getText());
                            rs3 = pst.executeQuery();
                            boolean tonTaiMaNH = rs3.next();
                            
                            if (tonTaiMaNH == false) {
                                txtTimMaNH.setText("");
                                cboTimMaKH.removeAllItems();
                                JOptionPane.showMessageDialog(pnlJFrame, "Mã học viên không tồn tại");
                            }
                            else {
                                if (suaDiem == false) {
                                    tim = true;
                                    bgr.clearSelection();
                                    cboChuyenDe.setEditable(true);
                                    cboChuyenDe.setSelectedItem("");
                                    cboChuyenDe.setEditable(false);
                                    cboChuyenDe.setSelectedItem(rs3.getString(1));

                                    if (rs3.getString(1) == null) {
                                        cboKhoaHoc.removeAllItems();
                                        cboTimMaKH.removeAllItems();
                                        fillToTable(false);
                                    }
                                    else {
                                        txtTim = txtTimMaNH.getText();
                                        cboKhoaHoc.setSelectedItem(rs3.getString(3) + " - (Khai giảng: " + rs3.getString(2) + ")");
                                        cboTimMaKH.removeAllItems();
                                        rs3.beforeFirst();
                                        while (rs3.next()) {
                                            cboTimMaKH.addItem(rs3.getString(1) + " (" + rs3.getString(2) + ")");
                                        }
                                    }

                                    for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                        tblGridView.changeSelection(i, 0, false, false);
                                        int index = tblGridView.getSelectedRow();
                                        Object row[] = (Object[]) list.get(index);
                                        if ((int) row[2] == Integer.parseInt(txtTimMaNH.getText())) {
                                            showDetail();
                                            break;
                                        }
                                    }
                                    
                                    tim = false;
                                }
                                else {
                                    pst = con.prepareCall("select tencd, ngaykgConverted, makh from dbo.fn_select_hocvien() where ngaykg < getdate() and manh = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                    pst.setString(1, txtTimMaNH.getText());
                                    rs3 = pst.executeQuery();
                                    boolean duocSuaDiem = rs3.next();

                                    if (duocSuaDiem == false) {
                                        txtTimMaNH.setText("");
                                        cboTimMaKH.removeAllItems();
                                        JOptionPane.showMessageDialog(pnlJFrame, "Học viên bạn muốn tìm không được phép sửa điểm vì chưa đăng ký khóa học nào hoặc khóa học đó chưa khai giảng");
                                    }
                                    else {
                                        tim = true;
                                        txtTim = txtTimMaNH.getText();
                                        cboTimMaKH.removeAllItems();
                                        rs3.beforeFirst();
                                        while (rs3.next()) {
                                            cboTimMaKH.addItem(rs3.getString(1) + " (" + rs3.getString(2) + ")");
                                        }
                                        tim = false;
                                    }
                                }
                            }
                        }
                    }
                } 
                catch (Exception ex) {
                }
            }
        });

        txtTimMaNH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnTim.doClick();
                }
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lamMoi();
            }
        });
        
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int chon = JOptionPane.showConfirmDialog(pnlJFrame, "Người học bạn muốn thêm đã có mã chưa", "Thêm", JOptionPane.YES_NO_OPTION, 3);
                if (chon == JOptionPane.YES_OPTION) {
                    txtMa.setEditable(true);
                    txtMa.requestFocus();
                    coMa = true;
                }
                else if (chon == JOptionPane.NO_OPTION) {
                    txtHoTen.setEditable(true);
                    cboGioiTinh.setEnabled(true);
                    txtNgaySinh.setEditable(true);
                    txtDienThoai.setEditable(true);
                    txtEmail.setEditable(true);
                    txtGhiChu.setEditable(true);    
                    txtHoTen.requestFocus();
                    coMa = false;
                }
                else {
                    return;
                }

                luu = 1;
                lamMoi();
                
                btnInsert.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnSave.setEnabled(true);
                btnBoQua.setEnabled(true);

                btnTim.setEnabled(false);
                txtTimMaNH.setEnabled(false);
                cboTimMaKH.setEnabled(false);
                
                tblGridView.setEnabled(false);
                rdoCo.setEnabled(false);
                rdoKo.setEnabled(false);
                
                btnDeleteRenderer.setEnabled(false);
                btnDeleteEditor.setEnabled(false);
                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                    tblGridView.changeSelection(i, 0, false, false);
                }
                tblGridView.changeSelection(0, 1, false, false);
                tblGridView.clearSelection();
                
                addItemKhoaHoc("handk >= getdate()");
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a[] = {"Sửa thông tin", "Sửa điểm"};
                infoDiem = JOptionPane.showOptionDialog(pnlJFrame, "Bạn muốn sửa thông tin học viên hay sửa điểm", "Sửa", 0, 3, null, a, null);
                switch (infoDiem) {
                    case 0:
                        luu = 2;
                        int indexInfo = tblGridView.getSelectedRow();

                        cboChuyenDe.setEnabled(false);
                        cboKhoaHoc.setEnabled(false);
                        cboTimMaKH.setEnabled(false);

                        txtMa.setEditable(false);
                        txtHoTen.setEditable(true);
                        cboGioiTinh.setEnabled(true);
                        txtNgaySinh.setEditable(true);
                        txtDienThoai.setEditable(true);
                        txtEmail.setEditable(true);
                        txtGhiChu.setEditable(true);  

                        btnInsert.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        btnSave.setEnabled(true);
                        btnBoQua.setEnabled(true);

                        btnDeleteRenderer.setEnabled(false);
                        btnDeleteEditor.setEnabled(false);
                        for (int i = 0; i < tblGridView.getRowCount(); i++) {
                            tblGridView.changeSelection(i, 0, false, false);
                        }

                        if (indexInfo == -1) {
                            tblGridView.changeSelection(0, 0, false, false);
                            tblGridView.clearSelection();
                        }
                        else {
                            tblGridView.changeSelection(0, 0, false, false);
                            tblGridView.changeSelection(indexInfo, 0, false, false);
                        } 
                        break;
                    case 1:
                        luu = 2;
                        suaDiem = true;
                        int indexDiem = tblGridView.getSelectedRow();

                        txtMa.setEditable(false);
                        
                        btnInsert.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        btnSave.setEnabled(false);
                        btnBoQua.setEnabled(true);
                        
                        txtTimMaNH.setText("");
                        cboTimMaKH.removeAllItems();

                        btnDeleteRenderer.setEnabled(false);
                        btnDeleteEditor.setEnabled(false);
                        for (int i = 0; i < tblGridView.getRowCount(); i++) {
                            tblGridView.changeSelection(i, 0, false, false);
                        }

                        if (indexDiem == -1) {
                            tblGridView.changeSelection(0, 0, false, false);
                            tblGridView.clearSelection();
                        }
                        else {
                            tblGridView.changeSelection(0, 0, false, false);
                            tblGridView.changeSelection(indexDiem, 0, false, false);
                        }
                        
                        if (cboKhoaHoc.getSelectedItem() == null) {
                            DefaultCellEditor dce = new DefaultCellEditor(new JTextField()) {
                                public boolean isCellEditable(EventObject anEvent) {
                                    return false;
                                }
                            };

                            tblGridView.getColumnModel().getColumn(6).setCellEditor(dce);                    
                        }
                        else {
                            DefaultCellEditor cotDiem = new DefaultCellEditor(new JTextField());
                            cotDiem.setClickCountToStart(1);
                            tblGridView.getColumnModel().getColumn(6).setCellEditor(cotDiem);
                        }

                        addItemKhoaHoc("ngaykg < getdate()");
                        break;
                }
            }
        });
        
        btnDeleteEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = tblGridView.getSelectedRow();
                    Object row[] = (Object[]) list.get(index);
                    pst = con.prepareStatement("select * from hocvien where manh = ?");
                    pst.setInt(1, (int) row[2]);
                    rs1 = pst.executeQuery();
                    boolean timThay = rs1.next();
                    int chon;
                    boolean xoaHocVien = true;

                    if (timThay == true) {
                        chon = JOptionPane.showConfirmDialog(pnlJFrame, "Bạn có chắc chắn muốn xóa học viên khỏi khóa học không", "Xóa", JOptionPane.YES_NO_OPTION, 3);
                    }
                    else {
                        chon = JOptionPane.showConfirmDialog(pnlJFrame, "Bạn có chắc muốn xóa vĩnh viễn người học này không", "Xóa", JOptionPane.YES_NO_OPTION, 3);
                        xoaHocVien = false;
                    }
                    
                    if (chon == JOptionPane.YES_OPTION) {
                        if (xoaHocVien == true) {
                            pst = con.prepareStatement("delete from hocvien where manh = ? and makh = ?");
                            pst.setInt(1, (int) row[2]);
                            pst.setInt(2, (int) row[1]);
                            pst.executeUpdate();
                            fillToTable(false);
                        }
                        else {
                            pst = con.prepareStatement("delete from nguoihoc where manh = ?");
                            pst.setInt(1, (int) row[2]);
                            pst.executeUpdate();
                            fillToTable(false);
                        }
                        
                        if (index == tblGridView.getRowCount()) {
                            if (index == 0) {
                                lamMoi();
                            }
                            else {
                                tblGridView.changeSelection(index - 1, 0, false, false);
                                showDetail();
                            }
                        }
                        else {
                            tblGridView.changeSelection(index, 0, false, false);
                            showDetail();
                        }
                    }
                }
                catch (Exception ex) {
                }
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luu == 1) {
                    if (coMa == true) {
                        if (txtMa.getText().isEmpty() || cboChuyenDe.getSelectedItem() == null || cboKhoaHoc.getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(pnlJFrame, "Không để trống ô mã sinh viên, chuyên đề hoặc khóa học");
                        }
                        else {
                            if (!txtMa.getText().matches(regexMa)) {
                                JOptionPane.showMessageDialog(pnlJFrame, "Mã chỉ chứa kí tự số");
                            }
                            else {
                                try {
                                    pst = con.prepareStatement("select * from nguoihoc where manh = ?");
                                    pst.setString(1, txtMa.getText());
                                    rs1 = pst.executeQuery();
                                    boolean timThay = rs1.next();
                                    
                                    if (timThay == false) {
                                        JOptionPane.showMessageDialog(pnlJFrame, "Không tồn tại mã học viên này");
                                    }
                                    else {
                                        boolean trung = false;
                                        pst = con.prepareStatement("select manh from hocvien where makh = ?");
                                        String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                                        String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                                        pst.setString(1, cboKhoaHocGetMaKH);
                                        rs1 = pst.executeQuery();
                                        
                                        while (rs1.next()) {
                                            if (rs1.getString(1).equals(txtMa.getText())) {
                                                JOptionPane.showMessageDialog(pnlJFrame, "Học viên đã được thêm vào khóa học rồi");
                                                trung = true;
                                                break;
                                            }
                                        }
                                        
                                        if (trung == false) {
                                            pst = con.prepareStatement("insert into HocVien(MaNH, MaKH) values (?, ?)");
                                            pst.setString(1, txtMa.getText());
                                            cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                                            cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                                            pst.setString(2, cboKhoaHocGetMaKH);
                                            pst.executeUpdate();
                                            fillToTable(false);
                                            for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                                tblGridView.changeSelection(i, 0, false, false);
                                                int index = tblGridView.getSelectedRow();
                                                Object row[] = (Object[]) list.get(index);
                                                if ((int) row[2] == Integer.parseInt(txtMa.getText())) {
                                                    showDetail();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } 
                                catch (Exception ex) {
                                }
                            }
                        }
                    }
                    else {
                        kiemTra();
                        if (sai == 0 && trong == 0) {
                            try {
                                pst = con.prepareCall("exec sp_insert_nguoihoc_hocvien ?, ?, ?, ?, ?, ?, ?");
                                pst.setString(1, txtHoTen.getText());
                                pst.setBoolean(2, cboGioiTinh.getSelectedItem().equals("Nam") ? true : false);
                                pst.setString(3, txtNgaySinh.getText());
                                pst.setString(4, txtDienThoai.getText());
                                pst.setString(5, txtEmail.getText());
                                pst.setString(6, txtGhiChu.getText());
                                String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                                String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                                pst.setString(7, cboKhoaHocGetMaKH);
                                pst.executeUpdate();
                                fillToTable(false);
                                tblGridView.changeSelection(tblGridView.getRowCount()-1, 0, false, false);
                            } 
                            catch (Exception ex) {
                            }
                        }
                    }
                    
                }
                else if (luu == 2) {
                    if (infoDiem == 0) {
                        int index = tblGridView.getSelectedRow();
                        if (index == -1) {
                            JOptionPane.showMessageDialog(pnlJFrame, "Bạn hãy chọn 1 học viên trong bảng để sửa");
                        }
                        else {
                            kiemTra();
                            if (sai == 0 && trong == 0) {
                                try {
                                    pst = con.prepareCall("exec sp_update_nguoihoc ?, ?, ?, ?, ?, ?, ?");
                                    pst.setString(1, txtHoTen.getText());
                                    pst.setBoolean(2, cboGioiTinh.getSelectedItem().equals("Nam") ? true : false);
                                    pst.setString(3, txtNgaySinh.getText());
                                    pst.setString(4, txtDienThoai.getText());
                                    pst.setString(5, txtEmail.getText());
                                    pst.setString(6, txtGhiChu.getText());
                                    pst.setString(7, txtMa.getText());
                                    pst.executeUpdate();
                                    fillToTable(false);
                                    tblGridView.changeSelection(index, 0, false, false);
                                } 
                                catch (Exception ex) {
                                }                            
                            }
                        }
                    }
                    else if (infoDiem == 1) {
                        boolean diemRegex = true;
                        boolean diemLimit = true;
                        tblGridView.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        
                        if (tblGridView.isEditing()) {
                            tblGridView.getCellEditor().stopCellEditing();
                        }
                        for (int i = 0; i < tblGridView.getRowCount(); i++) {
                            String point = String.valueOf(tblGridView.getValueAt(i, 6));
                            if (point.equalsIgnoreCase("null")) {
                                point = "";
                                tblGridView.setValueAt("", i, 6);
                            }
                            if (!point.matches(regexDiem)) {
                                diemRegex = false;
                                tblGridView.changeSelection(i, 0, true, false);
                            }
                        }
                        
                        if (diemRegex == true) {
                            for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                String diem = (String) tblGridView.getValueAt(i, 6);
                                if (!diem.isEmpty()) {
                                    try {
                                        double doubleDiem = Double.parseDouble(diem);
                                        if (doubleDiem < 0 || doubleDiem > 10) {
                                            diemLimit = false;
                                            tblGridView.changeSelection(i, 0, true, false);
                                        }
                                    } 
                                    catch (Exception ex) {
                                        diemRegex = false;
                                        tblGridView.changeSelection(i, 0, true, false);
                                    }
                                }
                            }
                        }

                        if (diemRegex == false || diemLimit == false) {
                            int index[] = tblGridView.getSelectedRows();
                            DefaultTableCellRenderer o = new DefaultTableCellRenderer() {
                                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                    setOpaque(true);
                                    setValue(value);
                                    setHorizontalAlignment(JLabel.CENTER);
                                    setFont(table.getFont());
                                    
                                    if (isSelected) {
                                        setBackground(table.getSelectionBackground());
                                        setForeground(table.getSelectionForeground());
                                        for (int i = 0; i < index.length; i++) {
                                            if (row == index[i]) {
                                                setBackground(Color.red);
                                                setForeground(Color.white);
                                            }
                                        }
                                    }
                                    else {
                                        setBackground(Color.white);
                                        setForeground(Color.black);
                                        for (int i = 0; i < index.length; i++) {
                                            if (row == index[i]) {
                                                setBackground(Color.red);
                                                setForeground(Color.white);
                                            }
                                        }
                                    }
                                    return this;
                                }
                            };
                            
                            tblGridView.getColumnModel().getColumn(6).setCellRenderer(o);
                            
                            tblGridView.selectAll();
                            for (int i = 0; i < index.length; i++) {
                                tblGridView.changeSelection(index[i], 0, true, false);
                            }
                            tblGridView.clearSelection();

                            if (diemRegex == false) {
                                JOptionPane.showMessageDialog(pnlJFrame, "Nhập sai định dạng điểm");
                            }
                            else if (diemLimit == false) {
                                JOptionPane.showMessageDialog(pnlJFrame, "Điểm phải nằm trong khoảng từ 0 đến 10");
                            }
                        }
                        else if (diemRegex == true && diemLimit == true) {
                            btnTim.setEnabled(true);
                            btnSave.setEnabled(false);
                            rdoCo.setEnabled(true);
                            rdoKo.setEnabled(true);
                            btnClear.setEnabled(true);
                            btnBoQua.setEnabled(true);
                            cboChuyenDe.setEnabled(true);
                            cboKhoaHoc.setEnabled(true);
                            txtTimMaNH.setEnabled(true);
                            cboTimMaKH.setEnabled(true);

                            
                            DefaultCellEditor cotDiem = new DefaultCellEditor(new JTextField());
                            cotDiem.setClickCountToStart(1);
                            tblGridView.getColumnModel().getColumn(6).setCellEditor(cotDiem);
                            
                            DefaultTableCellRenderer o = new DefaultTableCellRenderer();
                            o.setHorizontalAlignment(JLabel.CENTER);
                            tblGridView.getColumnModel().getColumn(6).setCellRenderer(o);
                            
                            for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                tblGridView.changeSelection(i, 0, false, false);
                            }
                            tblGridView.changeSelection(0, 0, false, false);
                            tblGridView.clearSelection();
                            
                            try {
                                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                    pst = con.prepareCall("exec sp_update_diem ?, ?, ?");
                                    pst.setString(1, (String) tblGridView.getValueAt(i, 6));
                                    pst.setInt(2, Integer.parseInt((String) tblGridView.getValueAt(i, 1)));
                                    
                                    String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                                    String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                                    pst.setInt(3, Integer.parseInt(cboKhoaHocGetMaKH));
                                    pst.executeUpdate();
                                }
                                fillToTable(true);
                            }
                            catch (Exception ex) {
                            }
                        }
                    }
                }
            }
        });
        
        btnBoQua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luu == 1) {
                    addItemKhoaHoc("");
                }
                if (luu == 2) {
                    try {
                        int index = tblGridView.getSelectedRow();
                        boolean xoa = true;
                        pst = con.prepareStatement("select makh from khoahoc where ngaykg <= getdate()");
                        rs4 = pst.executeQuery();
                        String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                        String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);

                        while (rs4.next()) {
                            if (rs4.getString(1).equalsIgnoreCase(cboKhoaHocGetMaKH)) {
                                btnDeleteRenderer.setEnabled(false);
                                btnDeleteEditor.setEnabled(false);
                                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                    tblGridView.changeSelection(i, 0, false, false);
                                }
                                tblGridView.changeSelection(0, 1, false, false);   
                                tblGridView.changeSelection(index, 1, false, false);     
                                xoa = false;
                            }
                        }

                        if (xoa == true && truongPhong == true) {
                            btnDeleteRenderer.setEnabled(true);
                            btnDeleteEditor.setEnabled(true);
                            for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                tblGridView.changeSelection(i, 0, false, false);
                            }
                            tblGridView.changeSelection(0, 1, false, false);     
                            tblGridView.changeSelection(index, 1, false, false);     
                        }
                    } 
                    catch (Exception ex) {
                    }
                    
                    if (suaDiem == true) {
                        if (tblGridView.isEditing() == true) {
                            tblGridView.getCellEditor().stopCellEditing();
                        }

                        addItemKhoaHoc("");
                        DefaultCellEditor lol = new DefaultCellEditor(new JTextField()) {
                            public boolean isCellEditable(EventObject anEvent) {
                                return false;
                            }
                        };
                        tblGridView.getColumnModel().getColumn(6).setCellEditor(lol);
                        tblGridView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    }
                    suaDiem = false;
                }

                luu = 0;
                txtMa.setEditable(false);
                txtHoTen.setEditable(false);
                cboGioiTinh.setEnabled(false);
                txtNgaySinh.setEditable(false);
                txtDienThoai.setEditable(false);
                txtEmail.setEditable(false);
                txtGhiChu.setEditable(false);
                
                btnInsert.setEnabled(true);
                btnUpdate.setEnabled(true);
                btnSave.setEnabled(false);
                btnBoQua.setEnabled(false);

                btnTim.setEnabled(true);
                txtTimMaNH.setEnabled(true);
                cboTimMaKH.setEnabled(true);

                cboChuyenDe.setEnabled(true);
                cboKhoaHoc.setEnabled(true);
                
                tblGridView.setEnabled(true);
                rdoCo.setEnabled(true);
                rdoKo.setEnabled(true);
            }
        });
        
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    txtEmail.setText(txtEmail.getText() + "@gmail.com");
                    txtEmail.select(txtEmail.getText().length()-10, txtEmail.getText().length());
                }
            }
        });
        
        tblGridView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
                    showDetail();
                }
                
                if (suaDiem == true) {
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        int index = tblGridView.getSelectedRow();
                        tblGridView.editCellAt(index, 6);
                        String cell = (String) tblGridView.getCellEditor(index, 6).getCellEditorValue();
                        JTextField txtCot6 = (JTextField) tblGridView.getCellEditor(index, 6).getTableCellEditorComponent(tblGridView, cell, true, index, 6);
                        txtCot6.setText(cell);
                        txtCot6.requestFocus();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        int index = tblGridView.getSelectedRow();
                        tblGridView.changeSelection(index+1, 6, false, false);
                        showDetail();
                        tblGridView.editCellAt(index+1, 6);

                        String cell = (String) tblGridView.getCellEditor(index+1, 6).getCellEditorValue();
                        JTextField txtCot6 = (JTextField) tblGridView.getCellEditor(index+1, 6).getTableCellEditorComponent(tblGridView, cell, true, index+1, 6);
                        txtCot6.requestFocus();
                    }                    
                }
            }
        });
        
        cboGioiTinh.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                cboGioiTinh.showPopup();
            }
        });
        
        cboTimMaKH.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        String cboTimMaKHGetSelectedItem = (String) cboTimMaKH.getSelectedItem();
                        String tenCD = cboTimMaKHGetSelectedItem.substring(0, cboTimMaKHGetSelectedItem.length()-13);
                        String ngayKG = cboTimMaKHGetSelectedItem.substring(cboTimMaKHGetSelectedItem.length()-11, cboTimMaKHGetSelectedItem.length()-1);
                        
                        pst = con.prepareCall("exec sp_find ?, ?");
                        pst.setString(1, tenCD);
                        pst.setString(2, ngayKG);
                        rs2 = pst.executeQuery();
                        rs2.next();
                        
                        tim = true;
                        bgr.clearSelection();
                        cboChuyenDe.setSelectedItem(tenCD);
                        cboKhoaHoc.setSelectedItem(rs2.getString(1) + " - (Khai giảng: " + ngayKG + ")");
                        
                        txtTimMaNH.setText(txtTim);
                        for (int i = 0; i < tblGridView.getRowCount(); i++) {
                            tblGridView.changeSelection(i, 0, false, false);
                            int index = tblGridView.getSelectedRow();
                            Object row[] = (Object[]) list.get(index);
                            if ((int) row[2] == Integer.parseInt(txtTimMaNH.getText())) {
                                showDetail();
                                break;
                            }
                        }
                        tim = false;
                    } 
                    catch (Exception ex) {
                    }
                }
            }
        });
        
        tblGridView.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (tblGridView.isEditing()) {
                    if (suaDiem == true) {
                        btnTim.setEnabled(false);
                        btnSave.setEnabled(true);
                        rdoCo.setEnabled(false);
                        rdoKo.setEnabled(false);
                        btnClear.setEnabled(false);
                        btnBoQua.setEnabled(false);
                        cboChuyenDe.setEnabled(false);
                        cboKhoaHoc.setEnabled(false);
                        txtTimMaNH.setEnabled(false);
                        cboTimMaKH.setEnabled(false);
                    }
                }
            }
        });
    }
    
    public void showDetail() {
        try {
            int index = tblGridView.getSelectedRow();
            Object row[] = (Object[]) list.get(index);

            cboChuyenDe.setSelectedItem(row[0]);
            cboKhoaHoc.setSelectedItem(row[1] + " - (Khai giảng: " + row[11] + ")");
            txtMa.setText(String.valueOf(row[2]));
            txtHoTen.setText((String) row[3]);
            cboGioiTinh.setSelectedItem((boolean) row[4] ? "Nam" : "Nữ");
            txtNgaySinh.setText((String) row[5]);
            txtDienThoai.setText((String) row[6]);
            txtEmail.setText((String) row[7]);
            txtGhiChu.setText((String) row[8]);            
        } 
        catch (Exception ex) {
        }
    }
    
    public void kiemTra() {
        sai = 0;
        trong = 0;
        JTextComponent txt[] = {txtHoTen, txtNgaySinh, txtDienThoai, txtEmail};
        String regex[] = {regexTen, regexNgaySinh, regexSDT, regexEmail};
        String a[] = {"họ tên", "ngày sinh", "điện thoại", "email"};

        for (int i = 0; i < txt.length; i++) {
            if (txt[i].getText().isEmpty()) {
                trong++;
                break;
            }
        }
        
        if (luu == 1) {
            if (cboChuyenDe.getSelectedItem() == null || cboKhoaHoc.getSelectedItem() == null) {
                trong++;
            }
        }
        
        if (trong > 0) {
            if (luu == 1) {
                JOptionPane.showMessageDialog(pnlJFrame, "Bạn hãy điền đủ thông tin vào các ô chuyên đề, khóa học, họ tên, ngày sinh, điện thoại, email");
            }
            else if (luu == 2) {
                JOptionPane.showMessageDialog(pnlJFrame, "Bạn hãy điền đủ thông tin vào các ô họ tên, ngày sinh, điện thoại, email");
            }
        }

        if (trong == 0) {
            for (int i = 0; i < txt.length; i++) {
                if (!txt[i].getText().matches(regex[i])) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Nhập sai định dạng " + a[i]);
                }
            }

            if (sai == 0) {
                JTextComponent txt2[] = {txtHoTen, txtEmail, txtGhiChu};
                int length[] = {50, 50, 255};
                String a2[] = {"họ tên", "email", "ghi chú"};

                for (int i = 0; i < txt2.length; i++) {
                    if (txt2[i].getText().length() > length[i]) {
                        sai++;
                        JOptionPane.showMessageDialog(pnlJFrame, a2[i] + " chỉ được phép chứa " + length[i] + " ký tự");
                    }
                }

                if (txtDienThoai.getText().length() > 10 || txtDienThoai.getText().length() < 9) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Điện thoại phải là 9 hoặc 10 số");
                }
            }

            if (sai == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);

                try {
                    sdf.parse(txtNgaySinh.getText());
                } 
                catch (Exception ex) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Ngày sinh không tồn tại");
                }
            }

            if (sai == 0) {
                try {
                    String sql = "select * from dbo.fn_select_hocvien()";
                    if (luu == 2) {
                        sql = "select * from dbo.fn_select_hocvien() where manh != "+txtMa.getText();
                    }
                    pst = con.prepareCall(sql);
                    rs1 = pst.executeQuery();
                    while (rs1.next()) {
                        if (rs1.getString(7).equalsIgnoreCase(txtDienThoai.getText())) {
                            sai++;
                            JOptionPane.showMessageDialog(pnlJFrame, "Trùng số điện thoại với học viên khác");
                        }

                        if (rs1.getString(8).equalsIgnoreCase(txtEmail.getText())) {
                            sai++;
                            JOptionPane.showMessageDialog(pnlJFrame, "Trùng email với học viên khác");
                        }
                    }

                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date ngaySinh = format.parse(txtNgaySinh.getText());
                    long ms = 31536000000L;
                    if ((new Date().getTime() - ngaySinh.getTime()) / ms < 18) {
                        sai++;
                        JOptionPane.showMessageDialog(pnlJFrame, "Học viên phải lớn hơn 18 tuổi");
                    }
                } 
                catch (Exception ex) {
                }
            }
            
            if (sai == 0) {
                try {
                    String sql = "select * from dbo.fn_select_hocvien()";
                    if (luu == 2) {
                        sql = "select * from dbo.fn_select_hocvien() where manh != "+txtMa.getText();
                    }
                    pst = con.prepareCall(sql);
                    rs1 = pst.executeQuery();

                    while (rs1.next()) {
                        String gioiTinh = rs1.getBoolean(5) ? "Nam" : "Nữ";
                        if (rs1.getString(4).equalsIgnoreCase(txtHoTen.getText()) && gioiTinh.equalsIgnoreCase((String) cboGioiTinh.getSelectedItem()) && rs1.getString(6).equalsIgnoreCase(txtNgaySinh.getText())) {
                            int chon = JOptionPane.showConfirmDialog(pnlJFrame, "Đã có học viên có cùng tên, giới tính, ngày sinh như bạn vừa nhập. Bạn có muốn tiếp tục không", "Trùng dữ liệu", JOptionPane.YES_NO_OPTION, 3);
                            if (chon == JOptionPane.NO_OPTION) {
                                sai++;
                                break;
                            }
                            else if (chon == JOptionPane.YES_OPTION) {
                                break;
                            }
                            else {
                                sai++;
                                break;
                            }
                        }
                    }
                } 
                catch (Exception ex) {
                }
            }
        }
    }
    
    public void fillToTable(boolean show) {
        try {
            int stt = 1;
            String sql;

            if (cboChuyenDe.getSelectedItem() == null) {
                sql = "select * from dbo.fn_select_hocvien() where tencd is null and makh is null";
            }
            else {
                sql = "select * from dbo.fn_select_hocvien() where tencd = ? and makh";
                
                if (cboKhoaHoc.getSelectedItem() == null) {
                    sql = sql + " is null";
                }
                else {
                    sql = sql + " = ?";
                }
            }
            
            if (rdoCo.isSelected()) {
                sql = sql + " and diem is not null";
            }
            else if (rdoKo.isSelected()) {
                sql = sql + " and diem is null";
            }
        
            list.clear();
            pst = con.prepareCall(sql);
            if (cboKhoaHoc.getSelectedItem() != null && cboChuyenDe.getSelectedItem() != null) {
                pst.setString(1, (String) cboChuyenDe.getSelectedItem());
                String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                pst.setString(2, cboKhoaHocGetMaKH);
            }
            else if (cboKhoaHoc.getSelectedItem() == null && cboChuyenDe.getSelectedItem() != null) {
                pst.setString(1, (String) cboChuyenDe.getSelectedItem());
            }
            rs4 = pst.executeQuery();
            
            if (tblGridView.isEditing() == true) {
                tblGridView.getCellEditor().stopCellEditing();
            }
            model.setRowCount(0);
            while (rs4.next()) {
                Object row[] = {rs4.getString(1), rs4.getInt(2), rs4.getInt(3), rs4.getString(4), rs4.getBoolean(5), rs4.getString(6), rs4.getString(7), rs4.getString(8), rs4.getString(9), rs4.getString(10), rs4.getDouble(11), rs4.getString(12)};
                list.add(row);
                Object rowTbl[] = {stt, rs4.getString(3), rs4.getString(4), rs4.getBoolean(5) ? rs4.getString(6) : "", rs4.getBoolean(5) ? "" :rs4.getString(6), rs4.getString(10), rs4.getString(11)};
                model.addRow(rowTbl);
                stt++;
            }
            
            if (show == true) {
                if (tblGridView.getRowCount() == 0) {
                    cboGioiTinh.setSelectedIndex(0);
                    txtMa.setText("");
                    txtHoTen.setText("");
                    txtNgaySinh.setText("");
                    txtDienThoai.setText("");
                    txtEmail.setText("");
                    txtGhiChu.setText("");
                }
                else {
                    tblGridView.changeSelection(0, 0, false, false);
                    showDetail();
                }
            }
            
            if (suaDiem == true) {
                if (cboKhoaHoc.getSelectedItem() == null) {
                    DefaultCellEditor dce = new DefaultCellEditor(new JTextField()) {
                        public boolean isCellEditable(EventObject anEvent) {
                            return false;
                        }
                    };

                    tblGridView.getColumnModel().getColumn(6).setCellEditor(dce);                    
                }
                else {
                    DefaultCellEditor cotDiem = new DefaultCellEditor(new JTextField());
                    cotDiem.setClickCountToStart(1);
                    tblGridView.getColumnModel().getColumn(6).setCellEditor(cotDiem);                    
                }
            }
            
            if (truongPhong == false) {
                btnDeleteRenderer.setEnabled(false);
                btnDeleteEditor.setEnabled(false);
                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                    tblGridView.changeSelection(i, 0, false, false);
                }
                tblGridView.changeSelection(0, 1, false, false);                  
            }
        }
        catch (Exception e) {
        }
    }
    
    public void lamMoi() {
        bgr.clearSelection();
        fillToTable(false);
        cboGioiTinh.setSelectedIndex(0);
        cboTimMaKH.removeAllItems();
        txtMa.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        txtTimMaNH.setText("");
        tblGridView.clearSelection();
    }
    
    public void addItemKhoaHoc(String where) {
        try {
            cboKhoaHoc.removeAllItems();
            String sql = "select makh, ngaykgconverted from dbo.fn_select_khoahoc() where tencd = ?";
            if (where != "") {
                sql = "select makh, ngaykgconverted from dbo.fn_select_khoahoc() where tencd = ? and " + where;
            }
            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, (String) cboChuyenDe.getSelectedItem());
            rs1 = pst.executeQuery();
            
            boolean a = rs1.next();
            if (a == true) {
                rs1.beforeFirst();
                while (rs1.next()) {
                    cboKhoaHoc.addItem(rs1.getString(1) + " - (Khai giảng: " + rs1.getString(2) + ")");
                }
            }
            else {
                if (tim == false) {
                    lamMoi();
                }
            }
        } 
        catch (Exception ex) {
        }        
    }
    
    public void eventCboKhoaHoc() {
        if (luu == 1 || luu == 2) {
            fillToTable(false);
        }
        else {
            fillToTable(true);

            try {
                boolean xoa = true;
                pst = con.prepareStatement("select makh from khoahoc where ngaykg <= getdate()");
                rs4 = pst.executeQuery();
                String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);

                while (rs4.next()) {
                    if (rs4.getString(1).equalsIgnoreCase(cboKhoaHocGetMaKH)) {
                        btnDeleteRenderer.setEnabled(false);
                        btnDeleteEditor.setEnabled(false);
                        for (int i = 0; i < tblGridView.getRowCount(); i++) {
                            tblGridView.changeSelection(i, 0, false, false);
                        }
                        tblGridView.changeSelection(0, 1, false, false);   
                        xoa = false;
                    }
                }

                if (xoa == true && truongPhong == true) {
                    btnDeleteRenderer.setEnabled(true);
                    btnDeleteEditor.setEnabled(true);
                    for (int i = 0; i < tblGridView.getRowCount(); i++) {
                        tblGridView.changeSelection(i, 0, false, false);
                    }
                    tblGridView.changeSelection(0, 1, false, false);     
                }
            }
            catch (Exception ex) {
            }
        }        
    }
}
