package panel;

import dao.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import object.*;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class LietKeSachTheoNgonNgu implements Khung {
    JPanel pnlThan, pnlThan2, pnlChucNang, pnlTieuDe_ChucNang;
    JTable tbl;
    JScrollPane scr;
    JLabel lblTieuDe, lblNgonNgu;
    JComboBox cboNgonNgu;
    JButton btnKetXuat;
    GridBagConstraints gbc;
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private SachDAO sachDAO = new SachDAO();

    public LietKeSachTheoNgonNgu() {
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
        lblTieuDe = new JLabel("Liệt kê sách theo ngôn ngữ");
        lblNgonNgu = new JLabel("Ngôn ngữ");
        pnlChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        cboNgonNgu = new JComboBox(new String[]{"Tiếng Việt", "Tiếng Anh", "Tiếng Trung", "Tiếng Nga", "Tiếng Hàn"});
//        txtCbo = (JTextField) cboChuDe.getEditor().getEditorComponent();
        btnKetXuat = Khung.taoButton("Kết xuất");
        Class lop[] = new Class[]{Integer.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class};
        String tenCot[] = {"STT", "Tiêu đề sách", "Số lượng", "Giá tiền", "Năm xuất bản", "Số trang"};
        tbl = Khung.taoTable(lop, tenCot);
        scr = new JScrollPane(tbl);
        gbc = new GridBagConstraints();
    }

    @Override
    public void taoDang() {
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);
        
        cboNgonNgu.setPreferredSize(new Dimension(200, 25));

        tbl.setIntercellSpacing(new Dimension(10, 1));
        tbl.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(100);
    }

    @Override
    public void event() {
        cboNgonNgu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                }
            }
        });
        
        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Bảng hiện đang không có thông tin", "Lưu ý", 2);
                }
                else {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Liệt kê sách theo ngôn ngữ");

                    // tiêu đề
                    XSSFFont fontTitle = workbook.createFont();
                    fontTitle.setBold(true);
                    fontTitle.setFontName("tahoma");
                    fontTitle.setFontHeight(20);

                    CellStyle cellStyleTitle = workbook.createCellStyle();
                    cellStyleTitle.setFont(fontTitle);
                    cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);

                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

                    Row r0 = sheet.createRow(0);
                    Cell c0r1 = r0.createCell(0);
                    c0r1.setCellValue("Bảng liệt kê sách theo ngôn ngữ");
                    c0r1.setCellStyle(cellStyleTitle);

                    // lọc
                    XSSFFont fontLoc = workbook.createFont();
                    fontLoc.setBold(false);
                    fontLoc.setFontName("tahoma");
                    fontLoc.setFontHeight(18);

                    sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 5));
                    CellStyle cellStyleLoc = workbook.createCellStyle();
                    cellStyleLoc.setFont(fontLoc);
                    cellStyleLoc.setAlignment(HorizontalAlignment.CENTER);

                    Row r2 = sheet.createRow(2);
                    Cell c0r2 = r2.createCell(0);
                    c0r2.setCellValue("Ngôn ngữ: " + cboNgonNgu.getSelectedItem());
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
                    for (int i = 0; i < ((DefaultTableModel) tbl.getModel()).getColumnCount(); i++) {
                        Cell cellRow4 = r4.createCell(i);
                        cellRow4.setCellValue(((DefaultTableModel) tbl.getModel()).getColumnName(i));
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

                    for (int i = 0; i < tbl.getRowCount(); i++) {
                        Row rowNormal = sheet.createRow(i+5);
                        for (int j = 0; j < 6; j++) {
                            Cell cellNormal = rowNormal.createCell(j);
                            Object value = tbl.getValueAt(i, j);
                            cellNormal.setCellValue(String.valueOf(value));

                            if (i == tbl.getRowCount()-1) {
                                cellNormal.setCellStyle(cellStyleNormal);
                            }
                            else {
                                cellNormal.setCellStyle(cellStyleNormal1);
                            }
                        }
                    }

                    sheet.setColumnWidth(0, 2000);
                    sheet.setColumnWidth(1, 27000);
                    sheet.setColumnWidth(2, 5000);
                    sheet.setColumnWidth(3, 7000);
                    sheet.setColumnWidth(4, 5000);
                    sheet.setColumnWidth(5, 5000);

                    try {
                        String home = System.getProperty("user.home");
                        String tenFile = "Liệt kê sách theo ngôn ngữ - " + System.currentTimeMillis() + ".xlsx";
                        File fileExcel = new File(home+"\\Desktop", tenFile);
                        FileOutputStream outputStream = new FileOutputStream(fileExcel);

                        workbook.write(outputStream);
                        workbook.close();
                        outputStream.close();

                        File file = new File("C:\\Users\\Admin\\Desktop\\"+tenFile);
                        Desktop.getDesktop().open(file);
                    } 
                    catch (Exception ex) {
                    }
                }
            }
        });
    }

    @Override
    public void add() {
        JComponent a[] = {lblNgonNgu, cboNgonNgu, btnKetXuat};
        Khung.addChung(pnlChucNang, null, a, 1, 3, null, 0);
        
        JComponent b[] = {lblTieuDe, pnlChucNang};
        Khung.addChung(pnlTieuDe_ChucNang, gbc, b, 2, 1, new Insets(10, 0, 0, 0), GridBagConstraints.CENTER);
        
        pnlThan2.add(pnlTieuDe_ChucNang, BorderLayout.NORTH);
        pnlThan2.add(scr, BorderLayout.CENTER);
        
        pnlThan.add(pnlThan2);
    }

    @Override
    public void fillToTable() {
        List<Sach> lstSach = sachDAO.lietKeSachTheoNgonNgu((String) cboNgonNgu.getSelectedItem());

        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int x = 1;
        NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        
        for (Sach s : lstSach) {
            String giaTien = n.format(s.getGiaTien());
            Object rows[] = {x, s.getTieuDe(), s.getSoLuong(), giaTien, s.getNXB(), s.getSoTrang()}; 
            model.addRow(rows);
            x++;
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
