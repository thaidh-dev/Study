package Lab2;

import java.util.Scanner;

public class Bai5 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập a: ");
        int a = Sc.nextInt();
        System.out.println("Nhập b: ");
        int b = Sc.nextInt();
        System.out.println("Nhập c: ");
        int c = Sc.nextInt();
        int MIN = (a < b) ? (a < c ? a : c) : (b < c ? b : c);
        System.out.println("Số lớn nhất: " + MIN);
    }

}
