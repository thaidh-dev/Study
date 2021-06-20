package panel;

import dao.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
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

public class ThongKeSach implements Khung {
    JPanel pnlThan, pnlThan2, pnlChucNang, pnlChucNang_Tbl, pnlTbl, pnlLietKe, pnlLietKe2;
    JTable tblTrai, tblPhai, tblLietKe;
    JScrollPane scrTrai, scrPhai, scrLietKe;
    JComboBox cboChuDe;
    JButton btnKetXuat;
    GridBagConstraints gbc;
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private SachDAO sachDAO = new SachDAO();

    public ThongKeSach() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        pnlThan = new JPanel(new CardLayout(5, 5));
        pnlThan2 = new JPanel(new BorderLayout(0, 20));
        pnlChucNang_Tbl = new JPanel(new BorderLayout());
        pnlChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        cboChuDe = new JComboBox();
        btnKetXuat = Khung.taoButton("Kết xuất");
        Class lop[] = {Integer.class, String.class, Integer.class};
        String tenCot[] = {"STT", "Chủ đề", "Số lượng sách"};
        tblTrai = Khung.taoTable(lop, tenCot);
        scrTrai = Khung.taoJScrollPane(tblTrai, null, new Dimension(0, 200));
        tblPhai = Khung.taoTable(lop, tenCot);
        scrPhai = Khung.taoJScrollPane(tblPhai, null, new Dimension(0, 200));
        gbc = new GridBagConstraints();
        pnlTbl = new JPanel(new GridLayout(1, 2, 5, 0));
        
