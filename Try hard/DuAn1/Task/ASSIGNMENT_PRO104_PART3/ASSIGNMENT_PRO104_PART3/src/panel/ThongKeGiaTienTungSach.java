package panel;

import dao.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.*;
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

public class ThongKeGiaTienTungSach implements Khung {

    JPanel pnlThan, pnlThan2, pnlChucNang, pnlTieuDe_ChucNang, pnlTbl;
    JTable tblTrai, tblPhai;
    JScrollPane scrTrai, scrPhai;
    JLabel lblTieuDe, lblGiaTien;
    JComboBox cboGiaTien;
    JButton btnKetXuat;
    GridBagConstraints gbc;
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private SachDAO sachDAO = new SachDAO();

    public ThongKeGiaTienTungSach() {
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
        pnlTbl = new JPanel(new GridLayout(1, 2, 5, 0));
        lblTieuDe = new JLabel("Thống kê giá tiền của từng sách");
        lblGiaTien = new JLabel("Giá tiền");
        pnlChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        cboGiaTien = new JComboBox();
        btnKetXuat = Khung.taoButton("Kết xuất");
        Class lop[] = {Integer.class, String.class, Integer.class};
        String tenCot[] = {"STT", "Tiêu đề sách", "Giá tiền"};
        tblTrai = Khung.taoTable(lop, tenCot);
        tblPhai = Khung.taoTable(lop, tenCot);
        scrTrai = Khung.taoJScrollPane(tblTrai, null, new Dimension());
        scrPhai = Khung.taoJScrollPane(tblPhai, null, new Dimension());
        gbc = new GridBagConstraints();
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

        cboGiaTien.setPreferredSize(new Dimension(200, 25));

        JTable tbl[] = {tblTrai, tblPhai};
        for (int i = 0; i < tbl.length; i++) {
            tbl[i].setEnabled(false);
            tbl[i].getColumnModel().getColumn(0).setPreferredWidth(0);
            tbl[i].getColumnModel().getColumn(1).setPreferredWidth(300);
            tbl[i].getColumnModel().getColumn(2).setPreferredWidth(100);
        }
    }

