package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;

public class QuanLyKhoaHoc extends Khung {
    JLabel lblTieuDe, lblChuyenDe, lblNgayKG, lblGhiChu, lblHan;
    JComboBox cboChuyenDe;
    JTextField txtNgayKG, txtTim, txtHan;
    JTextArea txtGhiChu;
    JButton btnInsert, btnUpdate, btnDelete, btnClear, btnTim, btnSave, btnBoQua;
    JPanel pnlBtn, pnlTim, pnlInfo, pnlJFrame, pnlCenter, pnlRdo, pnlTbl, pnlNorth;
    JScrollPane scrGhiChu, scrTbl;
    GridBagConstraints gbc;
    String tenCot[] = {"STT", "Mã KH", "Ngày khai giảng", "Hạn đăng ký"};
    JTable tblGridView;
    DefaultTableModel model;
    ButtonGroup bgrTrangThai;
    JRadioButton rdoCo, rdoKo;
    int luu = 0;
    int sai, trong;
    List<Object> list;
    String regexDate = "([0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}){0,1}";

    public QuanLyKhoaHoc() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();
        list = new ArrayList<>();
        
        pnlJFrame = new JPanel(new BorderLayout(0, 10));
        pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        pnlTim = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        pnlInfo = new JPanel(new GridBagLayout());
        pnlCenter = new JPanel(new GridBagLayout());
        pnlRdo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlTbl = new JPanel(new BorderLayout());
        pnlNorth = new JPanel(new BorderLayout(0, 10));
        
        btnInsert = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnClear = new JButton("Làm mới");
        btnTim = new JButton("Tìm theo mã");
        btnSave = new JButton("Lưu");
        btnBoQua = new JButton("Bỏ qua");
        
        lblTieuDe = new JLabel("Quản lý khóa học");
        lblChuyenDe = new JLabel("Chuyên đề");
        lblNgayKG = new JLabel("Ngày khai giảng");
        lblGhiChu = new JLabel("Ghi chú");
        lblHan = new JLabel("Hạn đăng ký");
        
        cboChuyenDe = new JComboBox();
        txtNgayKG = new JTextField(25);
        txtGhiChu = new JTextArea();
        scrGhiChu = new JScrollPane(txtGhiChu);
        txtTim = new JTextField(20);
        txtHan = new JTextField(25);
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };             
        };
        scrTbl = new JScrollPane(tblGridView);
        
        bgrTrangThai = new ButtonGroup();
        rdoKo = new JRadioButton("Chưa hết hạn đăng ký");
        rdoCo = new JRadioButton("Đã hết hạn đăng ký");
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("tahoma", Font.BOLD, 14));
        lblTieuDe.setForeground(Color.white);
        lblTieuDe.setOpaque(true);
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);
        lblTieuDe.setBackground(Color.darkGray);
        lblTieuDe.setPreferredSize(new Dimension(580, 30));
        
        scrGhiChu.setPreferredSize(new Dimension(280, 50));
        scrGhiChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        scrTbl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        tblGridView.setFillsViewportHeight(true);
        
        cboChuyenDe.setPreferredSize(new Dimension(280, 25));
        
        btnSave.setEnabled(false);
        btnBoQua.setEnabled(false);
        txtNgayKG.setEditable(false);
        txtHan.setEditable(false);
        txtGhiChu.setEditable(false);
        if (truongPhong == false) {
            btnDelete.setEnabled(false);
        }

        tblGridView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tblGridView.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(3).setCellRenderer(dtcr);

        
