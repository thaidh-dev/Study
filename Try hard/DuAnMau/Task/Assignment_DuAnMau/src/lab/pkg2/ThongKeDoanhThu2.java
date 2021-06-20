package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static lab.pkg2.Khung.cst;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThongKeDoanhThu2 extends Khung{
    JPanel pnlInfo, pnlJFrame, pnlNorth, pnlInfoLeft, pnlInfoRight;
    JLabel lblTitle, lblChuyenDe;
    JComboBox cboChuyenDe;
    JTable tblGridView;
    JScrollPane scrTbl;
    DefaultTableModel model;
    GridBagConstraints gbc;
    ButtonGroup bgr;
    JRadioButton rdoSLTang, rdoSLGiam;
    String tenCot[] = {"Năm", "Số lương học viên"};
    JButton btnKetXuat;

    public ThongKeDoanhThu2() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    @Override
    public void taoDoiTuong() {
        gbc = new GridBagConstraints();
        
        pnlInfo = new JPanel(new GridBagLayout());
        pnlInfoLeft = new JPanel(new GridBagLayout());
        pnlInfoRight = new JPanel(new GridBagLayout());
        pnlJFrame = new JPanel(new BorderLayout(0, 10));
        pnlNorth = new JPanel(new BorderLayout(0, 10));
        
        lblTitle = new JLabel("Bảng thống kê số lượng người học từng chuyên đề theo năm");
        lblChuyenDe = new JLabel("Chuyên đề");
        
        cboChuyenDe = new JComboBox();
        
        model = new DefaultTableModel(tenCot, 0);
        tblGridView = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrTbl = new JScrollPane(tblGridView);
        
        bgr = new ButtonGroup();
        rdoSLGiam = new JRadioButton("Số lượng giảm dần");
        rdoSLTang = new JRadioButton("Số lượng tăng dần");
        
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
        
        cboChuyenDe.setPreferredSize(new Dimension(170, 25));
        
        scrTbl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        tblGridView.setFillsViewportHeight(true);
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        tblGridView.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tblGridView.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        
        pnlInfoRight.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        
        rdoSLTang.setSelected(true);
    }

    @Override
    public void add() {
        bgr.add(rdoSLGiam);
        bgr.add(rdoSLTang);
        
        JComponent a[] = {rdoSLTang, rdoSLGiam};
        addChung(pnlInfoRight, gbc, a, 2, 1, new Insets(5, 5, 5, 5), 17);
        
        JComponent c[] = {lblChuyenDe, cboChuyenDe};
        addChung(pnlInfoLeft, gbc, c, 1, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlInfoLeft.add(btnKetXuat, gbc);
        
        JComponent b[] = {pnlInfoLeft, pnlInfoRight};
        addChung(pnlInfo, gbc, b, 1, 2, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
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
                    fillToTable();
                }
            }
        });
        
        eventPnlJFrame();
        
        rdoSLGiam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillToTable();
                }
            }
        });
        rdoSLTang.addItemListener(new ItemListener() {
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
                XSSFSheet sheet = workbook.createSheet("Thống kê số lượng người học từng chuyên đề theo năm");
                
                // tiêu đề
                XSSFFont fontTitle = workbook.createFont();
                fontTitle.setBold(true);
                fontTitle.setFontName("tahoma");
                fontTitle.setFontHeight(20);
                
                CellStyle cellStyleTitle = workbook.createCellStyle();
                cellStyleTitle.setFont(fontTitle);
                cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);

                Row r0 = sheet.createRow(0);
                Cell c0r1 = r0.createCell(0);
                c0r1.setCellValue("Bảng thống kê số lượng người học từng chuyên đề theo năm");
                c0r1.setCellStyle(cellStyleTitle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));

                //Chuyên đề
                XSSFFont fontKHAndNgayKG = workbook.createFont();
                fontKHAndNgayKG.setBold(false);
                fontKHAndNgayKG.setFontName("tahoma");
                fontKHAndNgayKG.setFontHeight(18);
                
                sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
                CellStyle cellStyleKHAndNgayKG = workbook.createCellStyle();
                cellStyleKHAndNgayKG.setFont(fontKHAndNgayKG);
                cellStyleKHAndNgayKG.setAlignment(HorizontalAlignment.CENTER);
                
                Row r2 = sheet.createRow(2);
                Cell c0r2 = r2.createCell(0);
                c0r2.setCellValue("Chuyên đề: " + cboChuyenDe.getSelectedItem());
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
                    Cell cellRow4 = r4.createCell(i);
                    cellRow4.setCellValue(tenCot[i]);
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
                
                for (int i = 0; i < tblGridView.getRowCount(); i++) {
                    Row rowNormal = sheet.createRow(i+5);
                    for (int j = 0; j < 2; j++) {
                        Cell cellNormal = rowNormal.createCell(j);
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
                
                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 7000);
                
                try {
                    String home = System.getProperty("user.home");
                    String tenFile = "Thống kê doanh thu, số lượng người học từng chuyên đề theo năm - " + System.currentTimeMillis() + ".xlsx";
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
        String sql = "select nam, sl from dbo.fn_2(?)";
        
        if (rdoSLGiam.isSelected()) {
            sql = "select nam, sl from dbo.fn_2(?) order by 'sl' desc";
        }
        else if (rdoSLTang.isSelected()) {
            sql = "select nam, sl from dbo.fn_2(?) order by 'sl' asc";
        }

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, (String) cboChuyenDe.getSelectedItem());
            rs1 = pst.executeQuery();
            
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
