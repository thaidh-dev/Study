package lab.pkg7;

import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String regexten = "[a-zA-Z ]+";
        String regexemail = "\\w+@\\w+\\.\\w+";
        String regexsdt = "0\\d{9,10}";
        String regexcmnd = "\\d{10}";
        System.out.print("Nhập họ tên: ");
        String ten = sc.nextLine();
        if (ten.matches(regexten)) {
            System.out.print("Nhập email: ");
            String email = sc.nextLine();
            if (email.matches(regexemail)) {
                System.out.print("Nhập sđt: ");
                String sdt = sc.nextLine();
                if (sdt.matches(regexsdt)) {
                    System.out.print("Nhập CMND: ");
                    String cmnd = sc.nextLine();
                    if (cmnd.matches(regexcmnd)) {
                        System.out.println("Đăng nhập thành công");
                    }
                    else {
                        System.out.println("Sai số CMND");
                    }
                }
                else {
                    System.out.println("Sai số điện thoại");
                }
            }
            else {
                System.out.println("Sai email");
            }
        }
        else {
            System.out.println("Sai tên");
        }
    }
    
}
