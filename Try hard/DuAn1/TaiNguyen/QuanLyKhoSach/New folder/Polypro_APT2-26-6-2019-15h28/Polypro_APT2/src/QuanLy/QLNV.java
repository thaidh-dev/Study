/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLy;

// <editor-fold defaultstate="collapsed" desc=" IMPORT ">
import DAO.NhanVienDAO;
import HeThong.ChaoJDialog;
import HeThong.DangNhapFrame;
import Helper.JdbcHelper;
import Helper.MailHelper;
import Model.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import polypro_apt2_main.MainFrame;
// </editor-fold>

/**
 *
 * @author phuoc.bleach
 */
public class QLNV extends JPanel {

    /*- C??c ch??? s??? c???a flagSave:
        + 0: m???c ?????nh
        + 1: th??m m???i
        + 2: S???a*/
    int flagSave = 0;
    JdbcHelper jdbcHelper = new JdbcHelper();
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    NhanVien nvSelected = null;
    List<NhanVien> listNhanVien = null;
    int indexNvSelectedInTable = -1;

    final int ROLE_TRUONG_PHONG = 1;
    final int FLAG_DEFAULT = 0;
    final int FLAG_INSERT = 1;
    final int FLAG_UPDATE = 2;

    public static boolean doneLoad = false;

    List<NhanVien> listSearchNV;
    int indexInListSearchNV = 0;

    final int FOUND = 1;
    final int NOT_FOUND = 0;
    int flagFind = NOT_FOUND;

    final int SEARCH = 1;
    final int NO_SEARCH = 0;
    int flagSearch = NO_SEARCH;

    MailHelper mailHelper = new MailHelper();

    //<editor-fold defaultstate="collapsed" desc="Component">
    JLabel lblSearchTitle, lblInformationTitle, lblMatKhau, lblXacNhanMatKhau, lblHoTen, lblVaiTro, lblID, lblEmail;
    JTextField txtSearchNV, txtHoTen, txtID, txtEmail;
    JPasswordField pwfMatKhau, pwfXacNhanMatKhau;
    JRadioButton rdoTP, rdoNV;
    ButtonGroup btgVaiTro;

    JButton btnContinueSearch, btnSearch, btnNew, btnUpdate, btnSave, btnDelete, btnCancel, btnResetPass;

    DefaultTableModel model;
    JTable tblNhanVien;
    JScrollPane scTable;
    JTableHeader tblHeader;
    //</editor-fold>

