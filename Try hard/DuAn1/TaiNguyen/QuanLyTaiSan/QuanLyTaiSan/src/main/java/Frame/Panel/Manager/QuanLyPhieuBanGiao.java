package Frame.Panel.Manager;

import Contant.CoreConstant;
import Entity.NhanVien;
import Entity.PhieuBanGiao;
import Entity.PhongBan;
import Entity.TaiSan;
import Utils.DateUtil;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyPhieuBanGiao extends JPanel {

    public QuanLyPhieuBanGiao() {
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
        initComponent();
        addControls();
        addEvents();

        statusDefault();
        loadDataToPhongBan();
        loadDataToCbxTaiSan();

        getPBSelected(indexPhongBan);
        loadDataToTblPhieuBanGiao(pbSelected);
        fillToForm(indexPhieuBanGiao);
    }

    public void initComponent() {
        lblTaiSan = new JLabel("Tài Sản");
        lblPhongBan = new JLabel("Phòng Ban");
        lblNguoiSuDung = new JLabel("Người Sử Dụng");
        lblNgayBatDau = new JLabel("Ngày Bàn Giao");
        lblTimKiem = new JLabel("Tìm kiếm");

        chk1 = new JCheckBox("Chỉ hiển thị phòng ban có tài sản");

        cbxTaiSan = new JComboBox();
        cbxPhongBan = new JComboBox();

        txtNguoiSuDung = new JTextField();
        txtNgayBatDau = new JTextField();
        txtTimKiemPBG = new JTextField();

        //<editor-fold desc="BUTTON">
        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa", new ImageIcon("src/Image/icon-delete"));
        btnEdit = new JButton("Sửa");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");
        btnSearch = new JButton("Tìm tiếp");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSearch.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        //<editor-fold desc="TABLE PHÒNG BAN">
        modelTblPhongBan = new DefaultTableModel();
        modelTblPhongBan.setColumnIdentifiers(new Object[] {"STT", "TÊN PHÒNG BAN"});
        tblPhongBan = new JTable(modelTblPhongBan){
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
        JTableHeader tblHeaderPhongBan = tblPhongBan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        /* Chỉnh sửa kích thước từng cột */
        tblHeaderPhongBan.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderPhongBan.getColumnModel().getColumn(1).setPreferredWidth(280);

        /* Chỉnh sửa độ cao dòng */
        tblPhongBan.setRowHeight(20);

        /* Chỉnh sửa màu nền khi chọn */
        tblPhongBan.setSelectionBackground(Color.decode("#3a4d8f"));

        tblPhongBan.setIntercellSpacing(new Dimension(0, 0));
        tblPhongBan.setShowGrid(false);

        tblPhongBan.setRowSorter(new TableRowSorter(modelTblPhongBan));
        tblPhongBan.setAutoCreateRowSorter(true);
        scTblPhongBan = new JScrollPane(tblPhongBan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="TABLE PHIẾU BÀN GIAO">
        modelTblPhieuBanGiao = new DefaultTableModel();
        modelTblPhieuBanGiao.setColumnIdentifiers(new Object[] {"STT", "Mã tài sản","Tên tài sản", "Người sử dụng", "Ngày bàn giao"});
        tblPhieuBanGiao = new JTable(modelTblPhieuBanGiao){
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

        JTableHeader tblHeaderTS = tblPhieuBanGiao.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        tblHeaderTS.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderTS.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(4).setPreferredWidth(100);

        tblPhieuBanGiao.setRowHeight(20);
        tblPhieuBanGiao.setSelectionBackground(Color.decode("#3a4d8f"));

        tblPhieuBanGiao.setRowSorter(new TableRowSorter(modelTblPhieuBanGiao));
        tblPhieuBanGiao.setAutoCreateRowSorter(true);

        scTblPhieuBanGiao = new JScrollPane(tblPhieuBanGiao, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="Set Font Segoe UI">
        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSearch.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        lblNgayBatDau.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        cbxPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        cbxTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtNgayBatDau.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtTimKiemPBG.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        chk1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtTimKiemPBG.setPreferredSize(new Dimension(250, 25));
        //</editor-fold>
    }

    public void addControls() {
        // LEFT
        JPanel pnLeftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnLeftTop.add(chk1);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnLeftTop, BorderLayout.NORTH);
        pnLeft.add(scTblPhongBan, BorderLayout.CENTER);

        JPanel pnRightTop = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 20, 5, 10);

        gbc.ipady = 5;

        gbc.gridx = 0; gbc.gridy = 0;
        pnRightTop.add(lblPhongBan, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnRightTop.add(lblTaiSan, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnRightTop.add(lblNguoiSuDung, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnRightTop.add(lblNgayBatDau, gbc);

        gbc.ipadx = 450;

        gbc.gridx = 1; gbc.gridy = 0;
        pnRightTop.add(cbxPhongBan, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        pnRightTop.add(cbxTaiSan, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        pnRightTop.add(txtNguoiSuDung, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        pnRightTop.add(txtNgayBatDau, gbc);

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
        pnSearchEmployee.add(txtTimKiemPBG);
//        pnSearchEmployee.add(btnSearch);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));

        JPanel pnTemp2 = new JPanel();
        pnTKTaiSan.add(pnSearchEmployee, BorderLayout.WEST);
        pnTKTaiSan.add(pnTemp2, BorderLayout.CENTER);
        pnTKTaiSan.add(pnExport, BorderLayout.EAST);

        JPanel pnTblTaiSan = new JPanel(new BorderLayout());
        pnTblTaiSan.add(pnTKTaiSan, BorderLayout.NORTH);
        pnTblTaiSan.add(scTblPhieuBanGiao, BorderLayout.CENTER);

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
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnNew();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnEdit();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnDelete();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnSave();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnCancel();
            }
        });

        tblPhongBan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexPhongBan = tblPhongBan.getSelectedRow();
                getPBSelected(indexPhongBan);
                cbxPhongBan.setSelectedItem(pbSelected.toString());
                loadDataToTblPhieuBanGiao(pbSelected);
                indexPhieuBanGiao = 0;
                fillToForm(indexPhieuBanGiao);
                statusDefault();
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

        tblPhieuBanGiao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexPhieuBanGiao = tblPhieuBanGiao.getSelectedRow();
                fillToForm(indexPhieuBanGiao);
                statusDefault();
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

        txtTimKiemPBG.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtTimKiemPBG.getText().length() > 0) {
                    phieuBanGiaoListSearch = getListSearch();
                    search();
                } else {
                    indexPBGSearch = 0;
                    indexPhieuBanGiao = 0;
                    fillToForm(indexPhieuBanGiao);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtTimKiemPBG.getText().length() > 0) {
                    phieuBanGiaoListSearch = getListSearch();
                    search();
                    if (phieuBanGiaoListSearch.size() > 0) {
                        btnNew.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnNew.setEnabled(false);
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                } else {
                    indexPBGSearch = 0;
                    indexPhieuBanGiao = 0;
                    fillToForm(indexPhieuBanGiao);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtTimKiemPBG.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && phieuBanGiaoListSearch.size() > 0 && txtTimKiemPBG.getText().length() > 0) {
                    indexPBGSearch = indexPBGSearch + 1;
                    search();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        txtTimKiemPBG.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtTimKiemPBG.setText("");
                    indexPhieuBanGiao = 0;
                    fillToForm(indexPhieuBanGiao);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    //<editor-fold desc="NEW EDIT DELETE SAVE CANCEL">
    public void setBtnNew () {
        clearForm();
        setStatus(CoreConstant.FLAG_INSERT);
        tblPhieuBanGiao.clearSelection();
        txtNguoiSuDung.requestFocus();
        txtNgayBatDau.setText(DateUtil.now());
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
        txtNguoiSuDung.requestFocus();
    }

    public void setBtnDelete() {
        boolean n = DialogUtils.showConfirmDialog("Bạn có chắc muốn xóa " + pbgSelected + " không?");
        if (n == true) {
            int i = SingletonDaoUtil.getPhieuBanGiaoDaoImpl().delete(pbgSelected);

            if (i > 0) {
                deleteSuccess();
            }
        }
    }

    public void setBtnSave() {
        if (flagSave == CoreConstant.FLAG_INSERT) {
            SingletonDaoUtil.getPhieuBanGiaoDaoImpl().save(getModel());
            saveSuccess();
        } else if (flagSave == CoreConstant.FLAG_UPDATE) {
            PhieuBanGiao pbg = pbgSelected;
            pbg.setTaiSan((TaiSan) cbxTaiSan.getSelectedItem());
            pbg.setNguoisudungs(txtNguoiSuDung.getText());
            SingletonDaoUtil.getPhieuBanGiaoDaoImpl().update(pbg);
            saveSuccess();
        }
    }

    public void setBtnCancel() {
        boolean n = DialogUtils.showConfirmDialog(messCancel);
        if (n == true) {
            setStatus(CoreConstant.FLAG_EMTY);
        }
    }

    public void saveSuccess() {
        loadDataToTblPhieuBanGiao(pbSelected);
        for (PhieuBanGiao item : phieuBanGiaoList) {
            if (item.getTaiSan().getMats().equalsIgnoreCase(getModel().getTaiSan().getMats())) {
                indexPhieuBanGiao = phieuBanGiaoList.indexOf(item);
                fillToForm(indexPhieuBanGiao);
                break;
            }
        }
        statusDefault();
    }

    public void deleteSuccess() {
        if (indexPhieuBanGiao == phieuBanGiaoList.size() -1) {
            indexPhieuBanGiao = indexPhieuBanGiao - 1;
        }
        loadDataToTblPhieuBanGiao(pbSelected);
        fillToForm(indexPhieuBanGiao);
    }
    //</editor-fold>

    //<editor-fold desc="LOAD DATA">
    public void loadDataToCbxTaiSan() {
        taiSanList = SingletonDaoUtil.getTaiSanDaoImpl().findAll();
        cbxTaiSan.removeAllItems();

        if (taiSanList.size() > 0) {
            for (TaiSan item : taiSanList) {
                cbxTaiSan.addItem(item);
            }
        }
    }

    public void loadDataToPhongBan() {
        modelTblPhongBan.setRowCount(0);
        cbxPhongBan.removeAllItems();

        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();
        int stt = 1;

        for (PhongBan item : phongBanList) {
            cbxPhongBan.addItem(item);
            modelTblPhongBan.addRow(new Object[] {
                stt, item.getTenpb()
            });
            stt++;
        }
    }

    public void loadDataToTblPhieuBanGiao(PhongBan phongBan) {
        modelTblPhieuBanGiao.setRowCount(0);

        if (phongBan != null) {
            phieuBanGiaoList = SingletonDaoUtil.getPhieuBanGiaoDaoImpl().getByPhongBan(phongBan);
            if (phieuBanGiaoList.size() > 0) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);

                int stt = 1;

                for (PhieuBanGiao item : phieuBanGiaoList) {
                    modelTblPhieuBanGiao.addRow(new Object[] {
                            stt, item.getTaiSan().getMats(), item.getTaiSan().getTents(), item.getNguoisudungs(), DateUtil.toString(item.getNgaybangiao())
                    });
                    stt++;
                }
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="SET STATUS">
    /* Trạng thái ban đầu */
    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtNguoiSuDung.setEditable(false);
        txtNgayBatDau.setEditable(false);

        cbxTaiSan.setEnabled(false);
        cbxPhongBan.setEnabled(false);
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
                fillToForm(indexPhieuBanGiao);
                break;
        }
    }

    public void btnChange() {
        btnSave.setVisible(true);
        btnNew.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(true);

        cbxTaiSan.setEnabled(true);
        txtNguoiSuDung.setEditable(true);
    }
    //</editor-fold>

    //<editor-fold desc="Tìm kiếm Phiếu bàn giao">
    public List<PhieuBanGiao> getListSearch() {
        String str = txtTimKiemPBG.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("mats", str);
        map.put("ngaybangiao", str);
        map.put("nguoisudung", str);
        List<PhieuBanGiao> list = SingletonDaoUtil.getPhieuBanGiaoDaoImpl().searchByProperty(map, pbSelected);
        return list;
    }

    public void search() {
        if (phieuBanGiaoListSearch.size() > 0) {

            if (indexPBGSearch == phieuBanGiaoListSearch.size()) {
                indexPBGSearch = 0;
            }

            for (PhieuBanGiao item : phieuBanGiaoList) {
                if (item.getId() == phieuBanGiaoListSearch.get(indexPBGSearch).getId()) {
                    indexPhieuBanGiao = phieuBanGiaoList.indexOf(item);
                    fillToForm(indexPhieuBanGiao);
                    break;
                }
            }
        } else {
            clearForm();
            indexPBGSearch = 0;
            indexPhieuBanGiao = 0;
            tblPhieuBanGiao.clearSelection();
        }
    }
    //</editor-fold>

    /* Lấy phòng ban selected */
    public void getPBSelected (int i) {
        if (phongBanList.size() > 0) {
            tblPhongBan.setRowSelectionInterval(i, i);
            Rectangle rect = tblPhongBan.getCellRect(i, 0, true);
            tblPhongBan.scrollRectToVisible(rect);
            sttPhongBan = Integer.parseInt(tblPhongBan.getValueAt(i, 0).toString());
            pbSelected = phongBanList.get(sttPhongBan - 1);
        }
    }

    /* Xóa trắng Form */
    public void clearForm() {
        setModel(null);
    }

    /* Fill dữ liệu lên form */
    public void fillToForm (int i) {
        if (phieuBanGiaoList.size() > 0) {
            tblPhieuBanGiao.setRowSelectionInterval(i, i);
            Rectangle rect = tblPhieuBanGiao.getCellRect(i, 0, true);
            tblPhieuBanGiao.scrollRectToVisible(rect);

            sttPhieuBanGiao = Integer.parseInt(tblPhieuBanGiao.getValueAt(i, 0).toString());
            pbgSelected = phieuBanGiaoList.get(sttPhieuBanGiao-1);
            setModel(pbgSelected);
        } else {
            clearForm();
        }
    }

    //<editor-fold desc="GET MODEL & SET MODEL">
    /* Set Model lên form */
    public void setModel(PhieuBanGiao phieuBanGiao) {
        if (phieuBanGiao != null) {
            TaiSan taiSan = phieuBanGiao.getTaiSan();
            for (TaiSan item : taiSanList) {
                if (item.getMats().equalsIgnoreCase(taiSan.getMats())) {
                    cbxTaiSan.setSelectedItem(item);
                    break;
                }
            }

            for (PhongBan item : phongBanList) {
                if (item.getMapb().equalsIgnoreCase(pbSelected.getMapb())) {
                    cbxPhongBan.setSelectedItem(item);
                    break;
                }
            }

            txtNguoiSuDung.setText(phieuBanGiao.getNguoisudungs());
            txtNgayBatDau.setText(DateUtil.toString(phieuBanGiao.getNgaybangiao()));
        } else {
            cbxPhongBan.setSelectedIndex(0);
            cbxTaiSan.setSelectedIndex(0);
            txtNguoiSuDung.setText("");
            txtNgayBatDau.setText("");
        }
    }

    /* Lấy model của Phiếu Bàn Giao */
    public PhieuBanGiao getModel() {
        PhieuBanGiao phieuBanGiao = new PhieuBanGiao();
        phieuBanGiao.setNguoisudungs(txtNguoiSuDung.getText().trim());
        phieuBanGiao.setNgaybangiao(new Date());
        phieuBanGiao.setPhongBan(pbSelected);
        phieuBanGiao.setTaiSan((TaiSan) cbxTaiSan.getSelectedItem());
        return phieuBanGiao;
    }
    //</editor-fold>

    //<editor-fold desc="Component">
    public boolean doneLoad = false;

    int flagSave;
    String messCancel;

    int sttPhongBan = -1;
    int sttPhieuBanGiao = -1;

    int indexPhongBan = 0;
    int indexPhieuBanGiao = 0;

    int indexPBGSearch;

    PhongBan pbSelected = new PhongBan();
    PhieuBanGiao pbgSelected = new PhieuBanGiao();

    List<TaiSan> taiSanList = new ArrayList<>();
    List<PhieuBanGiao> phieuBanGiaoList = new ArrayList<>();
    List<PhieuBanGiao> phieuBanGiaoListSearch = new ArrayList<>();
    List<PhongBan> phongBanList = new ArrayList<>();

    JTable tblPhongBan, tblPhieuBanGiao;
    DefaultTableModel modelTblPhongBan, modelTblPhieuBanGiao;
    JScrollPane scTblPhongBan, scTblPhieuBanGiao;

    JLabel lblTaiSan, lblPhongBan, lblNguoiSuDung, lblNgayBatDau, lblTimKiem;
    JTextField txtNguoiSuDung, txtTimKiemPBG, txtNgayBatDau;
    JButton btnNew, btnEdit, btnDelete, btnSave, btnCancel, btnSearch;

    JComboBox cbxTaiSan, cbxPhongBan;

    JCheckBox chk1;
    //</editor-fold>
}
