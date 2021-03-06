package panel;

import com.toedter.calendar.JDateChooser;
import dao.SachDAO;
import dao.TacGiaDAO;
import helper.DateHelper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import object.TacGia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import object.Sach;

public class ManHinhTacGia implements Khung {

    public JPanel pnlThan, pnlForm_NutChucNang, pnlForm, pnlNutChucNang, pnlTable, pnlTim, pnlTim2, pnlTim_ChucNang, pnlTieuDe_Tim_ChucNang, pnlRdoGioiTinh;
    JLabel lblTieuDe, lblTenTacGia, lblGioiTinh, lblNgaySinh;
    JTextField txtTim, txtTenTacGia;
    JRadioButton rdoNam, rdoNu;
    ButtonGroup bgrGioiTinh;
    JDateChooser txtNgaySinh;
    public JButton btnTim, btnThem, btnSua, btnXoa, btnBoQua;
    JTable tblTacGia;
    JScrollPane scrTacGia;
    Dimension d;
    Insets insets;
    GridBagConstraints gbc;
    private TacGiaDAO tgDAO = new TacGiaDAO();
    private TacGia TG = new TacGia();
    private List<TacGia> listTG;
    private ListSelectionModel selectionModel;
    private DefaultTableModel defaultTableModel;
    private int currentSearch = 0;
    private boolean addSuccessful = false;

