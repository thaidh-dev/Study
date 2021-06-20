package panel;

import com.toedter.calendar.JDateChooser;
import dao.DocGiaDAO;
import dao.MuonTraDAO;
import dao.SachDAO;
import helper.DateHelper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.*;
import java.util.List;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import object.DocGia;
import object.MuonTra;
import object.Sach;

public class PhieuTraMuon implements Khung {

    //<editor-fold defaultstate="collapsed" desc="Khai báo biến">
    JPanel pnlThan;
    private JPanel pnlTieuDe_Form, pnlForm, pnlInfo, pnlTieuDeSach, pnlBtn, pnlDanhSach, pnlDanhSach2, pnlTim_Loc, pnlTim, pnlLoc;
    private JLabel lblTieuDe, lblPhieuMuon, lblTheDocGia, lblTenDocGia, lblNgayHenTra, lblNhanVien, lblNgayMuon, lblNgayMuon2, lblTim;
    public JLabel lblTenNhanVien;
    private JTextField txtPhieuMuon, txtTheDocGia, txtTenDocGia, txtTim, txtMaSach, txtTimDG;
    private JTextArea txtTieuDeSach;
    private JDateChooser dateNgayHenTra;
    private JButton btnThemPhieu, btnBoQua, btnSuaPhieu, btnXoaPhieu, btnTraRenderred, btnTraEditor, btnTimDG, btnXNDG, btnHUYDG;
    private ButtonGroup bgrLoc;
    private JRadioButton rdoTatCa, rdoDaTra, rdoChuaTra, rdoQuaHan;
    private JScrollPane scrTieuDeSach, scrTbl, scrDG;
    private JTable tblBigTable, tblDG;
    private GridBagConstraints gbc;
    private Insets insets;
    private DefaultTableModel model, defaultTableModel;
    private JDialog sachDialog, docgiaDialog;
    private JPanel pnlSach, pnlCheckTG, pnlTimTG, pnlDG, pnlCheckDG, pnlTimDG;
    private JScrollPane scrTacGia;
    private JLabel lblTacGiaTim, lblDGTim;
    private JButton btnXacNhan, btnHuy;
    private JTextField txtSachTim, txtSachChon;
    private JTable tblTacGia;
    private static final int BOOLEAN_COLUMN = 4;
    private boolean addSuccessful = false;
    private MuonTraDAO mtDAO = new MuonTraDAO();
    private SachDAO sachDAO = new SachDAO();
    private DocGiaDAO dgDAO = new DocGiaDAO();
    private DocGia DG = new DocGia();
    private Sach sach = new Sach();
    private MuonTra MT = new MuonTra();
    private List<MuonTra> list;
    private List<Sach> listSach;
    private List<DocGia> listDG;
    private int maCu = 0;
    private String maMoi;

    private ListSelectionModel selectionModel;
    private List<Integer> index = new ArrayList<>();
    private List<Integer> index2 = new ArrayList<>();
    //</editor-fold>

    public PhieuTraMuon() {
        taoDoiTuong();
        taoDang();
        event();
        add();

        DefaultTableCellRenderer o = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setOpaque(true);
                setValue(value);
                setHorizontalAlignment(JLabel.CENTER);
                setFont(table.getFont());

                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                    for (int i = 0; i < index.size(); i++) {
                        if (row == index.get(i)) {
                            setForeground(Color.red);
                        }
                    }
                } else {
                    setBackground(Color.white);
                    setForeground(Color.black);
                    for (int i = 0; i < index.size(); i++) {
                        if (row == index.get(i)) {
                            setForeground(Color.red);
                        }
                    }
                }

