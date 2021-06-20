package panel;

import dao.*;
import helper.DateHelper;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javazoom.jl.player.Player;
import object.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

public class ManHinhChinh implements Khung {

    private JFrame win;
    private JMenuBar mnu;
    private JMenu menuFile, menuKhoSach, menuNhanVien, menuTacGia, menuNXB, menuMuonTra, menuThongKe;
    private JMenuItem mniDangXuat, mniUser, mniDoiMK;
    private JPanel pnlThemTG, pnlCardLayout, pnlThan, pnlDuoiTieuDe, pnlChuDe, pnlChuDe2, pnlChuDeTim, pnlChuDeTable, pnlSach, pnlSach2, pnlSachForm, pnlSachForm2, pnlSachForm3, pnlSachForm4, pnlSachTimVaChucNang, pnlSachTim, pnlSachChucNang, pnlSachTable, pnlSachTable2, pnlTacGia, pnlTimTG, pnlCheckTG;
    private JLabel lblTieuDe, lblChuDeTim, lblSachChuDe, lblSachTieuDe, lblNhaXuatBan, lblNgonNgu, lblTacGia, lblNamXuatBan, lblSoBanLuu, lblGia, lblSoTrang, lblSachTim, lblTacGiaTim;
    private JComboBox cboChuDe, cboNhaXuatBan, cboNgonNgu;
    private JTextField txtChuDeTim, txtTieuDe, txtNamXuatBan, txtSoTrang, txtSoBanLuu, txtGia, txtSachTim, txtTacGiaTim, txtMaTacGia;
    private JButton btnChuDeTim, btnSachTim, btnSachThem, btnSachSua, btnSachXoa, btnSachBoQua, btnXacNhanTG, btnHuyTG, btnChonTG, btnThemTG;
    private JCheckBox chkChuDe, chkSach;
    private JScrollPane scrChuDe, scrSach, scrTacGia, scrTxtTacGia;
    private JTable tblChuDe, tblSach, tblTacGia;
    private JTextArea txtTacGia;
    private DefaultTableModel defaultTableModel;
    private ListSelectionModel selectionModel;
    private DefaultComboBoxModel model;
    private GridBagConstraints gbc;
    private CardLayout cardLayout;
    private ManHinhNhanVien nv;
    private ManHinhNXB nxb;
    private ManHinhTacGia tg;
    private ManHinhThongKe tk;
    private PhieuTraMuon pmt;
    private SachDAO sachDAO = new SachDAO();
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private TacGiaDAO tgDAO = new TacGiaDAO();
    private Sach sach = new Sach();
    private ChuDe chuDe = new ChuDe();
    private TacGia tacgia = new TacGia();
    public JDialog tacgiaDialog, loginJDialog, themTGJDialog;
    private List<Sach> listSach;
    private List<ChuDe> listCD;
    private List<TacGia> listTG;
    private List<NhanVien> listNV;
    private int currentSearch = 0;
    private int currentSearchCD = 0;
    private int currentSearchTG = 0;
    private boolean addSuccessful = false;
    private String[] arrTG;
    private static final int BOOLEAN_COLUMN = 4;
    List<String> listCboNXB = new ArrayList();
    List<String> listCboChuDe = new ArrayList();
    List<String> listCboNgonNgu = new ArrayList();
    private LoginPanel loginPanel = new LoginPanel();
    private ThemTG themTGPanel;

