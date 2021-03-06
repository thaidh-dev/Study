/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThongKe_TongHop;

// <editor-fold defaultstate="collapsed" desc="IMPORT ">
import HeThong.DangNhapFrame;
import Helper.DateHelper;
import Helper.JdbcHelper;
import Model.KhoaHoc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
// </editor-fold>

/**
 *
 * @author MSI
 */
public class DoanhThuChuyenDe extends JPanel {

    JTable tblDoanhThu, tblSum;
    DefaultTableModel dtmDoanhThu, dtmSum;
    JComboBox cbxYear;
    JTextField txtFrom, txtTo;
    JRadioButton radYear, radPeriod;
    ButtonGroup btnGroup;
    JButton btnView, btnExport;

    DateHelper dateHelper = new DateHelper();
    JdbcHelper jdbcHelper = new JdbcHelper();

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("VI", "VN"));

    public static boolean doneLoad = false;

    public DoanhThuChuyenDe() {
        addControls();
        addEvents();
        showWindow();
    }

    private void showWindow() {
        this.setSize(1100, 650);
    }

    // <editor-fold defaultstate="collapsed" desc="ADD CONTROLS ">
    private void addControls() {
        this.setLayout(new BorderLayout());

        //CHECK XEM THEO N??M HO???C KHO???NG TH???I GIAN
        JPanel pnViewTime = new JPanel(new BorderLayout());

        JPanel pnYear = new JPanel(new FlowLayout());
        radYear = new JRadioButton("Xem theo n??m: ");
        cbxYear = new JComboBox();
        JLabel lblEmp = new JLabel(); //C??n ?????i kho???ng c??ch, kh??ng ch???a n???i dung
        radYear.setFont(new Font("Segoe UI", 0, 18));
        cbxYear.setPreferredSize(new Dimension(200, 35));
        lblEmp.setPreferredSize(new Dimension(287, 35));
        ((JLabel) cbxYear.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        pnYear.add(radYear);
        pnYear.add(cbxYear);
        pnYear.add(lblEmp);

        JPanel pnPeriod = new JPanel(new FlowLayout());
        radPeriod = new JRadioButton("Xem theo th???i gian t???: ");
        txtFrom = new JTextField(10);
        txtTo = new JTextField(10);
        JLabel lblTo = new JLabel(" ?????n ");
        btnView = new JButton("Xem");

        radPeriod.setFont(new Font("Segoe UI", 0, 18));
        lblTo.setFont(new Font("Segoe UI", 0, 18));
        txtFrom.setPreferredSize(new Dimension(0, 35));
        txtTo.setPreferredSize(new Dimension(0, 35));
        btnView.setPreferredSize(new Dimension(120, 35));
        btnView.setFont(new Font("Segoe UI", 0, 16));
        txtFrom.setHorizontalAlignment(JTextField.CENTER);
        txtTo.setHorizontalAlignment(JTextField.CENTER);

        radYear.setPreferredSize(radPeriod.getPreferredSize());
        cbxYear.setPreferredSize(txtFrom.getPreferredSize());

        pnPeriod.add(radPeriod);
        pnPeriod.add(txtFrom);
        pnPeriod.add(lblTo);
        pnPeriod.add(txtTo);
        pnPeriod.add(btnView);

        pnViewTime.add(pnYear, BorderLayout.NORTH);
        pnViewTime.add(pnPeriod, BorderLayout.CENTER);
        this.add(pnViewTime, BorderLayout.NORTH);

        btnGroup = new ButtonGroup();
        btnGroup.add(radPeriod);
        btnGroup.add(radYear);
        radYear.setSelected(true);
        txtFrom.setEnabled(false);
        txtTo.setEnabled(false);
        btnView.setEnabled(false);

        //  B???NG TH???NG K?? DOANH THU THEO CHUY??N ?????
        dtmDoanhThu = new DefaultTableModel();
        dtmDoanhThu.addColumn("STT");
        dtmDoanhThu.addColumn("CHUY??N ?????");
        dtmDoanhThu.addColumn("S??? KH??A H???C");
        dtmDoanhThu.addColumn("S??? H???C VI??N");
        dtmDoanhThu.addColumn("DOANH THU");
        tblDoanhThu = new JTable(dtmDoanhThu) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                if (column == 1) {
                    c.setHorizontalAlignment(JLabel.LEFT);
                } else {
                    c.setHorizontalAlignment(JLabel.CENTER);

                }
                return c;
            }
        };
        tblDoanhThu.setAutoCreateRowSorter(true);
        JScrollPane scTblDoanhThu = new JScrollPane(tblDoanhThu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scTblDoanhThu, BorderLayout.CENTER);

        JTableHeader headerDoanhThu = tblDoanhThu.getTableHeader();
        ((DefaultTableCellRenderer) headerDoanhThu.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblDoanhThu.setSelectionBackground(Color.decode("#3a4d8f"));
        //c??n gi???a n???i dung table
        DefaultTableCellRenderer rendererDoanhThu = new DefaultTableCellRenderer() {
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
        rendererDoanhThu.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < tblDoanhThu.getColumnCount() - 1; i++) {
            tblDoanhThu.setDefaultRenderer(tblDoanhThu.getColumnClass(i), rendererDoanhThu);
        }

        tblDoanhThu.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblDoanhThu.getColumnModel().getColumn(1).setPreferredWidth(390);
        tblDoanhThu.getColumnModel().getColumn(2).setPreferredWidth(165);
        tblDoanhThu.getColumnModel().getColumn(3).setPreferredWidth(195);
        tblDoanhThu.getColumnModel().getColumn(4).setPreferredWidth(320);
        tblDoanhThu.setRowHeight(30);

        //Panel table t???ng
        JPanel pnTblSum = new JPanel(new BorderLayout());
        pnTblSum.setPreferredSize(new Dimension(0, 140));

        JPanel pnLblSum = new JPanel(new FlowLayout());
        JLabel lblSum = new JLabel("T???ng");
        lblSum.setFont(new Font("Segoe UI", 0, 26));
        dtmSum = new DefaultTableModel();
        dtmSum.addColumn("S??? CHUY??N ?????");
        dtmSum.addColumn("S??? KH??A");
        dtmSum.addColumn("S??? H???C VI??N");
        dtmSum.addColumn("DOANH THU");
        tblSum = new JTable(dtmSum);
        JScrollPane scTblSum = new JScrollPane(tblSum, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JTableHeader headerSum = tblSum.getTableHeader();
        ((DefaultTableCellRenderer) headerSum.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        //c??n gi???a n???i dung table
        DefaultTableCellRenderer rendererSum = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setFont(new Font("Segoe UI", 1, 14));
                }
                return c;
            }
        };
        rendererSum.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < tblSum.getColumnCount(); i++) {
            tblSum.setDefaultRenderer(tblSum.getColumnClass(i), rendererSum);
        }

        tblSum.setRowHeight(30);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnExport = new JButton("Xu???t file Excel");
        pnButton.add(btnExport);

        pnLblSum.add(lblSum);
        pnTblSum.add(pnLblSum, BorderLayout.NORTH);
        pnTblSum.add(scTblSum, BorderLayout.CENTER);
        pnTblSum.add(pnButton, BorderLayout.SOUTH);
        this.add(pnTblSum, BorderLayout.SOUTH);

        //set ph??m t???t
        btnView.setMnemonic(KeyEvent.VK_V);