//        Set <KeyStroke> set = new HashSet();
//        set.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
//        JComponent txt[] = {cboChuyenDe,txtNgayKG,txtHan,txtGhiChu,btnInsert,btnUpdate,btnDelete,btnClear,
//            txtTim,btnTim,rdoKo,rdoCo};
//        for(int i=0;i<txt.length;i++){
//            txt[i].setFocusTraversalKeys(0, set);
//        }
    }

    @Override
    public void add() {
        pnlBtn.add(btnInsert);
        pnlBtn.add(btnUpdate);
        pnlBtn.add(btnDelete);
        pnlBtn.add(btnClear);
        pnlBtn.add(btnSave);
        pnlBtn.add(btnBoQua);
        
        pnlTim.add(txtTim);
        pnlTim.add(btnTim);

        bgrTrangThai.add(rdoKo);
        bgrTrangThai.add(rdoCo);
        pnlRdo.add(rdoKo);
        pnlRdo.add(rdoCo);
        
        JComponent a[] = {lblChuyenDe, lblNgayKG, lblHan, lblGhiChu, cboChuyenDe, txtNgayKG, txtHan, scrGhiChu};
        addChung(pnlInfo, gbc, a, 4, 2, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST);
        
        JComponent b[] = {pnlInfo, pnlBtn, pnlTim};
        addChung(pnlCenter, gbc, b, 3, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        pnlTbl.add(pnlRdo, BorderLayout.NORTH);
        pnlTbl.add(scrTbl, BorderLayout.CENTER);

        pnlNorth.add(lblTieuDe, BorderLayout.NORTH);
        pnlNorth.add(pnlCenter, BorderLayout.CENTER);
        
        pnlJFrame.add(pnlNorth, BorderLayout.NORTH);
        pnlJFrame.add(pnlTbl, BorderLayout.CENTER);
    }

    @Override
    public void event() {
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
        
        cboChuyenDe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (luu == 1) {
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
                    fillToTable(true);
                    
                    if (luu == 2) {
                        txtNgayKG();
                    }
                }
            }
        });

        rdoCo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable(true);
                    
                    if (luu == 2) {
                        txtNgayKG();
                    }
                }
            }
        });
        
        tblGridView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                showDetail();
                
                if (luu == 2) {
                    txtNgayKG();
                }
            }
        });
        
        tblGridView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
                    showDetail();
                    
                    if (luu == 2) {
                        txtNgayKG();
                    }
                }
            }
        });
        
        txtTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnTim.doClick();
                }
            }
        });
        
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTim.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(pnlJFrame, "Không để trống ô tìm kiếm");
                }
                else {
                    if (!txtTim.getText().matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(pnlJFrame, "Mã không đúng định dạng");
                    }
                    else {
                        try {
                            pst = con.prepareStatement("select tencd from dbo.fn_select_khoahoc() where makh = ?");
                            pst.setString(1, txtTim.getText());
                            rs2 = pst.executeQuery();
                            boolean timThay = rs2.next();
                            if (timThay == false) {
                                JOptionPane.showMessageDialog(pnlJFrame, "Không tìm thấy");
                            }
                            else {
                                bgrTrangThai.clearSelection();
                                cboChuyenDe.setEditable(true);
                                cboChuyenDe.setSelectedItem("");
                                cboChuyenDe.setEditable(false);
                                cboChuyenDe.setSelectedItem(rs2.getString(1));
                                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                                    tblGridView.changeSelection(i, 0, false, false);
                                    int index = tblGridView.getSelectedRow();
                                    Object row[] = (Object[]) list.get(index);
                                    if (row[0].equals(txtTim.getText())) {
                                        showDetail();
                                        txtNgayKG();
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
                luu = 1;
                lamMoi();
                txtNgayKG.setEditable(true);
                txtHan.setEditable(true);
                txtGhiChu.setEditable(true);
                btnInsert.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
                btnSave.setEnabled(true);
                btnTim.setEnabled(false);
                btnBoQua.setEnabled(true);
                txtNgayKG.requestFocus();
                tblGridView.setEnabled(false);
                rdoCo.setEnabled(false);
                rdoKo.setEnabled(false);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luu = 2;
                txtNgayKG.setEditable(true);
                txtHan.setEditable(true);
                txtGhiChu.setEditable(true);
                btnInsert.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
                btnSave.setEnabled(true);
                cboChuyenDe.setEnabled(false);
                btnBoQua.setEnabled(true);
                
                if (tblGridView.getSelectedRow() != -1) {
                    txtNgayKG();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luu = 3;
                btnInsert.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
                btnSave.setEnabled(true);
                btnBoQua.setEnabled(true);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luu == 1) {
                    try {
                        kiemTra();
                        
                        if (sai == 0 && trong == 0) {
                            pst = con.prepareStatement("select macd from chuyende where tencd = ?");
                            pst.setString(1, (String) cboChuyenDe.getSelectedItem());
                            rs1 = pst.executeQuery();
                            rs1.next();
                            
                            pst = con.prepareCall("exec sp_insert_khoahoc ?, ?, ?, ?");
                            pst.setInt(1, rs1.getInt(1));
                            pst.setString(2, txtNgayKG.getText());
                            pst.setString(3, txtHan.getText());
                            pst.setString(4, txtGhiChu.getText());
                            pst.executeUpdate();
                            fillToTable(false);
                            tblGridView.changeSelection(tblGridView.getRowCount()-1, 0, false, false);
                        }
                    } 
                    catch (Exception ex) {
                    }
                }
                else if (luu == 2) {
                    try {
                        int index = tblGridView.getSelectedRow();
                        if (index == -1) {
                            JOptionPane.showMessageDialog(pnlJFrame, "Hãy chọn 1 dòng trong bảng trước");
                        }
                        else {
                            kiemTra();
                            
                            if (sai == 0 && trong == 0) {
                                Object row[] = (Object[]) list.get(index);
                                pst = con.prepareCall("exec sp_update_khoahoc ?, ?, ?, ?");
                                pst.setInt(1, Integer.parseInt((String) row[0]));
                                pst.setString(2, txtNgayKG.getText());
                                pst.setString(3, txtHan.getText());
                                pst.setString(4, txtGhiChu.getText());
                                pst.executeUpdate();
                                fillToTable(false);
                                tblGridView.changeSelection(index, 0, false, false);
                            }
                        }
                    } 
                    catch (Exception ex) {
                    }
                }
                else if (luu == 3) {
                    try {
                        int index = tblGridView.getSelectedRow();

                        if (index == -1) {
                            JOptionPane.showMessageDialog(pnlJFrame, "Hãy chọn 1 dòng trong bảng trước");
                        }
                        else {
                            Object row[] = (Object[]) list.get(index);
                            pst = con.prepareStatement("delete from khoahoc where makh = ?");
                            pst.setInt(1, Integer.parseInt((String) row[0]));
                            pst.executeUpdate();
                            fillToTable(false);
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
                        JOptionPane.showMessageDialog(pnlJFrame, "Không thể xóa vì khóa này đang có học viên học");
                    }
                }
            }
        });

        btnBoQua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luu = 0;
                btnInsert.setEnabled(true);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
                btnTim.setEnabled(true);
                cboChuyenDe.setEnabled(true);
                btnSave.setEnabled(false);
                txtNgayKG.setEditable(false);
                txtHan.setEditable(false);
                txtGhiChu.setEditable(false);
                btnBoQua.setEnabled(false);
                tblGridView.setEnabled(true);
                rdoCo.setEnabled(true);
                rdoKo.setEnabled(true);
            }
        });
    }
    
    public void fillToTable(boolean show) {
        try {
            int stt = 1;
            list.clear();
            String sql = "select * from dbo.fn_select_khoahoc() where tencd = ?";
            if (rdoKo.isSelected()) {
                sql = "select * from dbo.fn_select_khoahoc() where tencd = ? and getdate() <= handk";
            }
            else if (rdoCo.isSelected()) {
                sql = "select * from dbo.fn_select_khoahoc() where tencd = ? and getdate() > handk";
            }
            
            pst = con.prepareCall(sql);
            pst.setString(1, (String) cboChuyenDe.getSelectedItem());
            rs1 = pst.executeQuery();
            
            model.setRowCount(0);
            while (rs1.next()) {
                Object rowLst[] = {rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7)};
                list.add(rowLst);
                Object rowTbl[] = {stt, rs1.getString(1), rs1.getString(4), rs1.getString(5)};
                model.addRow(rowTbl);
                stt++;
            }
            
            if (show == true) {
                if (tblGridView.getRowCount() == 0) {
                    txtNgayKG.setText("");
                    txtHan.setText("");
                    txtGhiChu.setText("");
                }
                else {
                    tblGridView.changeSelection(0, 0, false, false);
                    showDetail();
                }
            }
        }
        catch (Exception ex) {
        }
    }
    
    public void showDetail() {
        try {
            int index = tblGridView.getSelectedRow();
            Object row[] = (Object[]) list.get(index);
            txtNgayKG.setText((String) row[3]);
            txtHan.setText((String) row[4]);
            txtGhiChu.setText((String) row[6]);
        } 
        catch (Exception e) {
        }
    }
    
    public void lamMoi() {
        bgrTrangThai.clearSelection();
        fillToTable(false);
        txtNgayKG.setText("");
        txtHan.setText("");
        txtGhiChu.setText("");
        txtTim.setText("");
    }
    
    public void kiemTra() {
        sai = 0;
        trong = 0;
        JTextComponent txt[] = {txtNgayKG, txtHan};
        String regex[] = {regexDate, regexDate};
        String a[] = {"ngày khai giảng", "hạn đăng ký"};
        
        for (int i = 0; i < txt.length; i++) {
            if (txt[i].getText().isEmpty()) {
                trong++;
                JOptionPane.showMessageDialog(pnlJFrame, "Bạn hãy nhập đủ các ô ngày khai giảng và hạn đăng ký");
                break;
            }
        }
        
        if (trong == 0) {
            for (int i = 0; i < txt.length; i++) {
                if (!txt[i].getText().matches(regex[i])) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Nhập sai " + a[i] + " ('dd-MM-yyyy')");
                }
            }
            
            if (sai == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);

                try {
                    sdf.parse(txtNgayKG.getText());
                } 
                catch (Exception ex) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Ngày khai giảng không đúng định dạng");
                }
                
                try {
                    sdf.parse(txtHan.getText());
                } 
                catch (Exception ex) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Hạn đăng ký không đúng định dạng");
                }
                
                if (txtGhiChu.getText().length() > 255) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Ghi chú chỉ có tối đa 255 kí tự");
                }
            }
        }
        
        if (sai == 0 && trong == 0) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date handktxt = format.parse(txtHan.getText());
                Date ngaykgtxt = format.parse(txtNgayKG.getText());
                Date now = new Date();
                boolean x = true;
            
                if (luu == 2) {
                    int index = tblGridView.getSelectedRow();
                    Object row[] = (Object[]) list.get(index);
                    Date handkSelect = format.parse((String) row[4]);
                    Date ngaykgSelect = format.parse((String) row[3]);
                    
                    if (handkSelect.compareTo(handktxt) == 0) {
                        x = false;
                        if (ngaykgSelect.compareTo(ngaykgtxt) == 0) {
                        }
                        else if (ngaykgtxt.compareTo(now) <= 0) {
                            sai++;
                            JOptionPane.showMessageDialog(pnlJFrame, "Ngày khai giảng phải lớn hơn ngày hôm nay");
                        }
                    }
                }
                
                if (x == true) {
                    if (handktxt.compareTo(now) <= 0 || ngaykgtxt.compareTo(now) <= 0) {
                        sai++;
                        JOptionPane.showMessageDialog(pnlJFrame, "Hạn đăng ký và ngày khai giảng phải lớn hơn ngày hôm nay");
                    }
                }

                if (handktxt.compareTo(ngaykgtxt) >= 0 && sai == 0) {
                    sai++;
                    JOptionPane.showMessageDialog(pnlJFrame, "Ngày khai giảng phải lớn hơn hạn đăng ký");
                }
                
                if (sai == 0) {
                    pst = con.prepareCall("select ngaykg from dbo.fn_select_khoahoc() where tencd = ?");
                    pst.setString(1, (String) cboChuyenDe.getSelectedItem());
                    rs1 = pst.executeQuery();

                    if (luu == 1) {
                        while (rs1.next()) {
                            Date m = format.parse(rs1.getString(1));
                            if (ngaykgtxt.compareTo(m) == 0) {
                                sai++;
                                JOptionPane.showMessageDialog(pnlJFrame, "Một chuyên đề không có 2 khóa học khai giảng cùng ngày");
                                break;
                            }
                        }
                    }
                    else if (luu == 2) {
                        int index = tblGridView.getSelectedRow();
                        Object row[] = (Object[]) list.get(index);
                        Date ngaykgSelect = format.parse((String) row[3]);
                        
                        if (ngaykgtxt.compareTo(ngaykgSelect) != 0) {
                            while (rs1.next()) {
                                Date m = format.parse(rs1.getString(1));
                                if (ngaykgtxt.compareTo(m) == 0) {
                                    sai++;
                                    JOptionPane.showMessageDialog(pnlJFrame, "Một chuyên đề không có 2 khóa học khai giảng cùng ngày");
                                    break;
                                }
                            }
                        }
                    }
                }
            } 
            catch (Exception ex) {
            }      
        }
    }
    
    public void txtNgayKG() {
        try {
            int index = tblGridView.getSelectedRow();
            Object row[] = (Object[]) list.get(index);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date ngaykg = format.parse((String) row[3]);
            if (ngaykg.compareTo(new Date()) <= 0) {
                txtNgayKG.setEditable(false);
                txtHan.setEditable(false);
            }
            else {
                txtNgayKG.setEditable(true);
                txtHan.setEditable(true);
            }
        } 
        catch (Exception ex) {
        }        
    }
}
