package panel;

import dao.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import object.Sach;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThongKeSoPhieuMuonTheoThang implements Khung {
    JPanel pnlThan, pnlThan2, pnlThang_PhieuMuon, pnlThang, pnlPhieuMuon, pnlChucNang, pnlTieuDe_ChucNang;
    JTable tblThang, tblPhieuMuon;
    JScrollPane scrThang, scrPhieuMuon;
    JLabel lblTieuDe, lblNam;
    JButton btnKetXuat;
    JComboBox cboNam;
    GridBagConstraints gbc;
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private SachDAO sachDAO = new SachDAO();

    public ThongKeSoPhieuMuonTheoThang() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }

    @Override
    public void taoDoiTuong() {
        pnlThan = new JPanel(new CardLayout(5, 5));
        pnlThan2 = new JPanel(new BorderLayout(0, 20));
        pnlTieuDe_ChucNang = new JPanel(new GridBagLayout());
        pnlChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlThang_PhieuMuon = new JPanel(new BorderLayout(20, 0));
        pnlThang = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Số lượt mượn theo tháng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlPhieuMuon = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh sách", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblTieuDe = new JLabel("Thống kê số phiếu mượn theo tháng");
        lblNam = new JLabel("Năm");
        btnKetXuat = Khung.taoButton("Kết xuất");
        cboNam = new JComboBox();
        Class classThang[] = {Integer.class, Integer.class};
        String tenCotThang[] = {"Tháng", "Số lượt mượn"};
        tblThang = Khung.taoTable(classThang, tenCotThang);
        scrThang = Khung.taoJScrollPane(tblThang, null, new Dimension(300, 0));

        Class classPhieuMuon[] = {Integer.class, String.class, String.class, String.class, String.class};
        String tenCotPhieuMuon[] = {"STT", "Thẻ độc giả", "Tên độc giả", "Ngày mượn", "Ngày hẹn"};
        tblPhieuMuon = Khung.taoTable(classPhieuMuon, tenCotPhieuMuon);
        scrPhieuMuon = Khung.taoJScrollPane(tblPhieuMuon, null, new Dimension());
        gbc = new GridBagConstraints();
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);
        
        tblPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblPhieuMuon.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblPhieuMuon.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tblPhieuMuon.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        tblPhieuMuon.getColumnModel().getColumn(4).setCellRenderer(dtcr);
    }

    @Override
    public void event() {
        fillToTable();
        
        cboNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                    tblThang.changeSelection(0, 0, false, false);
                    fillToTblPhieuMuon();
                }
            }
        });
        
        tblThang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fillToTblPhieuMuon();
            }
        });
    
        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblThang.getSelectedRow();
                String thang, nam;
                
                thang = String.valueOf(tblThang.getValueAt(index, 0));
                nam = String.valueOf(cboNam.getSelectedItem());
                
                if (tblPhieuMuon.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Tháng " + thang + " không có lượt mượn nào", "Lưu ý", 2);
                }
                else {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Liệt kê phiếu mượn theo tháng");

                    // tiêu đề
                    XSSFFont fontTitle = workbook.createFont();
                    fontTitle.setBold(true);
                    fontTitle.setFontName("tahoma");
                    fontTitle.setFontHeight(20);

                    CellStyle cellStyleTitle = workbook.createCellStyle();
                    cellStyleTitle.setFont(fontTitle);
                    cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);
                 
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

                    Row r0 = sheet.createRow(0);
                    Cell c0r1 = r0.createCell(0);
                    c0r1.setCellValue("Bảng liệt kê phiếu mượn theo tháng");
                    c0r1.setCellStyle(cellStyleTitle);
                    
                    // lọc
                    XSSFFont fontLoc = workbook.createFont();
                    fontLoc.setBold(false);
                    fontLoc.setFontName("tahoma");
                    fontLoc.setFontHeight(18);

                    sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));
                    CellStyle cellStyleLoc = workbook.createCellStyle();
                    cellStyleLoc.setFont(fontLoc);
                    cellStyleLoc.setAlignment(HorizontalAlignment.CENTER);

                    Row r2 = sheet.createRow(2);
                    Cell c0r2 = r2.createCell(0);
                    c0r2.setCellValue("Thời gian: " + thang + "-" + nam);
                    c0r2.setCellStyle(cellStyleLoc);

                    // tên cột
                    XSSFFont fontTenCot = workbook.createFont();
                    fontTenCot.setBold(true);
                    fontTenCot.setFontName("tahoma");
                    fontTenCot.setFontHeight(14);
                    fontTenCot.setColor(IndexedColors.BLACK.getIndex());

                    CellStyle cellStyleTenCot = workbook.createCellStyle();
                    cellStyleTenCot.setAlignment(HorizontalAlignment.CENTER);
                    cellStyleTenCot.setFont(fontTenCot);
                    cellStyleTenCot.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                    cellStyleTenCot.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cellStyleTenCot.setBorderTop(BorderStyle.MEDIUM);
                    cellStyleTenCot.setTopBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyleTenCot.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyleTenCot.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyleTenCot.setBorderRight(BorderStyle.MEDIUM);
                    cellStyleTenCot.setRightBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyleTenCot.setBorderBottom(BorderStyle.MEDIUM);
                    cellStyleTenCot.setBottomBorderColor(IndexedColors.BLACK.getIndex());

                    Row r4 = sheet.createRow(4);
                    for (int i = 0; i < ((DefaultTableModel) tblPhieuMuon.getModel()).getColumnCount(); i++) {
                        Cell cellRow4 = r4.createCell(i);
                        cellRow4.setCellValue(((DefaultTableModel) tblPhieuMuon.getModel()).getColumnName(i));
                        cellRow4.setCellStyle(cellStyleTenCot);
                    }

                    // nội dung bảng
                    XSSFFont fontNormal = workbook.createFont();
                    fontNormal.setBold(false);
                    fontNormal.setFontName("tahoma");
                    fontNormal.setFontHeight(14);

                    CellStyle cellStyleNormal = workbook.createCellStyle();
                    cellStyleNormal.setFont(fontNormal);
                    cellStyleNormal.setAlignment(HorizontalAlignment.CENTER);
                    cellStyleNormal.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyleNormal.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyleNormal.setBorderRight(BorderStyle.MEDIUM);
                    cellStyleNormal.setRightBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyleNormal.setBorderBottom(BorderStyle.MEDIUM);
                    cellStyleNormal.setBottomBorderColor(IndexedColors.BLACK.getIndex());

                    CellStyle cellStyleNormal1 = workbook.createCellStyle();
                    cellStyleNormal1.setFont(fontNormal);
                    cellStyleNormal1.setAlignment(HorizontalAlignment.CENTER);
                    cellStyleNormal1.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyleNormal1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyleNormal1.setBorderRight(BorderStyle.MEDIUM);
                    cellStyleNormal1.setRightBorderColor(IndexedColors.BLACK.getIndex());

                    for (int i = 0; i < tblPhieuMuon.getRowCount(); i++) {
                        Row rowNormal = sheet.createRow(i+5);
                        for (int j = 0; j < 5; j++) {
                            Cell cellNormal = rowNormal.createCell(j);
                            Object value = tblPhieuMuon.getValueAt(i, j);
                            cellNormal.setCellValue(String.valueOf(value));

                            if (i == tblPhieuMuon.getRowCount()-1) {
                                cellNormal.setCellStyle(cellStyleNormal);
                            }
                            else {
                                cellNormal.setCellStyle(cellStyleNormal1);
                            }
                        }
                    }

                    sheet.setColumnWidth(0, 2000);
                    sheet.setColumnWidth(1, 7000);
                    sheet.setColumnWidth(2, 10000);
                    sheet.setColumnWidth(3, 5000);
                    sheet.setColumnWidth(4, 5000);

                    try {
                        String home = System.getProperty("user.home");
                        String tenFile = "Liệt kê phiếu mượn theo tháng" + " - " + System.currentTimeMillis() + ".xlsx";
                        File fileExcel = new File(home+"\\Desktop", tenFile);
                        FileOutputStream outputStream = new FileOutputStream(fileExcel);

                        workbook.write(outputStream);
                        workbook.close();
                        outputStream.close();

//                        File file = new File("C:\\Users\\Admin\\Desktop\\"+tenFile);
                        Desktop.getDesktop().open(fileExcel);
                    } 
                    catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }

    @Override
    public void add() {
        fillToComboBox();
        
        pnlChucNang.add(lblNam);
        pnlChucNang.add(cboNam);
        pnlChucNang.add(btnKetXuat);
        
        JComponent a[] = {lblTieuDe, pnlChucNang};
        Khung.addChung(pnlTieuDe_ChucNang, gbc, a, 2, 1, new Insets(10, 0, 0, 0), 10);
        
        pnlThang.add(scrThang);
        pnlPhieuMuon.add(scrPhieuMuon);
        
        pnlThang_PhieuMuon.add(pnlThang, BorderLayout.WEST);
        pnlThang_PhieuMuon.add(pnlPhieuMuon, BorderLayout.CENTER);
        
        pnlThan2.add(pnlTieuDe_ChucNang, BorderLayout.NORTH);
        pnlThan2.add(pnlThang_PhieuMuon, BorderLayout.CENTER);
        
        pnlThan.add(pnlThan2);
    }

    @Override
    public void fillToTable() {
        if (cboNam.getSelectedItem() != null) {
            try {
                int nam = Integer.parseInt(String.valueOf(cboNam.getSelectedItem()));
                List<Object[]> lst = ThongKeDAO.soLuongPhieuMuonTheoThang(nam);
                DefaultTableModel model = (DefaultTableModel) tblThang.getModel();
                model.setRowCount(0);

                for (Object row[] : lst) {
                    model.addRow(row);
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void fillToComboBox() {
        cboNam.removeAllItems();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        int nam = Integer.parseInt(format.format(now));
        for (int i = 0; i < 5; i++) {
            cboNam.addItem(nam - i);
        }
    }

    public void fillToTblPhieuMuon() {
        int index = tblThang.getSelectedRow();
                
        if (index != -1) {
            int thang = Integer.parseInt(String.valueOf(tblThang.getValueAt(index, 0)));
            int nam = Integer.parseInt(String.valueOf(cboNam.getSelectedItem()));

            List<Object[]> lst = ThongKeDAO.danhSachPhieuMUonTheoThang(thang, nam);
            DefaultTableModel model = (DefaultTableModel) tblPhieuMuon.getModel();
            model.setRowCount(0);

            int x = 1;
            for (Object o[] : lst) {
                Object row[] = new Object[o.length+1];
                row[0] = x;
                for (int i = 0; i < o.length; i++) {
                    row[i+1] = o[i];
                }
                model.addRow(row);
                x++;
            }
        }
    }
    
    @Override
    public void showItem(int a) {
    }

    @Override
    public void search() {
    }

    @Override
    public void insert() {
    }

    @Override
    public void update() {
    }

    @Override
    public void delete() {
    }

    @Override
    public void clear() {
    }

    @Override
    public void editOn() {
    }

    @Override
    public void editOff() {
    }
}