    @Override
    public void event() {
        cboGiaTien.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                }
            }
        });

        cboGiaTien.addItem("Dưới 200.000 đ");
        cboGiaTien.addItem("200.000 đ - 500.000 đ");
        cboGiaTien.addItem("500.000 đ - 1.000.000 đ");
        cboGiaTien.addItem("1.000.000 đ - 2.000.000 đ");
        cboGiaTien.addItem("2.000.000 đ - 3.000.000 đ");
        cboGiaTien.addItem("3.000.000 đ - 4.000.000 đ");
        cboGiaTien.addItem("Trên 4.000.000 đ");
        cboGiaTien.setSelectedIndex(0);

        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblTrai.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Bảng hiện đang không có thông tin", "Lưu ý", 2);
                } else {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Thống kê giá tiền theo từng sách");

                    // tiêu đề
                    XSSFFont fontTitle = workbook.createFont();
                    fontTitle.setBold(true);
                    fontTitle.setFontName("tahoma");
                    fontTitle.setFontHeight(20);

                    CellStyle cellStyleTitle = workbook.createCellStyle();
                    cellStyleTitle.setFont(fontTitle);
                    cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);

                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

                    Row r0 = sheet.createRow(0);
                    Cell c0r1 = r0.createCell(0);
                    c0r1.setCellValue("Bảng thống kê giá tiền theo từng sách");
                    c0r1.setCellStyle(cellStyleTitle);

                    // lọc
                    XSSFFont fontLoc = workbook.createFont();
                    fontLoc.setBold(false);
                    fontLoc.setFontName("tahoma");
                    fontLoc.setFontHeight(18);

                    sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
                    CellStyle cellStyleLoc = workbook.createCellStyle();
                    cellStyleLoc.setFont(fontLoc);
                    cellStyleLoc.setAlignment(HorizontalAlignment.CENTER);

                    Row r2 = sheet.createRow(2);
                    Cell c0r2 = r2.createCell(0);
                    c0r2.setCellValue("Giá tiền: " + cboGiaTien.getSelectedItem());
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
                    for (int i = 0; i < 3; i++) {
                        Cell cellRow4 = r4.createCell(i);
                        cellRow4.setCellValue(((DefaultTableModel) tblTrai.getModel()).getColumnName(i));
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

                    for (int i = 0; i < tblTrai.getRowCount(); i++) {
                        Row rowNormal = sheet.createRow(i + 5);
                        for (int j = 0; j < 3; j++) {
                            Cell cellNormal = rowNormal.createCell(j);
                            Object value = tblTrai.getValueAt(i, j);
                            cellNormal.setCellValue(String.valueOf(value));

//                            if (i == tbl.getRowCount()-1) {
//                                cellNormal.setCellStyle(cellStyleNormal);
//                            }
//                            else {
                            cellNormal.setCellStyle(cellStyleNormal1);
//                            }
                        }
                    }

                    if (tblPhai.getRowCount() == 0) {
                        Row r = sheet.getRow(4 + tblTrai.getRowCount());
                        for (int i = 0; i < 3; i++) {
                            Cell c = r.getCell(i);
                            c.setCellStyle(cellStyleNormal);
                        }
                    } else {
                        for (int i = 0; i < tblPhai.getRowCount(); i++) {
                            Row rowNormal = sheet.createRow(i + 5 + tblTrai.getRowCount());
                            for (int j = 0; j < 3; j++) {
                                Object value = tblPhai.getValueAt(i, j);
                                Cell cellNormal = rowNormal.createCell(j);
                                cellNormal.setCellValue(String.valueOf(value));

                                if (i == tblPhai.getRowCount() - 1) {
                                    cellNormal.setCellStyle(cellStyleNormal);
                                } else {
                                    cellNormal.setCellStyle(cellStyleNormal1);
                                }

                                //                            if (value == null) {
                                //                                for (int k = 0; k < 3; k++) {
                                //                                    sheet.getRow(rowNormal.getRowNum()-1).getCell(k).setCellStyle(cellStyleNormal);
                                //                                }
                                //                                break;
                                //                            }
                                //                            else {
                                //                                Cell cellNormal = rowNormal.createCell(j-3);
                                //                                cellNormal.setCellValue(String.valueOf(value));
                                //
                                //                                if (rowNormal.getRowNum() == tbl.getRowCount()*2-1+5) {
                                //                                    cellNormal.setCellStyle(cellStyleNormal);
                                //                                }
                                //                                else {
                                //                                    cellNormal.setCellStyle(cellStyleNormal1);
                                //                                }
                                //                            }
                            }
                        }
                    }

                    sheet.setColumnWidth(0, 2000);
                    sheet.setColumnWidth(1, 27000);
                    sheet.setColumnWidth(2, 5000);

                    try {
                        String home = System.getProperty("user.home");
                        String tenFile = "Thống kê giá tiền theo từng sách " + ".xlsx";
                        File fileExcel = new File(home + "\\Desktop", tenFile);
                        FileOutputStream outputStream = new FileOutputStream(fileExcel);

                        workbook.write(outputStream);
                        workbook.close();
                        outputStream.close();

//                        File file = new File("C:\\Users\\Admin\\Desktop\\" + tenFile);
                        Desktop.getDesktop().open(fileExcel);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }

    @Override
    public void add() {
        JComponent a[] = {lblGiaTien, cboGiaTien, btnKetXuat};
        Khung.addChung(pnlChucNang, null, a, 1, 3, null, 0);

        JComponent b[] = {lblTieuDe, pnlChucNang};
        Khung.addChung(pnlTieuDe_ChucNang, gbc, b, 2, 1, new Insets(10, 0, 0, 0), GridBagConstraints.CENTER);

        pnlTbl.add(scrTrai);
        pnlTbl.add(scrPhai);

        pnlThan2.add(pnlTieuDe_ChucNang, BorderLayout.NORTH);
        pnlThan2.add(pnlTbl, BorderLayout.CENTER);

        pnlThan.add(pnlThan2);
    }

    @Override
    public void fillToTable() {
        try {
            List<Sach> lst = null;

            switch (cboGiaTien.getSelectedIndex()) {
                case 0:
                    lst = sachDAO.thongKeGiaTien(0, 200000);
                    break;
                case 1:
                    lst = sachDAO.thongKeGiaTien(200000, 500000);
                    break;
                case 2:
                    lst = sachDAO.thongKeGiaTien(500000, 1000000);
                    break;
                case 3:
                    lst = sachDAO.thongKeGiaTien(1000000, 2000000);
                    break;
                case 4:
                    lst = sachDAO.thongKeGiaTien(2000000, 3000000);
                    break;
                case 5:
                    lst = sachDAO.thongKeGiaTien(3000000, 4000000);
                    break;
                case 6:
                    lst = sachDAO.thongKeGiaTien(4000000);
                    break;
            }

            DefaultTableModel modelTrai = (DefaultTableModel) tblTrai.getModel();
            modelTrai.setRowCount(0);
            DefaultTableModel modelPhai = (DefaultTableModel) tblPhai.getModel();
            modelPhai.setRowCount(0);
            int x = 1;

            NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            for (int i = 0; i < lst.size(); i++) {
                //Sach s;
                if (i < ((double) lst.size()) / 2) {
                    Sach s = lst.get(i);
                    Object row[] = {x, s.getTieuDe(), n.format(s.getGiaTien())};
                    modelTrai.addRow(row);
                } else {
                    Sach s = lst.get(i);
                    Object row[] = {x, s.getTieuDe(), n.format(s.getGiaTien())};
                    modelPhai.addRow(row);
                }
                x++;
            }
        } catch (Exception e) {
            System.out.println(e);
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
