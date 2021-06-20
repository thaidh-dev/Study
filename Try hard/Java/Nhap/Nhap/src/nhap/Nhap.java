package nhap;

import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.ImageIcon;

public class Nhap {
//    static {
//        System.out.println("đây là khối static");
//    }

    public static void main(String[] args) {
//        File folder = new File("C:\\Users\\Admin\\Desktop\\resources\\images");
//        if (folder.isDirectory()) {
//            File file[] = folder.listFiles();
//            for (File f : file) {
//                System.out.println(f.getName());
//            }
//        }
//        
//        
//        BigDecimal bd = new BigDecimal(1000.00);
//        BigDecimal bd2 = new BigDecimal(2);
//        BigDecimal kq = new BigDecimal(0);
//        int x = 500;
        //kq = bd.divide(bd2, MathContext.DECIMAL128);

        //System.out.println(kq);
//        bd = bd.setScale(0);
//        System.out.println(bd);
//        String a = "1.000.000.00";
//        System.out.println(a.replace(".", ""));
//        System.out.println(Double.parseDouble("1.000"));
//        StringBuilder ngaySinh = new StringBuilder(String.valueOf(19971124));
//        System.out.println(ngaySinh);
//        String nam = ngaySinh.substring(0, 4);
//        String thang = ngaySinh.substring(4, 6);
//        String ngay = ngaySinh.substring(6, 8);
//        StringBuilder ngaySinhNew = new StringBuilder(ngay);
//        ngaySinhNew.append("-");
//        ngaySinhNew.append(thang);
//        ngaySinhNew.append("-");
//        ngaySinhNew.append(nam);
//        System.out.println(ngaySinhNew.toString());
//        Integer x = 19971124;
//        String x = "1997-11-24";
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date d = format.parse(x.toString());
//            SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
//            
//            String a = format2.format(d);
//            System.out.println(a);
//        } catch (Exception ex) {
//            Logger.getLogger(Nhap.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(String.valueOf(012345));
//        
//        float f = 9999999999f;
//        int i = (int) f;
//        System.out.println(i);
//        DongVat dv = new DongVat("thái", 7);
//        DongVat dv2 = new DongVat("huy", 0);
//        dv = dv2;
//        System.out.println(dv.a);
//        System.out.println(dv.b);

        Xe x = new Xe();
        x.getLop();
        Xe x2 = new Xe();
        x2.getLop();

        Xe x3 = new Xe(new Lop("thái"));
        x3.getLop();
    }
    
     
}
