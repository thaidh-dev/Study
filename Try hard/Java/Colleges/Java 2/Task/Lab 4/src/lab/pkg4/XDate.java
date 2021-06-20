package lab.pkg4;

import java.text.*;
import java.util.Date;

public class XDate {
    private static SimpleDateFormat formater = new SimpleDateFormat();
    
    public static Date parse(String text, String pattern) throws Exception {
        try {
            formater.applyPattern(pattern);
            return formater.parse(text);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    public static Date parse(String text) throws Exception {
        return XDate.parse(text, "dd-MM-yyyy");
    }
    
    // trong try thực hiện lệnh, trong catch thực hiện ngoại lệ
    // catch chỉ làm khi có ngoại lệ
    // finally là kiểu j nó cũng thực hiện dù có lỗi hay không, hay dùng để khởi tạo lại biến
    // throws: đẩy ngoại lệ ra bên ngoài cho thằng khác xử lý, thường dùng đầu hàm khi định nghĩa
    // throw: phát sinh 1 ngoại lệ mà ta không muốn xử lý, đưa ra ngoại lệ nhưng chưa xử lý, định nghĩa trong thân hàm
    /* phân loại lỗi
    lỗi logic: chỉ phát sinh lỗi khi chương trình chạy (runtime)
    lỗi cú pháp: chương trình tự báo cho mà biết
    */
}