    public ManHinhChinh() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }

    @Override
    public void taoDoiTuong() {

        //<editor-fold defaultstate="collapsed" desc=" Cửa Sổ Chính">
        win = new JFrame("Hệ Thống Quản Lý Thư Viện");
        win.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));

        gbc = new GridBagConstraints();

        // menubar
        mnu = new JMenuBar();
        menuFile = new JMenu("Tuỳ Chọn");
        menuKhoSach = new JMenu("Kho sách");
        menuNhanVien = new JMenu("Nhân viên");
        menuTacGia = new JMenu("Tác Giả");
        menuNXB = new JMenu("Nhà xuất bản");
        menuMuonTra = new JMenu("Mượn Trả Sách");
        menuThongKe = new JMenu("Thống kê");
        mniDangXuat = new JMenuItem("Đăng xuất");
        mniDoiMK = new JMenuItem("Đổi Mật Khẩu");
        mniUser = new JMenuItem("Thành Béo");
        mniUser.setFont(new Font("Tahoma", 2, 19));
        mniUser.setForeground(Color.RED);

        // màn hình chính
        lblTieuDe = new JLabel("Quản lý thư viện");
        pnlThan = new JPanel(new BorderLayout(0, 5));
        pnlDuoiTieuDe = new JPanel(new BorderLayout(20, 0));

        // chủ đề
        pnlChuDe = Khung.taoJPanel(new CardLayout(5, 5), new SoftBevelBorder(SoftBevelBorder.RAISED));
        pnlChuDe2 = new JPanel(new BorderLayout(0, 20));
        pnlChuDeTim = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Tìm kiếm", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblChuDeTim = new JLabel("Tìm Kiếm");
        txtChuDeTim = new JTextField(20);
        btnChuDeTim = Khung.taoButton("Tìm kiếm");

        pnlChuDeTable = new JPanel(new BorderLayout());
        chkChuDe = new JCheckBox("Chỉ hiển thị những chủ đề đã có sách");
        Class classChuDe[] = {Integer.class, String.class};
        String tenCotChuDe[] = {"STT", "Chủ đề"};
        tblChuDe = Khung.taoTable(classChuDe, tenCotChuDe);
        scrChuDe = Khung.taoJScrollPane(tblChuDe, null, new Dimension());

        // sách
        pnlSach = new JPanel(new BorderLayout(0, 20));
        pnlSach2 = new JPanel(new BorderLayout(0, 10));
        pnlSachForm = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Thông tin", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlSachForm2 = new JPanel(new GridBagLayout());
        pnlSachForm3 = new JPanel(new GridBagLayout());
        pnlSachForm4 = new JPanel(new GridBagLayout());

        tblTacGia = createTable();
        tblTacGia.getModel().addTableModelListener(new CheckBoxModelListener());
        scrTacGia = Khung.taoJScrollPane(tblTacGia, null, new Dimension(330, 125));
        lblSachChuDe = new JLabel("Chủ đề");
        lblSachTieuDe = new JLabel("Tiêu đề");
        lblNhaXuatBan = new JLabel("Nhà xuất bản");
        lblNgonNgu = new JLabel("Ngôn ngữ");
        lblTacGia = new JLabel("Tác giả");
        lblNamXuatBan = new JLabel("Năm xuất bản");
        lblSoTrang = new JLabel("Số trang");
        cboChuDe = new JComboBox();
        cboNhaXuatBan = new JComboBox();
        cboNgonNgu = new JComboBox<>();
        String NN[] = {"Tiếng Việt", "Tiếng Anh", "Tiếng Trung", "Tiếng Nga", "Tiếng Hàn"};
        for (int i = 0; i < NN.length; i++) {
            listCboNgonNgu.add(NN[i]);
        }
        cboNgonNgu.setModel(new DefaultComboBoxModel<>(NN));
        txtTieuDe = new JTextField(28);
        txtTacGia = new JTextArea();
        txtTacGia.setToolTipText("Click Chuột Trái Để Chọn Tác Giả. Click Chuột Phải Để Thêm Tác Giả.");
        scrTxtTacGia = Khung.taoJScrollPane(null, txtTacGia, new Dimension(330, 157));
        txtMaTacGia = new JTextField();
        txtMaTacGia.setPreferredSize(new Dimension(330, 30));
        txtNamXuatBan = new JTextField(10);

        lblSoBanLuu = new JLabel("Số bản lưu");
        lblGia = new JLabel("Giá tiền");
        txtSoTrang = new JTextField(10);
        txtSoBanLuu = new JTextField(10);
        txtGia = new JTextField(10);

        pnlSachTimVaChucNang = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlSachTim = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Tìm kiếm", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblSachTim = new JLabel("Tiêu đề");
        txtSachTim = new JTextField(20);
        btnSachTim = Khung.taoButton("Tìm kiếm");

        pnlSachChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        btnSachThem = Khung.taoButton("Thêm");
        btnSachSua = Khung.taoButton("Sửa");
        btnSachXoa = Khung.taoButton("Xóa");
        btnSachBoQua = Khung.taoButton("Bỏ qua");

        btnChonTG = new JButton("Chọn Tác Giả");
        btnThemTG = new JButton("Thêm Tác Giả");
        btnChonTG.setPreferredSize(new Dimension(140, 26));
        btnThemTG.setPreferredSize(new Dimension(140, 26));
        pnlThemTG = new JPanel(new FlowLayout(-15, 25, 0));

        pnlSachTable = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh sách", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlSachTable2 = new JPanel(new BorderLayout());

        chkSach = new JCheckBox("Chỉ hiển thị sách vẫn còn trong thư viện");
        Class classSach[] = {Integer.class, String.class, String.class, Integer.class};
        String tenCotSach[] = {"STT", "Tiêu đề", "Tác giả", "Số bản lưu"};
        tblSach = Khung.taoTable(classSach, tenCotSach);
        scrSach = Khung.taoJScrollPane(tblSach, null, new Dimension(0, 380));

        cardLayout = new CardLayout(10, 10);
        pnlCardLayout = new JPanel(cardLayout);

        nv = new ManHinhNhanVien();
        nxb = new ManHinhNXB();
        tg = new ManHinhTacGia();
        tk = new ManHinhThongKe();
        pmt = new PhieuTraMuon();

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" Cửa Sổ Đăng Nhập">
        loginJDialog = new JDialog();
        loginJDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));
        loginJDialog.setTitle("HỆ THỐNG QUẢN LÝ Thư Viện");
        loginJDialog.setLayout(new BorderLayout());
        loginJDialog.setMinimumSize(new Dimension(600, 480));
        loginJDialog.setResizable(false);
        loginJDialog.setLocationRelativeTo(null);
        loginJDialog.getContentPane().add(loginPanel, BorderLayout.CENTER);

        loginJDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                loginJDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        });
        //</editor-fold>     
        //<editor-fold defaultstate="collapsed" desc=" Cửa Sổ Chọn Tác Giả">
        tacgiaDialog = new JDialog();
        tacgiaDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));
        tacgiaDialog.setTitle("Chọn Tác Giả");
        tacgiaDialog.setLayout(new BorderLayout());
        tacgiaDialog.setMinimumSize(new Dimension(600, 463));
        tacgiaDialog.setResizable(false);
        tacgiaDialog.setLocationRelativeTo(null);
        pnlTacGia = new JPanel(new BorderLayout());
        lblTacGiaTim = new JLabel("Tìm Kiếm");
        lblTacGiaTim.setFont(new Font("tahoma", 1, 17));
        btnXacNhanTG = new JButton("Xác Nhận");
        btnXacNhanTG.setPreferredSize(new Dimension(150, 30));
        btnHuyTG = new JButton("Huỷ Chọn");
        btnHuyTG.setPreferredSize(new Dimension(150, 30));
        txtTacGiaTim = new JTextField();
        txtTacGiaTim.setPreferredSize(new Dimension(400, 30));

        btnXacNhanTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTacGiaTim.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Tác Giả ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                    txtTacGiaTim.requestFocus();
                } else {
                    tacgiaDialog.dispose();
                    txtTacGia.setText(txtTacGiaTim.getText());
                }
            }
        });

