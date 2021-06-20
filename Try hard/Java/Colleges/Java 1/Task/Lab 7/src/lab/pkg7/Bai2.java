package lab.pkg7;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập username: ");
        String u = sc.nextLine();
        System.out.print("Nhập paswword: ");
        String p = sc.nextLine();
        if (u.equalsIgnoreCase("fpt")) {
            if (p.equals("polytechnic")) {
                System.out.println("Đăng nhập thành công");
            }
            else {
                System.out.println("Sai mật khẩu");
            }
        }
        else {
            System.out.println("Sai tài khoản");
        }
    }
    
}
