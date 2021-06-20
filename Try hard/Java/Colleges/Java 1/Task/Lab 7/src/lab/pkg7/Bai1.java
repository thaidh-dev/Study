package lab.pkg7;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Họ tên: ");
        String hoten = sc.nextLine();
        String ho = hoten.substring(0,hoten.indexOf(" "));
        String ten = hoten.substring(hoten.lastIndexOf(" ") + 1);
        System.out.println("Xuất họ: "+ho.toUpperCase());
        if (hoten.indexOf(" ") != hoten.lastIndexOf(" ")) {
            System.out.println("Xuất tên đệm: "+hoten.substring(hoten.indexOf(" ") + 1, hoten.lastIndexOf(" ")));
        }
        else
            System.out.println("Không có tên đệm");
        System.out.println("Xuất tên: "+ten.toUpperCase());
    }
    
}
