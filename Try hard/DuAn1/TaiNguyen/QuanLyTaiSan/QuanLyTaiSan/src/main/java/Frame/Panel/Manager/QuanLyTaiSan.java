package Frame.Panel.Manager;

import Contant.CoreConstant;
import Entity.*;
import Utils.DialogUtils;
import Utils.MoneyUtil;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyTaiSan extends JPanel {
    public QuanLyTaiSan() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
        statusDefault();

        loadDataToNhaSanXuat();
        getNSXSelected(indexNhaSanXuat);
        loadDataToTblTaiSan(nsxSelected);
        fillToForm(indexTaiSan);
    }

    public void initComponents() {

        //<editor-fold desc="Init">
        lblPhanLoai = new JLabel("Phân Loại");
        lblTenTaiSan = new JLabel("Tên tài sản");
        lblNhaSanXuat = new JLabel("Hãng sản xuất");
        lblMaTaiSan = new JLabel("Mã tài sản");
        lblMoTa = new JLabel("Mô tả");
        lblNguyenGia = new JLabel("Nguyên giá");
        lblTiLeKhauHao = new JLabel("Tỷ lệ khấu hao");
        lblThoiGianKhauHao = new JLabel("Thời gian khấu hao");
        lblTimKiem =  new JLabel("Tìm kiếm");
        lblNamBDSuDung = new JLabel("Năm bắt đầu sử dụng");
        lblTimKiemNhaSanXuat = new JLabel("Tìm kiếm");

        txtTenTaiSan = new JTextField();
        txtMaTaiSan = new JTextField();
        txtNguyenGia = new JTextField();
        txtTiLeKhauHao = new JTextField();
        txtThoiGianKhauHao = new JTextField();
        txtTimKiem = new JTextField();
        txtTimKiemNhaSanXuat = new JTextField();

        cbxNhaSanXuat = new JComboBox();
        cbxNamBDSuDung = new JComboBox();
        cbxNamBDSuDung.setAlignmentX(CENTER_ALIGNMENT);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int end = year - 10;

        cbxNamBDSuDung.addItem("--");
        for (int i = year; i >= end ; i--) {
            cbxNamBDSuDung.addItem(i);
        }

        rdo1 = new JRadioButton("Nhà cửa, vật kiến trúc");
        rdo2 = new JRadioButton("Phương tiện vận tải");
        rdo3 = new JRadioButton("Công cụ dụng cụ");
        btgPhanLoai = new ButtonGroup(); btgPhanLoai.add(rdo1); btgPhanLoai.add(rdo2); btgPhanLoai.add(rdo3);
        rdo1.setSelected(true);

        txaMoTa =  new JTextArea();
        txaMoTa.setLineWrap(true);
        scMoTa = new JScrollPane(txaMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="TABLE NHÀ SẢN XUẤT">
        modelTblNhaSanXuat = new DefaultTableModel();
        modelTblNhaSanXuat.setColumnIdentifiers(new Object[] {"STT", "Nhà Sản Xuất"});
        tblNhaSanXuat = new JTable(modelTblNhaSanXuat){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", 0, 13));
                if (column == 0) {
                    c.setHorizontalAlignment(JLabel.CENTER);
                }else {
                    c.setHorizontalAlignment(JLabel.LEFT);
                }
                return c;
            }
        };

        /* Căn giữa header table */
        JTableHeader tblHeaderPhongBan = tblNhaSanXuat.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        /* Chỉnh sửa kích thước từng cột */
        tblHeaderPhongBan.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderPhongBan.getColumnModel().getColumn(1).setPreferredWidth(280);

        /* Chỉnh sửa độ cao dòng */
        tblNhaSanXuat.setRowHeight(25);

        /* Chỉnh sửa màu nền khi chọn */
        tblNhaSanXuat.setSelectionBackground(Color.decode("#3a4d8f"));

        tblNhaSanXuat.setIntercellSpacing(new Dimension(0, 0));
        tblNhaSanXuat.setShowGrid(false);

        tblNhaSanXuat.setRowSorter(new TableRowSorter(modelTblNhaSanXuat));
        tblNhaSanXuat.setAutoCreateRowSorter(true);
        scTblNhaSanXuat = new JScrollPane(tblNhaSanXuat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="TABLE TÀI SẢN">
        modelTblTaiSan = new DefaultTableModel();
        modelTblTaiSan.setColumnIdentifiers(new Object[] {"STT", "Mã tài sản","Tên tài sản", "Nguyên giá", "Thời gian khấu hao"});
        tblTaiSan = new JTable(modelTblTaiSan){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };

        JTableHeader tblHeaderTS = tblTaiSan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblHeaderTS.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderTS.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(4).setPreferredWidth(100);

        tblTaiSan.setRowHeight(20);
        tblTaiSan.setSelectionBackground(Color.decode("#3a4d8f"));

        //<editor-fold desc="Hover table">
        //        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (!isSelected) {
//                    c.setBackground(row % 2 == 0 ? Color.decode("#e7e7e7") : Color.WHITE);
//                    c.setFont(new Font("Segoe UI", 0, 14));
//                }
//                return c;
//            }
//        };
//        renderer2.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
//        for (int i = 0; i < tblTaiSan.getColumnCount(); i++) {
//            tblTaiSan.setDefaultRenderer(tblTaiSan.getColumnClass(i), renderer2);
//        }
        //</editor-fold>

        tblTaiSan.setRowSorter(new TableRowSorter(modelTblTaiSan));
        tblTaiSan.setAutoCreateRowSorter(true);

        scTblTaiSan = new JScrollPane(tblTaiSan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="CUSTOM BUTTON">
        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa", new ImageIcon("src/Image/icon-delete"));
        btnEdit = new JButton("Sửa");
        btnExport = new JButton("Kết xuất");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");
        btnTKTS = new JButton("Tìm tiếp");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTKTS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        //<editor-fold desc="Set Font Segoe UI">
        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnExport.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnTKTS.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        lblPhanLoai.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTenTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblMaTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNguyenGia.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblMoTa.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTiLeKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblThoiGianKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTimKiemNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        rdo1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdo2.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdo3.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtTenTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtMaTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtNguyenGia.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txaMoTa.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtTiLeKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtThoiGianKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtTimKiemNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        cbxNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        cbxNamBDSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        txtTimKiem.setPreferredSize(new Dimension(250, 25));
        txtTimKiemNhaSanXuat.setPreferredSize(new Dimension(250, 25));
        btnTKTS.setVisible(false);
    }

    public void addControls() {
        // LEFT
        JPanel pnLeftTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnLeftTop.add(lblTimKiemNhaSanXuat);
        pnLeftTop.add(txtTimKiemNhaSanXuat);
        pnLeftTop.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)));

        JPanel pnLeft = new JPanel(new BorderLayout(0, 5));
        pnLeft.add(pnLeftTop, BorderLayout.NORTH);
        pnLeft.add(scTblNhaSanXuat, BorderLayout.CENTER);

        JPanel pnPhanLoai = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnPhanLoai.add(rdo1); pnPhanLoai.add(rdo2); pnPhanLoai.add(rdo3);

        JPanel pnRightTop = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        pnRightTop.add(lblPhanLoai, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnRightTop.add(lblTenTaiSan, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnRightTop.add(lblNhaSanXuat, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnRightTop.add(lblMoTa, gbc);

        gbc.gridwidth = 5;
        gbc.gridx = 1; gbc.gridy = 0;
        pnRightTop.add(pnPhanLoai, gbc);

        gbc.ipadx = 180;
        gbc.ipady = 5;

        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 1;
        pnRightTop.add(txtTenTaiSan, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        pnRightTop.add(cbxNhaSanXuat, gbc);

        gbc.gridheight = 2;
        gbc.gridx = 1; gbc.gridy = 3;
        pnRightTop.add(scMoTa, gbc);

        gbc.ipadx = 0;
        gbc.gridheight = 1;
        gbc.gridx = 2; gbc.gridy = 1;
        pnRightTop.add(lblMaTaiSan, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        pnRightTop.add(lblNguyenGia, gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        pnRightTop.add(lblNamBDSuDung, gbc);

        gbc.gridx = 2; gbc.gridy = 4;
        pnRightTop.add(lblTiLeKhauHao, gbc);

        gbc.gridx = 2; gbc.gridy = 5;
        pnRightTop.add(lblThoiGianKhauHao, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 3; gbc.gridy = 1;
        pnRightTop.add(txtMaTaiSan, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 3; gbc.gridy = 2;
        pnRightTop.add(txtNguyenGia, gbc);

        gbc.ipadx = 120;
        gbc.gridwidth = 1;

        gbc.gridx = 3; gbc.gridy = 3;
        pnRightTop.add(cbxNamBDSuDung, gbc);

        gbc.gridx = 3; gbc.gridy = 4;
        pnRightTop.add(txtTiLeKhauHao, gbc);

        gbc.gridx = 4; gbc.gridy = 4;
        pnRightTop.add(new JLabel("% / năm"), gbc);

        gbc.gridx = 3; gbc.gridy = 5;
        pnRightTop.add(txtThoiGianKhauHao, gbc);

        gbc.gridx = 4; gbc.gridy = 5;
        pnRightTop.add(new JLabel("năm"), gbc);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButton.add(btnNew);
        pnButton.add(btnEdit);
        pnButton.add(btnDelete);
        pnButton.add(btnSave);
        pnButton.add(btnCancel);

        JPanel pnRightTp = new JPanel(new BorderLayout(0,10));
        pnRightTp.add(pnRightTop, BorderLayout.CENTER);
        pnRightTp.add(pnButton, BorderLayout.SOUTH);

        JPanel pnTKTaiSan = new JPanel(new BorderLayout());
        JPanel pnSearchEmployee = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearchEmployee.add(lblTimKiem);
        pnSearchEmployee.add(txtTimKiem);
        pnSearchEmployee.add(btnTKTS);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));

        JPanel pnTemp2 = new JPanel();
        pnTKTaiSan.add(pnSearchEmployee, BorderLayout.WEST);
        pnTKTaiSan.add(pnTemp2, BorderLayout.CENTER);
        pnTKTaiSan.add(pnExport, BorderLayout.EAST);

        JPanel pnTblTaiSan = new JPanel(new BorderLayout());
        pnTblTaiSan.add(pnTKTaiSan, BorderLayout.NORTH);
        pnTblTaiSan.add(scTblTaiSan, BorderLayout.CENTER);

        JPanel pnRight = new JPanel(new BorderLayout());
        pnRight.add(pnRightTp, BorderLayout.NORTH);
        pnRight.add(pnTblTaiSan, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setPreferredSize(new Dimension(0, 5));

        this.setLayout(new BorderLayout(10, 10));
        this.add(pnTitle, BorderLayout.NORTH);
        this.add(pnLeft, BorderLayout.WEST);
        this.add(pnRight, BorderLayout.CENTER);

        pnLeft.setPreferredSize(new Dimension(340, 0));
    }

    public void addEvents() {
        /* Mouse Click bảng Tài Sản */
        tblTaiSan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexTaiSan = tblTaiSan.getSelectedRow();
                fillToForm(indexTaiSan);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        /* Mouse Click bảng Nhà Sản Xuất */
        tblNhaSanXuat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexNhaSanXuat = tblNhaSanXuat.getSelectedRow();
                getNSXSelected(indexNhaSanXuat);
                loadDataToTblTaiSan(nsxSelected);
                indexTaiSan = 0;
                fillToForm(indexTaiSan);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        /* Thêm mới */
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnNew();
            }
        });

        /* Sửa */
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnEdit();
            }
        });

        /* Xóa */
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnDelete();
            }
        });

        /* Lưu */
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnSave();
            }
        });

        /* Bỏ qua */
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnCancel();
            }
        });

        /* Tự động điền năm khấu hao*/
        txtThoiGianKhauHao.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                if (txtThoiGianKhauHao.getText().length() > 0) {
                    double thoigiankhauhao = Double.parseDouble(txtThoiGianKhauHao.getText());
                    double thoigian = 100/thoigiankhauhao;
                    double tylekhauhao = (double) Math.round(thoigian *100) /100;
                    txtTiLeKhauHao.setText(""+tylekhauhao);
                } else {
                    txtTiLeKhauHao.setText("");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                if (txtThoiGianKhauHao.getText().length() > 0) {
                    double thoigiankhauhao = Double.parseDouble(txtThoiGianKhauHao.getText());
                    double thoigian = 100/thoigiankhauhao;
                    double tylekhauhao = (double) Math.round(thoigian *100) /100;
                    txtTiLeKhauHao.setText(""+tylekhauhao);
                } else {
                    txtTiLeKhauHao.setText("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

        // <editor-fold defaultstate="collapsed" desc="Tìm kiếm Nhà sản xuất">
        txtTimKiemNhaSanXuat.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtTimKiemNhaSanXuat.getText().length() > 0) {
                    nhaSanXuatListTimKiem = getListTimKiemNhaSanXuat();
                    timKiemNhaSanXuat();
                } else {
                    indexTaiSan = 0;
                    indexNhaSanXuat = 0;
                    indexTimKiemTaiSan = 0;
                    indexTimKiemNhaSanXuat = 0;
                    getNSXSelected(indexNhaSanXuat);
                    loadDataToTblTaiSan(nsxSelected);
                    fillToForm(indexTaiSan);
                    txtTimKiem.setEditable(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtTimKiemNhaSanXuat.getText().length() > 0) {
                    nhaSanXuatListTimKiem = getListTimKiemNhaSanXuat();
                    timKiemNhaSanXuat();
                    if (nhaSanXuatListTimKiem.size() > 0) {
                        btnNew.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnNew.setEnabled(false);
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                } else {
                    indexTaiSan = 0;
                    indexNhaSanXuat = 0;
                    indexTimKiemTaiSan = 0;
                    indexTimKiemNhaSanXuat = 0;
                    getNSXSelected(indexNhaSanXuat);
                    loadDataToTblTaiSan(nsxSelected);
                    fillToForm(indexTaiSan);
                    txtTimKiem.setEditable(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtTimKiemNhaSanXuat.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && nhaSanXuatListTimKiem.size() > 0 && txtTimKiemNhaSanXuat.getText().length() > 0) {
                    indexTimKiemNhaSanXuat = indexTimKiemNhaSanXuat + 1;
                    timKiemNhaSanXuat();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtTimKiemNhaSanXuat.setText("");
                    indexTaiSan = 0;
                    indexNhaSanXuat = 0;
                    indexTimKiemTaiSan = 0;
                    indexTimKiemNhaSanXuat = 0;
                    getNSXSelected(indexNhaSanXuat);
                    loadDataToTblTaiSan(nsxSelected);
                    fillToForm(indexTaiSan);
                    txtTimKiem.setEditable(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Tìm kiếm Tài sản">
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                if (txtTimKiem.getText().length() > 0) {
                    taiSanListTimKiem = getListTimKiemTaiSan();
                    timKiemTaiSan();
                } else {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    fillToForm(indexTaiSan);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtTimKiem.getText().length() > 0) {
                    taiSanListTimKiem = getListTimKiemTaiSan();
                    timKiemTaiSan();
                    if (taiSanListTimKiem.size() > 0) {
                        btnNew.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnNew.setEnabled(false);
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                } else {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    fillToForm(indexTaiSan);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtTimKiem.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && taiSanListTimKiem.size() > 0 && txtTimKiem.getText().length() > 0) {
                    indexTimKiemTaiSan = indexTimKiemTaiSan + 1;
                    timKiemTaiSan();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtTimKiem.setText("");
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    fillToForm(indexTaiSan);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        // </editor-fold>
    }

    //<editor-fold desc="BUTTON">
    public void setBtnNew() {
        clearForm();
        tblTaiSan.clearSelection();
        setStatus(CoreConstant.FLAG_INSERT);
        txtMaTaiSan.setText(createID());
        txtTenTaiSan.requestFocus();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }

    public void setBtnDelete() {
        boolean i = DialogUtils.showConfirmDialog("Bạn có chắc muốn xóa tài sản " + tsSelected + " không ?");
        if (i == true) {
            int n = SingletonDaoUtil.getTaiSanDaoImpl().delete(tsSelected);
            if (n > 0) {
                if (indexTaiSan == taiSanList.size() -1) {
                    indexTaiSan = indexTaiSan - 1;
                }
                deleteSuccess();
            }
        }
    }

    public void setBtnSave() {
        TaiSan taiSan = getModel();
        if (flagSave == CoreConstant.FLAG_INSERT) {
            SingletonDaoUtil.getTaiSanDaoImpl().save(taiSan);
            saveSuccess();
        } else if (flagSave == CoreConstant.FLAG_UPDATE) {
            SingletonDaoUtil.getTaiSanDaoImpl().update(taiSan);
            saveSuccess();
        }
    }

    public void setBtnCancel() {
        boolean n = DialogUtils.showConfirmDialog(messCancel);
        if (n == true) {
            setStatus(CoreConstant.FLAG_EMTY);
        }
    }

    /* Xóa thành công */
    public void deleteSuccess() {
        loadDataToTblTaiSan(nsxSelected);
        fillToForm(indexTaiSan);
    }

    /* Lưu thành công */
    public void saveSuccess() {
        NhaSanXuat nhaSanXuat = (NhaSanXuat) cbxNhaSanXuat.getSelectedItem();
        if (nhaSanXuat.getId() != nsxSelected.getId()) {
            indexNhaSanXuat = cbxNhaSanXuat.getSelectedIndex();
            getNSXSelected(indexNhaSanXuat);
        }

        loadDataToTblTaiSan(nsxSelected);
        for (TaiSan item : taiSanList) {
            if (item.getMats().equalsIgnoreCase(txtMaTaiSan.getText())) {
                indexTaiSan = taiSanList.indexOf(item);
                fillToForm(indexTaiSan);
                break;
            }
        }
        statusDefault();
    }
    //</editor-fold>

    //<editor-fold desc="LOAD DATA">
    /* Load dữ liệu cho bảng Phòng Ban */
    public void loadDataToNhaSanXuat() {
        modelTblNhaSanXuat.setRowCount(0);
        cbxNhaSanXuat.removeAllItems();

        nhaSanXuatList = SingletonDaoUtil.getNhaSanXuatDaoImpl().findAll();
        int i = 1;

        for (NhaSanXuat item : nhaSanXuatList) {
            cbxNhaSanXuat.addItem(item);
            modelTblNhaSanXuat.addRow(new Object[] {
                    i, item.getTennsx() + " - " + item.getQuocGia().getTenqg()
            });
            i++;
        }
    }

    /* Load dữ liệu cho bảng Tài Sản */
    public void loadDataToTblTaiSan(NhaSanXuat nhaSanXuat) {
        modelTblTaiSan.setRowCount(0);

        if (nhaSanXuat != null) {
            taiSanList = SingletonDaoUtil.getTaiSanDaoImpl().getByNhaSanXuat(nsxSelected);
            if (taiSanList.size() > 0) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);

                int i = 1;
                for (TaiSan item : taiSanList) {
                    long nguyengia = Long.parseLong(String.valueOf(item.getNguyengia()));
                    modelTblTaiSan.addRow(new Object[] {
                            i, item.getMats(), item.getTents(), MoneyUtil.castIntToMoney(nguyengia),item.getThoigiankhauhao() + " năm"
                    });
                    i++;
                }

            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Tìm kiếm Nhà sản xuất">
    public List<NhaSanXuat> getListTimKiemNhaSanXuat() {
        String str = txtTimKiemNhaSanXuat.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("tennsx", str);

        List<NhaSanXuat> list = SingletonDaoUtil.getNhaSanXuatDaoImpl().searchByProperty(map);
        return list;
    }

    public void timKiemNhaSanXuat() {
        if (nhaSanXuatListTimKiem.size() > 0) {
            txtTimKiem.setEditable(true);

            if (indexTimKiemNhaSanXuat == nhaSanXuatListTimKiem.size()) {
                indexTimKiemNhaSanXuat = 0;
            }

            for (NhaSanXuat item : nhaSanXuatList) {
                if (item.getId() == nhaSanXuatListTimKiem.get(indexTimKiemNhaSanXuat).getId()) {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    indexNhaSanXuat = nhaSanXuatList.indexOf(item);
                    getNSXSelected(indexNhaSanXuat);
                    loadDataToTblTaiSan(nsxSelected);
                    fillToForm(indexTaiSan);
                    break;
                }
            }

        } else {
            clearForm();
            indexNhaSanXuat = 0;
            indexTaiSan = 0;
            indexTimKiemNhaSanXuat = 0;
            indexTimKiemTaiSan = 0;
            nsxSelected = null;
            loadDataToTblTaiSan(nsxSelected);
            tblNhaSanXuat.clearSelection();
            txtTimKiem.setEditable(false);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Tìm kiếm Tài sản">
    public List<TaiSan> getListTimKiemTaiSan() {
        String str = txtTimKiem.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("mats", str);
        map.put("tents", str);
        map.put("nambdsudung", str);
        List<TaiSan> list = new ArrayList<>();

        if (nsxSelected != null ) {
           list = SingletonDaoUtil.getTaiSanDaoImpl().searchByProperty(map, nsxSelected);
        }
        return list;
    }

    public void timKiemTaiSan() {
        if (taiSanListTimKiem.size() > 0) {

            if (indexTimKiemTaiSan == taiSanListTimKiem.size()) {
                indexTimKiemTaiSan = 0;
            }

            for (TaiSan item : taiSanList) {
                if (item.getMats().equalsIgnoreCase(taiSanListTimKiem.get(indexTimKiemTaiSan).getMats())) {
                    indexTaiSan = taiSanList.indexOf(item);
                    fillToForm(indexTaiSan);
                    break;
                }
            }
        } else {
            clearForm();
            indexTaiSan = 0;
            indexTimKiemTaiSan = 0;
            tblTaiSan.clearSelection();
        }
    }
    //</editor-fold>

    /* Lấy nhà sản xuất selected */
    public void getNSXSelected (int i) {
        if (nhaSanXuatList.size() > 0) {
            tblNhaSanXuat.setRowSelectionInterval(i, i);
            Rectangle rect = tblNhaSanXuat.getCellRect(i, 0, true);
            tblNhaSanXuat.scrollRectToVisible(rect);
            sttNhaSanXuat = Integer.parseInt(tblNhaSanXuat.getValueAt(i, 0).toString());
            nsxSelected = nhaSanXuatList.get(sttNhaSanXuat - 1);
        }
    }

    /* Fill dữ liệu lên form Tài Sản */
    public void fillToForm (int i) {
        if (taiSanList.size() > 0) {
            tblTaiSan.setRowSelectionInterval(i, i);
            Rectangle rect = tblTaiSan.getCellRect(i, 0, true);
            tblTaiSan.scrollRectToVisible(rect);

            sttTaiSan = Integer.parseInt(tblTaiSan.getValueAt(i, 0).toString());
            tsSelected = taiSanList.get(sttTaiSan-1);
            setModel(tsSelected);
        } else {
            clearForm();
        }
    }

    /* Lấy model của Tài Sản */
    public TaiSan getModel() {
        TaiSan taiSan = new TaiSan();

        if (rdo1.isSelected()) {
            taiSan.setPhanLoai(phanLoaiList.get(0));
        } else if (rdo2.isSelected()) {
            taiSan.setPhanLoai(phanLoaiList.get(1));
        } else {
            taiSan.setPhanLoai(phanLoaiList.get(2));
        }

        taiSan.setMats(txtMaTaiSan.getText());
        taiSan.setTents(txtTenTaiSan.getText());
        taiSan.setNhaSanXuat( (NhaSanXuat) cbxNhaSanXuat.getSelectedItem());
        taiSan.setNguyengia(Integer.parseInt(txtNguyenGia.getText()));
        taiSan.setMota(txaMoTa.getText());
        taiSan.setTilekhauhao(Double.parseDouble(txtTiLeKhauHao.getText()));
        taiSan.setThoigiankhauhao(Double.parseDouble(txtThoiGianKhauHao.getText()));

        if (cbxNamBDSuDung.getSelectedIndex() > 0){
            int nam = (int) cbxNamBDSuDung.getSelectedItem();
            taiSan.setNambdsudung(nam);
        } else {
            taiSan.setNambdsudung(-1);
        }
        return taiSan;
    }

    /* Fill dữ liệu thông tin chi tiết Tài Sản */
    public void setModel(TaiSan taiSan) {
        if (taiSan != null) {
            if (taiSan.getPhanLoai().getId() == 1) {
                rdo1.setSelected(true);
            } else if (taiSan.getPhanLoai().getId() == 2){
                rdo2.setSelected(true);
            } else {
                rdo3.setSelected(true);
            }

            txtMaTaiSan.setText(taiSan.getMats());
            txtTenTaiSan.setText(taiSan.getTents());

//            for (NhaSanXuat nsx : nhaSanXuatList) {
//                if (nsx.getId() == taiSan.getNhaSanXuat().getId()) {
//                    cbxNhaSanXuat.setSelectedItem(nsx);
//                    break;
//                }
//            }

            cbxNhaSanXuat.setSelectedItem(nsxSelected);
            txaMoTa.setText(taiSan.getMota());
            txtNguyenGia.setText(String.valueOf(taiSan.getNguyengia()));
            txtTiLeKhauHao.setText(String.valueOf(taiSan.getTilekhauhao()));
            txtThoiGianKhauHao.setText(String.valueOf(taiSan.getThoigiankhauhao()));
            if (taiSan.getNambdsudung() != -1) {
                cbxNamBDSuDung.setSelectedItem(taiSan.getNambdsudung());
            } else {
                cbxNamBDSuDung.setSelectedIndex(0);
            }
        } else {
            rdo1.setSelected(true);
            cbxNamBDSuDung.setSelectedIndex(0);
            txtMaTaiSan.setText("");
            txtTenTaiSan.setText("");
            txaMoTa.setText("");
            txtNguyenGia.setText("");
            txtTiLeKhauHao.setText("");
            txtThoiGianKhauHao.setText("");
            cbxNhaSanXuat.setSelectedItem(nsxSelected);
        }
    }

    /* Xóa trắng form*/
    public void clearForm() {
        setModel(null);
    }

    /* Trạng thái ban đầu của phần mềm */
    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtTenTaiSan.setEditable(false);
        txtThoiGianKhauHao.setEditable(false);
        txtTiLeKhauHao.setEditable(false);
        txtNguyenGia.setEditable(false);
        txtMaTaiSan.setEditable(false);
        txaMoTa.setEditable(false);
        cbxNhaSanXuat.setEnabled(false);
        cbxNamBDSuDung.setEnabled(false);
        rdo1.setEnabled(false);
        rdo2.setEnabled(false);
        rdo3.setEnabled(false);
    }

    /* Thay đổi trạng thái */
    public void setStatus (int insertable) {
        flagSave = insertable;

        switch (insertable) {
            case CoreConstant.FLAG_INSERT:
                messCancel = "Bạn có muốn bỏ qua thao tác thêm mới không?";
                clearForm();
                btnChange();
                break;
            case CoreConstant.FLAG_UPDATE:
                messCancel = "Bạn có muốn bỏ qua thao tác cập nhật thông tin không?";
                btnChange();
                break;
            default:
                statusDefault();
                fillToForm(indexTaiSan);
                break;
        }
    }

    public void btnChange() {
        btnSave.setVisible(true);
        btnNew.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(true);

        rdo1.setEnabled(true);
        rdo2.setEnabled(true);
        rdo3.setEnabled(true);
        cbxNamBDSuDung.setEnabled(true);
        cbxNhaSanXuat.setEnabled(true);
        txtMaTaiSan.setEditable(false);
        txtTenTaiSan.setEditable(true);
        txtNguyenGia.setEditable(true);
        txtThoiGianKhauHao.setEditable(true);
        txaMoTa.setEditable(true);
    }

    private String createID() {
        String lastID = SingletonDaoUtil.getTaiSanDaoImpl().getLastID();

        if (lastID != null) {
            StringBuilder ID = new StringBuilder();
            ID.append("TS");

            int pathNumber = Integer.parseInt(lastID.substring(2)) + 1;

            for (int i = 0; i < 4 - String.valueOf(pathNumber).length(); i++) {
                ID.append("0");
            }

            ID.append(pathNumber);

            return ID.toString();
        } else {
            return "TS00001";
        }
    }

    private boolean checkInfor() {

        return true;
    }

    //<editor-fold desc="COMPONENT">
    public boolean doneLoad = false;

    List<NhaSanXuat> nhaSanXuatList = new ArrayList<>();
    List<TaiSan> taiSanList = new ArrayList<>();
    List<PhanLoai> phanLoaiList = SingletonDaoUtil.getPhanLoaiDaoImpl().findAll();

    NhaSanXuat nsxSelected = new NhaSanXuat();
    TaiSan tsSelected = new TaiSan();

    int flagSave = CoreConstant.FLAG_EMTY;

    int sttTaiSan = -1;
    int sttNhaSanXuat = -1;

    int indexTaiSan = 0;
    int indexNhaSanXuat = 0;

    int indexTimKiemNhaSanXuat = 0;
    int indexTimKiemTaiSan = 0;

    List<TaiSan> taiSanListTimKiem = new ArrayList<>();
    List<NhaSanXuat> nhaSanXuatListTimKiem = new ArrayList<>();

    String messCancel;

    JLabel lblPhanLoai, lblTenTaiSan, lblMaTaiSan, lblNhaSanXuat, lblMoTa, lblNguyenGia, lblTiLeKhauHao, lblThoiGianKhauHao, lblTimKiem, lblNamBDSuDung, lblTimKiemNhaSanXuat;
    JTextField txtTenTaiSan, txtMaTaiSan, txtNguyenGia, txtTiLeKhauHao, txtThoiGianKhauHao, txtTimKiem, txtTimKiemNhaSanXuat;
    JComboBox cbxNhaSanXuat, cbxNamBDSuDung;
    JTextArea txaMoTa;
    JRadioButton rdo1, rdo2, rdo3;
    ButtonGroup btgPhanLoai;
    JScrollPane scMoTa, scTblNhaSanXuat, scTblTaiSan;
    JTable tblNhaSanXuat, tblTaiSan;
    DefaultTableModel modelTblNhaSanXuat, modelTblTaiSan;
    JButton btnTKTS, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    //</editor-fold>
}