//        btnView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_V,0), "View");
        //set tooltip
        btnView.setToolTipText("Ph??m t???t Alt + V");
        txtFrom.setToolTipText("dd-MM-yyyy");
        txtTo.setToolTipText("dd-MM-yyyy");
    }
    // </editor-fold>

    private void addEvents() {
        //load data to cbxYear
        loadDataToCbxYear();

        //load doanh thu m???c ?????nh
        if (cbxYear.getItemCount() > 0) {
            int year = (int) cbxYear.getItemAt(0);
            loadDataToTblDoanhThu(getListDoanhThu(year));
        }

        //Load t???ng m???c ?????nh
        loadDataToTblSum();

        doneLoad = true;

        // <editor-fold defaultstate="collapsed" desc="Ki???m tra l???a ch???n xem th???ng k?? theo n??m ho???c kho???ng th???i gian ">
        radPeriod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    modeView();

                    dtmDoanhThu.setRowCount(0);
                    loadDataToTblSum();;
                }
            }
        });
        radYear.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    modeView();

                    int year = (int) cbxYear.getSelectedItem();
                    loadDataToTblDoanhThu(getListDoanhThu(year));
                    loadDataToTblSum();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n n??t btnView ">
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDateFrom() && checkDateTo()) {
                    try {
                        if (dateHelper.toDate(txtTo.getText()).getTime() - dateHelper.toDate(txtFrom.getText()).getTime() >= 0) {
                            loadDataToTblDoanhThu(getListDoanhThu(txtFrom.getText(), txtTo.getText()));
                            loadDataToTblSum();
                        } else {
                            dtmDoanhThu.setRowCount(0);
                            loadDataToTblSum();
                            JOptionPane.showMessageDialog(null, "Ng??y k???t th??c ph???i l???n h??n ng??y b???t ?????u", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                            txtTo.requestFocus();
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    dtmDoanhThu.setRowCount(0);
                    loadDataToTblSum();
                    JOptionPane.showMessageDialog(null, "Ng??y ph???i c?? ?????nh d???ng ng??y-th??ng-n??m", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n up down date txtFrom ">
        txtFrom.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (checkDateFrom()) {
                        try {
                            if (dateHelper.toDate(txtTo.getText()).getTime() - dateHelper.toDate(txtFrom.getText()).getTime() > 0) {
                                UpDownDate(txtFrom, 1);
                            }
                        } catch (ParseException ex) {
//                            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                            UpDownDate(txtFrom, 1);
                        }
                    } else {
                        txtFrom.setText(dateHelper.now());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (checkDateFrom()) {
                        UpDownDate(txtFrom, -1);
                    } else {
                        txtFrom.setText(dateHelper.now());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.isShiftDown()) {
                    txtTo.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n up down date txtTo ">
        txtTo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (checkDateTo()) {
                        UpDownDate(txtTo, 1);
                    } else {
                        txtTo.setText(dateHelper.now());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (checkDateTo()) {
                        try {
                            if (dateHelper.toDate(txtTo.getText()).getTime() - dateHelper.toDate(txtFrom.getText()).getTime() > 0) {
                                UpDownDate(txtTo, -1);
                            }
                        } catch (ParseException ex) {
//                            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                            UpDownDate(txtTo, -1);
                        }
                    } else {
                        txtTo.setText(dateHelper.now());
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT && e.isShiftDown()) {
                    txtFrom.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n thay ?????i n??m cbxYear ">
        cbxYear.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int year = (int) cbxYear.getSelectedItem();
                    loadDataToTblDoanhThu(getListDoanhThu(year));
                    loadDataToTblSum();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n export file excel ">
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                export();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n key cho txtForm ">
        txtFrom.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (checkDateFrom() && checkDateTo()) {
                        try {
                            if (dateHelper.toDate(txtTo.getText()).getTime() - dateHelper.toDate(txtFrom.getText()).getTime() >= 0) {
                                loadDataToTblDoanhThu(getListDoanhThu(txtFrom.getText(), txtTo.getText()));
                                loadDataToTblSum();
                            } else {
                                JOptionPane.showMessageDialog(null, "Ng??y k???t th??c ph???i l???n h??n ng??y b???t ?????u", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                                txtTo.requestFocus();
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ng??y ph???i c?? ?????nh d???ng dd-MM-yyyy", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="S??? ki???n key cho txtTo ">
        txtTo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (checkDateFrom() && checkDateTo()) {
                        try {
                            if (dateHelper.toDate(txtTo.getText()).getTime() - dateHelper.toDate(txtFrom.getText()).getTime() >= 0) {
                                loadDataToTblDoanhThu(getListDoanhThu(txtFrom.getText(), txtTo.getText()));
                                loadDataToTblSum();
                            } else {
                                JOptionPane.showMessageDialog(null, "Ng??y k???t th??c ph???i l???n h??n ng??y b???t ?????u", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                                txtTo.requestFocus();
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ng??y ph???i c?? ?????nh d???ng dd-MM-yyyy", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>
    }

    // <editor-fold defaultstate="collapsed" desc="CHECK XEM TH???NG K?? THEO N??M HO???C KHO???NG TH???I GIAN ">
    private void modeView() {
        if (radYear.isSelected()) {
            txtFrom.setEnabled(false);
            txtTo.setEnabled(false);
            btnView.setEnabled(false);
            cbxYear.setEnabled(true);
        } else {
            txtFrom.setEnabled(true);
            txtTo.setEnabled(true);
            btnView.setEnabled(true);
            cbxYear.setEnabled(false);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CHECK DATE ">
    private boolean checkDateFrom() {
        String dateFromStr = txtFrom.getText();

        if (!dateFromStr.matches("^[0-9\\-]+$")) {
            txtFrom.requestFocus();
            return false;
        }

        try {
            Date dateFrom = dateHelper.toDate(dateFromStr);
        } catch (ParseException ex) {
            txtFrom.requestFocus(true);
            return false;
        }

        return true;
    }

    private boolean checkDateTo() {
        String dateToStr = txtTo.getText();

        if (!dateToStr.matches("^[0-9\\-]+$")) {
            txtTo.requestFocus();
            return false;
        }

        try {
            Date dateTo = dateHelper.toDate(dateToStr);
        } catch (ParseException ex) {
            txtTo.requestFocus();
            return false;
        }

        return true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="UP DOWN DATE ">
    private void UpDownDate(JTextField txt, int i) {
        String oldDate = txt.getText();

        try {
            String newDate = dateHelper.changeDate(oldDate, i);

            txt.setText(newDate);
        } catch (ParseException ex) {
            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GET LIST N??M ">
    private List<Integer> getListYear() {
        List<Integer> listYear = new ArrayList<>();

        String sql = "SELECT DISTINCT YEAR(dbo.KhoaHoc.ngayTao) FROM dbo.KhoaHoc ORDER BY YEAR(dbo.KhoaHoc.ngayTao) DESC";

        try {
            ResultSet rs = jdbcHelper.executeQuery(sql);

            while (rs.next()) {
                listYear.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listYear;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LOAD D??? LI???U V??O cbxYear ">
    private void loadDataToCbxYear() {
        cbxYear.removeAllItems();
        List<Integer> listYear = getListYear();
        for (Integer integer : listYear) {
            cbxYear.addItem(integer);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GET LIST DOANH THU THEO N??M ">
    private List<Object[]> getListDoanhThu(int year) {
        List<Object[]> listDoanhThu = new ArrayList<>();

        String sql = "{call sp_thongKeDoanhThuChuyenDe (?)}";

        try {
            ResultSet rs = jdbcHelper.executeQuery(sql, year);

            while (rs.next()) {
                listDoanhThu.add(readRs(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listDoanhThu;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GET LIST DOANH THU THEO KHO???NG TH???I GIAN ">
    private List<Object[]> getListDoanhThu(String timeForm, String timeTo) {
        List<Object[]> listDoanhThu = new ArrayList<>();

        String timeStart = null;
        String timeEnd = null;
        try {
            timeStart = dateHelper.castDateForm1ToForm2(timeForm);
            timeEnd = dateHelper.castDateForm1ToForm2(timeTo);
        } catch (ParseException ex) {
            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "{call sp_thongKeDoanhThuChuyenDeWithTime (?,?)}";

        try {
            ResultSet rs = jdbcHelper.executeQuery(sql, timeStart, timeEnd);

            while (rs.next()) {
                listDoanhThu.add(readRs(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listDoanhThu;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="?????C D??? LI???U T??? RS ">
    private Object[] readRs(ResultSet rs) throws SQLException {
        int stt = rs.getInt(1);
        String tenC?? = rs.getString(2);
        int soKH = rs.getInt(3);
        int soHV = rs.getInt(4);
        int doanhThu = rs.getInt(5) * 1000;

        return new Object[]{stt, tenC??, soKH, soHV, numberFormat.format(doanhThu)};
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LOAD D??? LI???U L??N TABLE DOANH THU ">
    private void loadDataToTblDoanhThu(List<Object[]> listDoanhThu) {

        dtmDoanhThu.setRowCount(0);

        for (Object object : listDoanhThu) {
            dtmDoanhThu.addRow((Object[]) object);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LOAD D??? LI???U L??N TABLE T???NG ">
    private void loadDataToTblSum() {
        dtmSum.setRowCount(0);
        if (dtmDoanhThu.getRowCount() > 0) {
            int soC?? = tblDoanhThu.getRowCount();
            int soKH = 0;
            int soHV = 0;
            int doanhThu = 0;

            for (int i = 0; i < soC??; i++) {
                String dtStr = (String) dtmDoanhThu.getValueAt(i, 4);

                soKH += (int) dtmDoanhThu.getValueAt(i, 2);
                soHV += (int) dtmDoanhThu.getValueAt(i, 3);
                try {
                    doanhThu += castMoneyToInt(dtStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            dtmSum.addRow(new Object[]{soC??, soKH, soHV, castIntToMoney(doanhThu)});
        } else {
            dtmSum.addRow(new Object[]{0, 0, 0, "0??"});
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CONVERT STRING TI???N SANG INT ">
    private long castMoneyToInt(String money) throws ParseException {
        return (long) numberFormat.parse(money);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CONVERT INT SANG STRING TI???N ">
    private String castIntToMoney(int money) {
        return numberFormat.format(money);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="REFRESH D??? LI???U ">
    public void refresh() {
        try {
            if (radYear.isSelected()) {
                int year = (int) cbxYear.getSelectedItem();

                loadDataToCbxYear();

                for (int i = 0; i < cbxYear.getItemCount(); i++) {
                    if ((int) cbxYear.getItemAt(i) == year) {
                        cbxYear.setSelectedItem(year);
                        loadDataToTblDoanhThu(getListDoanhThu(year));
                        loadDataToTblSum();
                        return;
                    }
                }

                loadDataToTblDoanhThu(getListDoanhThu(year));
                loadDataToTblSum();
            } else {
                if (checkDateFrom() && checkDateTo()) {
                    try {
                        if (dateHelper.toDate(txtTo.getText()).getTime() - dateHelper.toDate(txtFrom.getText()).getTime() >= 0) {
                            loadDataToTblDoanhThu(getListDoanhThu(txtFrom.getText(), txtTo.getText()));
                            loadDataToTblSum();
                        } else {
                            dtmDoanhThu.setRowCount(0);
                            loadDataToTblSum();
                            JOptionPane.showMessageDialog(null, "Ng??y k???t th??c ph???i l???n h??n ng??y b???t ?????u", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                            txtTo.requestFocus();
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    dtmDoanhThu.setRowCount(0);
                    loadDataToTblSum();
                    JOptionPane.showMessageDialog(null, "Ng??y ph???i c?? ?????nh d???ng ng??y-th??ng-n??m", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="EXPORT EXCEL ">
    private void exportExcel(File file) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet();
        sheet.trackAllColumnsForAutoSizing();

        writeHeader(sheet);

        writeContent(sheet);

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            desktop.open(file);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="WRITE HEADER ">
    private void writeHeader(SXSSFSheet sheet) throws FileNotFoundException, IOException {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 8));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 8));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 4));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 7, 8));

        SXSSFRow row;
        SXSSFCell cell;

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle1(sheet));
        cell.setCellValue("H??? TH???NG QU???N L?? KH??A H???C");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle1(sheet));
        cell.setCellValue("NH??M APT");

        if (radYear.isSelected()) {
            row = sheet.createRow(5);
            cell = row.createCell(0);
            cell.setCellStyle(createCellStyle1(sheet));
            cell.setCellValue("TH???NG K?? DOANH THU CHUY??N ????? N??M " + cbxYear.getSelectedItem());
        } else {
            row = sheet.createRow(5);
            cell = row.createCell(0);
            cell.setCellStyle(createCellStyle1(sheet));
            cell.setCellValue("TH???NG K?? DOANH THU CHUY??N ?????");

            row = sheet.createRow(6);
            cell = row.createCell(0);
            cell.setCellStyle(createCellStyle1(sheet));
            cell.setCellValue("T??? NG??Y" + txtFrom.getText() + " ?????N NG??Y " + txtTo.getText());
        }

        row = sheet.createRow(9);
        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue("STT");

        cell = row.createCell(1);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue("CHUY??N ?????");

        cell = row.createCell(2);
        cell.setCellStyle(createCellStyle2(sheet));

        cell = row.createCell(3);
        cell.setCellStyle(createCellStyle2(sheet));

        cell = row.createCell(4);
        cell.setCellStyle(createCellStyle2(sheet));

        cell = row.createCell(5);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue("S??? KH??A");

        cell = row.createCell(6);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue("S??? HV");

        cell = row.createCell(7);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue("DOANH THU");

        cell = row.createCell(8);
        cell.setCellStyle(createCellStyle2(sheet));

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="WRITE CONTENT ">
    private void writeContent(SXSSFSheet sheet) {
        List<Object[]> listDoanhThu = new ArrayList<>();

        if (radYear.isSelected()) {
            int year = (int) cbxYear.getSelectedItem();
            listDoanhThu = getListDoanhThu(year);
        } else {
            listDoanhThu = getListDoanhThu(txtFrom.getText(), txtTo.getText());
        }

        int indexRow = 10;

        SXSSFRow row;
        SXSSFCell cell;

        for (Object[] obj : listDoanhThu) {
            sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, 1, 4));
            sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, 7, 8));

            row = sheet.createRow(indexRow);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellStyle(createCellStyle3(sheet));
            cell.setCellValue((int) obj[0]);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(createCellStyle3(sheet));
            cell.setCellValue((String) obj[1]);

            cell = row.createCell(2);
            cell.setCellStyle(createCellStyle3(sheet));

            cell = row.createCell(3);
            cell.setCellStyle(createCellStyle3(sheet));

            cell = row.createCell(4);
            cell.setCellStyle(createCellStyle3(sheet));

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellStyle(createCellStyle3(sheet));
            cell.setCellValue((int) obj[2]);

            cell = row.createCell(6);
            cell.setCellStyle(createCellStyle3(sheet));
            cell.setCellValue((int) obj[3]);

            cell = row.createCell(7);
            cell.setCellStyle(createCellStyle3(sheet));
            try {
                cell.setCellValue(castMoneyToInt((String) obj[4]));
            } catch (ParseException ex) {
                Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
            }

            cell = row.createCell(8);
            cell.setCellStyle(createCellStyle3(sheet));

            indexRow++;
        }

        //SUM
        sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, 1, 4));
        sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, 7, 8));

        row = sheet.createRow(indexRow);

        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue("T???ng");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue((int) tblSum.getValueAt(0, 0));

        cell = row.createCell(2);
        cell.setCellStyle(createCellStyle2(sheet));

        cell = row.createCell(3);
        cell.setCellStyle(createCellStyle2(sheet));

        cell = row.createCell(4);
        cell.setCellStyle(createCellStyle2(sheet));

        cell = row.createCell(5, CellType.NUMERIC);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue((int) tblSum.getValueAt(0, 1));

        cell = row.createCell(6, CellType.NUMERIC);
        cell.setCellStyle(createCellStyle2(sheet));
        cell.setCellValue((int) tblSum.getValueAt(0, 2));

        cell = row.createCell(7);
        cell.setCellStyle(createCellStyle2(sheet));
        try {
            cell.setCellValue(castMoneyToInt((String) tblSum.getValueAt(0, 3)));
        } catch (ParseException ex) {
            Logger.getLogger(DoanhThuChuyenDe.class.getName()).log(Level.SEVERE, null, ex);
        }

        cell = row.createCell(8);
        cell.setCellStyle(createCellStyle2(sheet));

        sheet.addMergedRegion(new CellRangeAddress(indexRow + 2, indexRow + 2, 5, 7));
        row = sheet.createRow(indexRow + 2);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("NG?????I TH???NG K??");
        cell.setCellStyle(createCellStyle1(sheet));

        sheet.addMergedRegion(new CellRangeAddress(indexRow + 8, indexRow + 8, 5, 7));
        row = sheet.createRow(indexRow + 8);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue(DangNhapFrame.nvLogin.getHoTen());
        cell.setCellStyle(createCellStyle1(sheet));
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="AUTO RESIZE ">
    private static void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREATE CELLSTYLE1 ">
    private CellStyle createCellStyle1(SXSSFSheet sheet) {
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="CREATE CELLSTYLE2 ">
    private CellStyle createCellStyle2(SXSSFSheet sheet) {
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 11); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" DESCRIPTION ">
    private CellStyle createCellStyle3(SXSSFSheet sheet) {
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 11); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
    // </editor-fold>
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="METHOD CHO btnExport">
    private void export() {
        if (tblDoanhThu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ch??a c?? d??? li???u ????? xu???t ra file Excel. Vui l??ng ki???m tra l???i!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = fileChooser.showSaveDialog(null);

        String path = "";
        if (radYear.isSelected()) {
            path = fileChooser.getSelectedFile() + "\\Th???ng k?? doanh thu n??m " + cbxYear.getSelectedItem() + "_" + System.currentTimeMillis() + " .xlsx";
        } else {
            path = fileChooser.getSelectedFile() + "\\Th???ng k?? doanh thu t??? ng??y " + txtFrom.getText() + " ?????n ng??y " + txtTo.getText() + "_" + System.currentTimeMillis() + " .xlsx";
        }

        if (i == fileChooser.APPROVE_OPTION) {
            try {
                File file = new File(path);

                exportExcel(file);
            } catch (IOException ex) {
                Logger.getLogger(BangDiemKhoaHoc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // </editor-fold>
}
