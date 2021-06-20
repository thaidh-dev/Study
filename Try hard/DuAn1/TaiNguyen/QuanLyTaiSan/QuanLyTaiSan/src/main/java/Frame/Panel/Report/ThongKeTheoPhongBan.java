package Frame.Panel.Report;

import Contant.CoreConstant;
import DAO.DAOImpl.ThongKe2DaoImpl;
import Utils.DialogUtils;
import Utils.MoneyUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ThongKeTheoPhongBan extends JPanel{

    public ThongKeTheoPhongBan() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {
        btnExport = new JButton("Kết xuất");
        btnExport.setFont(new Font("Segoe UI", 0, 12));

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {
                "STT", "Tên phòng ban" ,"Số lượng", "Tổng giá trị tài sản"
        });
        table =  new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        scTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JTableHeader tblHeader1 = table.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setFont(new Font("Segoe UI", 0, 14));

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);

        table.setRowHeight(25);

        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {
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
        renderer1.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.setDefaultRenderer(table.getColumnClass(i), renderer1);
        }

        table.setRowSorter(new TableRowSorter(model));
        table.setAutoCreateRowSorter(true);
    }

    public void addControls() {
        JPanel pnTK1 = new JPanel(new BorderLayout());
        JPanel pnTK1Item1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnTK1Item1.add(btnExport);
        pnTK1.add(pnTK1Item1, BorderLayout.NORTH);
        pnTK1.add(scTable, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(pnTK1, BorderLayout.CENTER);
    }



    public void addEvents() {
        loadDataToTable();

        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getRowCount()<=0){
                    DialogUtils.showMessageDialog("Không có dữ liệu để kết xuất!", CoreConstant.TYPE_INFORMATION);
                    return;
                }else{
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("Thống kê số lượng và tổng giá trị tài sản theo phòng ban");
                    XSSFRow row = sheet.createRow(0);
                    XSSFCell cell0 = row.createCell(0);
                    cell0.setCellValue("STT");
                    XSSFCell cell1 = row.createCell(1);
                    cell1.setCellValue("Tên phòng ban");
                    XSSFCell cell2 = row.createCell(2);
                    cell2.setCellValue("Số lượng");
                    XSSFCell cell3 = row.createCell(3);
                    cell3.setCellValue("Tổng giá trị tài sản");
                    for(int i=1;i<table.getRowCount()+1;i++){
                        XSSFRow row_dl = sheet.createRow(i);
                        for(int j=0;j<4;j++){
                            XSSFCell cell = row_dl.createCell(j);
                            if(table.getValueAt(i-1, j) instanceof Integer){
                                cell.setCellValue((Integer)table.getValueAt(i-1, j));
                            }else{
                                cell.setCellValue((String)table.getValueAt(i-1, j));
                            }
                        }
                    }
                    try {
                        String home = System.getProperty("user.home");
                        String tenFile = "Thống kê số lượng và tổng giá trị tài sản theo phòng ban - " + System.currentTimeMillis() + ".xlsx";
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

    public void loadDataToTable() {
        List<Object[]> list = new ThongKe2DaoImpl().getListThongKe2();
        model.setRowCount(0);
        int stt = 1;
        for (Object[] item : list) {
            String money = null;
            if (item[2] != null) {
                String temp = item[2].toString();
                money = MoneyUtil.castIntToMoney(Long.parseLong(temp));
            } else {
                money = "0";
            }
            model.addRow(new Object[]{
                    stt,item[0], item[1], money
            });
            stt++;
        }
    }

    JTable table;
    DefaultTableModel model;
    JScrollPane scTable;
    JButton btnExport;
}
