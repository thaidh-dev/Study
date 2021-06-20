
package Lab7_hoantppd02166;

import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập số điện thoại: ");
        String phone = Sc.next();
        while (!phone.matches("0\\d{9,10}")){
            System.out.println("Nhập sai! Vui lòng nhập lại!");
            System.out.println("Nhập số điện thoại: ");
            phone = Sc.next();
            continue;
        }
        System.out.println("Nhập Email: ");
        String mail = Sc.next();
        while (!mail.matches("\\w+@\\w+\\.\\w+")){
            System.out.println("Nhập sai! Vui lòng nhập lại!");
            System.out.println("Nhập Email: ");
            mail = Sc.next();
            continue;
        }
        System.out.println("Nhập số CMND: ");
        String cmnd = Sc.next();
        while (!cmnd.matches("[0-9]{9}")){
            System.out.println("Nhập sai! Vui lòng nhập lại!");
            System.out.println("Nhập số CMND: ");
            cmnd = Sc.next();
            continue;
        } 
        System.out.println("Nhập thành công!" + "\n" + "Điện thoại: " + phone + "\n" + "Email: " + mail + "\n" + "Số CMND: " + cmnd);
    }
    
    
}
