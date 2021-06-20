package ass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Employee implements Serializable{
    static List <Employee> list = new ArrayList<>();
    static Map <String, Employee> map = new HashMap<>();
    static JButton btnNew, btnSave, btnDelete, btnFind, btnOpen, btnExit;
    static JTextField txtMa, txtTen, txtTuoi, txtEmail, txtLuong;
    static Method a = new Method();
    static JTable tbl;
    static JFrame f;
    static int eventMoi = 0;
    static JLabel lblTrangThai;
    
    String ma, ten, email;
    double tuoi, luong;
    String regexMa = "\\w*";
    String regexTen = "[a-zA-z ]*";
    String regexEmail = "\\w+@\\w+(\\.\\w+){1,2}";
    
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof Employee) {
            Employee e = (Employee) anObject;
            if (ma.equals(e.ma) &&
                    ten.equals(e.ten) &&
                    tuoi == e.tuoi &&
                    email.equals(e.email) &&
                    luong == e.luong) {
                return true;    
            }
        }
        return false;
    }
}


