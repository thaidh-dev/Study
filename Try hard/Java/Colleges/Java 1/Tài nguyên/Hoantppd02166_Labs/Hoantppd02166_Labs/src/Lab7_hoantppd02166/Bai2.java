
package Lab7_hoantppd02166;

import java.util.Scanner;

public class Bai2 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập Username: ");
        String user = Sc.next();
        System.out.println("Nhập Password: ");
        String pass = Sc.next();
        if(user.equalsIgnoreCase("FPT")){
            if(pass.equals("polytechnic")){
                System.out.println("Đăng nhập thành công!");
            }
            else System.out.println("Sai Password");
        }
        else System.out.println("Sai Username hoặc Password");
    }
}
