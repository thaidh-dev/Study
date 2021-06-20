package panel;

import dao.ChuDeDAO;
import dao.SachDAO;
import dao.ThongKeDAO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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

public class LietKePMQuaHan implements Khung {
    JPanel pnlThan, pnlThan2, pnlPhieuMuon_Sach, pnlSach, pnlPhieuMuon, pnlTieuDe_ChucNang, pnlChucNang;
    JTable tblSach, tblPhieuMuon;
    JScrollPane scrSach, scrPhieuMuon;
    JLabel lblTieuDe;
    JButton btnKetXuat;
    GridBagConstraints gbc;
    private ChuDeDAO cdDAO = new ChuDeDAO();
    private SachDAO sachDAO = new SachDAO();

    public LietKePMQuaHan() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }

    @Override
    public void taoDoiTuong() {
        pnlThan2 = new JPanel(new BorderLayout(0, 0));
        pnlThan = new JPanel(new CardLayout(5, 5));
        pnlTieuDe_ChucNang = new JPanel(new GridBagLayout());
        pnlChucNang = Khung.taoJPanel(new FlowLayout(FlowLayout.CENTER, 10, 10), new TitledBorder(new LineBorder(Color.gray, 1), "Chức năng", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlPhieuMuon_Sach = new JPanel(new BorderLayout(20, 0));
        pnlSach = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Sách đã mượn", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        pnlPhieuMuon = Khung.taoJPanel(new CardLayout(5, 5), new TitledBorder(new LineBorder(Color.gray, 1), "Danh sách phiếu mượn quá hạn", 0, 0, new Font("", Font.BOLD, 15), Color.gray));
        lblTieuDe = new JLabel("Liệt kê phiếu mượn quá hạn chưa trả");
        btnKetXuat = Khung.taoButton("Kết xuất");
        Class classThang[] = {Integer.class, String.class};
        String tenCotThang[] = {"STT", "Tiêu đề sách"};
        tblSach = Khung.taoTable(classThang, tenCotThang);
        scrSach = Khung.taoJScrollPane(tblSach, null, new Dimension(300, 0));

        Class classPhieuMuon[] = {Integer.class, String.class, String.class, String.class, String.class};
        String tenCotPhieuMuon[] = {"Mã phiếu mượn", "Thẻ độc giả", "Tên độc giả", "Ngày mượn", "Ngày hẹn"};
        tblPhieuMuon = Khung.taoTable(classPhieuMuon, tenCotPhieuMuon);
        scrPhieuMuon = Khung.taoJScrollPane(tblPhieuMuon, null, new Dimension());
        gbc = new GridBagConstraints();
    }

    @Override
    public void taoDang() {
        lblTieuDe.setFont(new Font("", Font.BOLD, 30));
        lblTieuDe.setHorizontalAlignment(JLabel.CENTER);
        
        tblPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblPhieuMuon.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblPhieuMuon.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tblPhieuMuon.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        tblPhieuMuon.getColumnModel().getColumn(4).setCellRenderer(dtcr);

        tblSach.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblSach.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    @Override
    public void event() {
        fillToTable();
        
        tblPhieuMuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = tblPhieuMuon.getSelectedRow();
                
                if (index != -1) {
                    int maPM = Integer.parseInt(String.valueOf(tblPhieuMuon.getValueAt(index, 0)));
                    
                    List<Object[]> lst = ThongKeDAO.cacSachDaMuonQuaHan(maPM);
                    DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
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
        });

        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblPhieuMuon.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pnlThan, "Bảng hiện đang không có thông tin", "Lưu ý", 2);
                } 
                else {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Liệt kê phiếu mượn quá hạn");

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
                    c0r1.setCellValue("Bảng liệt kê phiếu mượn quá hạn");
                    c0r1.setCellStyle(cellStyleTitle);

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

                    Row r2 = sheet.createRow(2);
                    for (int i = 0; i < tblPhieuMuon.getColumnCount(); i++) {
                        Cell cellRow2 = r2.createCell(i);
                        cellRow2.setCellValue((String) tblPhieuMuon.getColumnModel().getColumn(i).getHeaderValue());
                        cellRow2.setCellStyle(cellStyleTenCot);
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
                        Row rowNormal = sheet.createRow(i+3);
                        for (int j = 0; j < tblPhieuMuon.getColumnCount(); j++) {
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

                    sheet.setColumnWidth(0, 6000);
                    sheet.setColumnWidth(1, 7000);
                    sheet.setColumnWidth(2, 15000);
                    sheet.setColumnWidth(3, 5000);
                    sheet.setColumnWidth(4, 5000);

                    try {
                        String home = System.getProperty("user.home");
                        String tenFile = "Liệt kê phiếu mượn quá hạn" + " - " + System.currentTimeMillis() + ".xlsx";
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
        pnlChucNang.add(btnKetXuat);
        
        JComponent a[] = {lblTieuDe, pnlChucNang};
        Khung.addChung(pnlTieuDe_ChucNang, gbc, a, 2, 1, new Insets(10, 0, 0, 0), 10);
        
        pnlSach.add(scrSach);
        pnlPhieuMuon.add(scrPhieuMuon);
        
        pnlPhieuMuon_Sach.add(pnlSach, BorderLayout.EAST);
        pnlPhieuMuon_Sach.add(pnlPhieuMuon, BorderLayout.CENTER);
        
        pnlThan2.add(pnlTieuDe_ChucNang, BorderLayout.NORTH);
        pnlThan2.add(pnlPhieuMuon_Sach, BorderLayout.CENTER);
        
        pnlThan.add(pnlThan2);
    }

    @Override
    public void fillToTable() {
        try {
            List<Object[]> lst = ThongKeDAO.danhSachPhieuMuonQuaHan();
            DefaultTableModel model = (DefaultTableModel) tblPhieuMuon.getModel();
            model.setRowCount(0);
            
            for (Object row[] : lst) {
                model.addRow(row);
            }
        } 
        catch (Exception e) {
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
