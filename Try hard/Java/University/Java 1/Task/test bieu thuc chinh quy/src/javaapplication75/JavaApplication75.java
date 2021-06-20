package javaapplication75;

import java.util.Scanner;

public class JavaApplication75 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = "\\d(.[a-z]){1,2}";
        System.out.print("Nhập: ");
        String b = sc.nextLine();
        if (b.matches(a)) {
            System.out.println("Thành công");
        }
        else {
            System.out.println("Sai");
        }
    }
    
}
