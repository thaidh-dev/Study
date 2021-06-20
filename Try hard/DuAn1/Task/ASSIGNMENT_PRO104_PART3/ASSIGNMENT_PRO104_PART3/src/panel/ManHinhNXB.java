package panel;

import dao.NhaXuatBanDAO;
import dao.SachDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.*;
import object.NhaXuatBan;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import object.Sach;

public class ManHinhNXB implements Khung {

    public JPanel pnlThan, pnlForm_NutChucNang, pnlForm, pnlNutChucNang, pnlTable, pnlTim, pnlTim2, pnlTim_ChucNang, pnlTieuDe_Tim_ChucNang;
    JLabel lblTieuDe, lblTenNXB, lblSDT, lblDiaChi;
    JTextField txtTim, txtTenNXB, txtSDT, txtDiaChi;
    public JButton btnTim, btnThem, btnSua, btnXoa, btnBoQua;
    JTable tblNXB;
    JScrollPane scrNXB;
    Dimension d;
    Insets insets;
    GridBagConstraints gbc;
    DefaultTableModel defaultTableModel;
    private NhaXuatBanDAO NXBDAO = new NhaXuatBanDAO();
    private NhaXuatBan NXB = new NhaXuatBan();
    private List<NhaXuatBan> listNXB;
    private ListSelectionModel selectionModel;
    private int currentSearch = 0;
    private boolean addSuccessful = false;