        pnlLietKe = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh sách các sách", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlLietKe2 = new JPanel(new BorderLayout());
        Class lopLietKe[] = new Class[]{Integer.class, String.class, Integer.class, Integer.class, String.class, Integer.class};
        String tenCotLietKe[] = {"STT", "Tiêu đề sách", "Số lượng", "Giá tiền", "Ngôn ngữ", "Số trang"};
        tblLietKe = Khung.taoTable(lopLietKe, tenCotLietKe);
        scrLietKe = new JScrollPane(tblLietKe);
    }

    @Override
    public void taoDang() {
        scrLietKe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        cboChuDe.setPreferredSize(new Dimension(200, 25));
        
        JTable tbl[] = {tblTrai, tblPhai};
        
        for (int i = 0; i < tbl.length; i++) {
            tbl[i].getColumnModel().getColumn(0).setPreferredWidth(0);
            tbl[i].getColumnModel().getColumn(1).setPreferredWidth(300);
            tbl[i].getColumnModel().getColumn(2).setPreferredWidth(100);
        }
        
        tblLietKe.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblLietKe.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblLietKe.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblLietKe.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblLietKe.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblLietKe.getColumnModel().getColumn(5).setPreferredWidth(100);

    }

    @Override
    public void event() {
        cboChuDe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    tblTrai.getColumnModel().getColumn(1).setHeaderValue(cboChuDe.getSelectedItem());
                    tblPhai.getColumnModel().getColumn(1).setHeaderValue(cboChuDe.getSelectedItem());
                    tblTrai.revalidate();
                    tblTrai.repaint();
                    tblPhai.revalidate();
                    tblPhai.repaint();
                    fillToTable();
                    if (tblTrai.getRowCount() != 0) {
                        fillToTableLietKe(0, (String) tblTrai.getValueAt(0, 1));
                    }
                }
            }
        });
        
        cboChuDe.addItem("Chủ đề");
        cboChuDe.addItem("Nhà xuất bản");
        cboChuDe.addItem("Tác giả");
        cboChuDe.addItem("Ngôn ngữ");
        cboChuDe.setSelectedIndex(0);

        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblTrai.getSelectedRow();
                String chon;
                int soLuong;
                
                if (index == -1) {
                    index = tblPhai.getSelectedRow();
                    chon = (String) tblPhai.getValueAt(index, 1);
                    soLuong = Integer.parseInt((String) tblPhai.getValueAt(index, 2));
                }
                else {
                    chon = (String) tblTrai.getValueAt(index, 1);
                    soLuong = Integer.parseInt((String) tblTrai.getValueAt(index, 2));
                }
                
                if (tblTrai.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Bảng hiện đang không có thông tin", "Lưu ý", 2);
                }
                else if (tblLietKe.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Không có sách thuộc " + cboChuDe.getSelectedItem().toString().toLowerCase() + ": " + chon + " trong thư viện", "Lưu ý", 2);
                }
                else {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Liệt kê sách theo " + cboChuDe.getSelectedItem().toString().toLowerCase());

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
                    c0r1.setCellValue("Bảng liệt kê sách theo " + cboChuDe.getSelectedItem().toString().toLowerCase());
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
                    c0r2.setCellValue(cboChuDe.getSelectedItem() + ": " + chon);
                    c0r2.setCellStyle(cellStyleLoc);

                    sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 5));
                    Row r3 = sheet.createRow(3);
                    Cell c0r3 = r3.createCell(0);
                    c0r3.setCellValue("Số lượng sách: " + soLuong);
                    c0r3.setCellStyle(cellStyleLoc);

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

                    Row r5 = sheet.createRow(5);
                    for (int i = 0; i < ((DefaultTableModel) tblLietKe.getModel()).getColumnCount(); i++) {
                        Cell cellRow5 = r5.createCell(i);
                        cellRow5.setCellValue(((DefaultTableModel) tblLietKe.getModel()).getColumnName(i));
                        cellRow5.setCellStyle(cellStyleTenCot);
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

                    for (int i = 0; i < tblLietKe.getRowCount(); i++) {
                        Row rowNormal = sheet.createRow(i+6);
                        for (int j = 0; j < 6; j++) {
                            Cell cellNormal = rowNormal.createCell(j);
                            Object value = tblLietKe.getValueAt(i, j);
                            cellNormal.setCellValue(String.valueOf(value));

                            if (i == tblLietKe.getRowCount()-1) {
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
                        String tenFile = "Liệt kê sách theo " + cboChuDe.getSelectedItem().toString().toLowerCase() + " - " + System.currentTimeMillis() + ".xlsx";
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
        
        tblTrai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = tblTrai.getSelectedRow();
                if (index != -1) {
                    tblPhai.clearSelection();
                    String chon = (String) tblTrai.getValueAt(index, 1);
                    fillToTableLietKe(index, chon);
                }            
            }
        });

        tblPhai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = tblPhai.getSelectedRow();
                if (index != -1) {
                    tblTrai.clearSelection();
                    String chon = (String) tblPhai.getValueAt(index, 1);
                    fillToTableLietKe(index, chon);
                }
            }
        });
    }

    @Override
    public void add() {
        JComponent a[] = {cboChuDe, btnKetXuat};
        Khung.addChung(pnlChucNang, null, a, 1, 2, null, 0);
        
        pnlChucNang_Tbl.add(pnlChucNang, BorderLayout.NORTH);
        pnlChucNang_Tbl.add(pnlTbl, BorderLayout.CENTER);
        
        pnlTbl.add(scrTrai);
        pnlTbl.add(scrPhai);
        
        pnlLietKe2.add(scrLietKe, BorderLayout.CENTER);
        pnlLietKe.add(pnlLietKe2);
        
        pnlThan2.add(pnlChucNang_Tbl, BorderLayout.NORTH);
        pnlThan2.add(pnlLietKe, BorderLayout.CENTER);
        
        pnlThan.add(pnlThan2);
    }

    @Override
    public void fillToTable() {
        try {
            List lst = null;
            
            switch (cboChuDe.getSelectedIndex()) {
                case 0:
                    lst = sachDAO.thongKeChuDe();
                    break;
                case 1:
                    lst = sachDAO.thongKeNXB();
                    break;
                case 2:
                    lst = sachDAO.thongKeTacGia();
                    break;
                case 3:
                    lst = sachDAO.thongKeNgonNgu();
                    break;
            }
            
            DefaultTableModel modelTrai = (DefaultTableModel) tblTrai.getModel();
            modelTrai.setRowCount(0);
            DefaultTableModel modelPhai = (DefaultTableModel) tblPhai.getModel();
            modelPhai.setRowCount(0);
            int x = 1;
            
            for (int i = 0; i < lst.size(); i++) {
                if (i < ((double) lst.size())/2) {
                    Object a[] = (Object[]) lst.get(i);
                    Object b[] = {x, a[0], a[1]};
                    modelTrai.addRow(b);
                }
                else {
                    Object a[] = (Object[]) lst.get(i);
                    Object b[] = {x, a[0], a[1]};
                    modelPhai.addRow(b);
                }
                x++;
            }
            
            tblTrai.changeSelection(0, 0, false, false);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void fillToTableLietKe(int index, String chon) {
        List<Sach> lstSach = null;
        switch (cboChuDe.getSelectedIndex()) {
            case 0:
                lstSach = sachDAO.lietKeSachTheoChuDe(chon);
                break;
            case 1:
                lstSach = sachDAO.lietKeSachTheoNXB(chon);
                break;
            case 2:
                lstSach = sachDAO.lietKeSachTheoTacGia(chon);
                break;
            case 3:
                lstSach = sachDAO.lietKeSachTheoNgonNgu(chon);
                break;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblLietKe.getModel();
        model.setRowCount(0);
        int x = 1;
        NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        
        for (Sach s : lstSach) {
            String giaTien = n.format(s.getGiaTien());
            Object rows[] = {x, s.getTieuDe(), s.getSoLuong(), giaTien, s.getNgonNgu(), s.getSoTrang()}; 
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