//        txtTacGiaTim.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                String tacgia = txtTacGiaTim.getText().trim();
//                filterTG(tacgia);
//            }
//        });
        btnHuyTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                huyToTableTG();
                txtTacGiaTim.setText("");
            }
        });
        pnlTimTG = new JPanel(new FlowLayout(10, 38, 10));
        pnlTimTG.add(lblTacGiaTim);
        pnlTimTG.add(txtTacGiaTim);

        pnlCheckTG = new JPanel(new FlowLayout(10, 100, 10));
        pnlCheckTG.add(btnXacNhanTG);
        pnlCheckTG.add(btnHuyTG);

        pnlTacGia.add(pnlTimTG, BorderLayout.NORTH);
        pnlTacGia.add(scrTacGia, BorderLayout.CENTER);
        pnlTacGia.add(pnlCheckTG, BorderLayout.SOUTH);
        tacgiaDialog.getContentPane().add(pnlTacGia, BorderLayout.CENTER);

        tacgiaDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                tacgiaDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" Cửa Sổ Thêm Tác Giả">
        themTGJDialog = new JDialog();
        themTGJDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));
        themTGJDialog.setTitle("                              Thêm Tác Giả");
        themTGJDialog.setLayout(new BorderLayout());
        themTGJDialog.setMinimumSize(new Dimension(390, 270));
        themTGJDialog.setResizable(false);
        themTGJDialog.setLocationRelativeTo(null);
        themTGPanel = new ThemTG();
        themTGJDialog.getContentPane().add(themTGPanel, BorderLayout.NORTH);

        themTGJDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                themTGJDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        //</editor-fold>
    }

    @Override
    public void taoDang() {
        win.setLayout(new BorderLayout(0, 5));

        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

        tblChuDe.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblChuDe.getColumnModel().getColumn(1).setPreferredWidth(300);
        cboChuDe.setPreferredSize(txtGia.getPreferredSize());
        cboChuDe.setEditable(true);
        cboNhaXuatBan.setPreferredSize(txtTieuDe.getPreferredSize());
        cboNhaXuatBan.setEditable(true);
        cboNgonNgu.setPreferredSize(txtGia.getPreferredSize());
        cboNgonNgu.setEditable(true);

        mnu.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        tblSach.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblSach.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblSach.getColumnModel().getColumn(2).setPreferredWidth(350);
        tblSach.getColumnModel().getColumn(3).setPreferredWidth(50);

        tblTacGia.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblTacGia.getColumnModel().getColumn(1).setPreferredWidth(170);
        tblTacGia.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblTacGia.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblTacGia.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblTacGia.setRowHeight(30);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tblTacGia.getColumnModel().getColumn(3).setCellRenderer(dtcr);
    }

    @Override
    public void add() {
        menuFile.add(mniUser);
        menuFile.add(mniDoiMK);
        menuFile.add(mniDangXuat);

        JComponent a[] = {menuFile, menuNhanVien, menuKhoSach, menuTacGia, menuNXB, menuMuonTra, menuThongKe};
        Khung.addChung(mnu, null, a, 1, 7, null, 0);

        JComponent b[] = {lblChuDeTim, txtChuDeTim, btnChuDeTim};
        Khung.addChung(pnlChuDeTim, null, b, 1, 3, null, 0);

        pnlChuDeTable.add(chkChuDe, BorderLayout.NORTH);
        pnlChuDeTable.add(scrChuDe, BorderLayout.CENTER);

        pnlChuDe2.add(pnlChuDeTim, BorderLayout.NORTH);
        pnlChuDe2.add(pnlChuDeTable, BorderLayout.CENTER);

        pnlChuDe.add(pnlChuDe2);

        JComponent c[] = {lblSachTieuDe, lblNhaXuatBan, txtTieuDe, cboNhaXuatBan};
        Khung.addChung(pnlSachForm2, gbc, c, 2, 2, new Insets(7, 7, 7, 10), 17);

        JComponent d[] = {lblSachChuDe, lblNamXuatBan, lblSoBanLuu, cboChuDe, txtNamXuatBan, txtSoBanLuu, lblNgonNgu, lblGia, lblSoTrang, cboNgonNgu, txtGia, txtSoTrang};
        Khung.addChung(pnlSachForm3, gbc, d, 3, 4, new Insets(7, 7, 7, 7), 17);

        JComponent e[] = {pnlSachForm2, pnlSachForm3};
        Khung.addChung(pnlSachForm, gbc, e, 2, 1, new Insets(0, 20, 0, 0), 17);

        JComponent f[] = {lblTacGia, scrTxtTacGia};
        Khung.addChung(pnlSachForm4, gbc, f, 1, 2, new Insets(7, 7, 7, 7), GridBagConstraints.NORTHWEST);

//        pnlThemTG.add(btnChonTG);
//        pnlThemTG.add(btnThemTG);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.insets = new Insets(0, 0, 0, 0);
//        gbc.anchor = GridBagConstraints.EAST;
//        pnlSachForm4.add(pnlThemTG, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 7);
        gbc.anchor = GridBagConstraints.EAST;
        pnlSachForm4.add(txtMaTacGia, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 25, 0, 7);
        pnlSachForm.add(pnlSachForm4, gbc);
        gbc.gridheight = 1;

        JComponent i[] = {lblSachTim, txtSachTim, btnSachTim};
        Khung.addChung(pnlSachTim, null, i, 1, 3, null, 0);
        JComponent k[] = {btnSachThem, btnSachSua, btnSachXoa, btnSachBoQua};
        Khung.addChung(pnlSachChucNang, null, k, 1, 4, null, 0);

        pnlSachTimVaChucNang.add(pnlSachTim);
        pnlSachTimVaChucNang.add(pnlSachChucNang);

        pnlSach2.add(pnlSachForm, BorderLayout.NORTH);
        pnlSach2.add(pnlSachTimVaChucNang, BorderLayout.SOUTH);

        pnlSachTable2.add(chkSach, BorderLayout.NORTH);
        pnlSachTable2.add(scrSach, BorderLayout.CENTER);

        pnlSachTable.add(pnlSachTable2);

        pnlSach.add(pnlSach2, BorderLayout.NORTH);
        pnlSach.add(pnlSachTable, BorderLayout.CENTER);

        pnlDuoiTieuDe.add(pnlChuDe, BorderLayout.WEST);
        pnlDuoiTieuDe.add(pnlSach, BorderLayout.CENTER);

        pnlThan.add(lblTieuDe, BorderLayout.NORTH);
        pnlThan.add(pnlDuoiTieuDe, BorderLayout.CENTER);

        pnlCardLayout.add(pnlThan, "1");
        pnlCardLayout.add(nv.pnlThan, "2");
        pnlCardLayout.add(nxb.pnlThan, "3");
        pnlCardLayout.add(tg.pnlThan, "4");
        pnlCardLayout.add(pmt.pnlThan, "5");
        pnlCardLayout.add(tk.pnlThan, "6");

        win.add(mnu, BorderLayout.NORTH);
        win.add(pnlCardLayout, BorderLayout.CENTER);

        win.pack();
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void event() {

        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Đăng Nhập">
        loginPanel.btnDangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (loginPanel.authentication()) {
                    loginJDialog.dispose();
                    win.setVisible(true);
                    if (loginPanel.isNhanVien) {
                        mniUser.setText(loginPanel.whoIsLogin);
                        menuKhoSach.isSelected();
                        menuKhoSach.setSelected(true);
                        cardLayout.show(pnlCardLayout, "1");
                        btnSachXoa.setVisible(false);
                        tg.btnXoa.setVisible(false);
                        nxb.btnXoa.setVisible(false);
                        menuNhanVien.setVisible(false);
                        menuMuonTra.setVisible(false);
                    } else {
                        mniUser.setText(loginPanel.whoIsLogin);
                        menuKhoSach.isSelected();
                        menuKhoSach.setSelected(true);
                        cardLayout.show(pnlCardLayout, "1");
                        btnSachXoa.setVisible(true);
                        tg.btnXoa.setVisible(true);
                        nxb.btnXoa.setVisible(true);
                        menuNhanVien.setVisible(true);
                        menuMuonTra.setVisible(true);
                    }
                    if (loginPanel.txtMatKhau.getText().equals("123456")) {
                        JOptionPane.showMessageDialog(null, "Đây là tài khoản mới được tạo với mật khẩu mặc định yêu cầu bạn đổi mật khẩu mới để được bảo mật hơn !");
                        new DoiMatKhau();
                    }
                } else if (LoginPanel.existedAccount) {
                    JOptionPane.showMessageDialog(loginJDialog, "Mật khẩu bạn nhập vào không đúng !");
                } else {
                    JOptionPane.showMessageDialog(loginJDialog, "Tài khoản bạn đã nhập không tồn tại !");
                }
            }
        });

        loginPanel.txtMatKhau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (loginPanel.authentication()) {
                        loginJDialog.dispose();
                        win.setVisible(true);
                        if (loginPanel.isNhanVien) {
                            mniUser.setText(loginPanel.whoIsLogin);
                            menuKhoSach.isSelected();
                            menuKhoSach.setSelected(true);
                            cardLayout.show(pnlCardLayout, "1");
                            btnSachXoa.setVisible(false);
                            tg.btnXoa.setVisible(false);
                            nxb.btnXoa.setVisible(false);
                            menuNhanVien.setVisible(false);
                            menuMuonTra.setVisible(false);
                        } else {
                            mniUser.setText(loginPanel.whoIsLogin);
                            menuKhoSach.isSelected();
                            menuKhoSach.setSelected(true);
                            cardLayout.show(pnlCardLayout, "1");
                            btnSachXoa.setVisible(true);
                            tg.btnXoa.setVisible(true);
                            nxb.btnXoa.setVisible(true);
                            menuNhanVien.setVisible(true);
                            menuMuonTra.setVisible(true);
                        }
                        if (loginPanel.txtMatKhau.getText().equals("123456")) {
                            JOptionPane.showMessageDialog(null, "Đây là tài khoản mới được tạo với mật khẩu mặc định yêu cầu bạn đổi mật khẩu mới để được bảo mật hơn !");
                            new DoiMatKhau();
                        }
                    } else if (LoginPanel.existedAccount) {
                        JOptionPane.showMessageDialog(loginJDialog, "Mật khẩu bạn nhập vào không đúng !");
                    } else {
                        JOptionPane.showMessageDialog(loginJDialog, "Tài khoản bạn đã nhập không tồn tại !");
                    }
                }
            }
        });

        loginPanel.lblQen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                new QuenMatKhau();
            }
        });

        loginPanel.lblQen.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                loginPanel.lblQen.setForeground(Color.red);
            }
        });

        loginPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                loginPanel.lblQen.setForeground(Color.gray);
            }
        });

        loginPanel.btnKetThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát Không ?", "Thoát Chương Trình",
                        JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //</editor-fold >
        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Bảng Menu">
        mniDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                win.dispose();
                loginJDialog.setVisible(true);
                loginPanel.txtTaiKhoan.setText("");
                loginPanel.txtMatKhau.setText("");
                loginPanel.txtTaiKhoan.requestFocus();
            }
        });

        mniDoiMK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DoiMatKhau();
            }
        });

        menuKhoSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!pnlCardLayout.getComponent(0).isShowing()) {
                    cardLayout.show(pnlCardLayout, "1");
                }
                fillComboNXB();
                fillToTable();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuKhoSach.setSelected(false);
                menuNhanVien.setSelected(false);
                menuNXB.setSelected(false);
                menuTacGia.setSelected(false);
                menuThongKe.setSelected(false);
            }
        ;
        });

        menuNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!pnlCardLayout.getComponent(1).isShowing()) {
                    cardLayout.show(pnlCardLayout, "2");
                }