                tblBigTable.revalidate();
                tblBigTable.repaint();
                return this;
            }
        };

        tblBigTable.getColumnModel().getColumn(6).setCellRenderer(o);

        DefaultTableCellRenderer dtcrTra = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (isSelected) {
//                    btnTraRenderred.setText("Trả");
//                    btnTraRenderred.setEnabled(true);
                    //btnTraRenderred.setVisible(true);
                    for (int i = 0; i < index2.size(); i++) {
                        if (row == index2.get(i)) {
//                            btnTraRenderred.setText("Đã trả");
//                            btnTraRenderred.setEnabled(false);
//                            btnTraRenderred.setVisible(false);
                            return this;
                        }
                    }
                } else {
//                    btnTraRenderred.setText("Trả");
//                    btnTraRenderred.setEnabled(true);
                    //btnTraRenderred.setVisible(true);
                    for (int i = 0; i < index2.size(); i++) {
                        if (row == index2.get(i)) {
//                            btnTraRenderred.setText("Đã trả");
//                            btnTraRenderred.setEnabled(false);
//                            btnTraRenderred.setVisible(false);
                            return this;
                        }
                    }
                }

                tblBigTable.revalidate();
                tblBigTable.repaint();

                return btnTraRenderred;
            }
        };

        DefaultCellEditor dceTra = new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (isSelected) {
//                    btnTraEditor.setText("Trả");
//                    btnTraEditor.setEnabled(true);
                    //btnTraEditor.setVisible(true);
                    for (int i = 0; i < index2.size(); i++) {
                        if (row == index2.get(i)) {
//                            btnTraEditor.setText("Đã trả");
//                            btnTraEditor.setEnabled(false);
//                            btnTraEditor.setVisible(false);
                            JTextField txt = new JTextField();
                            txt.setText("   Đã Trả");
                            txt.setEditable(false);
                            return txt;
                        }
                    }
                } else {
//                    btnTraEditor.setText("Trả");
//                    btnTraEditor.setEnabled(true);
                    //btnTraEditor.setVisible(true);
                    for (int i = 0; i < index2.size(); i++) {
                        if (row == index2.get(i)) {
//                            btnTraEditor.setText("Đã trả");
//                            btnTraEditor.setEnabled(false);
//                            btnTraEditor.setVisible(false);
                            JTextField txt = new JTextField();
                            txt.setText("   Đã Trả");
                            txt.setEditable(false);
                            return txt;
                        }
                    }
                }

                btnTraEditor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fireEditingStopped();
                    }
                });

                tblBigTable.revalidate();
                tblBigTable.repaint();

                return btnTraEditor;
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }
        };

        tblBigTable.getColumnModel().getColumn(7).setCellRenderer(dtcrTra);
        tblBigTable.getColumnModel().getColumn(7).setCellEditor(dceTra);
    }

    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();
        insets = new Insets(5, 5, 5, 5);

        //<editor-fold defaultstate="collapsed" desc="pnl">
        pnlThan = new JPanel(new BorderLayout(0, 20));
        pnlTieuDe_Form = new JPanel(new BorderLayout(0, 10));
        pnlForm = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Phiếu mượn sách", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlInfo = new JPanel(new GridBagLayout());
        pnlTieuDeSach = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Tiêu đề sách", 2, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlBtn = new JPanel(new GridBagLayout());
        pnlDanhSach = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh sách", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlDanhSach2 = new JPanel(new BorderLayout());
        pnlTim_Loc = new JPanel(new GridLayout(1, 2, 0, 0));
        pnlTim = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlLoc = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="lbl">
        lblTieuDe = new JLabel("Quản lý phiếu mượn");
        lblPhieuMuon = new JLabel("Phiếu mượn");
        lblTheDocGia = new JLabel("Thẻ độc giả");
        lblTenDocGia = new JLabel("Tên độc giả");
        lblNgayHenTra = new JLabel("Ngày hẹn trả");
        lblNhanVien = new JLabel("Nhân viên");
        lblNgayMuon = new JLabel("Ngày mượn");
        lblTenNhanVien = new JLabel("Nguyễn Tiến Thành");
        lblNgayMuon2 = new JLabel();
        lblNgayMuon2.setText(DateHelper.toString(DateHelper.now(), "dd-MM-yyyy"));
        lblTim = new JLabel("Tìm kiếm");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="txt">
        txtPhieuMuon = new JTextField(25);
        txtTheDocGia = new JTextField(25);
        txtTenDocGia = new JTextField(25);
        txtMaSach = new JTextField(25);
        txtTim = new JTextField(25);
        dateNgayHenTra = new JDateChooser();
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="btn">
        btnBoQua = new JButton("Bỏ Qua");
        btnThemPhieu = new JButton("Thêm Phiếu");
        btnSuaPhieu = new JButton("Gia Hạn Thêm");
        btnXoaPhieu = new JButton("Xóa Phiếu");
        btnTraRenderred = new JButton("Trả");
        btnTraEditor = new JButton("");
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="tbl">
        txtTieuDeSach = new JTextArea();
        scrTieuDeSach = Khung.taoJScrollPane(null, txtTieuDeSach, new Dimension(500, 89));

        String tenCot[] = {"STT", "Phiếu mượn", "Tên độc giả", "Tiêu đề sách", "Ngày mượn", "Ngày hẹn", "Ngày trả", "Trạng thái"};
        model = new DefaultTableModel(tenCot, 7) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 7:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };
        tblBigTable = new JTable(model);
        scrTbl = Khung.taoJScrollPane(tblBigTable, null, new Dimension());

        //// dôc giả
        Class classDG[] = {Integer.class, Integer.class, String.class};
        String tenCotDG[] = {"STT", "Thẻ Độc Giả", "Tên Độc Giả"};
        tblDG = Khung.taoTable(classDG, tenCotDG);
        scrDG = Khung.taoJScrollPane(tblDG, null, new Dimension(250, 125));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="rdo">
        bgrLoc = new ButtonGroup();
        rdoTatCa = new JRadioButton("Tất cả");
        rdoTatCa.setSelected(true);
        rdoDaTra = new JRadioButton("Đã trả sách");
        rdoChuaTra = new JRadioButton("Chưa trả sách");
        rdoQuaHan = new JRadioButton("Trả quá hạn");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Cửa Sổ Chọn Sách">
        sachDialog = new JDialog();
        sachDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));
        sachDialog.setTitle("Chọn Sách");
        sachDialog.setLayout(new BorderLayout());
        sachDialog.setMinimumSize(new Dimension(600, 463));
        sachDialog.setResizable(false);
        sachDialog.setLocationRelativeTo(null);
        pnlSach = new JPanel(new BorderLayout());
        lblTacGiaTim = new JLabel("Tìm Kiếm");
        lblTacGiaTim.setFont(new Font("tahoma", 1, 17));
        btnXacNhan = new JButton("Xác Nhận");
        btnXacNhan.setPreferredSize(new Dimension(150, 30));
        btnHuy = new JButton("Huỷ Chọn");
        btnHuy.setPreferredSize(new Dimension(150, 30));
        txtSachTim = new JTextField();
        txtSachTim.setPreferredSize(new Dimension(380, 30));
        txtSachChon = new JTextField();
        txtSachChon.setPreferredSize(new Dimension(20, 30));

        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSachChon.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Tác Giả ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                    txtSachChon.requestFocus();
                } else {
                    sachDialog.dispose();
                    txtTieuDeSach.setText(txtSachChon.getText());
                }
            }
        });

        txtSachTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String csach = txtSachTim.getText().trim();
                filterTG(csach);
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                huyToTableTG();
                txtSachChon.setText("");
                txtMaSach.setText("");
                txtTieuDeSach.setText("");
            }
        });

        tblTacGia = createTable();
        tblTacGia.getModel().addTableModelListener(new PhieuTraMuon.CheckBoxModelListener());
        scrTacGia = Khung.taoJScrollPane(tblTacGia, null, new Dimension(330, 125));

        pnlTimTG = new JPanel(new FlowLayout(10, 38, 10));
        pnlTimTG.add(lblTacGiaTim);
        pnlTimTG.add(txtSachTim);
        pnlTimTG.add(txtSachChon);

        pnlCheckTG = new JPanel(new FlowLayout(10, 100, 10));
        pnlCheckTG.add(btnXacNhan);
        pnlCheckTG.add(btnHuy);

        pnlSach.add(pnlTimTG, BorderLayout.NORTH);
        pnlSach.add(scrTacGia, BorderLayout.CENTER);
        pnlSach.add(pnlCheckTG, BorderLayout.SOUTH);
        sachDialog.getContentPane().add(pnlSach, BorderLayout.CENTER);

        fillToTableTG();
        txtSachChon.setVisible(false);
        sachDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                sachDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Cửa Sổ Đọc Giả">
        docgiaDialog = new JDialog();
        docgiaDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));
        docgiaDialog.setTitle("Độc Giả");
        docgiaDialog.setLayout(new BorderLayout());
        docgiaDialog.setMinimumSize(new Dimension(500, 463));
        docgiaDialog.setResizable(false);
        docgiaDialog.setLocationRelativeTo(null);
        pnlDG = new JPanel(new BorderLayout());
        lblDGTim = new JLabel("Tìm Kiếm");
        lblDGTim.setFont(new Font("tahoma", 1, 17));
        btnXNDG = new JButton("Xác Nhận");
        btnXNDG.setPreferredSize(new Dimension(150, 30));
        btnTimDG = new JButton("Tìm Kiếm");
        btnTimDG.setPreferredSize(new Dimension(105, 30));
        btnHUYDG = new JButton("Huỷ Chọn");
        btnHUYDG.setPreferredSize(new Dimension(150, 30));
        txtTimDG = new JTextField();
        txtTimDG.setPreferredSize(new Dimension(230, 30));

        btnXNDG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTimDG.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Đọc Giả ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                    txtTimDG.requestFocus();
                } else {
                    docgiaDialog.dispose();
                    int cdg = tblDG.getSelectedRow();
                    String now = DateHelper.toString(DateHelper.now(), "ddMMyyyy");
                    if (cdg != -1) {
                        txtTheDocGia.setText(String.valueOf("DG" + now + listDG.get(cdg).getTheDG()));
                        txtTenDocGia.setText(listDG.get(cdg).getTenDG());
                    }
                }
            }
        });

        txtTimDG.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (btnTimDG.getText().equals("Tìm Kiếm") || btnTimDG.getText().equals("Thêm")) {
                    search();
                }
            }
        });

        txtTimDG.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                currentSearchDG = 0;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                currentSearchDG = 0;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                currentSearchDG = 0;
            }
        });

        btnTimDG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        btnTimDG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btnTimDG.getText().equals("Thêm")) {
                    insertCD();
                    int rowIndex = tblDG.getRowCount() - 1;
                    int columnIndex = 0;
                    boolean includeSpacing = true;
                    Rectangle cellRect = tblDG.getCellRect(rowIndex, columnIndex, includeSpacing);
                    tblDG.scrollRectToVisible(cellRect);
                    tblDG.setRowSelectionInterval(tblDG.getRowCount() - 1, tblDG.getRowCount() - 1);
                } else if (btnTimDG.getText().equals("Sửa")) {
                    updateCD();
                } else if (btnTimDG.getText().equals("Tìm Kiếm")) {
                    search();
                }

            }
        });

        txtTimDG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new PhieuTraMuon.PopupmenuTblDG();
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        btnHUYDG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblDG.clearSelection();
                txtTimDG.setText("");
                txtTheDocGia.setText("");
                txtTenDocGia.setText("");
            }
        });

        pnlTimDG = new JPanel(new FlowLayout(10, 20, 10));
        pnlTimDG.add(lblDGTim);
        pnlTimDG.add(txtTimDG);
        pnlTimDG.add(btnTimDG);

        pnlCheckDG = new JPanel(new FlowLayout(10, 67, 10));
        pnlCheckDG.add(btnXNDG);
        pnlCheckDG.add(btnHUYDG);

        pnlDG.add(pnlTimDG, BorderLayout.NORTH);
        pnlDG.add(scrDG, BorderLayout.CENTER);
        pnlDG.add(pnlCheckDG, BorderLayout.SOUTH);
        docgiaDialog.getContentPane().add(pnlDG, BorderLayout.CENTER);

        docgiaDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                docgiaDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        //</editor-fold>
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

        dateNgayHenTra.setDateFormatString("dd-MM-yyyy");
        dateNgayHenTra.setPreferredSize(txtPhieuMuon.getPreferredSize());

        btnThemPhieu.setPreferredSize(new Dimension(140, 25));
        btnBoQua.setPreferredSize(new Dimension(140, 25));
        btnSuaPhieu.setPreferredSize(new Dimension(140, 25));
        btnXoaPhieu.setPreferredSize(new Dimension(140, 25));

        txtTieuDeSach.setLineWrap(true);

        tblBigTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblBigTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblBigTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblBigTable.getColumnModel().getColumn(3).setPreferredWidth(400);
        tblBigTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblBigTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblBigTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        tblBigTable.getColumnModel().getColumn(7).setPreferredWidth(50);

        tblTacGia.getColumnModel().getColumn(0).setPreferredWidth(35);
        tblTacGia.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblTacGia.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblTacGia.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblTacGia.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblTacGia.setRowHeight(30);

        tblDG.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblDG.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblDG.getColumnModel().getColumn(2).setPreferredWidth(180);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tblBigTable.getColumnModel().getColumn(4).setCellRenderer(dtcr);
        tblBigTable.getColumnModel().getColumn(5).setCellRenderer(dtcr);
        tblBigTable.getColumnModel().getColumn(6).setCellRenderer(dtcr);

        DefaultTableCellRenderer dtcrTra = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return btnTraRenderred;
            }
        };

        DefaultCellEditor dceTra = new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                btnTraEditor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fireEditingStopped();
                    }
                });
                return btnTraEditor;
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                if (btnTraEditor.isEnabled()) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        btnTraRenderred.setBackground(Color.red);
        tblBigTable.getColumnModel().getColumn(7).setCellRenderer(dtcrTra);
        tblBigTable.getColumnModel().getColumn(7).setCellEditor(dceTra);

        JTableHeader tblBigTableHeader = tblBigTable.getTableHeader();
        ((DefaultTableCellRenderer) tblBigTableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tblDGHeader = tblDG.getTableHeader();
        ((DefaultTableCellRenderer) tblDGHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblBigTable.setIntercellSpacing(new Dimension(10, 1));
        tblBigTable.setFillsViewportHeight(true);
        tblBigTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBigTable.setRowHeight(30);
        tblDG.setRowHeight(30);

        txtTieuDeSach.setToolTipText("Click Chuột Trái Để Chọn Sách.");
        txtTheDocGia.setToolTipText("Click Chuột Trái Để Chọn Độc Giả.");
        txtTenDocGia.setToolTipText("Click Chuột Trái Để Chọn Độc Giả.");
    }

    @Override
    public void add() {

        JComponent a[] = {lblPhieuMuon, lblTheDocGia, lblTenDocGia, lblNgayHenTra, txtPhieuMuon, txtTheDocGia, txtTenDocGia, dateNgayHenTra};
        Khung.addChung(pnlInfo, gbc, a, 4, 2, new Insets(7, 5, 7, 5), 17);

        pnlTieuDeSach.add(scrTieuDeSach);
        pnlTieuDeSach.add(txtMaSach);

        JComponent b[] = {lblNhanVien, lblNgayMuon, btnThemPhieu, btnBoQua, lblTenNhanVien, lblNgayMuon2, btnSuaPhieu, btnXoaPhieu};
        Khung.addChung(pnlBtn, gbc, b, 4, 2, new Insets(10, 5, 10, 5), 10);

        JComponent c[] = {pnlInfo, pnlTieuDeSach, pnlBtn};
        Khung.addChung(pnlForm, gbc, c, 1, 3, new Insets(5, 30, 5, 30), 10);

        pnlTieuDe_Form.add(lblTieuDe, BorderLayout.NORTH);
        pnlTieuDe_Form.add(pnlForm, BorderLayout.CENTER);

        pnlTim.add(lblTim);
        pnlTim.add(txtTim);

        bgrLoc.add(rdoTatCa);
        bgrLoc.add(rdoDaTra);
        bgrLoc.add(rdoChuaTra);
        bgrLoc.add(rdoQuaHan);
        pnlLoc.add(rdoTatCa);
        pnlLoc.add(rdoDaTra);
        pnlLoc.add(rdoChuaTra);
        pnlLoc.add(rdoQuaHan);

        pnlTim_Loc.add(pnlTim);
        pnlTim_Loc.add(pnlLoc);

        pnlDanhSach2.add(pnlTim_Loc, BorderLayout.NORTH);
        pnlDanhSach2.add(scrTbl, BorderLayout.CENTER);

        pnlDanhSach.add(pnlDanhSach2);

        pnlThan.add(pnlTieuDe_Form, BorderLayout.NORTH);
        pnlThan.add(pnlDanhSach, BorderLayout.CENTER);
    }

    @Override
    public void event() {

        editOff();
        fillToTable();

        //<editor-fold defaultstate="collapsed" desc=" Sự Kiện radioButton">
        rdoTatCa.addItemListener((ItemEvent e) -> {
            fillToTable();
        });

        rdoDaTra.addItemListener((ItemEvent e) -> {
            daTra();
        });

        rdoChuaTra.addItemListener((ItemEvent e) -> {
            chuaTra();
        });

        rdoQuaHan.addItemListener((ItemEvent e) -> {
            quaHan();
        });

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" Sự Kiện Bảng Và Tìm Kiếm">
        txtTieuDeSach.setEditable(false);
        txtTieuDeSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sachDialog.setVisible(true);
            }
        });

        selectionModel = tblBigTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg) {
                int b = tblBigTable.getSelectedRow();
                if (b != -1) {
                    showItem(b);
                }
            }
        });

        tblBigTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int i = tblBigTable.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    showItem(i);
                }
            }
        });

        tblDG.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int i = tblDG.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    txtTimDG.setText(listDG.get(i).getTenDG());
                }
            }
        });

        txtTheDocGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (txtTheDocGia.getText().matches("(\\d){1,5}") && txtTheDocGia.getText().length() > 0) {
                    txtTenDocGia.setText("");
                    list = mtDAO.selectByTheDG(Integer.parseInt(txtTheDocGia.getText().trim()));
                    for (int i = 0; i < list.size(); i++) {
                        txtTenDocGia.setText(list.get(i).getTenDG());
                    }
                } else if (txtTheDocGia.getText().length() == 0) {
                    txtTenDocGia.setText("");
                }
            }
        });

        txtTheDocGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillToTableDG();
                docgiaDialog.setVisible(true);
            }
        });

        txtTenDocGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillToTableDG();
                docgiaDialog.setVisible(true);
            }
        });

        //<editor-fold defaultstate="collapsed" desc="Sự Kiện Click Bảng DG">
        tblDG.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // <editor-fold defaultstate="collapsed" desc="Sự Kiện Click chuột phải ">
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int r = tblDG.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < tblDG.getRowCount()) {
                        tblDG.setRowSelectionInterval(r, r);
                    } else {
                        tblDG.clearSelection();
                    }
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Sự Kiện Click Chuột Trái">
                indexx = tblDG.getSelectedRow();
                if (indexx != -1) {
                    txtTimDG.setText(listDG.get(indexx).getTenDG());
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new PhieuTraMuon.PopupmenuTblDG();
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
//                }
                //</editor-fold>
            }
        });
        //</editor-fold>

        txtTim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String loc = txtTim.getText().trim();
                filterPM(loc);
            }
        });

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" Sự Kiện Button">
        btnThemPhieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThemPhieu.getText().equals("Thêm Phiếu")) {
                    clear();
                    editOn();
                    huyToTableTG();
                    tblBigTable.clearSelection();
                    tblBigTable.setEnabled(false);
                    txtTheDocGia.requestFocus();
                    btnThemPhieu.setText("Lưu Phiếu Mượn");
                    btnBoQua.setText("Huỷ");
                    txtTim.setEnabled(false);
                    btnSuaPhieu.setEnabled(false);
                    btnXoaPhieu.setEnabled(false);
                } else {
                    insert();
                    if (addSuccessful) {
                        btnSuaPhieu.setEnabled(true);
                        btnXoaPhieu.setEnabled(true);
                        btnThemPhieu.setText("Thêm Phiếu");
                        btnBoQua.setText("Bỏ Qua");
                        txtTim.setEnabled(true);
                        tblBigTable.setEnabled(true);
                        editOff();
                        int rowIndex = tblBigTable.getRowCount() - 1;
                        int columnIndex = 0;
                        boolean includeSpacing = true;
                        Rectangle cellRect = tblBigTable.getCellRect(rowIndex, columnIndex, includeSpacing);
                        tblBigTable.scrollRectToVisible(cellRect);
                        tblBigTable.setRowSelectionInterval(rowIndex, rowIndex);
                    }
                }
            }
        });

        btnSuaPhieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Phiếu Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblBigTable.getSelectedRow();
                    if (btnSuaPhieu.getText().equals("Gia Hạn Thêm")) {
                        txtPhieuMuon.setEnabled(false);
                        txtTheDocGia.setEnabled(false);
                        txtTenDocGia.setEnabled(false);
                        dateNgayHenTra.setEnabled(true);
                        dateNgayHenTra.requestFocus();
                        btnSuaPhieu.setText("Lưu Phiếu Mượn");
                        btnBoQua.setText("Huỷ");
                        btnThemPhieu.setEnabled(false);
                        btnXoaPhieu.setEnabled(false);
                    } else {
                        update();
                        if (addSuccessful) {
                            editOff();
                            btnThemPhieu.setEnabled(true);
                            btnXoaPhieu.setEnabled(true);
                            btnSuaPhieu.setText("Gia Hạn Thêm");
                            btnBoQua.setText("Bỏ Qua");
                            tblBigTable.setRowSelectionInterval(selected, selected);
                            rdoTatCa.isSelected();
                        }
                    }
                }
            }
        }
        );

        btnXoaPhieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Một Phiếu Trong Bảng Dữ liệu ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblBigTable.getSelectedRow();
                    int rowIndex = tblBigTable.getRowCount() - 1;
                    delete();
                    rdoTatCa.isSelected();
                    if (selected == rowIndex) {
                        tblBigTable.setRowSelectionInterval(tblBigTable.getRowCount() - 1, tblBigTable.getRowCount() - 1);
                    } else {
                        tblBigTable.setRowSelectionInterval(selected, selected);
                    }
                }
            }
        });

        btnBoQua.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int selected = tblBigTable.getSelectedRow();
                if (btnBoQua.getText().equals("Bỏ Qua")) {
                    clear();
                    btnThemPhieu.setEnabled(true);
                    btnSuaPhieu.setEnabled(true);
                    btnXoaPhieu.setEnabled(true);
                    btnBoQua.setEnabled(true);
                    txtTim.setText("");
                    tblBigTable.clearSelection();
                } else {
                    clear();
                    editOff();
                    txtTim.setEnabled(true);
                    btnThemPhieu.setText("Thêm Phiếu");
                    btnSuaPhieu.setText("Gia Hạn Thêm");
                    btnXoaPhieu.setText("Xoá Phiếu");
                    btnBoQua.setText("Bỏ Qua");
                    btnThemPhieu.setEnabled(true);
                    btnSuaPhieu.setEnabled(true);
                    btnXoaPhieu.setEnabled(true);
                    tblBigTable.setEnabled(true);
                    tblBigTable.setBackground(Color.WHITE);
                    rdoTatCa.isSelected();
                    if (selected == -1) {
                        tblBigTable.clearSelection();
                    }
                }
            }
        }
        );

        btnTraEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = tblBigTable.getSelectedRow();
                boolean tt = list.get(a).isTrangThai();
                if (tt == true) {
                    JOptionPane.showMessageDialog(null, "Phiếu Mượn Đã Được Trả", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Trả Phiếu Mượn Này Không?",
                            "Confirm Message", JOptionPane.YES_NO_OPTION);
                    if (rep == JOptionPane.NO_OPTION) {
                        return;
                    }
                    MT.setMaPM(list.get(a).getMaPM());
                    mtDAO.traSach(MT);
                    JOptionPane.showMessageDialog(null, "Trả Sách Thành Công !");
                    fillToTable();
                    rdoTatCa.isSelected();
                    tblBigTable.setRowSelectionInterval(a, a);
                }
            }
        });
        //</editor-fold>

    }

    //<editor-fold defaultstate="collapsed" desc=" Sự Kiện table Chọn Sách">
    private JTable createTable() {
        String[] cols = {"Mã Sách", "Tiêu Đề", "Ngôn Ngữ", "Số Lượng", "Chọn"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, cols) {
            @Override
            public Class
                    getColumnClass(int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3) {
                    return String.class;

                }
                return column == BOOLEAN_COLUMN ? Boolean.class
                        : String.class;
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

    public class CheckBoxModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            int a = tblTacGia.getSelectedRow();
            if (tblTacGia.isValid() == true) {
                txtSachChon.setText("");
                txtMaSach.setText("");
                for (int i = 0; i < tblTacGia.getRowCount(); i++) {
                    if (tblTacGia.getValueAt(i, 4) != null && String.valueOf(tblTacGia.getValueAt(i, 4)).equals("true")) {
                        txtSachChon.setText(txtSachChon.getText() + String.valueOf(tblTacGia.getValueAt(i, 1) + ", "));
                        txtMaSach.setText(txtMaSach.getText() + String.valueOf(tblTacGia.getValueAt(i, 0) + ", ").substring(4));
                    }
                }
                if (txtSachChon.getText().length() != 0 && txtMaSach.getText().length() != 0) {
                    txtSachChon.setText(txtSachChon.getText().substring(0, txtSachChon.getText().length() - 2));
                    txtMaSach.setText(txtMaSach.getText().substring(0, txtMaSach.getText().length() - 2));
                }
            }
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Sự Kiện Fill Bảng Dữ Liệu">
    @Override
    public void fillToTable() {
        list = mtDAO.select();
        loadData();
    }

    public void daTra() {
        list = mtDAO.daTra();
        loadData();
    }

    public void chuaTra() {
        list = mtDAO.chuaTra();
        loadData();
    }

    public void quaHan() {
        list = mtDAO.quaHan();
        loadData();
    }

    void loadData() {
        if (tblBigTable.isEditing() == true) {
            tblBigTable.getCellEditor().stopCellEditing();
        }
        index.clear();

        model = (DefaultTableModel) tblBigTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String ngayMuon = format.format(list.get(i).getNgayMuon());
            String ngayHen = format.format(list.get(i).getNgayHen());
            Object row[] = new Object[8];
            row[0] = i + 1;
            row[1] = "PM000" + list.get(i).getMaPM();
            row[2] = list.get(i).getTenDG();
            row[3] = list.get(i).getTenSach();
            row[4] = ngayMuon;
            row[5] = ngayHen;
            if (list.get(i).getNgayTra() != null) {
                String ngayTra = format.format(list.get(i).getNgayTra());
                if (DateHelper.toDate(ngayTra, "dd-MM-yyyy").after(DateHelper.toDate(ngayHen, "dd-MM-yyyy"))) {
                    row[6] = ngayTra + " Quá Hạn";
                    row[7] = false;
                    index.add(i);
                } else {
                    row[6] = ngayTra;
                    row[7] = false;
                }
            } else {
                row[6] = null;
                row[7] = true;
            }

            model.addRow(row);
        }

        index2.clear();
        for (int i = 0; i < tblBigTable.getRowCount(); i++) {
            String b = String.valueOf(tblBigTable.getValueAt(i, 7));
            if (b.equals("false")) {
                index2.add(i);
            }
        }
    }

    void maPM() {
        list = mtDAO.autoMaPM();
        for (int i = 0; i < list.size(); i++) {
            maCu = list.get(i).getMaPM();
        }
    }

    void fillToTableTG() {
        listSach = sachDAO.checkBox();
        model = (DefaultTableModel) tblTacGia.getModel();

        String a = txtMaSach.getText();
        String b[] = a.split(",");

        model.setRowCount(0);

        for (Sach cs : listSach) {
            Object row[] = new Object[5];
            row[0] = "MS00" + cs.getMaSach();
            row[1] = cs.getTieuDe();
            row[2] = cs.getNgonNgu();
            row[3] = cs.getSoLuong();

            for (int i = 0; i < b.length; i++) {
                b[i] = b[i].trim();
                if (!b[i].equals("")) {
                    int c = Integer.parseInt(b[i]);
                    if (cs.getMaSach().equals(c)) {
                        row[4] = true;
                        break;
                    }
                }
            }
            model.addRow(row);
        }
    }

    void huyToTableTG() {
        listSach = sachDAO.checkBox();
        model = (DefaultTableModel) tblTacGia.getModel();

        model.setRowCount(0);

        for (Sach tg : listSach) {
            Object row[] = new Object[5];
            row[0] = "MS00" + tg.getMaSach();
            row[1] = tg.getTieuDe();
            row[2] = tg.getNgonNgu();
            row[3] = tg.getSoLuong();
            row[4] = false;
            model.addRow(row);
        }
    }

    void filterTG(String TG) {
        model = (DefaultTableModel) tblTacGia.getModel();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(model);
        tblTacGia.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(TG));
    }

    void filterPM(String TG) {
        model = (DefaultTableModel) tblBigTable.getModel();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(model);
        tblBigTable.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(TG));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Xử lí sự kiện Phiếu Mượn">
    @Override
    public void showItem(int c
    ) {
        String now = DateHelper.toString(list.get(c).getNgayMuon(), "ddMMyyyy");
        txtPhieuMuon.setText("PM000" + String.valueOf(list.get(c).getMaPM()));
        txtTheDocGia.setText("DG" + now + String.valueOf(list.get(c).getTheDG()));
        txtTenDocGia.setText(list.get(c).getTenDG());
        dateNgayHenTra.setDate(list.get(c).getNgayHen());
        lblNgayMuon.setText(DateHelper.toString(list.get(c).getNgayMuon(), "dd-MM-yyyy"));
        txtTieuDeSach.setText(list.get(c).getTenSach());
        if (list.get(c).isTrangThai() == true) {
            btnThemPhieu.setEnabled(false);
            btnSuaPhieu.setEnabled(false);
            btnXoaPhieu.setEnabled(false);
            btnBoQua.setEnabled(true);
        } else {
            btnThemPhieu.setEnabled(true);
            btnSuaPhieu.setEnabled(true);
            btnXoaPhieu.setEnabled(true);
            btnBoQua.setEnabled(true);
        }
    }

    @Override
    public void search() {
        for (int i = currentSearchDG; i < listDG.size(); i++) {
            String searchText = panel.Khung.removeAccent(txtTimDG.getText().trim()).toLowerCase();
            String comparingText = panel.Khung.removeAccent(listDG.get(i).getTenDG()).toLowerCase();
            if (comparingText.contains(searchText)) {
                currentSearchDG = i + 1;
                int rowIndex = i;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblDG.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblDG.setRowSelectionInterval(i, i);
                tblDG.scrollRectToVisible(cellRect);
                break;
            } else {
                currentSearchDG = 0;
                tblDG.clearSelection();
            }
        }
    }

    @Override
    public void insert() {
        try {
            if (checkNull()) {
                maPM();
                if (maCu != 0) {
                    int index = maCu;
                    maMoi = String.valueOf(++index);
                    MT.setMaPM(Integer.parseInt(maMoi));
                } else {
                    maMoi = "1";
                    MT.setMaPM(Integer.parseInt(maMoi));
                }
                if (txtTheDocGia.getText().length() > 10) {
                    String theDG = txtTheDocGia.getText().trim().substring(10);
                    int MaThe = Integer.parseInt(theDG);
                    System.out.println(MaThe);
                    MT.setTheDG(MaThe);
                } else if (txtTheDocGia.getText().length() < 3 && txtTheDocGia.getText().matches(("\\d"))) {
                    MT.setTheDG(Integer.parseInt(txtTheDocGia.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Số Thẻ Độc Giả VD: Nhập Số 1 !", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                    txtTheDocGia.requestFocus();
                    return;
                }
                MT.setMaNV("NV00001");
                MT.setMaSach(txtMaSach.getText());
                MT.setNgayHen(dateNgayHenTra.getDate());
                mtDAO.insertPM(MT);
                mtDAO.insertPMCT(MT);
                addSuccessful = true;
                JOptionPane.showMessageDialog(null, "Tạo Phiếu Mượn Sách Thành Công !");
                fillToTable();
            } else {
                addSuccessful = false;
            }
        } catch (Exception ex) {
            addSuccessful = false;
            System.out.println(ex);
        }
    }

    @Override
    public void update() {
        try {
            if (checkSua()) {
                MT.setNgayHen(dateNgayHenTra.getDate());
                String MAPM = txtPhieuMuon.getText().trim().substring(5);
                int MaThe = Integer.parseInt(MAPM);
                System.out.println(MaThe);
                MT.setMaPM(MaThe);
                addSuccessful = true;
                mtDAO.updatePM(MT);
                JOptionPane.showMessageDialog(null, "Sửa Phiếu Mượn Thành Công");
                fillToTable();
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
            addSuccessful = false;
            System.out.println(e);
        }
    }

    @Override
    public void delete() {
        int x = tblBigTable.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Xoá Phiếu Mượn Này Không?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            String MAPM = txtPhieuMuon.getText().trim().substring(5);
            int MaPM = Integer.parseInt(MAPM);
            int a = tblBigTable.getSelectedRow();
            String now = DateHelper.toString(DateHelper.now(), "dd-MM-yyyy");
            String ngayMuon = DateHelper.toString(list.get(a).getNgayMuon(), "dd-MM-yyyy");
            if (ngayMuon.equals(now)) {
                mtDAO.deletePMCT(MaPM);
                mtDAO.deletePM(MaPM);
                JOptionPane.showMessageDialog(null, "Xoá Phiếu Mượn Thành Công !");
                fillToTable();
            } else {
                JOptionPane.showMessageDialog(null, "Phiếu Mượn Đã Tạo Sau Một Ngày Nên Không Được Xoá ! ", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void clear() {
        txtTenDocGia.setText("");
        txtTheDocGia.setText("");
        dateNgayHenTra.setDate(null);
        txtMaSach.setText("");
        txtTieuDeSach.setText("");
        txtSachChon.setText("");
        txtSachTim.setText("");
        txtPhieuMuon.setText("");
    }

    @Override
    public void editOn() {
        txtPhieuMuon.setEnabled(false);
        txtTheDocGia.setEnabled(true);
        txtTenDocGia.setEnabled(false);
        dateNgayHenTra.setEnabled(true);
    }

    @Override
    public void editOff() {
        txtPhieuMuon.setEnabled(false);
        txtTheDocGia.setEnabled(false);
        txtTenDocGia.setEnabled(false);
        dateNgayHenTra.setEnabled(false);
    }

    private boolean checkNull() throws SQLException {
        if (txtTenDocGia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Thẻ Độc Giả", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            txtTheDocGia.requestFocus();
            return false;
        }
        if (txtMaSach.getText().equals("") || txtTieuDeSach.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Sách", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            sachDialog.setVisible(true);
            return false;
        } else if (dateNgayHenTra.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Đúng Định Dạng dd-MM-yyyy", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            dateNgayHenTra.requestFocusInWindow();
            return false;
        } else if (dateNgayHenTra.getDate().before(DateHelper.now())) {
            JOptionPane.showMessageDialog(null, "Ngày Hẹn Trả Phải Sau Ngày Mượn 1 Ngày", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            dateNgayHenTra.requestFocusInWindow();
            return false;
        } else if (dateNgayHenTra.getDate().after(DateHelper.addMonth(1))) {
            JOptionPane.showMessageDialog(null, "Ngày Hẹn Trả Không Được Quá 1 Tháng", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            dateNgayHenTra.requestFocusInWindow();
            return false;
        }

        return true;
    }

    private boolean checkSua() {
        if (dateNgayHenTra.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Đúng Định Dạng dd-MM-yyyy", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            dateNgayHenTra.requestFocusInWindow();
            return false;
        } else if (dateNgayHenTra.getDate().before(DateHelper.now())) {
            JOptionPane.showMessageDialog(null, "Ngày Hẹn Trả Phải Sau Ngày Mượn 1 Ngày", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            dateNgayHenTra.requestFocusInWindow();
            return false;
        } else if (dateNgayHenTra.getDate().after(DateHelper.addMonth(1))) {
            JOptionPane.showMessageDialog(null, "Ngày Hẹn Trả Không Được Quá 1 Tháng", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            dateNgayHenTra.requestFocusInWindow();
            return false;
        }
        return true;
    }

    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Xử lí xự kiện đọc giả">
    void fillToTableDG() {
        listDG = dgDAO.select();
        String now = DateHelper.toString(DateHelper.now(), "ddMMyyyy");
        model = (DefaultTableModel) tblDG.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (DocGia dg : listDG) {
            Object row[] = new Object[3];
            row[0] = stt++;
            row[1] = "DG" + now + dg.getTheDG();
            row[2] = dg.getTenDG();
            model.addRow(row);
        }
    }

    void deleteCD() {
        int x = tblDG.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Xoá Độc Giả Này Không ?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            int ID = (int) listDG.get(x).getTheDG();
            MuonTra the = mtDAO.findMaPM(ID);
            System.out.println(the);
            if (the == null) {
                dgDAO.delete(ID);
                JOptionPane.showMessageDialog(null, "Xóa thành công !", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                fillToTableDG();
                btnTimDG.setText("Tìm Kiếm");
            } else {
                System.out.println(the);
                JOptionPane.showMessageDialog(null, "Độc Giả Đã Có Thẻ Mượn Không Thể Xoá ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void insertCD() {
        DG.setTenDG(txtTimDG.getText());
        dgDAO.insert(DG);
        JOptionPane.showMessageDialog(null, "Thêm Thành Công");
        fillToTableDG();
        btnTimDG.setText("Tìm Kiếm");
    }

    void updateCD() {
        int x = tblDG.getSelectedRow();
        int ID = (int) listDG.get(x).getTheDG();
        System.out.println(ID);
        DG.setTenDG(txtTimDG.getText());
        DG.setTheDG(ID);
        dgDAO.update(DG);
        JOptionPane.showMessageDialog(null, "Sửa Thành Công");
        fillToTableDG();
        fillToTable();
        tblDG.setRowSelectionInterval(x, x);
        btnTimDG.setText("Tìm Kiếm");

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tạo Popup Thêm Sửa Xoá ĐỌc Giả">
    class PopupmenuTblDG extends JPopupMenu {

        public PopupmenuTblDG() {
            JMenuItem mnuThemDG = new JMenuItem("Thêm Độc Giả");
            JMenuItem mnuSuaDG = new JMenuItem("Sửa Độc Giả");
            JMenuItem mnuXoaDG = new JMenuItem("Xoá Độc Giả");
            JMenuItem mnuBoDG = new JMenuItem("Bỏ Qua");
            this.add(mnuThemDG);
            this.add(mnuSuaDG);
            this.add(mnuXoaDG);
            this.add(mnuBoDG);

            mnuXoaDG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selected = tblDG.getSelectedRow();
                    int rowIndex = tblDG.getRowCount() - 1;
                    deleteCD();
                    txtTimDG.setText("");
                    if (selected == rowIndex) {
                        tblDG.setRowSelectionInterval(tblDG.getRowCount() - 1, tblDG.getRowCount() - 1);
                    } else {
                        tblDG.setRowSelectionInterval(selected, selected);
                    }
                }
            });

            mnuSuaDG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtTimDG.requestFocus();
                    btnTimDG.setText("Sửa");
                }
            });
            mnuThemDG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtTimDG.requestFocus();
                    txtTimDG.setText("");
                    btnTimDG.setText("Thêm");
                }
            });

            mnuBoDG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtTimDG.requestFocus();
                    txtTimDG.setText("");
                    btnTimDG.setText("Tìm Kiếm");
                    tblDG.clearSelection();
                }
            });
        }

    }
// </editor-fold>

    int indexx = 0;
    private int currentSearchDG = 0;
}
