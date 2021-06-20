package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
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

public class TongHopDiem extends Khung {
    JPanel pnlInfo, pnlJFrame, pnlNorth, pnlRdo;
    JButton btnKetXuat;
    JLabel lblTitle, lblChuyenDe, lblKhoaHoc;
    JComboBox cboChuyenDe, cboKhoaHoc;
    JTable tblGridView;
    JScrollPane scrTbl;
    ButtonGroup bgrTangGiam;
    JRadioButton rdoGiam, rdoTang;
    DefaultTableModel model;
    GridBagConstraints gbc;
    String tenCot[] = {"STT", "Họ tên", "Điểm", "Xếp loại"};
    String ngayKG;

    public TongHopDiem() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();

        pnlInfo = new JPanel(new GridBagLayout());
        pnlJFrame = new JPanel(new BorderLayout(0, 10));
        pnlNorth = new JPanel(new BorderLayout(0, 10));
        pnlRdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 3));
        
        lblTitle = new JLabel("Bảng tổng hợp điểm khóa học");
        lblChuyenDe = new JLabel("Chuyên đề");
        lblKhoaHoc = new JLabel("Khóa học");
        
        cboChuyenDe = new JComboBox();
        cboKhoaHoc = new JComboBox();
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrTbl = new JScrollPane(tblGridView);
        
        bgrTangGiam = new ButtonGroup();
        rdoTang = new JRadioButton("Điểm tăng dần");
        rdoGiam = new JRadioButton("Điểm giảm dần");
        
        btnKetXuat = new JButton("Xuất file Excel");
    }

    @Override
    public void taoDang() {
        lblTitle.setFont(new Font("tahoma", Font.BOLD, 14));
        lblTitle.setForeground(Color.white);
        lblTitle.setOpaque(true);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBackground(Color.darkGray);
        lblTitle.setPreferredSize(new Dimension(580, 30));

        cboChuyenDe.setPreferredSize(new Dimension(210, 25));
        cboKhoaHoc.setPreferredSize(new Dimension(210, 25));
        
        scrTbl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        tblGridView.setFillsViewportHeight(true);
        tblGridView.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblGridView.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblGridView.getColumnModel().getColumn(2).setPreferredWidth(0);
        tblGridView.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        tblGridView.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        
        pnlRdo.setPreferredSize(new Dimension(130, 61));
        pnlRdo.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        
        rdoTang.setSelected(true);
    }

    @Override
    public void add() {
        bgrTangGiam.add(rdoTang);
        bgrTangGiam.add(rdoGiam);
        pnlRdo.add(rdoTang);
        pnlRdo.add(rdoGiam);
        
        JComponent a[] = {lblChuyenDe, lblKhoaHoc, cboChuyenDe, cboKhoaHoc};
        addChung(pnlInfo, gbc, a, 2, 2, new Insets(5, 5, 5, 5), 17);
        gbc.gridheight = 2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        pnlInfo.add(pnlRdo, gbc);

        gbc.gridx = 3;
        pnlInfo.add(btnKetXuat, gbc);
        
        gbc.gridheight = 1;
        
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlInfo, BorderLayout.CENTER);
        
        pnlJFrame.add(pnlNorth, BorderLayout.NORTH);
        pnlJFrame.add(scrTbl, BorderLayout.CENTER);
    }

    @Override
    public void event() {
        cboChuyenDe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        cboKhoaHoc.removeAllItems();
                        pst = con.prepareStatement("select distinct makh, ngaykgconverted from dbo.fn_select_hocvien() where tencd = ?");
                        pst.setString(1, (String) cboChuyenDe.getSelectedItem());
                        rs1 = pst.executeQuery();

                        while (rs1.next()) {
                            cboKhoaHoc.addItem(rs1.getString(1) + " - (Khai giảng: " + rs1.getString(2) + ")");
                        }
                    } 
                    catch (Exception ex) {
                    }
                }
            }
        });

        cboKhoaHoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                model.setRowCount(0);
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                }
            }
        });
        
        eventPnlJFrame();
        
        rdoGiam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                }
            }
        });

        rdoTang.addItemListener(new ItemListener() {
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
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Tổng hợp điểm khóa học");
                
                // tiêu đề
                XSSFFont fontTitle = workbook.createFont();
                fontTitle.setBold(true);
                fontTitle.setFontName("tahoma");
                fontTitle.setFontHeight(20);
                
                CellStyle cellStyleTitle = workbook.createCellStyle();
                cellStyleTitle.setFont(fontTitle);
                cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);
                
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

                Row r0 = sheet.createRow(0);
                Cell c0r1 = r0.createCell(0);
                c0r1.setCellValue("Bảng tổng hợp điểm khóa học");
                c0r1.setCellStyle(cellStyleTitle);
                
                //Khóa học và ngày khai giảng
                XSSFFont fontKHAndNgayKG = workbook.createFont();
                fontKHAndNgayKG.setBold(false);
                fontKHAndNgayKG.setFontName("tahoma");
                fontKHAndNgayKG.setFontHeight(18);
                
                sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 3));
                CellStyle cellStyleKHAndNgayKG = workbook.createCellStyle();
                cellStyleKHAndNgayKG.setFont(fontKHAndNgayKG);
                cellStyleKHAndNgayKG.setAlignment(HorizontalAlignment.CENTER);

                Row r2 = sheet.createRow(2);
                Cell c0r2 = r2.createCell(0);
                c0r2.setCellValue("Chuyên đề: " + cboChuyenDe.getSelectedItem());
                c0r2.setCellStyle(cellStyleKHAndNgayKG);
                
                sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3));
                Row r3 = sheet.createRow(3);
                Cell c0r3 = r3.createCell(0);
                String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                if (cboKhoaHocGetSelectedItem == null) {
                    c0r3.setCellValue("Khóa học:");
                    c0r3.setCellStyle(cellStyleKHAndNgayKG);
                }
                else {
                    String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                    c0r3.setCellValue("Khóa học: " + cboKhoaHocGetMaKH);
                    c0r3.setCellStyle(cellStyleKHAndNgayKG);
                }
                
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 3));
                Row r4 = sheet.createRow(4);
                Cell c0r4 = r4.createCell(0);
                if (ngayKG == null) {
                    c0r4.setCellValue("Ngày khai giảng:");
                    c0r4.setCellStyle(cellStyleKHAndNgayKG);
                }
                else {
                    c0r4.setCellValue("Ngày khai giảng: " + ngayKG);
                    c0r4.setCellStyle(cellStyleKHAndNgayKG);
                }
                
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
                
                Row r6 = sheet.createRow(6);
                for (int i = 0; i < tenCot.length; i++) {
                    Cell cellRow6 = r6.createCell(i);
                    cellRow6.setCellValue(tenCot[i]);
                    cellRow6.setCellStyle(cellStyleTenCot);
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
                
                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                    Row rowNormal = sheet.createRow(i+7);
                    for (int j = 0; j < 4; j++) {
                        Cell cellNormal = rowNormal.createCell(j);
                        Object value = tblGridView.getValueAt(i, j);
                        if (value.getClass() == Double.class) {
                            cellNormal.setCellValue((double) value);
                            if (i == tblGridView.getRowCount()-1) {
                                cellNormal.setCellStyle(cellStyleNormal);
                            }
                            else {
                                cellNormal.setCellStyle(cellStyleNormal1);
                            }
                        }
                        else {
                            cellNormal.setCellValue(String.valueOf(value));
                            if (i == tblGridView.getRowCount()-1) {
                                cellNormal.setCellStyle(cellStyleNormal);
                            }
                            else {
                                cellNormal.setCellStyle(cellStyleNormal1);
                            }
                        }
                    }
                }
                
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 7000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                
                try {
                    String home = System.getProperty("user.home");
                    String tenFile = "Tổng hợp điểm khóa học - " + System.currentTimeMillis() + ".xlsx";
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
        });
    }
    
    public void fillToTable() {
        try {
            String sql = "select * from dbo.fn_1(?)";
            
            if (rdoGiam.isSelected()) {
                sql = "select * from dbo.fn_1(?) order by Diem desc";
            }
            else if (rdoTang.isSelected()) {
                sql = "select * from dbo.fn_1(?) order by Diem asc";
            }
            
            if (cboKhoaHoc.getSelectedItem() != null) {
                pst = con.prepareStatement(sql);
                String cboKhoaHocGetSelectedItem = (String) cboKhoaHoc.getSelectedItem();
                String cboKhoaHocGetMaKH = cboKhoaHocGetSelectedItem.substring(0, cboKhoaHocGetSelectedItem.length() - 27);
                pst.setInt(1, Integer.parseInt(cboKhoaHocGetMaKH));
                rs4 = pst.executeQuery();

                model.setRowCount(0);
                int a = 1;
                while (rs4.next()) {
                    Object row[] = {a, rs4.getString(1), rs4.getDouble(2), rs4.getString(3)};
                    model.addRow(row);
                    a++;
                    ngayKG = rs4.getString(4);
                }          
            }
        }
        catch (Exception e) {
        }        
    }
    
    public void eventPnlJFrame() {
        try {
            cboChuyenDe.removeAllItems();
            pst = con.prepareStatement("select TenCD from ChuyenDe");
            rs3 = pst.executeQuery();
            while (rs3.next()) {
                cboChuyenDe.addItem(rs3.getString(1));
            }
        } 
        catch (Exception ex) {
        }        
    }
}