    public QLNV() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.init();
            this.addControls();
            this.disableAll();
            this.addEvents();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() {
        //<editor-fold defaultstate="collapsed" desc="init">
        lblSearchTitle = new JLabel("T??m ki???m nh??n vi??n");
        lblInformationTitle = new JLabel("Th??ng tin nh??n vi??n");
        lblMatKhau = new JLabel("M???t kh???u");
        lblXacNhanMatKhau = new JLabel("X??c nh???n m???t kh???u");
        lblHoTen = new JLabel("H??? t??n");
        lblVaiTro = new JLabel("Vai tr??");
        lblID = new JLabel("M?? nh??n vi??n");
        lblEmail = new JLabel("Email");

        txtHoTen = new JTextField();
        txtSearchNV = new JTextField();
        txtID = new JTextField();
        txtEmail = new JTextField();
        txtID.setEditable(false);

        pwfMatKhau = new JPasswordField();
        pwfXacNhanMatKhau = new JPasswordField();

        rdoNV = new JRadioButton("Nh??n vi??n");
        rdoTP = new JRadioButton("Tr?????ng ph??ng");
        rdoNV.setSelected(true);
        btgVaiTro = new ButtonGroup();
        btgVaiTro.add(rdoNV);
        btgVaiTro.add(rdoTP);

        btnContinueSearch = new JButton("T??m ti???p");
        btnSearch = new JButton("T??m ki???m");
        btnNew = new JButton("Th??m m???i");
        btnSave = new JButton("L??u");
        btnUpdate = new JButton("S???a");
        btnDelete = new JButton("X??a");
        btnCancel = new JButton("B??? qua");
        btnResetPass = new JButton("Kh??i ph???c m???t kh???u");

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "STT", "M?? NH??N VI??N", "H??? V?? T??N", "EMAIL", "VAI TR??"
        });

        tblNhanVien = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                if (column == 2) {
                    c.setHorizontalAlignment(JLabel.LEFT);
                } else {
                    c.setHorizontalAlignment(JLabel.CENTER);

                }
                return c;
            }
        };
        scTable = new JScrollPane(tblNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblHeader = tblNhanVien.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(110);
        tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(300);
        tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(140);

        tblNhanVien.setRowHeight(30);
        tblNhanVien.setSelectionBackground(Color.decode("#3a4d8f"));
        //c??n gi???a n???i dung table
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.decode("#e7e7e7") : Color.WHITE);
                    c.setFont(new Font("Segoe UI", 0, 14));
                }
                return c;
            }
        };
        for (int i = 0; i < tblNhanVien.getColumnCount(); i++) {
            tblNhanVien.setDefaultRenderer(tblNhanVien.getColumnClass(i), renderer);
        }

        tblNhanVien.setRowSorter(new TableRowSorter(model));
        tblNhanVien.setAutoCreateRowSorter(true);

        //Font
        lblInformationTitle.setFont(new Font("Segoe UI", 1, 18));
        lblSearchTitle.setFont(new Font("Segoe UI", 1, 18));

        lblHoTen.setFont(new Font("Segoe UI", 0, 14));
        lblMatKhau.setFont(new Font("Segoe UI", 0, 14));
        lblVaiTro.setFont(new Font("Segoe UI", 0, 14));
        lblXacNhanMatKhau.setFont(new Font("Segoe UI", 0, 14));
        lblID.setFont(new Font("Segoe UI", 0, 14));

        btnNew.setFont(new Font("Segoe UI", 0, 13));
        btnSave.setFont(new Font("Segoe UI", 0, 13));
        btnUpdate.setFont(new Font("Segoe UI", 0, 13));
        btnDelete.setFont(new Font("Segoe UI", 0, 13));
        btnSearch.setFont(new Font("Segoe UI", 0, 13));
        btnContinueSearch.setFont(new Font("Segoe UI", 0, 13));
        btnCancel.setFont(new Font("Segoe UI", 0, 13));

        tblNhanVien.setFont(new Font("Segoe UI", 0, 12));

        btnUpdate.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSearch.setPreferredSize(new Dimension(0, 0));
        btnContinueSearch.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());

        //Cursor
        btnContinueSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //set ph??m t???t
        btnSave.setMnemonic(KeyEvent.VK_L);
        btnNew.setMnemonic(KeyEvent.VK_T);
        btnUpdate.setMnemonic(KeyEvent.VK_S);
        btnDelete.setMnemonic(KeyEvent.VK_X);
        btnCancel.setMnemonic(KeyEvent.VK_B);
        btnSearch.setMnemonic(KeyEvent.VK_F);

        // set tooltip
        btnSave.setToolTipText("Ph??m t???t Alt + S");
        btnNew.setToolTipText("Ph??m t???t Alt + N");
        btnUpdate.setToolTipText("Ph??m t???t Alt + U");
        btnDelete.setToolTipText("Ph??m t???t Alt + D");
        btnCancel.setToolTipText("Ph??m t???t Alt + Z");

        txtSearchNV.setToolTipText("T??m ki???m nh??n vi??n theo m?? ho???c theo t??n");
        pwfMatKhau.setToolTipText("M???t kh???u ??t nh???t 6 k?? t???");

        // Size
        rdoTP.setMargin(new Insets(0, 0, 0, 20));
        //</editor-fold>
    }

    private void addControls() {
        //<editor-fold defaultstate="collapsed" desc="LEFT">
        JPanel pnSearch = new JPanel();
        pnSearch.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "T??m ki???m", 1, 2, new Font("Segor UI", 1, 18)));

        txtSearchNV.setPreferredSize(new Dimension(300, 25));
        pnSearch.add(txtSearchNV);
        pnSearch.add(btnContinueSearch);
        pnSearch.add(btnSearch);

        JPanel pnLeft = new JPanel(new BorderLayout(0, 5));
        pnLeft.add(scTable, BorderLayout.CENTER);
        pnLeft.add(pnSearch, BorderLayout.NORTH);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="RIGHT">
        JPanel pnInformation = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 115;

        gbc.gridy = 1;
        pnInformation.add(lblInformationTitle, gbc);

        gbc.gridy = 2;
        pnInformation.add(lblID, gbc);

        gbc.gridy = 3;
        pnInformation.add(txtID, gbc);

        gbc.gridy = 4;
        pnInformation.add(lblHoTen, gbc);

        gbc.gridy = 5;
        pnInformation.add(txtHoTen, gbc);

        gbc.gridy = 6;
        pnInformation.add(lblMatKhau, gbc);

        gbc.gridy = 7;
        pnInformation.add(pwfMatKhau, gbc);

        gbc.gridy = 8;
        pnInformation.add(lblXacNhanMatKhau, gbc);

        gbc.gridy = 9;
        pnInformation.add(pwfXacNhanMatKhau, gbc);

        gbc.gridy = 10;
        pnInformation.add(lblEmail, gbc);

        gbc.gridy = 11;
        pnInformation.add(txtEmail, gbc);

        gbc.gridy = 12;
        pnInformation.add(lblVaiTro, gbc);

        JPanel pnGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pnGioiTinh.add(rdoTP);
        pnGioiTinh.add(rdoNV);

        gbc.gridy = 13;
        pnInformation.add(pnGioiTinh, gbc);

        JPanel pnButton1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnButton1.add(btnNew);
        pnButton1.add(btnUpdate);
        pnButton1.add(btnSave);
        if (DangNhapFrame.nvLogin.getVaiTro() == ROLE_TRUONG_PHONG) {
            pnButton1.add(btnDelete);
        }
        pnButton1.add(btnCancel);

        gbc.ipadx = 10;
        gbc.gridy = 14;
        pnInformation.add(pnButton1, gbc);

        JPanel pnButton2 = new JPanel();
        pnButton2.add(btnResetPass);

        gbc.gridy = 15;
        pnInformation.add(pnButton2, gbc);

        JPanel pnItems = new JPanel();

        JPanel pnRight = new JPanel(new BorderLayout());
        pnRight.add(pnInformation, BorderLayout.NORTH);
        pnRight.add(pnItems, BorderLayout.CENTER);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="CONTAINER">
        this.setLayout(new BorderLayout(0, 5));
        this.add(pnLeft, BorderLayout.CENTER);
        this.add(pnRight, BorderLayout.EAST);
        //</editor-fold>
    }

    private void disableAll() {
    }

    private void addEvents() {
        lockFormInfo();
        buttonStatus0();

        try {
            //Load d??? li???u l??n table
            loadDataToTable();
        } catch (SQLException ex) {
            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listNhanVien.size() > 0) {
            nvSelected = listNhanVien.get(0);
            loadDataToForm();

            tblNhanVien.setRowSelectionInterval(0, 0);

            indexNvSelectedInTable = 0;
        }

        doneLoad = true;

        // <editor-fold defaultstate="collapsed" desc="key listener txt m???t kh???u">
        pwfMatKhau.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    pwfXacNhanMatKhau.requestFocus();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cancelSave();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="key listener txt nh???p l???i m???t kh???u">
        pwfXacNhanMatKhau.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    txtHoTen.requestFocus();
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    pwfMatKhau.requestFocus();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cancelSave();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="key listener txt h??? t??n">
        txtHoTen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (rdoTP.isSelected()) {
                        rdoTP.requestFocus();
                    } else {
                        rdoNV.requestFocus();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    pwfXacNhanMatKhau.requestFocus();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cancelSave();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="key listener radTP">
        rdoTP.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    txtHoTen.requestFocus();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cancelSave();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="key listener radNV">
        rdoNV.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    txtHoTen.requestFocus();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cancelSave();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        //<editor-fold defaultstate="collapsed" desc="S??? KI???N N??T btnNew">
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagSave = FLAG_INSERT;
                unlockFormInfo();
                buttonStatusSave();
                resetForm();
                rdoNV.setSelected(true);
                txtHoTen.requestFocus();

                tblNhanVien.clearSelection();
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="s??? ki???n n??t btnUpdate">
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unlockFormInfo();
                buttonStatusSave();
                txtHoTen.requestFocus();
                flagSave = FLAG_UPDATE;
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="s??? ki???n n??t btnCancel">
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelSave();
            }
        });
        //</editor-fold>

        // <editor-fold defaultstate="collapsed" desc="s??? ki???n n??t btnSave ">
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (flagSave) {
                    // <editor-fold defaultstate="collapsed" desc="case1: insert">
                    case 1:
                        try {
                            insertNV();
                        } catch (SQLException ex) {
                            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    // </editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="case 2: update">
                    case 2:
                        try {
                            updateNV();
                        } catch (SQLException ex) {
                            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    // </editor-fold>

                    default:
                        System.out.println("??ang ??? flag 0");
                        break;
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n click v??o h??ng trong b???ng">
        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // <editor-fold defaultstate="collapsed" desc="Click chu???t ph???i ">
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int r = tblNhanVien.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < tblNhanVien.getRowCount()) {
                        tblNhanVien.setRowSelectionInterval(r, r);
                    } else {
                        tblNhanVien.clearSelection();
                    }
                }
                // </editor-fold>

                if (flagSave != 0) {
                    String mess = "";
                    switch (flagSave) {
                        case FLAG_INSERT:
                            mess = "B???n c?? mu???n b??? qua thao t??c th??m m???i nh??n vi??n kh??ng?";
                            break;
                        case FLAG_UPDATE:
                            mess = "B???n c?? mu???n b??? qua thao t??c c???p nh???t th??ng tin nh??n vi??n kh??ng?";
                            break;
                    }

                    int choose = JOptionPane.showConfirmDialog(null, mess, "H???i", JOptionPane.YES_NO_OPTION);

                    if (choose == JOptionPane.YES_OPTION) {
                        indexNvSelectedInTable = tblNhanVien.getSelectedRow();
                        int indexNvSelectedInTbl = (int) tblNhanVien.getValueAt(indexNvSelectedInTable, 0) - 1;
                        nvSelected = listNhanVien.get(indexNvSelectedInTbl);

                        loadDataToForm();
                        lockFormInfo();
                        buttonStatus0();

                        flagSave = 0;

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            JPopupMenu popup = new PopupTblNV();
                            popup.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                } else {
                    indexNvSelectedInTable = tblNhanVien.getSelectedRow();
                    int indexNvSelectedInTbl = (int) tblNhanVien.getValueAt(indexNvSelectedInTable, 0) - 1;
                    nvSelected = listNhanVien.get(indexNvSelectedInTbl);

                    loadDataToForm();
                    lockFormInfo();
                    buttonStatus0();

                    flagSave = 0;

                    if (e.getButton() == MouseEvent.BUTTON3) {
                        JPopupMenu popup = new PopupTblNV();
                        popup.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n n??t search">
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSearchNV.requestFocus();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n n??t btnSearchContinue">
        btnContinueSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueSearch();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n n??t btnDelete ">
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteNV();
                } catch (SQLException ex) {
                    Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n up down table">
        tblNhanVien.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int stt = (int) tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0) - 1;
                    nvSelected = listNhanVien.get(stt);

                    loadDataToForm();

                    indexNvSelectedInTable = tblNhanVien.getSelectedRow();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int stt = (int) tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0) - 1;
                    nvSelected = listNhanVien.get(stt);

                    loadDataToForm();

                    indexNvSelectedInTable = tblNhanVien.getSelectedRow();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n search v???i txtSearch ">
        txtSearchNV.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtSearchNV.getText().length() > 0) {
                    indexInListSearchNV = 0;

                    flagSearch = SEARCH;

                    try {
                        listSearchNV = nhanVienDAO.get(txtSearchNV.getText().trim());

                        searchNV();
                    } catch (SQLException ex) {
                        Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (flagFind == NOT_FOUND) {
                        btnUpdate.setEnabled(false);
                    } else {
                        btnUpdate.setEnabled(true);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtSearchNV.getText().length() > 0) {
                    indexInListSearchNV = 0;

                    try {
                        listSearchNV = nhanVienDAO.get(txtSearchNV.getText().trim());

                        searchNV();
                    } catch (SQLException ex) {
                        Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (flagFind == NOT_FOUND) {
                        btnUpdate.setEnabled(false);
                    } else {
                        btnUpdate.setEnabled(true);
                    }
                } else {
                    flagSearch = NO_SEARCH;

                    Rectangle rect = tblNhanVien.getCellRect(0, 0, true);
                    tblNhanVien.scrollRectToVisible(rect);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="s??? ki???n ph??m v???i txtSearch ">
        txtSearchNV.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    nvSelected = listNhanVien.get(0);

                    tblNhanVien.setRowSelectionInterval(0, 0);

                    loadDataToForm();
                    btnDelete.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnCancel.setEnabled(true);
                    txtSearchNV.setText("");

                    flagSearch = NO_SEARCH;

                    Rectangle rect = tblNhanVien.getCellRect(0, 0, true);
                    tblNhanVien.scrollRectToVisible(rect);
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    continueSearch();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n reset pass ">
        btnResetPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            }
        });
        // </editor-fold>

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
                s.add("facebook.com");

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

        refresh30s();
    }

    // <editor-fold defaultstate="collapsed" desc="T???O M?? NH??N VI??N">
    private String createID() throws SQLException {
        String lastID = nhanVienDAO.getLastIdNhanVien();

        if (lastID != null) {
            StringBuilder ID = new StringBuilder();
            ID.append("NV");

            int pathNumber = Integer.parseInt(lastID.substring(2)) + 1;

            for (int i = 0; i < 5 - String.valueOf(pathNumber).length(); i++) {
                ID.append("0");
            }

            ID.append(pathNumber);

            return ID.toString();
        } else {
            return "NV00001";
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="KH??A FORM NH???P TH??NG TIN V?? N??T L??U X??A S???A B??? QUA">
    private void lockFormInfo() {
        txtSearchNV.setEditable(true);
        btnContinueSearch.setEnabled(true);
        btnSearch.setEnabled(true);
        txtHoTen.setEditable(false);
        pwfMatKhau.setEditable(false);
        rdoTP.setEnabled(false);
        rdoNV.setEnabled(false);
        pwfXacNhanMatKhau.setEditable(false);
        txtEmail.setEditable(false);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="M??? KH??A FORM NH???P TH??NG TIN ">
    private void unlockFormInfo() {
        txtSearchNV.setEditable(false);
        btnContinueSearch.setEnabled(false);
        btnSearch.setEnabled(false);
        txtHoTen.setEditable(true);
        rdoNV.setEnabled(true);
        rdoTP.setEnabled(true);

        if (flagSave == FLAG_INSERT) {
            txtEmail.setEditable(true);
            pwfMatKhau.setEditable(true);
            pwfXacNhanMatKhau.setEditable(true);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="BUTTON TR???NG TH??I M???C ?????NH">
    private void buttonStatus0() {
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        btnDelete.setVisible(true);
        btnNew.setVisible(true);
        btnUpdate.setVisible(true);
        btnResetPass.setVisible(true);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnDelete.setEnabled(true);
        btnNew.setEnabled(true);
        btnUpdate.setEnabled(true);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="BUTTON TR???NG TH??I SAVE">
    private void buttonStatusSave() {
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(false);
        btnNew.setVisible(false);
        btnUpdate.setVisible(false);
        btnResetPass.setVisible(false);

        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnDelete.setEnabled(false);
        btnNew.setEnabled(false);
        btnUpdate.setEnabled(false);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="RESET FORM ">
    private void resetForm() {
        txtID.setText("");
        txtHoTen.setText("");
        pwfMatKhau.setText("");
        pwfXacNhanMatKhau.setText("");
        txtEmail.setText("");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LOAD D??? LI???U L??N TABLE ">
    private void loadDataToTable() throws SQLException {
        listNhanVien = nhanVienDAO.getAll();

        model.setRowCount(0);
        int stt = 1;
        for (NhanVien nhanVien : listNhanVien) {
            String vaiTro = "Nh??n vi??n";
            if (nhanVien.getVaiTro() == 1) {
                vaiTro = "Tr?????ng ph??ng";
            }

            model.addRow(new Object[]{stt, nhanVien.getID(), nhanVien.getHoTen(), nhanVien.getEmail(), vaiTro});
            stt++;
        }

        tblNhanVien.setRowSelectionInterval(0, 0);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LOAD D??? LI???U L??N FORM ">
    private void loadDataToForm() {
        txtID.setText(nvSelected.getID());
        pwfMatKhau.setText(nvSelected.getMatKhau());
        pwfXacNhanMatKhau.setText(nvSelected.getMatKhau());
        txtHoTen.setText(nvSelected.getHoTen());
        txtEmail.setText(nvSelected.getEmail());

        if (nvSelected.getVaiTro() == 0) {
            rdoNV.setSelected(true);
        } else {
            rdoTP.setSelected(true);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="T??M KI???M NH??N VI??N ">
    private void searchNV() throws SQLException {
        if (listSearchNV.size() > 0) {
            NhanVien nvInListSearch = listSearchNV.get(indexInListSearchNV);

            for (NhanVien nv : listNhanVien) {
                if (nv.getID().equals(nvInListSearch.getID())) {
                    nvSelected = nv;

                    loadDataToForm();

                    for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                        if (tblNhanVien.getValueAt(i, 1).equals(nvSelected.getID())) {
                            tblNhanVien.setRowSelectionInterval(i, i);
                            flagFind = FOUND;

                            Rectangle rect = tblNhanVien.getCellRect(i, 0, true);
                            tblNhanVien.scrollRectToVisible(rect);
                            return;
                        }
                    }
                }
            }
        } else {
            flagFind = NOT_FOUND;
            resetForm();
            tblNhanVien.clearSelection();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CHECK TH??NG TIN NH??N VI??N ">
    private boolean checkInfo() throws SQLException {
        if (txtHoTen.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui l??ng nh???p h??? t??n ?????y ?????!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        }

        if (!txtHoTen.getText().matches("^[A-Za-z???????????????????????????????????????????????????????????????????????????????????????????-???\\s\']{5,100}$")) {
            JOptionPane.showMessageDialog(null, "Vui l??ng nh???p h??? t??n ?????y ?????, kh??ng ch???a s??? v?? c??c k?? t??? ?????c bi???t \n, . / ; < > ? : \" { } - = _ + ` ~ ! @ $ % ^ & * ( ) \\ |", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        }

        if (pwfMatKhau.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Vui l??ng nh???t m???t kh???u!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            pwfMatKhau.requestFocus();
            return false;
        }

        if (pwfMatKhau.getPassword().length < 6 || pwfMatKhau.getText().matches("\\s")) {
            JOptionPane.showMessageDialog(null, "M???t kh???u ph???i c?? ????? d??i ??t nh???t 6 k?? t??? v?? kh??ng ch???a k?? t??? kho???ng tr???ng!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            pwfMatKhau.requestFocus();
            return false;
        }

        if (!pwfXacNhanMatKhau.getText().equalsIgnoreCase(pwfMatKhau.getText())) {
            JOptionPane.showMessageDialog(null, "X??c nh???n m???t kh???u ph???i tr??ng v???i m???t kh???u!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            pwfXacNhanMatKhau.requestFocus();
            return false;
        }

        if (txtEmail.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui l??ng nh???p email!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (!txtEmail.getText().matches("^([\\w\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{1})+$")) {
            JOptionPane.showMessageDialog(null, "Email kh??ng h???p l???!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        if (flagSave == FLAG_INSERT) {
            if (nhanVienDAO.checkEmailExistsInDb(txtEmail.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Email ???? ???????c s??? d???ng ????? ????ng k?? nh??n vi??n!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                txtEmail.requestFocus();
                return false;
            }

            try {
                if (mailHelper.checkEmail(txtEmail.getText()).equals("false")) {
                    JOptionPane.showMessageDialog(null, "Email kh??ng t???n t???i. Vui l??ng ki???m tra l???i!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                    txtEmail.requestFocus();
                    return false;
                }
            } catch (Exception ex) {
                Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="TH??M NH??N VI??N M???I ">
    private void insertNV() throws SQLException {
        if (checkInfo()) {
            try {
                NhanVien truongPhong = nhanVienDAO.checkTpExists();

                if (rdoTP.isSelected() == true && truongPhong != null) {
                    int choose = JOptionPane.showConfirmDialog(null, "??ng " + truongPhong.getHoTen() + " ??ang l?? tr?????ng ph??ng.\nB???n c?? mu???n ti???p t???c ?????i tr?????ng ph??ng m???i kh??ng?", "H???i", JOptionPane.YES_OPTION);

                    if (choose == JOptionPane.YES_OPTION) {
                        insert();

                        loadDataToTable();
                        lockFormInfo();
                        buttonStatus0();
                        flagSave = 0;

                        JOptionPane.showMessageDialog(null, "T??i kho???n c???a b???n ???? b??? thay ?????i quy???n th??nh Nh??n vi??n. Vui l??ng ????ng nh???p l???i ????? s??? d???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                        ChaoJDialog.frLogin.setVisible(true);
                        DangNhapFrame.mainFrame.setVisible(false);
                    }
                } else {
                    insert();

                    loadDataToTable();
                    lockFormInfo();
                    buttonStatus0();
                    flagSave = 0;
                }

                nvSelected = listNhanVien.get(listNhanVien.size() - 1);

                for (int j = 0; j < tblNhanVien.getRowCount(); j++) {
                    if (tblNhanVien.getValueAt(j, 1).equals(nvSelected.getID())) {
                        tblNhanVien.setRowSelectionInterval(j, j);
                        break;
                    }
                }

                indexNvSelectedInTable = tblNhanVien.getSelectedRow();

                loadDataToForm();

                //send mail th??ng b??o ????ng k?? th??nh c??ng
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String to = nvSelected.getEmail();
                        String sub = "Th??ng b??o ????ng k?? t??i kho???n nh??n vi??n t???i L???p tr??nh City";
                        String content = "Ch??o " + nvSelected.getHoTen() + "!"
                                + "\nB???n ???? ???????c ????ng k?? t??i kho???n ????? ????ng nh???p v??o Ph???n m???m qu???n l?? kh??a h???c c???a L???p tr??nh City."
                                + "\n- T??n ????ng nh???p: " + nvSelected.getID()
                                + "\n- M???t kh???u: " + nvSelected.getMatKhau()
                                + "\n  B???n vui l??ng ????ng nh???p v??o ph???n m???m v?? ?????i m???t kh???u ????? ?????m b???o t??nh b???o m???t.";

                        try {
                            mailHelper.sendMail(to, sub, content);
                        } catch (MessagingException ex) {
                            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            } catch (SQLException ex) {
                Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insert() throws SQLException {
        String id = createID();
        int vaiTro = 0;
        if (rdoTP.isSelected()) {
            vaiTro = 1;
        }

        NhanVien nv = new NhanVien(id, pwfMatKhau.getText(), txtHoTen.getText(), txtEmail.getText(), vaiTro);

        nhanVienDAO.insert(nv);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="X??A NH??N VI??N ">
    private void deleteNV() throws SQLException {
        if (!nvSelected.getID().equalsIgnoreCase(DangNhapFrame.nvLogin.getID())) {
            int choose = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n x??a nh??n vi??n n??y kh??ng?", "H???i", JOptionPane.YES_NO_OPTION);

            if (choose == JOptionPane.YES_OPTION) {

                String id = nvSelected.getID();

                int i = nhanVienDAO.delete(id);

                if (i > 0) {
                    loadDataToTable();
                    lockFormInfo();
                    loadDataToFormAfterDelete();
                    btnDelete.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnCancel.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "X??a kh??ng th??nh c??ng. Vui l??ng ki???m tra l???i!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Kh??ng ???????c x??a t??i kho???n ??ang ????ng nh???p v??o ph???n m???m!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="C???P NH???T NH??N VI??N ">
    private void updateNV() throws SQLException {
        if (checkInfo()) {
            try {
                NhanVien truongPhong = nhanVienDAO.checkTpExists();

                if (nvSelected.getVaiTro() == 1) {
                    if (rdoNV.isSelected() == true) {
                        int choose = JOptionPane.showConfirmDialog(null, "??ng " + truongPhong.getHoTen() + " ??ang l?? tr?????ng ph??ng.\nB???n c?? mu???n chuy???n ??ng " + truongPhong.getHoTen() + " th??nh nh??n vi??n kh??ng?", "H???i", JOptionPane.YES_OPTION);

                        if (choose == JOptionPane.NO_OPTION || choose == JOptionPane.CLOSED_OPTION) {
                            return;
                        }

                        update();

                        loadDataToTable();
                        lockFormInfo();
                        buttonStatus0();
                        flagSave = 0;

                        JOptionPane.showMessageDialog(null, "T??i kho???n c???a b???n ???? b??? thay ?????i quy???n th??nh Nh??n vi??n. Vui l??ng ????ng nh???p l???i ????? s??? d???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                        ChaoJDialog.frLogin.setVisible(true);
                        ChaoJDialog.frLogin.progressBar.setValue(0);
                        DangNhapFrame.mainFrame.setVisible(false);
                    }
                } else {
                    if (truongPhong != null && rdoTP.isSelected()) {
                        int choose = JOptionPane.showConfirmDialog(null, "??ng " + truongPhong.getHoTen() + " ??ang l?? tr?????ng ph??ng.\nB???n c?? mu???n ti???p t???c ?????i tr?????ng ph??ng m???i kh??ng?", "H???i", JOptionPane.YES_OPTION);

                        if (choose == JOptionPane.NO_OPTION || choose == JOptionPane.CLOSED_OPTION) {
                            return;
                        }
                    }
                    update();

                    loadDataToTable();
                    lockFormInfo();
                    buttonStatus0();
                    flagSave = 0;

                    JOptionPane.showMessageDialog(null, "T??i kho???n c???a b???n ???? b??? thay ?????i quy???n th??nh Nh??n vi??n. Vui l??ng ????ng nh???p l???i ????? s??? d???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                    ChaoJDialog.frLogin.setVisible(true);
                    ChaoJDialog.frLogin.progressBar.setValue(0);
                    DangNhapFrame.mainFrame.setVisible(false);
                }

                for (int j = 0; j < tblNhanVien.getRowCount(); j++) {
                    if (tblNhanVien.getValueAt(j, 1).equals(nvSelected.getID())) {
                        tblNhanVien.setRowSelectionInterval(j, j);
                        break;
                    }
                }

                indexNvSelectedInTable = tblNhanVien.getSelectedRow();
            } catch (SQLException ex) {
                Logger.getLogger(QLHV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void update() throws SQLException {
        int vaiTro = 0;
        if (rdoTP.isSelected()) {
            vaiTro = 1;
        }

        nvSelected.setHoTen(txtHoTen.getText());
        nvSelected.setMatKhau(pwfMatKhau.getText());
        nvSelected.setVaiTro(vaiTro);

        nhanVienDAO.update(nvSelected);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="KI???M TRA HV SAU HV ???????C CH???N C?? T???N T???I KH??NG ">
    private boolean checkAfterHvselectedExists() {
        try {
            if (tblNhanVien.getValueAt(indexNvSelectedInTable, 0) != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LOAD TH??NG HV L??N FORM SAU DELETE ">
    private void loadDataToFormAfterDelete() {
        if (checkAfterHvselectedExists()) {
            int stt = (int) tblNhanVien.getValueAt(indexNvSelectedInTable, 0) - 1;
            nvSelected = listNhanVien.get(stt);
            loadDataToForm();

            tblNhanVien.setRowSelectionInterval(indexNvSelectedInTable, indexNvSelectedInTable);
        } else {
            int stt = (int) tblNhanVien.getValueAt(indexNvSelectedInTable - 1, 0) - 1;
            nvSelected = listNhanVien.get(stt);
            loadDataToForm();

            tblNhanVien.setRowSelectionInterval(indexNvSelectedInTable - 1, indexNvSelectedInTable - 1);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREATE POPUPMENU ">
    class PopupTblNV extends JPopupMenu {

        public PopupTblNV() {
            JMenuItem mnuUpdate = new JMenuItem("S???a");
            JMenuItem mnuDelete = new JMenuItem("X??a");
            this.add(mnuUpdate);
            if (DangNhapFrame.nvLogin.getVaiTro() == 1) {
                this.add(mnuDelete);
            }

            mnuDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        deleteNV();
                    } catch (SQLException ex) {
                        Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            mnuUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    unlockFormInfo();
                    buttonStatusSave();
                    txtHoTen.requestFocus();
                    flagSave = 2;
                }
            });
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CANCEL SAVE">
    private void cancelSave() {
        String mess = "";
        switch (flagSave) {
            case FLAG_INSERT:
                mess = "B???n c?? mu???n b??? qua thao t??c th??m m???i nh??n vi??n kh??ng?";
                break;
            case FLAG_UPDATE:
                mess = "B???n c?? mu???n b??? qua thao t??c c???p nh???t th??ng tin nh??n vi??n kh??ng?";
                break;
        }

        int choose = JOptionPane.showConfirmDialog(null, mess, "H???i", JOptionPane.YES_NO_OPTION);

        if (choose == JOptionPane.NO_OPTION || choose == JOptionPane.CLOSED_OPTION) {
            return;
        }

        lockFormInfo();
        buttonStatus0();
        if (flagSave == FLAG_INSERT) {
            resetForm();
            tblNhanVien.setRowSelectionInterval(indexNvSelectedInTable, indexNvSelectedInTable);
            nvSelected = listNhanVien.get((int) tblNhanVien.getValueAt(indexNvSelectedInTable, 0) - 1);
            loadDataToForm();
        }
        flagSave = FLAG_DEFAULT;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREATE RANDOM PASSWORD">
    private String createRandomPass() {
        String[] arrWord = new String[]{"z", "c", "x", "v", "b", "n", "m", "a", "s", "d", "f", "g", "h", "j", "k", "l", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "Z", "X", "C", "V", "B", "N", "M", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};

        StringBuilder strPass = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int numberRandom = (int) Math.ceil(Math.random() * 51);
            strPass.append(arrWord[numberRandom]);
        }

        return strPass.toString();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="RESET PASSWORD">
    private void resetPassword() {
        int choose = JOptionPane.showConfirmDialog(null, "B???n c?? mu???n kh??i ph???c m???t kh???u cho t??i kho???n " + nvSelected.getID() + " kh??ng?", "H???i", JOptionPane.YES_NO_OPTION);

        if (choose == JOptionPane.NO_OPTION || choose == JOptionPane.CLOSED_OPTION) {
            return;
        }

        String newPass = createRandomPass();

        String emailTo = nvSelected.getEmail();
        String sub = "Kh??i ph???c m???t kh???u cho t??i kho???n Ph???n m???m qu???n l?? kh??a h???c";
        String content = "Ch??o " + nvSelected.getHoTen() + "!"
                + "\nT??i kho???n c???a b???n v???a ???????c y??u c???u kh??i ph???c m???t kh???u"
                + "\nM???t kh???u m???c ?????nh ch??ng t??i t???o cho b???n l??: " + newPass
                + "\nB??y gi??? b???n ???? c?? th??? ????ng nh???p v??o ph???n m???m b???ng m???t kh???u n??y";

        try {
            mailHelper.sendMail(emailTo, sub, content);

            int rs = nhanVienDAO.resetPass(newPass, nvSelected.getID());

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Kh??i ph???c m???t kh???u th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (MessagingException ex) {
//            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Kh??i ph???c m???t kh???u kh??ng th??nh c??ng. Vui l??ng ki???m tra l???i k???t n???i Internet!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="T??M TI???P ">
    private void continueSearch() {
        if (flagSearch == SEARCH) {
            if (indexInListSearchNV < listSearchNV.size() - 1) {
                indexInListSearchNV++;
            } else {
                indexInListSearchNV = 0;
            }

            try {
                searchNV();
            } catch (SQLException ex) {
                Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="REFRESH ">
    private void refresh() {
        try {
            //Load d??? li???u l??n table
            loadDataToTable();
        } catch (SQLException ex) {
            Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listNhanVien.size() > 0) {
            for (NhanVien nv : listNhanVien) {
                if (nv.getID().equalsIgnoreCase(nvSelected.getID())) {
                    loadDataToForm();
                    for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                        if (tblNhanVien.getValueAt(i, 1).equals(nvSelected.getID())) {
                            tblNhanVien.setRowSelectionInterval(i, i);
                        }
                        indexNvSelectedInTable = i;
                    }
                    return;
                }
            }
            int sttInList = (int) tblNhanVien.getValueAt(0, 0) - 1;
            nvSelected = listNhanVien.get(sttInList);
            loadDataToForm();

            tblNhanVien.setRowSelectionInterval(0, 0);

            indexNvSelectedInTable = 0;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="REFRESH 30S 1 L???N ">
    private void refresh30s() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flagLogout = false;
                while (doneLoad) {
                    if (flagSave == FLAG_DEFAULT) {
                        refresh();
                    }

                    try {
                        NhanVien nv = nhanVienDAO.getByID(DangNhapFrame.nvLogin.getID());

                        if (nv.getVaiTro() != DangNhapFrame.nvLogin.getVaiTro() && flagLogout == false) {
                            JOptionPane.showMessageDialog(null, "T??i kho???n c???a b???n ???? b??? thay ?????i quy???n. Vui l??ng ????ng nh???p l???i ????? s??? d???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                            ChaoJDialog.frLogin.setVisible(true);
                            ChaoJDialog.frLogin.progressBar.setValue(0);
                            DangNhapFrame.mainFrame.setVisible(false);
                            flagLogout = true;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QLNV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    // </editor-fold>
}