//                MenuSelectionManager.defaultManager().clearSelectedPath();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuKhoSach.setSelected(false);
                menuNhanVien.setSelected(false);
                menuNXB.setSelected(false);
                menuTacGia.setSelected(false);
                menuThongKe.setSelected(false);
            }
        ;
        });

        menuNXB.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!pnlCardLayout.getComponent(2).isShowing()) {
                    cardLayout.show(pnlCardLayout, "3");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuKhoSach.setSelected(false);
                menuNhanVien.setSelected(false);
                menuNXB.setSelected(false);
                menuTacGia.setSelected(false);
                menuMuonTra.setSelected(false);
                menuThongKe.setSelected(false);
            }
        ;
        });
        
        menuTacGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!pnlCardLayout.getComponent(3).isShowing()) {
                    cardLayout.show(pnlCardLayout, "4");
                }
                fillToTableTG();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuKhoSach.setSelected(false);
                menuNhanVien.setSelected(false);
                menuNXB.setSelected(false);
                menuTacGia.setSelected(false);
                menuThongKe.setSelected(false);
            }
        ;
        });
        
        menuMuonTra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!pnlCardLayout.getComponent(4).isShowing()) {
                    cardLayout.show(pnlCardLayout, "5");
                }
                pmt.fillToTable();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuKhoSach.setSelected(false);
                menuNhanVien.setSelected(false);
                menuNXB.setSelected(false);
                menuTacGia.setSelected(false);
                menuMuonTra.setSelected(false);
                menuThongKe.setSelected(false);
            }
        ;
        });
        
        menuThongKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!pnlCardLayout.getComponent(5).isShowing()) {
                    cardLayout.show(pnlCardLayout, "6");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuKhoSach.setSelected(false);
                menuNhanVien.setSelected(false);
                menuNXB.setSelected(false);
                menuTacGia.setSelected(false);
                menuMuonTra.setSelected(false);
                menuThongKe.setSelected(false);
            }
        ;
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Load Dữ Liệu Lên Bảng">
        editOff();
        fillComboCD();
        fillComboNXB();
        fillToTable();
        fillToTableTG();
        fillTableCD();

        AutoCompleteDecorator.decorate(cboNhaXuatBan);
        AutoCompleteDecorator.decorate(cboChuDe);
        AutoCompleteDecorator.decorate(cboNgonNgu);

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Bảng Chủ Đề">
        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Click Bảng Chủ Đề">
        tblChuDe.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // <editor-fold defaultstate="collapsed" desc="Sự Kiện Click chuột phải ">
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int r = tblChuDe.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < tblChuDe.getRowCount()) {
                        tblChuDe.setRowSelectionInterval(r, r);
                    } else {
                        tblChuDe.clearSelection();
                    }
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Sự Kiện Click Chuột Trái">
                indexx = tblChuDe.getSelectedRow();
                if (indexx != -1) {
                    showCD(indexx);
                    locCD();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new ManHinhChinh.PopupmenuTblCD();
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
//                }
                //</editor-fold>
            }
        });
        //</editor-fold>

        btnChuDeTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btnChuDeTim.getText().equals("Thêm")) {
                    insertCD();
                    int rowIndex = tblChuDe.getRowCount() - 1;
                    int columnIndex = 0;
                    boolean includeSpacing = true;
                    Rectangle cellRect = tblChuDe.getCellRect(rowIndex, columnIndex, includeSpacing);
                    tblChuDe.scrollRectToVisible(cellRect);
                    tblChuDe.setRowSelectionInterval(tblChuDe.getRowCount() - 1, tblChuDe.getRowCount() - 1);
                } else if (btnChuDeTim.getText().equals("Sửa")) {
                    updateCD();
                } else if (btnChuDeTim.getText().equals("Tìm kiếm")) {
                    searchCD();
                }

            }
        });

        txtChuDeTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                currentSearchCD = 0;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                currentSearchCD = 0;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                currentSearchCD = 0;
            }
        });

        txtChuDeTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (btnChuDeTim.getText().equals("Tìm kiếm") || btnChuDeTim.getText().equals("Thêm")) {
                    searchCD();
                }
            }
        });

        txtChuDeTim.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new ManHinhChinh.PopupmenuTblCD();
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        tblChuDe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int CD = tblChuDe.getSelectedRow();
                    showCD(CD);
                }
            }
        });

        chkChuDe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chkChuDe.isSelected()) {
                    chekcBoxCD();
                } else {
                    fillTableCD();
                }
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Bảng Tác Giả">
//       keyPressed
//       
        txtTacGia.setLineWrap(true);
        txtTacGia.setEnabled(false);
        txtMaTacGia.setVisible(false);

        themTGPanel.btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themTGPanel.txtTen.requestFocus();
                tacgia.setTacGia(themTGPanel.txtTen.getText());
                boolean sex;
                if (themTGPanel.rdoNam.isSelected()) {
                    sex = true;
                } else {
                    sex = false;
                }
                tacgia.setGioiTinh(sex);
                tacgia.setNgaySinh(themTGPanel.txtNgaySinh.getDate());

                if (CheckNullTG() == true) {
                    tgDAO.insert(tacgia);
                    JOptionPane.showMessageDialog(null, "Thêm Thành Công");
                    themTGJDialog.dispose();
                    tacgiaDialog.setVisible(true);
                    fillToTableTG();
                    int rowIndex = tblTacGia.getRowCount() - 1;
                    int columnIndex = 0;
                    boolean includeSpacing = true;
                    Rectangle cellRect = tblTacGia.getCellRect(rowIndex, columnIndex, includeSpacing);
                    tblTacGia.scrollRectToVisible(cellRect);
                    tblTacGia.setRowSelectionInterval(rowIndex, rowIndex);
                } else {
                    themTGJDialog.setVisible(true);
                }
            }
        });

        themTGPanel.btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themTGJDialog.dispose();
            }
        });

        txtTacGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new ManHinhChinh.PopupmenuTblTG();
                    popup.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    tacgiaDialog.setVisible(true);
                }
            }
        });
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Bảng Sách">
        selectionModel = tblSach.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg) {
                int b = tblSach.getSelectedRow();
                if (b != -1) {
                    showItem(b);
                }
            }
        });

        tblSach.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int i = tblSach.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    showItem(i);
                }
            }
        });

        btnSachThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnSachThem.getText().equals("Thêm")) {
                    editOn();
                    clear();
                    huyToTableTG();
                    txtTieuDe.requestFocus();
                    txtSachTim.setEnabled(false);
                    btnSachTim.setEnabled(false);
                    tblSach.setEnabled(false);
                    tblSach.clearSelection();
                    tblSach.setBackground(Color.WHITE);
                    btnSachThem.setText("Lưu");
                    btnSachBoQua.setText("Hủy");
                    btnSachSua.setEnabled(false);
                    btnSachXoa.setEnabled(false);
                    btnSachBoQua.setForeground(Color.RED);
                } else {
                    insert();
                    if (addSuccessful) {
                        editOff();
                        txtSachTim.setEnabled(true);
                        btnSachTim.setEnabled(true);
                        btnSachThem.setText("Thêm");
                        btnSachSua.setEnabled(true);
                        btnSachXoa.setEnabled(true);
                        tblSach.setEnabled(true);
                        tblSach.setBackground(Color.WHITE);
                        huyToTableTG();
                        txtTacGiaTim.setText("");
                    }
                }
            }
        });

        btnSachSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Sách Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblSach.getSelectedRow();
                    if (btnSachSua.getText().equals("Sửa")) {
                        editOn();
                        fillToTableTG();
                        txtTieuDe.requestFocus();
                        btnSachSua.setText("Lưu");
                        btnSachBoQua.setText("Hủy");
                        btnSachBoQua.setForeground(Color.RED);
                        btnSachThem.setEnabled(false);
                        btnSachXoa.setEnabled(false);
                    } else {
                        update();
                        if (addSuccessful) {
                            editOff();
                            btnSachSua.setText("Sửa");
                            btnSachThem.setEnabled(true);
                            btnSachXoa.setEnabled(true);
                            btnSachBoQua.setText("Bỏ Qua");
                            btnSachBoQua.setEnabled(true);
                            tblSach.setRowSelectionInterval(selected, selected);
                            huyToTableTG();
                            txtTacGiaTim.setText("");
                        }
                    }
                }
            }
        });

        btnSachXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Sách Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblSach.getSelectedRow();
                    int rowIndex = tblSach.getRowCount() - 1;
                    delete();
                    if (selected == rowIndex) {
                        tblSach.setRowSelectionInterval(tblSach.getRowCount() - 1, tblSach.getRowCount() - 1);
                    } else {
                        tblSach.setRowSelectionInterval(selected, selected);
                        huyToTableTG();
                        txtTacGiaTim.setText("");
                    }
                }
            }
        });

        btnSachBoQua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = tblSach.getSelectedRow();
                if (btnSachBoQua.getText().equals("Bỏ Qua")) {
                    clear();
                    huyToTableTG();
                    tblSach.clearSelection();
                    txtSachTim.setEnabled(true);
                    btnSachTim.setEnabled(true);
                } else {
                    editOff();
                    txtSachTim.setEnabled(true);
                    btnSachTim.setEnabled(true);
                    btnSachThem.setText("Thêm");
                    btnSachSua.setText("Sửa");
                    btnSachBoQua.setText("Bỏ Qua");
                    btnSachThem.setEnabled(true);
                    btnSachSua.setEnabled(true);
                    btnSachXoa.setEnabled(true);
                    tblSach.setEnabled(true);
                    tblSach.setBackground(Color.WHITE);
                    if (selected == -1) {
                        tblSach.clearSelection();
                    }
                }
            }
        });

        chkSach.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (chkSach.isSelected()) {
                    checkBox();
                } else {
                    fillToTable();
                }
            }
        });

        txtSachTim.getDocument().addDocumentListener(new DocumentListener() {
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

        txtSachTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                search();
            }
        });
