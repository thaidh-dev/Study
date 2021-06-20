package lab.pkg6;

import java.util.Scanner;

public class Bai1 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a,b;
        do {
        System.out.print("Nhập họ tên: ");
        a = sc.nextLine();
        b = "[a-zA-z ]+";
        if (!a.matches(b)) {
            System.out.println("Mời bạn nhập lại");
        }
        }
        while (!a.matches(b));
        System.out.println("Tên: "+(a.substring(a.lastIndexOf(" ")+1)).toUpperCase());
        System.out.println("Họ: "+(a.substring(0, a.indexOf(" "))).toUpperCase());
        if (a.indexOf(" ") != a.lastIndexOf(" ")) {
            System.out.println("Tên đệm: "+a.substring(a.indexOf(" ")+1, a.lastIndexOf(" ")));
        }
        else {
            System.out.println("Không có tên đệm");
        }
    }
}
