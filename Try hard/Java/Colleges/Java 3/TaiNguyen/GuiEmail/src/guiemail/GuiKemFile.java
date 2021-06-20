package guiemail;

import java.io.File;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class GuiKemFile {

    public static void main(String[] args) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", true); // xác thực: chứng thực những cái mày khai báo ra là đúng sự thật
            p.put("mail.smtp.starttls.enable", true); //chuẩn bảo mật gmail: startTLS
            p.put("mail.smtp.host", "smtp.gmail.com"); // kết nối với máy chủ smtp(là giao thức truyền tải thư, là bộ các quy tắc hỗ trợ cho việc gửi thư)
            p.put("mail.smtp.port", 587); // kết nối cổng 587 TLS(cổng mới), có cổng 465 SSL là cổng cũ, có cổng mới gửi được file

            Authenticator a = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("thaidhph06986@fpt.edu.vn", "123@123a");
                }
            };
            
            Session s = Session.getInstance(p, a); // tạo 1 phiên làm việc

            Message m = new MimeMessage(s);
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse("duonghongthai07@gmail.com"));
            m.setSubject("Thư vô cực");
            
            //CC để gửi email cho nhiều người cùng lúc và những người này cũng sẽ xem được danh sách những người cùng nhận được nội dung mail.
            //BCC gửi email cho nhiều người nhận khác nhưng những người này không thể nhìn thấy danh sách của những người cùng được nhận email này. Vì thế để bảo mật danh sách nhận email thì tính năng BCC sẽ được sử dụng.
            //TO
            BodyPart bp2 = new MimeBodyPart();
            bp2.setContent("alo alo, có nhận được không", "text/html; charset=utf-8");
            
            //text và file phải để riêng ra;
            
            BodyPart bp = new MimeBodyPart();
            File f = new File("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 8\\mail\\Mò được.rar");
            bp.setDataHandler(new DataHandler(new FileDataSource(f)));
//            bp.setFileName("hello");
            
            BodyPart bp3 = new MimeBodyPart();
            File f3 = new File("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 8\\mail\\a.rar");
            bp3.setDataHandler(new DataHandler(new FileDataSource(f3)));

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(bp);
            mp.addBodyPart(bp3);
            mp.addBodyPart(bp2);
            
            m.setContent(mp);
            Transport.send(m);
            System.out.println("gửi thành công");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
