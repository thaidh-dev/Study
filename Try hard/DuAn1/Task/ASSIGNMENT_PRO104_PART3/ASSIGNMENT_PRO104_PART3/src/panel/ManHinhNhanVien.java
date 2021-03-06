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
        pnlForm_NutChucNang = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Ch???c n??ng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblTieuDe = new JLabel("Qu???n l?? nh??n vi??n");

        pnlForm = new JPanel(new GridBagLayout());
        cboChucVu = new JComboBox(new String[]{"Nh??n Vi??n", "Tr?????ng Kho"});
        lblHoTen = new JLabel("H??? t??n");
        lblChucVu = new JLabel("Ch???c v???");
        lblTaiKhoan = new JLabel("T??i kho???n");
        lblMaNV = new JLabel("M?? Nh??n Vi??n");
        lblEmail = new JLabel("Email");
        txtHoTen = new JTextField(20);
        txtTaiKhoan = new JTextField(20);
        txtMa = new JTextField(20);
        txtEmail = new JTextField(20);
        gbc = new GridBagConstraints();

        pnlNutChucNang = new JPanel(new GridBagLayout());
        btnThem = Khung.taoButton("Th??m");
        btnSua = Khung.taoButton("S???a");
        btnXoa = Khung.taoButton("X??a");
        btnBoQua = Khung.taoButton("B??? qua");

        pnlTable = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh s??ch", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlTim = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "T??m ki???m", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlTim2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        txtTim = new JTextField(20);
        btnTim = Khung.taoButton("T??m ki???m");
        Class c[] = {Integer.class, String.class, String.class, String.class, String.class, String.class};
        String tenCot[] = {"STT", "M?? Nh??n Vi??n", "H??? T??n", "Email", "T??i kho???n", "Ch???c v???"};
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

        // <editor-fold defaultstate="collapsed" desc="G???i ?? email ">
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
                if (btnThem.getText().trim().equals("Th??m")) {
                    editOn();
                    clear();
                    txtTim.setEnabled(false);
                    btnTim.setEnabled(false);
                    tblNhanVien.setEnabled(false);
                    tblNhanVien.clearSelection();
                    tblNhanVien.setBackground(Color.WHITE);
                    btnThem.setText("L??u");
                    btnBoQua.setText("H???y");
                    btnSua.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnBoQua.setForeground(Color.RED);
                } else {
                    insert();
                    if (addSuccessful) {
                        editOff();
                        txtTim.setEnabled(true);
                        btnTim.setEnabled(true);
                        btnThem.setText("Th??m");
                        btnBoQua.setText("B??? Qua");
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
                    JOptionPane.showMessageDialog(null, "M???i B???n Ch???n M???t Nh??n Vi??n Trong B???ng D??? li???u ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblNhanVien.getSelectedRow();
                    if (btnSua.getText().trim().equals("S???a")) {
                        editOn();
                        btnSua.setText("L??u");
                        btnBoQua.setText("H???y");
                        btnBoQua.setForeground(Color.RED);
                        btnThem.setEnabled(false);
                        btnXoa.setEnabled(false);
                    } else {
                        update();
                        if (addSuccessful) {
                            editOff();
                            btnSua.setText("S???a");
                            btnThem.setEnabled(true);
                            btnXoa.setEnabled(true);
                            btnBoQua.setText("B??? Qua");
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
                    JOptionPane.showMessageDialog(btnTim, "M???i B???n Ch???n M???t Nh??n Vi??n Trong B???ng D??? li???u ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
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
                if (btnBoQua.getText().trim().equals("B??? Qua")) {
                    clear();
                    txtTim.setEnabled(true);
                    btnTim.setEnabled(true);
                } else {
                    editOff();
                    txtTim.setEnabled(true);
                    btnTim.setEnabled(true);
                    btnThem.setText("Th??m");
                    btnSua.setText("S???a");
                    btnBoQua.setText("B??? Qua");
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
            row[5] = listNV.get(i).isChucVu() ? "Tr?????ng Kho" : "Nh??n Vi??n";
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
            if (chucvu.equals("Nh??n Vi??n")) {
                NV.setChucVu(false);
                role = false;
            } else {
                NV.setChucVu(true);
                role = true;
            }
            if (CheckNull() == true) {
                //Check tr??ng t??i kho???n.
                for (int i = 0; i < listNV.size(); i++) {
                    if (listNV.get(i).getTaiKhoan().equalsIgnoreCase(tk)) {
                        JOptionPane.showMessageDialog(null, "T??i Kho???n N??y ???? T???n T???i M???i B???n Nh???p L???i ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                        txtTaiKhoan.requestFocus();
                        return;
                    } else if (listNV.get(i).getMaNV().equalsIgnoreCase(ma)) {
                        JOptionPane.showMessageDialog(null, "M?? Nh??n Vi??n N??y ???? T???n T???i M???i B???n Nh???p L???i ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                        txtMa.requestFocus();
                    }
                }
                if (role && scanForBoss()) {
                    JOptionPane.showMessageDialog(null, "V??? tr?? tr?????ng ph??ng ???? c?? ng?????i s??? h???u !", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                    cboChucVu.requestFocus();
                    return;
                } else {
                    //send mail th??ng b??o ????ng k?? th??nh c??ng
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String to = txtEmail.getText();
                            String sub = "Th??ng B??o ????ng K?? T??i Kho???n L??m Vi???c T???i TienThanhLibrarian";
                            String content = "Xin Ch??o " + txtHoTen.getText() + "!"
                                    + "\nB???n ???? ???????c ????ng k?? t??i kho???n l??m vi???c t???i TienThanhLibrarian th??nh c??ng. Ch??ng t??i g???i email x??c nh???n th??ng tin c???a b???n:"
                                    + "\n  "
                                    + "\n 1,  M?? S??? Nh??n Vi??n: " + txtMa.getText()
                                    + "\n 2,  H??? v?? t??n: " + txtHoTen.getText()
                                    + "\n 3,  Ch???c V???: " + (NV.isChucVu() == true ? "Tr?????ng Kho" : "Nh??n Vi??n")
                                    + "\n 4,  T??i Kho???n ????ng Nh???p: " + txtTaiKhoan.getText()
                                    + "\n 5,  M???t Kh???u ????ng Nh???p: " + "123456"
                                    + "\n 6,  Email B???n ???? ????ng K??: " + txtEmail.getText()
                                    + "\n  "
                                    + "\n  L??u ??: B???n nh??? thay ?????i m???t kh???u c???a m??nh ??? l???n ?????u ti??n ????ng nh???p v??o ph???n m???m qu???n l?? th?? vi???n."
                                    + "\n  "
                                    + "\n  Ch??c b???n c?? m???t ng??y l??m vi???c th???t nhi???u h???ng kh???i v?? th??nh c??ng\n"
                                    + "\n"
                                    + " M???i th???c m???c vui l??ng li??n h??? l???i cho ch??ng t??i qua email: mrrthanhbeo@gmail.com or ??i???n tho???i s???: 0976566686\n"
                                    + "\n"
                                    + "\n  "
                                    + "\n  Tr??n Tr???ng";
                            try {
                                mailHelper.sendMail(to, sub, content);
                            } catch (MessagingException ex) {
                                System.out.println(ex);
                            }
                        }
                    }).start();
                    nvDAO.insert(NV);
                    fillToTable();
                    JOptionPane.showMessageDialog(null, "Th??m Th??nh C??ng M???i B???n Ki???m Tra Email ????? L???y M???t Kh???u ????ng Nh???p !", "Th??nh C??ng", JOptionPane.INFORMATION_MESSAGE);
                    addSuccessful = true;
                }
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "L???i", JOptionPane.ERROR_MESSAGE);
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
            if (chucvu.equals("Nh??n Vi??n")) {
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
                        JOptionPane.showMessageDialog(null, "C???p nh???p th??nh c??ng!", "Th??ng B??o", JOptionPane.INFORMATION_MESSAGE);
                        fillToTable();
                        editOff();
                        addSuccessful = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "V??? tr?? tr?????ng ph??ng ???? c?? ng?????i s??? h???u !");
                    }
                } else {
                    nvDAO.update(NV);
                    listNV.set(x, NV);
                    JOptionPane.showMessageDialog(null, "C???p nh???p th??nh c??ng!", "Th??ng B??o", JOptionPane.INFORMATION_MESSAGE);
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
        int rep = JOptionPane.showConfirmDialog(null, "B???n C?? Mu???n Xo?? Nh??n Vi??n N??y Kh??ng?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            if (txtTaiKhoan.getText().trim().equals(LoginPanel.loggingAcc)) {
                JOptionPane.showMessageDialog(null, "B???n Kh??ng Th??? X??a T??i Kho???n C???a Ch??nh M??nh Khi ???? ????ng Nh???p!", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String ID = txtMa.getText();
            nvDAO.deleteNV(ID);
            JOptionPane.showMessageDialog(btnThem, "X??a th??nh c??ng!", "Th??ng B??o", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "M???i B???n Nh???p T??i Kho???n ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            txtTaiKhoan.requestFocus();
            return false;
        }
        if (txtMa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "M???i B???n Nh???p M?? Nh??n Vi??n ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            txtMa.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "M???i B???n Nh???p Email ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
            JOptionPane.showMessageDialog(null, "Sai ?????nh D???ng Email M???i B???n Nh???p L???i ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        if (mailHelper.checkEmail(txtEmail.getText()).equals("false")) {
            JOptionPane.showMessageDialog(null, "Email kh??ng t???n t???i. Vui l??ng ki???m tra l???i!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (nvDAO.checkTrungMail(txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Email " + NV.getEmail() + " ???? ???????c s??? d???ng ????? ????ng k?? t??i kho???n!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (txtHoTen.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "M???i B???n Nh???p H??? T??n Nh??n Vi??n ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
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