//</editor-fold>

    }

    //<editor-fold defaultstate="collapsed" desc="Xử lý Sự Kiện Bảng Sách">
    private JTable createTable() {
        String[] cols = {"Mã Tác Giả", "Tác Giả", "Giới Tính", "Ngày Sinh", "Chọn"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, cols) {
            @Override
            public Class getColumnClass(int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3) {
                    return String.class;
                }
                return column == BOOLEAN_COLUMN ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == BOOLEAN_COLUMN;
            }
        };
        JTable table = new JTable(model);
        table.setIntercellSpacing(new Dimension(10, 1));

        JTableHeader tableHeader = table.getTableHeader();
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        return table;
    }

//// Checkbox Tác Giả
    public class CheckBoxModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            int a = tblTacGia.getSelectedRow();
            if (tblTacGia.isValid() == true) {
                txtTacGiaTim.setText("");
                txtMaTacGia.setText("");
                for (int i = 0; i < tblTacGia.getRowCount(); i++) {
                    if (tblTacGia.getValueAt(i, 4) != null && String.valueOf(tblTacGia.getValueAt(i, 4)).equals("true")) {
                        txtTacGiaTim.setText(txtTacGiaTim.getText() + String.valueOf(tblTacGia.getValueAt(i, 1) + ", "));
                        txtMaTacGia.setText(txtMaTacGia.getText() + String.valueOf(tblTacGia.getValueAt(i, 0) + ", ").substring(4));
                    }
                }
                if (txtTacGiaTim.getText().length() != 0 && txtMaTacGia.getText().length() != 0) {
                    txtTacGiaTim.setText(txtTacGiaTim.getText().substring(0, txtTacGiaTim.getText().length() - 2));
                    txtMaTacGia.setText(txtMaTacGia.getText().substring(0, txtMaTacGia.getText().length() - 2));
                }
            }
        }
    }

    private void fillComboCD() {
        model = (DefaultComboBoxModel) cboChuDe.getModel();
        model.removeAllElements();
        cboChuDe.setSelectedItem("----- Chọn -----");
        try {
            listCD = cdDAO.select();
            for (ChuDe chuDe : listCD) {
                model.addElement(chuDe);
                listCboChuDe.add(chuDe.getTenCD());
            }
        } catch (Exception e) {
        }
    }

    private void fillComboNXB() {
        model = (DefaultComboBoxModel) cboNhaXuatBan.getModel();
        model.removeAllElements();
        cboNhaXuatBan.setSelectedItem("----- Chọn Nhà Xuất Bản -----");
        cboChuDe.setSelectedItem("----- Chọn -----");
        try {
            List<NhaXuatBan> listNXB = new NhaXuatBanDAO().select();
            for (NhaXuatBan NXB : listNXB) {
                model.addElement(NXB);
                listCboNXB.add(NXB.getTenNXB());
            }
        } catch (Exception e) {
        }
    }

    void fillToTableTG() {
        listTG = tgDAO.select();
        defaultTableModel = (DefaultTableModel) tblTacGia.getModel();

        String a = txtMaTacGia.getText();
        String b[] = a.split(",");

        defaultTableModel.setRowCount(0);

        for (TacGia tg : listTG) {
            Object row[] = new Object[5];
            row[0] = "TG00" + tg.getMaTG();
            row[1] = tg.getTacGia();
            row[2] = tg.isGioiTinh() ? "Nam" : "Nữ";
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String ngaySinh = format.format(tg.getNgaySinh());
            row[3] = ngaySinh;

            for (int i = 0; i < b.length; i++) {
                b[i] = b[i].trim();
                if (!b[i].equals("")) {
                    int c = Integer.parseInt(b[i]);
                    if (tg.getMaTG() == c) {
                        row[4] = true;
                        break;
                    }
                }
            }
            defaultTableModel.addRow(row);
        }
    }

    void huyToTableTG() {
        listTG = tgDAO.select();
        defaultTableModel = (DefaultTableModel) tblTacGia.getModel();

        defaultTableModel.setRowCount(0);

        for (TacGia tg : listTG) {
            Object row[] = new Object[5];
            row[0] = "TG00" + tg.getMaTG();
            row[1] = tg.getTacGia();
            row[2] = tg.isGioiTinh() ? "Nam" : "Nữ";
            row[3] = tg.getNgaySinh();
            row[4] = false;
            defaultTableModel.addRow(row);
        }
    }

    void filterTG(String TG) {
        defaultTableModel = (DefaultTableModel) tblTacGia.getModel();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
        tblTacGia.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(TG));
    }

    @Override
    public void fillToTable() {
        listSach = sachDAO.select();
        defaultTableModel = (DefaultTableModel) tblSach.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listSach.size(); i++) {
            Object row[] = new Object[4];
            row[0] = i + 1;
            row[1] = listSach.get(i).getTieuDe();
            row[2] = listSach.get(i).getTenTG();
            row[3] = listSach.get(i).getSoLuong();
            defaultTableModel.addRow(row);
        }
    }

    void locCD() {
        listSach = sachDAO.selectByKeyword(txtChuDeTim.getText());
        defaultTableModel = (DefaultTableModel) tblSach.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listSach.size(); i++) {
            Object row[] = new Object[4];
            row[0] = i + 1;
            row[1] = listSach.get(i).getTieuDe();
            row[2] = listSach.get(i).getTenTG();
            row[3] = listSach.get(i).getSoLuong();
            defaultTableModel.addRow(row);
        }
    }

    @Override
    public void showItem(int a) {
        int selectCD = (int) listSach.get(a).getMaCD();
        cboChuDe.setSelectedIndex(selectCD - 1);
        int selectNXB = (int) listSach.get(a).getMaNXB();
        cboNhaXuatBan.setSelectedIndex(selectNXB - 1);
        cboNgonNgu.setSelectedItem(listSach.get(a).getNgonNgu());
        txtTieuDe.setText(listSach.get(a).getTieuDe());
        txtNamXuatBan.setText(String.valueOf(listSach.get(a).getNXB()));
        txtSoBanLuu.setText(String.valueOf(listSach.get(a).getSoLuong()));
        txtSoTrang.setText(String.valueOf(listSach.get(a).getSoTrang()));
        txtGia.setText(String.valueOf(listSach.get(a).getGiaTien()));
        txtTacGia.setText(listSach.get(a).getTenTG());
        txtMaTacGia.setText(listSach.get(a).getTacGia());
    }

    @Override
    public void search() {
        for (int i = currentSearch; i < listSach.size(); i++) {
            String searchText = panel.Khung.removeAccent(txtSachTim.getText().trim()).toLowerCase();
            String comparingText = panel.Khung.removeAccent(listSach.get(i).getTieuDe()).toLowerCase();
            if (comparingText.contains(searchText)) {
                currentSearch = i + 1;
                int rowIndex = i;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblSach.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblSach.setRowSelectionInterval(i, i);
                tblSach.scrollRectToVisible(cellRect);
                break;
            } else {
                currentSearch = 0;
                tblSach.clearSelection();
            }
        }
    }

    @Override
    public void insert() {
        try {
            if (CheckNull() == true) {
                String tieuDe = txtTieuDe.getText();
                String namXB = txtNamXuatBan.getText();
                String soTrang = txtSoTrang.getText();
                String ngonNgu = (String) cboNgonNgu.getSelectedItem();
                String giaTien = txtGia.getText();

                sach.setTieuDe(tieuDe);
                sach.setTacGia(txtMaTacGia.getText());
                NhaXuatBan NXB = (NhaXuatBan) cboNhaXuatBan.getSelectedItem();
                sach.setMaNXB(NXB.getMaNXB());
                sach.setNgonNgu(ngonNgu);
                ChuDe chuDe = (ChuDe) cboChuDe.getSelectedItem();
                sach.setMaCD(chuDe.getMaCD());
                sach.setNXB(Integer.parseInt(namXB));
                sach.setSoTrang(Integer.parseInt(soTrang));
                sach.setGiaTien(Integer.parseInt(giaTien));

                for (int i = 0; i < listSach.size(); i++) {
                    if (listSach.get(i).getTieuDe().equalsIgnoreCase(tieuDe) && listSach.get(i).getNXB() == Integer.parseInt(namXB) && listSach.get(i).getSoTrang() == Integer.parseInt(soTrang) && listSach.get(i).getGiaTien() == Integer.parseInt(giaTien) && listSach.get(i).getNgonNgu().equalsIgnoreCase(ngonNgu)) {
                        String ma = listSach.get(i).getMaSach();
                        int soLuong = Integer.parseInt(listSach.get(i).getSoLuong());
                        int SLM = Integer.parseInt(txtSoBanLuu.getText()) + soLuong;
                        System.out.println("Mã Sach: " + ma);
                        System.out.println("Số Lượng Sach: " + soLuong);
                        sach.setSoLuong(String.valueOf(SLM));
                        sach.setMaSach(ma);
                        sachDAO.update(sach);
                        JOptionPane.showMessageDialog(null, "Sách: " + listSach.get(i).getTieuDe() + " Đã Có Trong Thư Viện. Số Lượng Sách Được Tăng Lên: " + SLM + " !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                        addSuccessful = true;
                        fillToTable();
                        int rowIndex = i;
                        int columnIndex = 0;
                        boolean includeSpacing = true;
                        Rectangle cellRect = tblSach.getCellRect(rowIndex, columnIndex, includeSpacing);
                        tblSach.setRowSelectionInterval(i, i);
                        tblSach.scrollRectToVisible(cellRect);
                        return;
                    }
                }

                sach.setSoLuong(txtSoBanLuu.getText());
                sachDAO.insert(sach);
                JOptionPane.showMessageDialog(null, "Thêm Thành Công !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                addSuccessful = true;
                fillToTable();
                fillToTableTG();
                int rowIndex = tblSach.getRowCount() - 1;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblSach.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblSach.scrollRectToVisible(cellRect);
                tblSach.setRowSelectionInterval(rowIndex, rowIndex);
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
        int x = tblSach.getSelectedRow();
        try {
            if (CheckNull() == true) {
                sach.setTieuDe(txtTieuDe.getText());
                sach.setTacGia(txtMaTacGia.getText());
                NhaXuatBan NXB = (NhaXuatBan) cboNhaXuatBan.getSelectedItem();
                System.out.println(NXB);
                sach.setMaNXB(NXB.getMaNXB());
                sach.setNgonNgu((String) cboNgonNgu.getSelectedItem());
                ChuDe chuDe = (ChuDe) cboChuDe.getSelectedItem();
                sach.setMaCD(chuDe.getMaCD());
                sach.setNXB(Integer.parseInt(txtNamXuatBan.getText()));
                sach.setSoTrang(Integer.parseInt(txtSoTrang.getText()));
                sach.setSoLuong(txtSoBanLuu.getText());
                sach.setGiaTien(Integer.parseInt(txtGia.getText()));
                sach.setMaSach(listSach.get(x).getMaSach());

                sachDAO.update(sach);
                fillToTable();
                fillToTableTG();
                JOptionPane.showMessageDialog(null, "Cập Nhật Thành Công !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                addSuccessful = true;
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
    public void delete() {
        int x = tblSach.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Xoá Sách Này Không ?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            int ID = Integer.parseInt(listSach.get(x).getMaSach());
            sachDAO.delete(ID);
            JOptionPane.showMessageDialog(null, "Xóa thành công !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
            fillToTable();
            fillComboCD();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void checkBox() {
        listSach = sachDAO.checkBox();
        defaultTableModel = (DefaultTableModel) tblSach.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listSach.size(); i++) {
            Object row[] = new Object[4];
            row[0] = i + 1;
            row[1] = listSach.get(i).getTieuDe();
            row[2] = listSach.get(i).getTenTG();
            row[3] = listSach.get(i).getSoLuong();
            defaultTableModel.addRow(row);
        }
    }

    public void clear() {
        txtTieuDe.setText("");
        txtSachTim.setText("");
        txtGia.setText("");
        txtSoTrang.setText("");
        txtSoBanLuu.setText("");
        tblSach.clearSelection();
        tblChuDe.clearSelection();
        txtNamXuatBan.setText("");
        tblTacGia.clearSelection();
        cboNhaXuatBan.setSelectedItem("----- Chọn Nhà Xuất Bản -----");
        cboChuDe.setSelectedItem("----- Chọn -----");
        cboNgonNgu.setSelectedItem("----- Chọn -----");
        txtTacGia.setText("");
        txtMaTacGia.setText("");
        txtTacGiaTim.setText("");
    }

    public void editOn() {
        txtTieuDe.setEnabled(true);
        txtGia.setEnabled(true);
        txtSoTrang.setEnabled(true);
        txtSoBanLuu.setEnabled(true);
        cboChuDe.setEnabled(true);
        cboNgonNgu.setEnabled(true);
        cboNhaXuatBan.setEnabled(true);
        txtNamXuatBan.setEnabled(true);
    }

    public void editOff() {
        txtTieuDe.setEnabled(false);
        txtGia.setEnabled(false);
        txtSoTrang.setEnabled(false);
        txtSoBanLuu.setEnabled(false);
        cboChuDe.setEnabled(false);
        cboNgonNgu.setEnabled(false);
        cboNhaXuatBan.setEnabled(false);
        txtNamXuatBan.setEnabled(false);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Xử Lý Sự Kiện Chủ Đề">
    void fillTableCD() {
        listCD = cdDAO.select();
        defaultTableModel = (DefaultTableModel) tblChuDe.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listCD.size(); i++) {
            Object row[] = new Object[2];
            row[0] = i + 1;
            row[1] = listCD.get(i).getTenCD();
            defaultTableModel.addRow(row);
        }
    }

    void showCD(int a) {
        int cd = tblChuDe.getSelectedRow();
        txtChuDeTim.setText(listCD.get(cd).getTenCD());
    }

    void deleteCD() {
        int x = tblChuDe.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Xoá Chủ Đề Này Không ?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            int ID = (int) listCD.get(x).getMaCD();
            Sach listS = new SachDAO().findID(ID);
            System.out.println(listS);
            if (listS == null) {
                cdDAO.delete(ID);
                JOptionPane.showMessageDialog(null, "Xóa thành công !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                fillTableCD();
                fillComboCD();
                btnChuDeTim.setText("Tìm kiếm");
            } else {
                System.out.println(listS);
                JOptionPane.showMessageDialog(null, "Chủ Đề Đang Có Sách Không Thể Xoá ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void insertCD() {
        chuDe.setTenCD(txtChuDeTim.getText());
        cdDAO.insert(chuDe);
        fillTableCD();
        fillComboCD();
        JOptionPane.showMessageDialog(null, "Thêm Thành Công");
        btnChuDeTim.setText("Tìm kiếm");
    }

    void updateCD() {
        int x = tblChuDe.getSelectedRow();
        int ID = (int) listCD.get(x).getMaCD();
        chuDe.setTenCD(txtChuDeTim.getText());
        chuDe.setMaCD(ID);
        cdDAO.update(chuDe);
        JOptionPane.showMessageDialog(null, "Sửa Thành Công");
        fillTableCD();
        fillComboCD();
        tblChuDe.setRowSelectionInterval(x, x);
        btnChuDeTim.setText("Tìm kiếm");
    }

    void searchCD() {
        for (int i = currentSearchCD; i < listCD.size(); i++) {
            String searchText = panel.Khung.removeAccent(txtChuDeTim.getText().trim()).toLowerCase();
            String comparingText = panel.Khung.removeAccent(listCD.get(i).getTenCD()).toLowerCase();
            if (comparingText.contains(searchText)) {
                currentSearchCD = i + 1;
                int rowIndex = i;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblChuDe.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblChuDe.setRowSelectionInterval(i, i);
                tblChuDe.scrollRectToVisible(cellRect);
                break;
            } else {
                currentSearchCD = 0;
                tblChuDe.clearSelection();
            }
        }
    }

    void chekcBoxCD() {
        listCD = cdDAO.checkBox();
        defaultTableModel = (DefaultTableModel) tblChuDe.getModel();
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listCD.size(); i++) {
            Object row[] = new Object[2];
            row[0] = i + 1;
            row[1] = listCD.get(i).getTenCD();
            defaultTableModel.addRow(row);

        }
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Tạo Popup Thêm Sửa Xoá Chủ Đề">
    class PopupmenuTblCD extends JPopupMenu {

        public PopupmenuTblCD() {
            JMenuItem mnuThem = new JMenuItem("Thêm");
            JMenuItem mnuSua = new JMenuItem("Sửa");
            JMenuItem mnuXoa = new JMenuItem("Xoá");
            JMenuItem mnuBoqua = new JMenuItem("Mới");
            if (loginPanel.isNhanVien) {
                this.add(mnuThem);
                this.add(mnuSua);
                this.add(mnuBoqua);
            } else {
                this.add(mnuThem);
                this.add(mnuSua);
                this.add(mnuBoqua);
                this.add(mnuXoa);
            }

            mnuXoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selected = tblChuDe.getSelectedRow();
                    int rowIndex = tblChuDe.getRowCount() - 1;
                    deleteCD();
                    txtChuDeTim.setText("");
                    if (selected == rowIndex) {
                        tblChuDe.setRowSelectionInterval(tblChuDe.getRowCount() - 1, tblChuDe.getRowCount() - 1);
                    } else {
                        tblChuDe.setRowSelectionInterval(selected, selected);
                    }
                }
            });

            mnuSua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtChuDeTim.requestFocus();
                    btnChuDeTim.setText("Sửa");
                }
            });
            mnuThem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtChuDeTim.requestFocus();
                    txtChuDeTim.setText("");
                    btnChuDeTim.setText("Thêm");
                }
            });

            mnuBoqua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtChuDeTim.requestFocus();
                    txtChuDeTim.setText("");
                    btnChuDeTim.setText("Tìm kiếm");
                    tblChuDe.clearSelection();
                }
            });
        }
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tạo Popup Thêm Sửa Xoá Tác Giả">
    class PopupmenuTblTG extends JPopupMenu {

        public PopupmenuTblTG() {
            JMenuItem mnuThemTG = new JMenuItem("Thêm Tác Giả");
            this.add(mnuThemTG);

            mnuThemTG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    themTGJDialog.setVisible(true);
                }
            });

        }
    }
