package guiemail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class GuiText {
    public static void main(String[] args) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", true); // xác thực: chứng thực những cái mày khai báo ra là đúng sự thật
            p.put("mail.smtp.starttls.enable", true); //chuẩn bảo mật gmail: startTLS
            p.put("mail.smtp.host", "smtp.gmail.com"); // kết nối với máy chủ smtp(là giao thức truyền tải thư, là bộ các quy tắc hỗ trợ cho việc gửi thư)
            //p.put("mail.smtp.port", 587); // kết nối cổng 587 TLS(cổng mới), có cổng 465 SSL là cổng cũ, không viết dòng này cũng chả sao

            Authenticator a = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("thaidhph06986@fpt.edu.vn", "123@123a");
                }
            };
            
            Session s = Session.getInstance(p, a); // tạo 1 phiên làm việc

            Message m = new MimeMessage(s);
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse("duonghongthai07@gmail.com"));
            m.setSubject("Thư một");
            m.setText("alo alo, có nhận được chưa");
            
            //CC để gửi email cho nhiều người cùng lúc và những người này cũng sẽ xem được danh sách những người cùng nhận được nội dung mail.
            //BCC gửi email cho nhiều người nhận khác nhưng những người này không thể nhìn thấy danh sách của những người cùng được nhận email này. Vì thế để bảo mật danh sách nhận email thì tính năng BCC sẽ được sử dụng.
            //TO
            
            Transport.send(m);
            System.out.println("gửi thành công");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