    public ManHinhNXB() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }

    @Override
    public void taoDoiTuong() {
        d = new Dimension(90, 25);
        insets = new Insets(5, 5, 5, 5);

        pnlThan = new JPanel(new BorderLayout(0, 20));
        pnlTim_ChucNang = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlTieuDe_Tim_ChucNang = new JPanel(new BorderLayout(0, 10));
        pnlForm_NutChucNang = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblTieuDe = new JLabel("Quản lý nhà xuất bản");

        pnlForm = new JPanel(new GridBagLayout());
        lblTenNXB = new JLabel("Tên nhà xuất bản");
        lblSDT = new JLabel("Số điện thoại");
        lblDiaChi = new JLabel("Địa chỉ");
        txtTenNXB = new JTextField(20);
        txtSDT = new JTextField(20);
        txtDiaChi = new JTextField(20);
        gbc = new GridBagConstraints();

        pnlNutChucNang = new JPanel(new GridBagLayout());
        btnThem = Khung.taoButton("Thêm");
        btnSua = Khung.taoButton("Sửa");
        btnXoa = Khung.taoButton("Xóa");
        btnBoQua = Khung.taoButton("Bỏ qua");

        pnlTable = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh sách", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlTim = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Tìm kiếm", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlTim2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        txtTim = new JTextField(20);
        btnTim = Khung.taoButton("Tìm kiếm");
        Class classNXB[] = {Integer.class, String.class, Integer.class, String.class};
        String tenCotNXB[] = {"STT", "Tên nhà xuất bản", "Số Điện Thoại", "Địa Chỉ"};
        tblNXB = Khung.taoTable(classNXB, tenCotNXB);
        scrNXB = Khung.taoJScrollPane(tblNXB, null, new Dimension(0, 100));
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

        tblNXB.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblNXB.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblNXB.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblNXB.getColumnModel().getColumn(3).setPreferredWidth(400);
    }

    @Override
    public void add() {
        JComponent b[] = {lblTenNXB, lblSDT, lblDiaChi, txtTenNXB, txtSDT, txtDiaChi};
        Khung.addChung(pnlForm, gbc, b, 3, 2, insets, 17);

        JComponent c[] = {btnThem, btnSua, btnXoa, btnBoQua};
        Khung.addChung(pnlNutChucNang, gbc, c, 4, 1, insets, GridBagConstraints.CENTER);

        JComponent d[] = {pnlForm, pnlNutChucNang};
        Khung.addChung(pnlForm_NutChucNang, gbc, d, 1, 2, insets, 17);

        JComponent a[] = {txtTim, btnTim};
        Khung.addChung(pnlTim2, null, a, 1, 2, null, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlTim.add(pnlTim2, gbc);

        pnlTim_ChucNang.add(pnlTim);
        pnlTim_ChucNang.add(pnlForm_NutChucNang);

        pnlTable.add(scrNXB);

        pnlTieuDe_Tim_ChucNang.add(lblTieuDe, BorderLayout.NORTH);
        pnlTieuDe_Tim_ChucNang.add(pnlTim_ChucNang, BorderLayout.CENTER);

        pnlThan.add(pnlTieuDe_Tim_ChucNang, BorderLayout.NORTH);
        pnlThan.add(pnlTable, BorderLayout.CENTER);
    }

    @Override
    public void event() {
        editOff();
        fillToTable();
        selectionModel = tblNXB.getSelectionModel();

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                int b = tblNXB.getSelectedRow();
                if (b != -1) {
                    showItem(b);
                }
            }
        });

        selectionModel = tblNXB.getSelectionModel();

        tblNXB.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int i = tblNXB.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    showItem(i);
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().equals("Thêm")) {
                    editOn();
                    clear();

                    txtTim.setEnabled(false);
                    btnTim.setEnabled(false);
                    tblNXB.setEnabled(false);
                    tblNXB.clearSelection();
                    tblNXB.setBackground(Color.WHITE);
                    btnThem.setText("Lưu");
                    btnBoQua.setText("Hủy");
                    btnSua.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnBoQua.setForeground(Color.RED);
                } else {
                    insert();
                    if (addSuccessful) {
                        editOff();
                        txtTim.setEnabled(true);
                        btnTim.setEnabled(true);
                        btnThem.setText("Thêm");
                        btnBoQua.setText("Bỏ Qua");
                        btnSua.setEnabled(true);
                        btnXoa.setEnabled(true);
                        tblNXB.setEnabled(true);
                        tblNXB.setBackground(Color.WHITE);
                        int rowIndex = tblNXB.getRowCount() - 1;
                        int columnIndex = 0;
                        boolean includeSpacing = true;
                        Rectangle cellRect = tblNXB.getCellRect(rowIndex, columnIndex, includeSpacing);
                        tblNXB.scrollRectToVisible(cellRect);
                        tblNXB.setRowSelectionInterval(tblNXB.getRowCount() - 1, tblNXB.getRowCount() - 1);
                    }
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Chuyên Đề Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblNXB.getSelectedRow();
                    if (btnSua.getText().equals("Sửa")) {
                        editOn();
                        btnSua.setText("Lưu");
                        btnBoQua.setText("Hủy");
                        btnBoQua.setForeground(Color.RED);
                        btnThem.setEnabled(false);
                        btnXoa.setEnabled(false);
                    } else {
                        update();
                        if (addSuccessful) {
                            editOff();
                            btnSua.setText("Sửa");
                            btnThem.setEnabled(true);
                            btnXoa.setEnabled(true);
                            btnBoQua.setText("Bỏ Qua");
                            btnBoQua.setEnabled(true);
                            tblNXB.setRowSelectionInterval(selected, selected);
                        }
                    }
                }
            }
        }
        );

        btnXoa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Nhà Xuất Bản Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblNXB.getSelectedRow();
                    int rowIndex = tblNXB.getRowCount() - 1;
                    delete();
                    if (selected == rowIndex) {
                        tblNXB.setRowSelectionInterval(tblNXB.getRowCount() - 1, tblNXB.getRowCount() - 1);
                    } else {
                        tblNXB.setRowSelectionInterval(selected, selected);
                    }
                }
            }
        });

        btnBoQua.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int selected = tblNXB.getSelectedRow();
                if (btnBoQua.getText().equals("Bỏ Qua")) {
                    clear();
                    txtTim.setEnabled(true);
                    btnTim.setEnabled(true);
                } else {
                    clear();
                    editOff();
                    txtTim.setEnabled(true);
                    btnTim.setEnabled(true);
                    btnThem.setText("Thêm");
                    btnSua.setText("Sửa");
                    btnBoQua.setText("Bỏ Qua");
                    btnBoQua.setForeground(Color.blue);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnXoa.setEnabled(true);
                    tblNXB.setEnabled(true);
                    tblNXB.setBackground(Color.WHITE);
                    if (selected == -1) {
                        tblNXB.clearSelection();
                    }
                }
            }
        }
        );

        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                currentSearch = 0;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                currentSearch = 0;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                currentSearch = 0;
            }
        });

        txtTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    search();
            }
        });
    }

    @Override
    public void fillToTable() {
        listNXB = NXBDAO.select();
        defaultTableModel = (DefaultTableModel) tblNXB.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listNXB.size(); i++) {
            Object row[] = new Object[4];
            row[0] = i + 1;
            row[1] = listNXB.get(i).getTenNXB();
            row[2] = listNXB.get(i).getSDT();
            row[3] = listNXB.get(i).getDiaChi();
            defaultTableModel.addRow(row);
        }
    }

    @Override
    public void showItem(int a) {
        int selectNXB = tblNXB.getSelectedRow();
        txtTenNXB.setText(listNXB.get(selectNXB).getTenNXB());
        txtSDT.setText(listNXB.get(selectNXB).getSDT());
        txtDiaChi.setText(listNXB.get(selectNXB).getDiaChi());
    }

    @Override
    public void search() {
        for (int i = currentSearch; i < listNXB.size(); i++) {
            String searchText = Khung.removeAccent(txtTim.getText().trim()).toLowerCase();
            String comparingText = Khung.removeAccent(listNXB.get(i).getTenNXB()).toLowerCase();
            if (comparingText.contains(searchText)) {
                currentSearch = i + 1;
                int rowIndex = i;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblNXB.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblNXB.setRowSelectionInterval(i, i);
                tblNXB.scrollRectToVisible(cellRect);
                break;
            } else {
                currentSearch = 0;
                tblNXB.clearSelection();
            }
        }
    }

    @Override
    public void insert() {
        try {
            if (CheckNull() == true) {
                String ten = txtTenNXB.getText().trim();
                String sdt = txtSDT.getText().trim();
                NXB.setTenNXB(ten);
                NXB.setSDT(sdt);
                NXB.setDiaChi(txtDiaChi.getText());
                
                    ///check trùng tên nxb
                for (int i = 0; i < listNXB.size(); i++) {
                    if (listNXB.get(i).getTenNXB().equalsIgnoreCase(ten)) {
                        JOptionPane.showMessageDialog(null, "Nhà Xuất Bản Đã Tồn Tại !", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                        txtTenNXB.requestFocus();
                        return;
                    } 
                }
                NXBDAO.insert(NXB);
                addSuccessful = true;
                JOptionPane.showMessageDialog(null, "Thêm Thành Công !", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                fillToTable();
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
            System.out.println(e);
            addSuccessful = false;
        }
    }

    @Override
    public void update() {
        int x = tblNXB.getSelectedRow();
        try {
            NXB.setTenNXB(txtTenNXB.getText());
            NXB.setSDT(txtSDT.getText());
            NXB.setDiaChi(txtDiaChi.getText());
            NXB.setMaNXB((int) listNXB.get(x).getMaNXB());
            if (CheckNull() == true) {
                NXBDAO.update(NXB);
                JOptionPane.showMessageDialog(null, "Cập Nhập Thành Công !", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                addSuccessful = true;
                fillToTable();
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
            addSuccessful = false;
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Nhà Xuất Bản Đã Tồn Tại ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void delete() {
        int x = tblNXB.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Xoá Nhà Xuất Bản Này Không ?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            int ID = (int) listNXB.get(x).getMaNXB();
            Sach nxb = new SachDAO().findMaNXB(ID);
            System.out.println(nxb);
            if (nxb == null) {
                NXBDAO.delete(ID);
                JOptionPane.showMessageDialog(null, "Xóa thành công !", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                fillToTable();
            } else {
                System.out.println(nxb);
                JOptionPane.showMessageDialog(null, "Nhà Xuất Bản Đang Có Sách Không Thể Xoá ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clear() {
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtTenNXB.setText("");
        txtTim.setText("");
    }

    public void editOn() {
        txtTenNXB.setEnabled(true);
        txtDiaChi.setEnabled(true);
        txtSDT.setEnabled(true);
    }

    public void editOff() {
        txtTenNXB.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtSDT.setEnabled(false);
    }

    public boolean CheckNull() {
        if (txtTenNXB.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Tên Nhà Xuất Bản ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtTenNXB.requestFocus();
            return false;
        } else if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Số Điện Thoại Nhà Xuất Bản ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtSDT.requestFocus();
            return false;
        } else if (!txtSDT.getText().matches("0[0-9]{9}")) {
            JOptionPane.showMessageDialog(null, "Sai Định Dạng Số Điện Thoại Mời Bạn Nhập Lại ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtSDT.requestFocus();
            return false;
        } else if (txtDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Địa Chỉ Nhà Xuất Bản ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtDiaChi.requestFocus();
            return false;
        }
        return true;
    }
}