    public ManHinhTacGia() {
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
        pnlForm_NutChucNang = Khung.taoJPanel(new GridBagLayout(), new TitledBorder(new LineBorder(Color.gray, 1), "Ch???c n??ng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblTieuDe = new JLabel("Qu???n l?? t??c gi???");

        pnlForm = new JPanel(new GridBagLayout());
        lblTenTacGia = new JLabel("T??n t??c gi???");
        txtTenTacGia = new JTextField(20);
        lblGioiTinh = new JLabel("Gi???i t??nh");
        pnlRdoGioiTinh = new JPanel(new FlowLayout());
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("N???");
        bgrGioiTinh = new ButtonGroup();
        lblNgaySinh = new JLabel("Ng??y sinh");
        txtNgaySinh = new JDateChooser();
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
        Class classTacGia[] = {Integer.class, String.class, String.class, String.class};
        String tenCotTacGia[] = {"STT", "T??n t??c gi???", "Gi???i T??nh", "Ng??y Sinh"};
        tblTacGia = Khung.taoTable(classTacGia, tenCotTacGia);
        scrTacGia = Khung.taoJScrollPane(tblTacGia, null, new Dimension(0, 100));
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

        tblTacGia.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblTacGia.getColumnModel().getColumn(1).setPreferredWidth(700);
        tblTacGia.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblTacGia.getColumnModel().getColumn(3).setPreferredWidth(50);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tblTacGia.getColumnModel().getColumn(3).setCellRenderer(dtcr);

        txtNgaySinh.setPreferredSize(txtTenTacGia.getPreferredSize());
    }

    @Override
    public void add() {
        bgrGioiTinh.add(rdoNam);
        bgrGioiTinh.add(rdoNu);
        pnlRdoGioiTinh.add(rdoNam);
        pnlRdoGioiTinh.add(rdoNu);

        rdoNam.setSelected(true);
        JComponent b[] = {lblTenTacGia, lblGioiTinh, lblNgaySinh, txtTenTacGia, pnlRdoGioiTinh, txtNgaySinh};
        Khung.addChung(pnlForm, gbc, b, 3, 2, insets, 17);

        JComponent c[] = {btnThem, btnSua, btnXoa, btnBoQua};
        Khung.addChung(pnlNutChucNang, gbc, c, 4, 1, insets, GridBagConstraints.CENTER);

//        JComponent d[] = {pnlForm, pnlNutChucNang};
//        Khung.addChung(pnlForm_NutChucNang, gbc, d, 2, 1, insets, 17);
        JComponent d[] = {pnlForm, pnlNutChucNang};
        Khung.addChung(pnlForm_NutChucNang, gbc, d, 1, 2, insets, 17);

        JComponent a[] = {txtTim, btnTim};
        Khung.addChung(pnlTim2, null, a, 1, 2, null, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlTim.add(pnlTim2, gbc);

        pnlTim_ChucNang.add(pnlTim);
        pnlTim_ChucNang.add(pnlForm_NutChucNang);

        pnlTable.add(scrTacGia);

        pnlTieuDe_Tim_ChucNang.add(lblTieuDe, BorderLayout.NORTH);
        pnlTieuDe_Tim_ChucNang.add(pnlTim_ChucNang, BorderLayout.CENTER);

        pnlThan.add(pnlTieuDe_Tim_ChucNang, BorderLayout.NORTH);
        pnlThan.add(pnlTable, BorderLayout.CENTER);
    }

    @Override
    public void event() {
        editOff();
        fillToTable();

        selectionModel = tblTacGia.getSelectionModel();

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                int b = tblTacGia.getSelectedRow();
                if (b != -1) {
                    showItem(b);
                }
            }
        });

        selectionModel = tblTacGia.getSelectionModel();

        tblTacGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int i = tblTacGia.getSelectedRow();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    showItem(i);
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().equals("Th??m")) {
                    editOn();
                    clear();
                    txtTenTacGia.requestFocus();
                    txtTim.setEnabled(false);
                    btnTim.setEnabled(false);
                    tblTacGia.setEnabled(false);
                    tblTacGia.clearSelection();
                    tblTacGia.setBackground(Color.WHITE);
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
                        tblTacGia.setEnabled(true);
                        tblTacGia.setBackground(Color.WHITE);
                        int rowIndex = tblTacGia.getRowCount() - 1;
                        int columnIndex = 0;
                        boolean includeSpacing = true;
                        Rectangle cellRect = tblTacGia.getCellRect(rowIndex, columnIndex, includeSpacing);
                        tblTacGia.scrollRectToVisible(cellRect);
                        tblTacGia.setRowSelectionInterval(tblTacGia.getRowCount() - 1, tblTacGia.getRowCount() - 1);
                    }
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectionModel.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "M???i B???n Ch???n M???t T??c Gi??? Trong B???ng D??? li???u ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblTacGia.getSelectedRow();
                    if (btnSua.getText().equals("S???a")) {
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
                            tblTacGia.setRowSelectionInterval(selected, selected);
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
                    JOptionPane.showMessageDialog(btnTim, "M???i B???n Ch???n M???t T??c Trong B???ng D??? li???u ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
                } else {
                    int selected = tblTacGia.getSelectedRow();
                    int rowIndex = tblTacGia.getRowCount() - 1;
                    delete();
                    if (selected == rowIndex) {
                        tblTacGia.setRowSelectionInterval(tblTacGia.getRowCount() - 1, tblTacGia.getRowCount() - 1);
                    } else {
                        tblTacGia.setRowSelectionInterval(selected, selected);
                    }
                }
            }
        });

        btnBoQua.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int selected = tblTacGia.getSelectedRow();
                if (btnBoQua.getText().equals("B??? Qua")) {
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
                    tblTacGia.setEnabled(true);
                    tblTacGia.setBackground(Color.WHITE);
                    if (selected == -1) {
                        tblTacGia.clearSelection();
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
        listTG = tgDAO.select();
        defaultTableModel = (DefaultTableModel) tblTacGia.getModel();
        defaultTableModel.setRowCount(0);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < listTG.size(); i++) {
            Object row[] = new Object[4];
            row[0] = i + 1;
            row[1] = listTG.get(i).getTacGia();
            row[2] = listTG.get(i).isGioiTinh() ? "Nam" : "N???";
            row[3] = format.format(listTG.get(i).getNgaySinh());
            defaultTableModel.addRow(row);
        }
    }

    @Override
    public void showItem(int a) {
        try {
            txtTenTacGia.setText(listTG.get(a).getTacGia());
            if (listTG.get(a).isGioiTinh() == true) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtNgaySinh.setDate(DateHelper.convertDateToString(listTG.get(a).getNgaySinh()));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void search() {
        for (int i = currentSearch; i < listTG.size(); i++) {
            String searchText = Khung.removeAccent(txtTim.getText().trim()).toLowerCase();
            String comparingText = Khung.removeAccent(listTG.get(i).getTacGia()).toLowerCase();
            if (comparingText.contains(searchText)) {
                currentSearch = i + 1;
                int rowIndex = i;
                int columnIndex = 0;
                boolean includeSpacing = true;
                Rectangle cellRect = tblTacGia.getCellRect(rowIndex, columnIndex, includeSpacing);
                tblTacGia.setRowSelectionInterval(i, i);
                tblTacGia.scrollRectToVisible(cellRect);
                break;
            } else {
                currentSearch = 0;
                tblTacGia.clearSelection();
            }
        }
    }

    @Override
    public void insert() {
        try {
            TG.setTacGia(txtTenTacGia.getText());
            boolean sex;
            if (rdoNam.isSelected()) {
                sex = true;
            } else {
                sex = false;
            }
            TG.setGioiTinh(sex);
            TG.setNgaySinh(txtNgaySinh.getDate());
            if (CheckNull() == true) {
                for (int i = 0; i < listTG.size(); i++) {
                    if (listTG.get(i).getTacGia().equalsIgnoreCase(txtTenTacGia.getText()) && listTG.get(i).getNgaySinh().equals(txtNgaySinh.getDate())) {
                        int rep = JOptionPane.showConfirmDialog(null, "B???n C?? Mu???n Th??m T??c Gi??? N??y Kh??ng ?",
                                "Tr??ng T??c Gi???", JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.NO_OPTION) {
                            return;
                        }
                    }
                }

                tgDAO.insert(TG);
                JOptionPane.showMessageDialog(null, "Th??m Th??nh C??ng");
                fillToTable();
                addSuccessful = true;
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
        try {
            int a = tblTacGia.getSelectedRow();
            TG.setTacGia(txtTenTacGia.getText());
            boolean sex;
            if (rdoNam.isSelected()) {
                sex = true;
            } else {
                sex = false;
            }
            TG.setGioiTinh(sex);
            TG.setNgaySinh(txtNgaySinh.getDate());
            TG.setMaTG((int) listTG.get(a).getMaTG());

            if (CheckNull() == true) {
                tgDAO.update(TG);
                JOptionPane.showMessageDialog(null, "C???p Nh???t Th??nh C??ng");
                fillToTable();
                addSuccessful = true;
            } else {
                addSuccessful = false;
            }
        } catch (Exception e) {
            System.out.println(e);
            addSuccessful = false;
        }
    }

    @Override
    public void delete() {
        int x = tblTacGia.getSelectedRow();
        int rep = JOptionPane.showConfirmDialog(null, "B???n C?? Mu???n Xo?? T??c Gi??? N??y Kh??ng ?",
                "Confirm Message", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            int ID = (int) listTG.get(x).getMaTG();
            Sach tgSach = new SachDAO().findMaTG(String.valueOf(ID));
            System.out.println(tgSach);
            if (tgSach == null) {
                tgDAO.delete(ID);
                JOptionPane.showMessageDialog(null, "X??a th??nh c??ng !", "Th??ng B??o", JOptionPane.INFORMATION_MESSAGE);
                fillToTable();
            } else {
                System.out.println(tgSach);
                JOptionPane.showMessageDialog(null, "T??c Gi??? ??ang C?? S??ch Kh??ng Th??? Xo?? ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void clear() {
        txtTenTacGia.setText("");
        txtNgaySinh.setDate(null);
        tblTacGia.clearSelection();
        txtTim.setText("");
    }

    @Override
    public void editOn() {
        txtTenTacGia.setEnabled(true);
        txtNgaySinh.setEnabled(true);
        rdoNam.setEnabled(true);
        rdoNu.setEnabled(true);
    }

    @Override
    public void editOff() {
        txtTenTacGia.setEnabled(false);
        txtNgaySinh.setEnabled(false);
        rdoNam.setEnabled(false);
        rdoNu.setEnabled(false);
    }

    private boolean CheckNull() {
        if (txtTenTacGia.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "M???i B???n Nh???p T??n T??c Gi??? ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            txtTenTacGia.requestFocus();
            return false;
        } else if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(null, "M???i B???n Nh???p Ng??y Sinh Theo ?????nh D???ng dd/MM/yyyy ?", "C???nh B??o", JOptionPane.WARNING_MESSAGE);
            txtNgaySinh.requestFocusInWindow();
            return false;
        }
        return true;
    }
}
