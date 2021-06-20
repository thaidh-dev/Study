package Frame.Panel.Manager;

import Contant.CoreConstant;
import Entity.NhaSanXuat;
import Entity.PhongBan;
import Entity.QuocGia;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyNhaSanXuat extends JPanel {
    public QuanLyNhaSanXuat() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyNhaSanXuat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuanLyNhaSanXuat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuanLyNhaSanXuat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QuanLyNhaSanXuat.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {
        lblName = new JLabel("Tên nhà sản xuất");
        lblNational = new JLabel("Quốc gia");
        lblSearch = new JLabel("Tìm kiếm");

        txtName = new JTextField();
        txtSearch = new JTextField();

        cbxNational = new JComboBox();

        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa");
        btnEdit = new JButton("Sửa");
        btnExport = new JButton("Kết xuất");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");
        btnNext = new JButton("Tìm tiếp");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        modelTblProducer = new DefaultTableModel();
        modelTblProducer.setColumnIdentifiers(new Object[] {"STT","Tên nhà sản xuất", "Quốc gia"});
        tblProducer = new JTable(modelTblProducer);
        scTblProducer = new JScrollPane(tblProducer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        txtSearch.setPreferredSize(new Dimension(200, 25));
    }

    public void addControls() {
        JPanel pnDetail = new JPanel(new BorderLayout());

        JPanel pnDetailItem = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.ipadx = 50;
        gbc.ipady = 5;

        gbc.gridx = 0; gbc.gridy = 0;
        pnDetailItem.add(lblName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnDetailItem.add(lblNational, gbc);

        gbc.ipadx = 200;
        gbc.ipady = 5;

        gbc.gridx = 1; gbc.gridy = 0;
        pnDetailItem.add(txtName, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        pnDetailItem.add(cbxNational, gbc);


        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnButtons.add(btnNew);
        pnButtons.add(btnEdit);
        pnButtons.add(btnEdit);
        pnButtons.add(btnDelete);
        pnButtons.add(btnSave);
        pnButtons.add(btnCancel);

        pnDetail.add(pnDetailItem, BorderLayout.CENTER);
        pnDetail.add(pnButtons, BorderLayout.SOUTH);

        JPanel pnTable = new JPanel(new BorderLayout());

        JPanel pnSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);
//        pnSearch.add(btnNext);

        pnTable.add(pnSearch, BorderLayout.NORTH);
        pnTable.add(scTblProducer);

        this.setLayout(new BorderLayout(0, 10));
        this.add(pnDetail, BorderLayout.NORTH);
        this.add(pnTable, BorderLayout.CENTER);;
    }

    public void addEvents() {
        loadDataToCbx();
        loadDataToTblNhaSanXuat();
        fillToForm(indexNhaSanXuat);
        statusDefault();

        tblProducer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (flagSave == CoreConstant.FLAG_EMTY) {
                    indexNhaSanXuat = tblProducer.getSelectedRow();
                    fillToForm(indexNhaSanXuat);
                } else {
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);

                    if (answer){
                        indexNhaSanXuat = tblProducer.getSelectedRow();
                        tblProducer.setRowSelectionInterval(indexNhaSanXuat, indexNhaSanXuat);
                        Rectangle rect = tblProducer.getCellRect(indexNhaSanXuat, 0, true);
                        tblProducer.scrollRectToVisible(rect);
                        fillToForm(indexNhaSanXuat);
                        statusDefault();
                    }
                }
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

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setTxtSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setTxtSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && nhaSanXuatListTimKiem.size() > 0 && txtSearch.getText().length() > 0) {
                    indeNhaSanXuatTimKiem = indeNhaSanXuatTimKiem + 1;
                    search();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtSearch.setText("");
                    indexNhaSanXuat = 0;
                    fillToForm(indexNhaSanXuat);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void setBtnNew() {
        setStatus(CoreConstant.FLAG_INSERT);
        clearForm();
        tblProducer.clearSelection();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }

    public void setBtnSave() {
        if (checkInfor()) {
            NhaSanXuat nhaSanXuat = getModel();
            if (flagSave == CoreConstant.FLAG_INSERT) {
                SingletonDaoUtil.getNhaSanXuatDaoImpl().save(nhaSanXuat);
                saveSuccess();
            } else if (flagSave == CoreConstant.FLAG_UPDATE) {
                nhaSanXuat.setId(nsxSelected.getId());
                SingletonDaoUtil.getNhaSanXuatDaoImpl().update(nhaSanXuat);
                saveSuccess();
            }
        }
    }

    public void setBtnDelete() {
        boolean answer = DialogUtils.showConfirmDialog("Bạn có chắc muốn xóa nhà sản xuất "+nsxSelected + " không?");
        if (answer) {
            int n = SingletonDaoUtil.getNhaSanXuatDaoImpl().delete(nsxSelected);

            if (n > 0) {
                deleteSuccess();
            }
        }
    }

    public void setBtnCancel() {
        if (DialogUtils.showConfirmDialog(messCancel)) {
            statusDefault();
            fillToForm(indexNhaSanXuat);
        }
    }

    public void setTxtSearch() {
        if (txtSearch.getText().length() > 0) {
            nhaSanXuatListTimKiem = getListNhaSanXuatTimKiem();
            search();

            if (nhaSanXuatListTimKiem.size() > 0) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }

        } else {
            indexNhaSanXuat = 0;
            indeNhaSanXuatTimKiem = 0;
            fillToForm(indexNhaSanXuat);

            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }

    //<editor-fold desc="Xóa thành công">
    public void deleteSuccess() {
        if (indexNhaSanXuat == nhaSanXuatList.size() -1) {
            indexNhaSanXuat = indexNhaSanXuat - 1;
        }
        loadDataToTblNhaSanXuat();
        fillToForm(indexNhaSanXuat);
    }
    //</editor-fold>

    //<editor-fold desc="Lưu thành công">
    public void saveSuccess() {
        loadDataToTblNhaSanXuat();
        for (NhaSanXuat item : nhaSanXuatList) {
            if (item.getTennsx().equalsIgnoreCase(getModel().getTennsx())
                && item.getQuocGia().getMaqg().equalsIgnoreCase(getModel().getQuocGia().getMaqg())) {
                indexNhaSanXuat = nhaSanXuatList.indexOf(item);
                fillToForm(indexNhaSanXuat);
                break;
            }
        }
        statusDefault();
    }
    //</editor-fold>

    //<editor-fold desc="Lấy danh sách nhà sản xuất tìm kiếm">
    public java.util.List<NhaSanXuat> getListNhaSanXuatTimKiem() {
        String str = txtSearch.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("tennsx", str);
        List<NhaSanXuat> list = SingletonDaoUtil.getNhaSanXuatDaoImpl().searchByProperty(map);
        return list;
    }
    //</editor-fold>

    //<editor-fold desc="Tìm kiếm nhà sản xuất">
    public void search() {
        if (nhaSanXuatListTimKiem.size() > 0) {

            if (indeNhaSanXuatTimKiem == nhaSanXuatListTimKiem.size()) {
                indeNhaSanXuatTimKiem = 0;
            }

            for (NhaSanXuat item : nhaSanXuatList) {
                if (item.getId() == (nhaSanXuatListTimKiem.get(indeNhaSanXuatTimKiem).getId())) {
                    indexNhaSanXuat = nhaSanXuatList.indexOf(item);
                    fillToForm(indexNhaSanXuat);
                    break;
                }
            }
        } else {
            clearForm();
            indexNhaSanXuat = 0;
            indeNhaSanXuatTimKiem = 0;
            tblProducer.clearSelection();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Check Information">
    public boolean checkInfor() {
        return true;
    }
    //</editor-fold>

    public void loadDataToCbx() {
        cbxNational.removeAllItems();
        quocGiaList = SingletonDaoUtil.getQuocGiaDaoImpl().findAll();

        for (QuocGia item : quocGiaList) {
            cbxNational.addItem(item);
        }
    }

    //<editor-fold desc="Load dữ liệu vào bảng danh sách nhà sản xuất">
    public void loadDataToTblNhaSanXuat() {
        modelTblProducer.setRowCount(0);
        nhaSanXuatList = SingletonDaoUtil.getNhaSanXuatDaoImpl().findAll();
        int stt = 1;
        for (NhaSanXuat item : nhaSanXuatList) {
            modelTblProducer.addRow(new Object[] {
                    stt, item.getTennsx(), item.getQuocGia().getTenqg()
            });
            stt++;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Status mặc định">
    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtName.setEditable(false);
        cbxNational.setEnabled(false);
    }
    //</editor-fold>

    //<editor-fold desc="Set status theo flagSave">
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
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Set model nhà sản xuất">
    public void setModel(NhaSanXuat nhaSanXuat) {
        if (nhaSanXuat != null) {
            txtName.setText(nhaSanXuat.getTennsx());
            for (QuocGia item : quocGiaList) {
                if (item.getMaqg().equalsIgnoreCase(nhaSanXuat.getQuocGia().getMaqg())) {
                    int n = quocGiaList.indexOf(item);
                    cbxNational.setSelectedIndex(n);
                    break;
                }
            }
        } else {
            txtName.setText("");
            cbxNational.setSelectedIndex(0);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Lấy model nhà sản xuất từ Form">
    public NhaSanXuat getModel() {
        String tennsx = txtName.getText().trim();
        QuocGia quocGia = (QuocGia) cbxNational.getSelectedItem();

        NhaSanXuat nhaSanXuat = new NhaSanXuat(tennsx, quocGia);
        return  nhaSanXuat;
    }
    //</editor-fold>

    //<editor-fold desc="Xóa trắng form">
    public void clearForm() {
        setModel(null);
    }
    //</editor-fold>

    //<editor-fold desc="Fill dữ liệu lên Form">
    public void fillToForm(int i) {
        if (nhaSanXuatList.size() > 0) {
            tblProducer.setRowSelectionInterval(i, i);
            Rectangle rect = tblProducer.getCellRect(i, 0, true);
            tblProducer.scrollRectToVisible(rect);

            sttNhaSanXuat = (Integer) (tblProducer.getValueAt(i, 0));
            nsxSelected = nhaSanXuatList.get(sttNhaSanXuat-1);
            setModel(nsxSelected);
        } else {
            clearForm();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Button thay đổi theo flagSave">
    public void btnChange() {
        btnSave.setVisible(true);
        btnNew.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(true);

        txtName.setEditable(true);
        cbxNational.setEnabled(true);
    }
    //</editor-fold>

    //<editor-fold desc="Description">
    List<NhaSanXuat> nhaSanXuatList = new ArrayList<>();
    List<NhaSanXuat> nhaSanXuatListTimKiem = new ArrayList<>();
    List<QuocGia> quocGiaList = new ArrayList<>();

    NhaSanXuat nsxSelected = new NhaSanXuat();

    int indexNhaSanXuat = 0;
    int indeNhaSanXuatTimKiem = 0;

    int sttNhaSanXuat = -1;

    int flagSave;
    String messCancel;

    JLabel lblName, lblNational,lblSearch;
    JTextField txtName, txtSearch;
    JComboBox cbxNational;
    JButton btnNext, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    JTable tblProducer;
    DefaultTableModel modelTblProducer;
    JScrollPane scTblProducer;
    //</editor-fold>
}
