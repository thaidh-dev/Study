package lab.pkg6;

import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String hoten, email, sdt, cmnd;
        String rehoten = "[a-zA-Z ]+";
        String reemail = "\\w+@\\w+(.\\w+){1,2}";
        String resdt = "\\d{10}";
        String recmnd = "\\d{10}";
        do {
        System.out.print("Nhập họ tên: ");
        hoten = sc.nextLine();
        if (hoten.matches(rehoten)) {
            do {
            System.out.print("Nhập email: ");
            email = sc.nextLine();
            if (email.matches(reemail)) {
                do {
                System.out.print("Nhập sđt: ");
                sdt = sc.nextLine();
                if (sdt.matches(resdt)) {
                    do {
                    System.out.print("Nhập cmnd: ");
                    cmnd = sc.nextLine();
                    if (cmnd.matches(recmnd)) {
                        System.out.println("Đăng nhập thành công");
                    }
                    else {
                        System.out.println("Nhập lại");
                    }
                    }
                    while (!cmnd.matches(recmnd));
                }
                else {
                    System.out.println("Nhập lại");
                }
                }
                while (!sdt.matches(resdt));
            }
            else {
                System.out.println("Nhập lại");
            }
            }
            while (!email.matches(reemail));
    
        }
        else {
            System.out.println("Nhập lại");
        }
        }
        while (!hoten.matches(rehoten));
    }
}
