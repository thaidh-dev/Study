package Utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class ExportUtils {

    public static void exportToExcel(JTable table, String name, Object[] Column) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("STT");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("Lo?i tài s?n");
        XSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("S? l??ng");
        XSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("T?ng giá tr? tài s?n");

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
            String tenFile = name+ " - " + System.currentTimeMillis() + ".xlsx";
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