// </editor-fold>

    private boolean CheckNull() {
        if (txtTieuDe.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Tiêu Đề Sách ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtTieuDe.requestFocus();
            return false;
        } else if (txtMaTacGia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Tác Giả Sách ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            tacgiaDialog.setVisible(true);
            return false;
        } else if (cboNhaXuatBan.getSelectedItem().equals("----- Chọn Nhà Xuất Bản -----")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Nhà Xuất Bản ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            cboNhaXuatBan.requestFocus();
            return false;
        } else if (cboChuDe.getSelectedItem().equals("----- Chọn -----")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Chủ Đề Sách ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            cboChuDe.requestFocus();
            return false;
        } else if (cboNgonNgu.getSelectedItem().equals("----- Chọn -----")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Ngôn Ngữ Sách ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            cboNgonNgu.requestFocus();
            return false;
        } else if (!txtNamXuatBan.getText().matches("(\\d){4}")) {
            JOptionPane.showMessageDialog(null, "Năm Xuất Bản Phải Là Số Và Phải Nhập Đúng Theo Định Dạng yyyy ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtNamXuatBan.requestFocus();
            return false;
        } else if (Integer.parseInt(txtNamXuatBan.getText()) < 1900) {
            JOptionPane.showMessageDialog(null, "Năm Xuất Bản Phải Lớn Hơn Năm 1900 ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtNamXuatBan.requestFocus();
            return false;
        } else if (DateHelper.toDate(txtNamXuatBan.getText(), "yyyy").after(DateHelper.now())) {
            JOptionPane.showMessageDialog(null, "Năm Xuất Bản Không Được Lớn Hơn Năm Hiện Tại ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtNamXuatBan.requestFocus();
            return false;
        } else if (!txtGia.getText().matches("(\\d){1,10}")) {
            JOptionPane.showMessageDialog(null, "Giá Tiền Phải Là Số ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtGia.requestFocus();
            return false;
        } else if (Integer.parseInt(txtGia.getText()) < 5000 || Integer.parseInt(txtGia.getText()) > 5000000) {
            JOptionPane.showMessageDialog(null, "Giá Tiền Phải Lớn Hơn 5000vnđ Hoặc Nhỏ Hơn 5.000.000vnđ ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtGia.requestFocus();
            return false;
        } else if (!txtSoTrang.getText().matches("(\\d){1,10}")) {
            JOptionPane.showMessageDialog(null, "Số Trang Phải Là Số ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtSoTrang.requestFocus();
            return false;
        } else if (!txtSoBanLuu.getText().matches("[0-9]{1,4}") || Integer.parseInt(txtSoBanLuu.getText()) < 0) {
            JOptionPane.showMessageDialog(null, "Số Bản Lưu Phải Là Số Và Lớn Hơn Hoặc Bằng 0 ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtSoBanLuu.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean CheckNullTG() {
        if (themTGPanel.txtTen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Tên Tác Giả ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            themTGPanel.txtTen.requestFocus();
            return false;
        } else if (themTGPanel.txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Ngày Sinh Theo Định Dạng dd/MM/yyyy ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            themTGPanel.txtNgaySinh.requestFocusInWindow();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            ManHinhChinh MHC = new ManHinhChinh();
                            MHC.loginJDialog.setVisible(true);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                });
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        FileInputStream fis = new FileInputStream("src\\Icon\\em.mp3");
                        Player player = new Player(fis);
                        player.play();
                        player.close();
                        fis.close();
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }.start();
    }

    private String maTacGia = "";
    public int choose;
    int stt = -1;
    int indexx = 0;

}
