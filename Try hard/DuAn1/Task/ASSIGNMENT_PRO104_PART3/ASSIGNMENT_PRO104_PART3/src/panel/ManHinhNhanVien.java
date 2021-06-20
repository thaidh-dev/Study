package panel;

import dao.NhanVienDAO;
import helper.MailHelper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import object.NhanVien;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.mail.MessagingException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManHinhNhanVien implements Khung {

    public JPanel pnlThan, pnlForm_NutChucNang, pnlForm, pnlNutChucNang, pnlTable, pnlTim, pnlTim2, pnlTim_ChucNang, pnlTieuDe_Tim_ChucNang;
    JLabel lblTieuDe, lblHoTen, lblChucVu, lblTaiKhoan, lblMaNV, lblEmail;
    JTextField txtTim, txtMa, txtEmail, txtHoTen, txtTaiKhoan;
    public JButton btnTim, btnThem, btnSua, btnXoa, btnBoQua;
    JTable tblNhanVien;
    JScrollPane scrNhanVien;
    JComboBox cboChucVu;
    Dimension d;
    Insets insets;
    GridBagConstraints gbc;
    DefaultTableModel defaultTableModel;
    private ListSelectionModel selectionModel;
    private MailHelper mailHelper = new MailHelper();
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private NhanVien NV = new NhanVien();
    private List<NhanVien> listNV;
    private int currentSearch = 0;
    private boolean addSuccessful = false;
    private NhanVien nvSelected = null;

    public ManHinhNhanVien() {
        taoDoiTuong();
        taoDang();
        add();
        event();

    }

    @Override
    public void taoDoiTuong() {
        d = new Dimension(90, 25);
        insets = new Insets(5, 5, 5, 5);

        pnlThan = new JPanel(new BorderLayout(0, 20));
        pnlTim_ChucNang = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlTieuDe_Tim_ChucNang = new JPanel(new BorderLayout(0, 10));
        pnlForm_NutChucNang = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblTieuDe = new JLabel("Quản lý nhân viên");

        pnlForm = new JPanel(new GridBagLayout());
        cboChucVu = new JComboBox(new String[]{"Nhân Viên", "Trưởng Kho"});
        lblHoTen = new JLabel("Họ tên");
        lblChucVu = new JLabel("Chức vụ");
        lblTaiKhoan = new JLabel("Tài khoản");
        lblMaNV = new JLabel("Mã Nhân Viên");
        lblEmail = new JLabel("Email");
        txtHoTen = new JTextField(20);
        txtTaiKhoan = new JTextField(20);
        txtMa = new JTextField(20);
        txtEmail = new JTextField(20);
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
        Class c[] = {Integer.class, String.class, String.class, String.class, String.class, String.class};
        String tenCot[] = {"STT", "Mã Nhân Viên", "Họ Tên", "Email", "Tài khoản", "Chức vụ"};
        tblNhanVien = Khung.taoTable(c, tenCot);
        scrNhanVien = Khung.taoJScrollPane(tblNhanVien, null, new Dimension(0, 100));
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

        cboChucVu.setPreferredSize(txtHoTen.getPreferredSize());

        tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(100);

    }

    @Override
    public void add() {
        JComponent b[] = {lblMaNV, lblHoTen, lblChucVu, lblTaiKhoan, lblEmail, txtMa, txtHoTen, cboChucVu, txtTaiKhoan, txtEmail};
        Khung.addChung(pnlForm, gbc, b, 5, 2, insets, 17);

        JComponent e[] = {btnThem, btnSua, btnXoa, btnBoQua};
        Khung.addChung(pnlNutChucNang, gbc, e, 4, 1, insets, GridBagConstraints.CENTER);

        JComponent d[] = {pnlForm, pnlNutChucNang};
        Khung.addChung(pnlForm_NutChucNang, gbc, d, 1, 2, insets, 17);

        JComponent a[] = {txtTim, btnTim};
        Khung.addChung(pnlTim2, null, a, 1, 2, null, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlTim.add(pnlTim2, gbc);

        pnlTim_ChucNang.add(pnlTim);
        pnlTim_ChucNang.add(pnlForm_NutChucNang);

        pnlTable.add(scrNhanVien);

        pnlTieuDe_Tim_ChucNang.add(lblTieuDe, BorderLayout.NORTH);
        pnlTieuDe_Tim_ChucNang.add(pnlTim_ChucNang, BorderLayout.CENTER);

        pnlThan.add(pnlTieuDe_Tim_ChucNang, BorderLayout.NORTH);
        pnlThan.add(pnlTable, BorderLayout.CENTER);
    }

    @Override
    public void event() {
        editOff();
        fillToTable();

        selectionModel = tblNhanVien.getSelectionModel();

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                int b = tblNhanVien.getSelectedRow();
                if (b != -1) {
                    showItem(b);
                }
            }
        });

        selectionModel = tblNhanVien.getSelectionModel();

        tblNhanVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int i = tblNhanVien.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    showItem(i);
                }
            }
        });

        // <editor-fold defaultstate="collapsed" desc="Gợi ý email ">
        txtEmail.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Set<String> s = new TreeSet<>();
                s.add("gmail.com");
                s.add("fpt.edu.vn");

                String text = txtEmail.getText();
                int lengthText = text.length();
                int lengthCheck = text.indexOf("@");
                int n = lengthText - lengthCheck - 1;

                for (String data : s) {
                    String str = "";

                    if (lengthCheck >= 0) {
                        str = text.substring(0, lengthCheck + 1);

                        for (int j = 0; j < n; j++) {
                            if (n < data.length()) {
                                str += data.charAt(j);
                            }
                        }
                    }

                    if (str.equals(text) && str.length() > 0 && e.getKeyCode() != 8) {
                        txtEmail.setText(text + data.substring(n));
                        txtEmail.setSelectionStart(lengthText);
                        txtEmail.setSelectionEnd(data.length() + lengthText);
                    }
                }
            }
        });
        // </editor-fold>

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().trim().equals("Thêm")) {
                    editOn();
                    clear();
                    txtTim.setEnabled(false);
                    btnTim.setEnabled(false);
                    tblNhanVien.setEnabled(false);
                    tblNhanVien.clearSelection();
                    tblNhanVien.setBackground(Color.WHITE);
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
                        tblNhanVien.setEnabled(true);
                        tblNhanVien.setBackground(Color.WHITE);
                        int rowIndex = tblNhanVien.getRowCount() - 1;
                        int columnIndex = 0;
                        boolean includeSpacing = true;
                        Rectangle cellRect = tblNhanVien.getCellRect(rowIndex, columnIndex, includeSpacing);
                        tblNhanVien.scrollRectToVisible(cellRect);
                        tblNhanVien.setRowSelectionInterval(tblNhanVien.getRowCount() - 1, tblNhanVien.getRowCount() - 1);
                    }
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Nhân Viên Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblNhanVien.getSelectedRow();
                    if (btnSua.getText().trim().equals("Sửa")) {
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
                            tblNhanVien.setRowSelectionInterval(selected, selected);
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
                    JOptionPane.showMessageDialog(btnTim, "Mời Bạn Chọn Một Nhân Viên Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblNhanVien.getSelectedRow();
                    int rowIndex = tblNhanVien.getRowCount() - 1;
                    delete();
                    if (selected == rowIndex) {
                        tblNhanVien.setRowSelectionInterval(tblNhanVien.getRowCount() - 1, tblNhanVien.getRowCount() - 1);
                    } else {
                        tblNhanVien.setRowSelectionInterval(selected, selected);
                    }
                }
            }
        });

        btnBoQua.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int selected = tblNhanVien.getSelectedRow();
                if (btnBoQua.getText().trim().equals("Bỏ Qua")) {
                    clear();
                    txtTim.setEnabled(true);
                    btnTim.setEnabled(true);
                } else {
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
                    tblNhanVien.setEnabled(true);
                    tblNhanVien.setBackground(Color.WHITE);
                    if (selected == -1) {
                        tblNhanVien.clearSelection();
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
        listNV = nvDAO.select();
        defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listNV.size(); i++) {
            Object row[] = new Object[6];
            row[0] = i + 1;
            row[1] = listNV.get(i).getMaNV();
            row[2] = listNV.get(i).getTenNV();
            row[3] = listNV.get(i).getEmail();
            row[4] = listNV.get(i).getTaiKhoan();
            row[5] = listNV.get(i).isChucVu() ? "Trưởng Kho" : "Nhân Viên";
            defaultTableModel.addRow(row);
        }
    }

    @Override
    public void showItem(int a) {
        int selectNV = tblNhanVien.getSelectedRow();
        txtMa.setText(listNV.get(selectNV).getMaNV());
        txtHoTen.setText(listNV.get(selectNV).getTenNV());
        if (listNV.get(selectNV).isChucVu() == true) {
            cboChucVu.setSelectedIndex(1);
        } else {
            cboChucVu.setSelectedIndex(0);
        }
        txtTaiKhoan.setText(listNV.get(selectNV).getTaiKhoan());
        txtEmail.setText(listNV.get(selectNV).getEmail());
    }

    @Override
    public void search() {
        for (int i = currentSearch; i < listNV.size(); i++) {
            String searchText = Khung.removeAccent(txtTim.getText().trim().trim()).toLowerCase();
            String comparingText = Khung.removeAccent(listNV.get(i).getTenNV()).toLowerCase();
            if (comparingText.contains(searchText)) {
                currentSearch = i + 1;
                int rowIndex = i;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblNhanVien.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblNhanVien.setRowSelectionInterval(i, i);
                tblNhanVien.scrollRectToVisible(cellRect);
                break;
            } else {
                currentSearch = 0;
                tblNhanVien.clearSelection();
            }
        }
    }

    @Override
    public void insert() {
        try {
            String tk = txtTaiKhoan.getText().trim();
            String ma = txtMa.getText().trim();
            NV.setMaNV(txtMa.getText().trim());
            NV.setTenNV(txtHoTen.getText().trim());
            NV.setTaiKhoan(tk);
            NV.setEmail(txtEmail.getText().trim());
            String chucvu = (String) cboChucVu.getSelectedItem();
            boolean role;
            if (chucvu.equals("Nhân Viên")) {
                NV.setChucVu(false);
                role = false;
            } else {
                NV.setChucVu(true);
                role = true;
            }
            if (CheckNull() == true) {
                //Check trùng tài khoản.
                for (int i = 0; i < listNV.size(); i++) {
                    if (listNV.get(i).getTaiKhoan().equalsIgnoreCase(tk)) {
                        JOptionPane.showMessageDialog(null, "Tài Khoản Này Đã Tồn Tại Mời Bạn Nhập Lại ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                        txtTaiKhoan.requestFocus();
                        return;
                    } else if (listNV.get(i).getMaNV().equalsIgnoreCase(ma)) {
                        JOptionPane.showMessageDialog(null, "Mã Nhân Viên Này Đã Tồn Tại Mời Bạn Nhập Lại ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                        txtMa.requestFocus();
                    }
                }
                if (role && scanForBoss()) {
                    JOptionPane.showMessageDialog(null, "Vị trí trưởng phòng đã có người sở hữu !", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                    cboChucVu.requestFocus();
                    return;
                } else {
                    //send mail thông báo đăng ký thành công
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String to = txtEmail.getText();
                            String sub = "Thông Báo Đăng Ký Tài Khoản Làm Việc Tại TienThanhLibrarian";
                            String content = "Xin Chào " + txtHoTen.getText() + "!"
                                    + "\nBạn đã được đăng ký tài khoản làm việc tại TienThanhLibrarian thành công. Chúng tôi gửi email xác nhận thông tin của bạn:"
                                    + "\n  "
                                    + "\n 1,  Mã Số Nhân Viên: " + txtMa.getText()
                                    + "\n 2,  Họ và tên: " + txtHoTen.getText()
                                    + "\n 3,  Chức Vụ: " + (NV.isChucVu() == true ? "Trưởng Kho" : "Nhân Viên")
                                    + "\n 4,  Tài Khoản Đăng Nhập: " + txtTaiKhoan.getText()
                                    + "\n 5,  Mật Khẩu Đăng Nhập: " + "123456"
                                    + "\n 6,  Email Bạn Đã Đăng Ký: " + txtEmail.getText()
                                    + "\n  "
                                    + "\n  Lưu ý: Bạn nhớ thay đổi mật khẩu của mình ở lần đầu tiên đăng nhập vào phần mềm quản lý thư viện."
                                    + "\n  "
                                    + "\n  Chúc bạn có một ngày làm việc thật nhiều hứng khởi và thành công\n"
                                    + "\n"
                                    + " Mọi thắc mắc vui lòng liên hệ lại cho chúng tôi qua email: mrrthanhbeo@gmail.com or Điện thoại số: 0976566686\n"
                                    + "\n"
                                    + "\n  "
                                    + "\n  Trân Trọng";
                            try {
                                mailHelper.sendMail(to, sub, content);
                            } catch (MessagingException ex) {
                                System.out.println(ex);
                            }
                        }
                    }).start();
                    nvDAO.insert(NV);
                    fillToTable();
                    JOptionPane.showMessageDialog(null, "Thêm Thành Công Mời Bạn Kiểm Tra Email Để Lấy Mật Khẩu Đăng Nhập !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                    addSuccessful = true;
                }
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Lỗi", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            addSuccessful = false;
        }
    }

    @Override
    public void update() {
        int x = tblNhanVien.getSelectedRow();

        try {
            NV.setTenNV(txtHoTen.getText().trim());
            NV.setTaiKhoan(txtTaiKhoan.getText().trim());
            NV.setEmail(txtEmail.getText().trim());
            String chucvu = (String) cboChucVu.getSelectedItem();
            boolean role;
            if (chucvu.equals("Nhân Viên")) {
                NV.setChucVu(false);
                role = false;
            } else {
                NV.setChucVu(true);
                role = true;
            }
            NV.setMaNV(txtMa.getText().trim());

            if (CheckNull() == true) {
                if (role && scanForBoss()) {
                    if (NV.getMaNV() == LoginPanel.idIsLogin && LoginPanel.isNhanVien == false) {
                        nvDAO.update(NV);
                        listNV.set(x, NV);
                        JOptionPane.showMessageDialog(null, "Cập nhập thành công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                        fillToTable();
                        editOff();
                        addSuccessful = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Vị trí trưởng phòng đã có người sở hữu !");
                    }
                } else {
                    nvDAO.update(NV);
                    listNV.set(x, NV);
                    JOptionPane.showMessageDialog(null, "Cập nhập thành công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                    fillToTable();
                    editOff();
                    addSuccessful = true;
                }
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void delete() {
        int x = tblNhanVien.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Xoá Nhân Viên Này Không?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            if (txtTaiKhoan.getText().trim().equals(LoginPanel.loggingAcc)) {
                JOptionPane.showMessageDialog(null, "Bạn Không Thể Xóa Tài Khoản Của Chính Mình Khi Đã Đăng Nhập!", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String ID = txtMa.getText();
            nvDAO.deleteNV(ID);
            JOptionPane.showMessageDialog(btnThem, "Xóa thành công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
            fillToTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void clear() {
        txtHoTen.setText("");
        txtMa.setText("");
        txtTaiKhoan.setText("");
        txtTim.setText("");
        txtEmail.setText("");
        cboChucVu.setSelectedIndex(0);
    }

    @Override
    public void editOn() {
        txtHoTen.setEnabled(true);
        txtMa.setEnabled(true);
        txtTaiKhoan.setEnabled(true);
        txtEmail.setEnabled(true);
        cboChucVu.setEnabled(true);
    }

    @Override
    public void editOff() {
        txtHoTen.setEnabled(false);
        txtMa.setEnabled(false);
        txtTaiKhoan.setEnabled(false);
        txtEmail.setEnabled(false);
        cboChucVu.setEnabled(false);
    }

    public boolean CheckNull() throws SQLException, Exception {
        if (txtTaiKhoan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Tài Khoản ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtTaiKhoan.requestFocus();
            return false;
        }
        if (txtMa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Mã Nhân Viên ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtMa.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Email ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
            JOptionPane.showMessageDialog(null, "Sai Định Dạng Email Mời Bạn Nhập Lại ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        if (mailHelper.checkEmail(txtEmail.getText()).equals("false")) {
            JOptionPane.showMessageDialog(null, "Email không tồn tại. Vui lòng kiểm tra lại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (nvDAO.checkTrungMail(txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Email " + NV.getEmail() + " đã được sử dụng để đăng kí tài khoản!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (txtHoTen.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Họ Tên Nhân Viên ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        }
        return true;
    }

    public boolean scanForBoss() {
        for (NhanVien nv : listNV) {
            if (nv.isChucVu()) {
                return true;
            }
        }
        return false;
    }
}
