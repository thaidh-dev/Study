package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
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

public class TongHopSoLuong extends Khung {
    JPanel pnlInfo, pnlJFrame, pnlNorth, pnlRdo;
    JLabel lblTitle, lblChuyenDe, lblKhoaHoc, lblNam;
    JComboBox cboNam;
    JTable tblGridView;
    JScrollPane scrTbl;
    ButtonGroup bgrTangGiam;
    JRadioButton rdoGiam, rdoTang;
    DefaultTableModel model;
    GridBagConstraints gbc;
    String tenCot[] = {"Tháng", "Số lượng"};
    JButton btnKetXuat;

    public TongHopSoLuong() {
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
        pnlRdo = new JPanel(new GridBagLayout());
        
        lblTitle = new JLabel("Bảng tổng hơp số lượng người đăng ký theo tháng");
        lblChuyenDe = new JLabel("Chuyên đề");
        lblKhoaHoc = new JLabel("Khóa học");
        lblNam = new JLabel("Năm");
        
        cboNam = new JComboBox();
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }            
        };
        scrTbl = new JScrollPane(tblGridView);
        
        bgrTangGiam = new ButtonGroup();
        rdoTang = new JRadioButton("Số lượng tăng dần");
        rdoGiam = new JRadioButton("Số lượng giảm dần");
        
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
        
        cboNam.setPreferredSize(new Dimension(200, 25));
        
        tblGridView.setFillsViewportHeight(true);    
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        tblGridView.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        
        pnlRdo.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        
        rdoTang.setSelected(true);
    }

    @Override
    public void add() {
        bgrTangGiam.add(rdoTang);
        bgrTangGiam.add(rdoGiam);
        
        JComponent rdo[] = {rdoTang, rdoGiam};
        addChung(pnlRdo, gbc, rdo, 2, 1, new Insets(5, 5, 5, 5), 17);
        
        JComponent a[] = {lblNam, cboNam};
        addChung(pnlInfo, gbc, a, 1, 2, new Insets(5, 5, 5, 5), 17);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlInfo.add(btnKetXuat, gbc);
        
        gbc.gridheight = 2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        pnlInfo.add(pnlRdo, gbc);
        gbc.gridheight = 1;
        
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlInfo, BorderLayout.CENTER);
        
        pnlJFrame.add(pnlNorth, BorderLayout.NORTH);    
        pnlJFrame.add(scrTbl, BorderLayout.CENTER);    
    }

    @Override
    public void event() {
        cboNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                }
            }
        });
        
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
        
        eventPnlJFrame();
        
        btnKetXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Tổng hơp số lượng người đăng ký theo tháng");
                
                // tiêu đề
                XSSFFont fontTitle = workbook.createFont();
                fontTitle.setBold(true);
                fontTitle.setFontName("tahoma");
                fontTitle.setFontHeight(20);
                
                CellStyle cellStyleTitle = workbook.createCellStyle();
                cellStyleTitle.setFont(fontTitle);
                cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);
                
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));

                Row r0 = sheet.createRow(0);
                Cell c0r1 = r0.createCell(0);
                c0r1.setCellValue("Bảng tổng hơp số lượng người đăng ký theo tháng");
                c0r1.setCellStyle(cellStyleTitle);
                
                //năm
                XSSFFont fontKHAndNgayKG = workbook.createFont();
                fontKHAndNgayKG.setBold(false);
                fontKHAndNgayKG.setFontName("tahoma");
                fontKHAndNgayKG.setFontHeight(18);
                
                sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 11));
                CellStyle cellStyleKHAndNgayKG = workbook.createCellStyle();
                cellStyleKHAndNgayKG.setFont(fontKHAndNgayKG);
                cellStyleKHAndNgayKG.setAlignment(HorizontalAlignment.CENTER);

                Row r2 = sheet.createRow(2);
                Cell c0r2 = r2.createCell(0);
                c0r2.setCellValue("Năm: " + cboNam.getSelectedItem());
                c0r2.setCellStyle(cellStyleKHAndNgayKG);
                
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
                for (int i = 0; i < tenCot.length; i++) {
                    Cell cellRow4 = r4.createCell(i+5);
                    cellRow4.setCellValue(tenCot[i]);
                    cellRow4.setCellStyle(cellStyleTenCot);
                }

                sheet.autoSizeColumn(5);
                sheet.autoSizeColumn(6);
                
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
                    Row rowNormal = sheet.createRow(i+5);
                    for (int j = 0; j < tenCot.length; j++) {
                        Cell cellNormal = rowNormal.createCell(j+5);
                        Object value = tblGridView.getValueAt(i, j);
                            
                        cellNormal.setCellValue((int) value);
                        if (i == tblGridView.getRowCount()-1) {
                            cellNormal.setCellStyle(cellStyleNormal);
                        }
                        else {
                            cellNormal.setCellStyle(cellStyleNormal1);
                        }
                    }
                }
                
//                sheet.setColumnWidth(0, 5000);
//                sheet.setColumnWidth(1, 5000);
                
                try {
                    String home = System.getProperty("user.home");
                    String tenFile = "Tổng hơp số lượng người đăng ký theo tháng - " + System.currentTimeMillis() + ".xlsx";
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
            String sql = "select * from dbo.fn_3_2(?)";
            
            if (rdoGiam.isSelected()) {
                sql = "select * from dbo.fn_3_2(?) order by 'sl' desc";
            }
            else if (rdoTang.isSelected()) {
                sql = "select * from dbo.fn_3_2(?) order by 'sl' asc";
            }

            cst = con.prepareCall(sql);
            cst.setInt(1, (int) cboNam.getSelectedItem());
            rs1 = cst.executeQuery();

            model.setRowCount(0);
            while (rs1.next()) {
                Object row[] = {rs1.getInt(1), rs1.getInt(2)};
                model.addRow(row);
            }
        }
        catch (Exception e) {
        }        
    }
    
    public void eventPnlJFrame() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy");
        int nam = Integer.parseInt(format.format(now));
        cboNam.removeAllItems();
        for (int i = 0; i < 5; i++) {
            cboNam.addItem(nam);
            nam--;
        }
    }
}
