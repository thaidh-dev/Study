package panel;

import dao.ChuDeDAO;
import dao.SachDAO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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

public class ThongKeSoLuongDauSach implements Khung {

    JPanel pnlThan, pnlThan2, pnlChucNang, pnlTieuDe_ChucNang, pnlTbl;
    JTable tblTrai, tblPhai;
    JScrollPane scrTrai, scrPhai;
    JLabel lblTieuDe;
    JCheckBox chk;
    JButton btnKetXuat;
    GridBagConstraints gbc;
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private SachDAO sachDAO = new SachDAO();

    public ThongKeSoLuongDauSach() {
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
        lblTieuDe = new JLabel("Thống kê số lượng mỗi đầu sách");
        pnlChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        chk = new JCheckBox("Dưới 5 cuốn");
        btnKetXuat = Khung.taoButton("Kết xuất");
        Class lop[] = {Integer.class, String.class, Integer.class};
        String tenCot[] = {"STT", "Tiêu đề sách", "Số lượng"};
        tblTrai = Khung.taoTable(lop, tenCot);
        scrTrai = Khung.taoJScrollPane(tblTrai, null, new Dimension());
        tblPhai = Khung.taoTable(lop, tenCot);
        scrPhai = Khung.taoJScrollPane(tblPhai, null, new Dimension());
        gbc = new GridBagConstraints();
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);

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
        chk.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fillToTable();
            }
        });

        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblTrai.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Bảng hiện đang không có thông tin", "Lưu ý", 2);
                } else {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Thống kê số lượng mỗi đầu sách");

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
                    c0r1.setCellValue("Bảng thống kê số lượng mỗi đầu sách");
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
                    c0r2.setCellValue("Số lượng: " + (chk.isSelected() ? "dưới 5 cuốn" : "toàn bộ"));
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
                        cellRow4.setCellValue((String) tblTrai.getColumnModel().getColumn(i).getHeaderValue());
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
                        String tenFile = "Thống kê số lượng mỗi đầu sách" + " - " + System.currentTimeMillis() + ".xlsx";
                        File fileExcel = new File(home + "\\Desktop", tenFile);
                        FileOutputStream outputStream = new FileOutputStream(fileExcel);

                        workbook.write(outputStream);
                        workbook.close();
                        outputStream.close();

//                        File file = new File("C:\\Users\\Admin\\Desktop\\" + tenFile);
                        Desktop.getDesktop().open(fileExcel);
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }

    @Override
    public void add() {
        JComponent a[] = {chk, btnKetXuat};
        Khung.addChung(pnlChucNang, null, a, 1, 2, null, 0);

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

            if (chk.isSelected()) {
                lst = sachDAO.thongKeSoLuongMoiDauSach(true);
            } else {
                lst = sachDAO.thongKeSoLuongMoiDauSach(false);
            }

            DefaultTableModel modelTrai = (DefaultTableModel) tblTrai.getModel();
            modelTrai.setRowCount(0);
            DefaultTableModel modelPhai = (DefaultTableModel) tblPhai.getModel();
            modelPhai.setRowCount(0);
            int x = 1;

            for (int i = 0; i < lst.size(); i++) {
                if (i < ((double) lst.size()) / 2) {
                    Sach s = lst.get(i);
                    Object row[] = {x, s.getTieuDe(), s.getSoLuong()};
                    modelTrai.addRow(row);
                } else {
                    Sach s = lst.get(i);
                    Object row[] = {x, s.getTieuDe(), s.getSoLuong()};
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
