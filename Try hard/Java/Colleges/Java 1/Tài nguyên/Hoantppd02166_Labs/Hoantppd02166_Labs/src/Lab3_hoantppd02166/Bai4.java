package Lab3_hoantppd02166;

import java.util.Scanner;

public class Bai4 {

    public static void main(String[] args) {

        do {
            Menu();
            Scanner Sc = new Scanner(System.in);
            System.out.print("Chọn một chức năng: ");
            int so = Sc.nextInt();
            switch (so) {
                case 1:
                    System.out.println("1. Giải phương trình bậc nhất");
                    batnhat();
                    break;
                case 2:
                    System.out.println("2. Giải phương trình bậc 2");
                    bat2();
                    break;
                case 3:
                    System.out.println("3. Tính tiền điện ");
                    tiendien();
                    break;
                case 4:
                    System.out.println("Kết thúc chương trình");
                    System.exit(0);
            }
        } while (true);
    }

    public static void Menu() {
        System.out.println("*------------------------------------------------------*");
        System.out.println("1. Giải phương trình bậc nhất");
        System.out.println("2. Giải phương trình bậc 2");
        System.out.println("3. Tính tiền điện ");
        System.out.println("4. Kết thúc ");
        System.out.println("***---------------------------------------------------***");
    }

    public static void batnhat() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập số a: ");
        double a = Sc.nextInt();
        System.out.println("Nhập số b: ");
        double b = Sc.nextInt();
        double x;
        if (a != 0) {
            x = -b / a;
            System.out.println("Vì a khác 0");
            System.out.println("x=-b/a" + "=" + "-" + b + "/" + a + "=" + x);
        } else {
            if (b == 0) {
                System.out.println("Phương trình vô số nghiệm");
            } else {
                System.out.println("Phương trình vô nghiệm");
            }
        }
    }

    public static void bat2() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập số a: ");
        double a = Sc.nextInt();
        System.out.println("Nhập số b: ");
        double b = Sc.nextInt();
        System.out.println("Nhập số c: ");
        double c = Sc.nextInt();
        double x1, x2, delta;
        delta = (b * b) - 4 * a * c;
        if (a == 0) {
            x1 = -c / b;
            System.out.println("X=-c/b" + "=" + x1);
        } else {
            if (delta < 0) {
                System.out.println("Phương trình vô nghiệm");
            } else if (delta == 0) {
                x1 = -b / 2 * a;
                System.out.println("Phương trình có nghiệm kép: " + x1);
            } else {
                x1 = (-b - Math.sqrt(delta)) / (2 * a);
                x2 = (-b + Math.sqrt(delta)) / (2 * a);
                System.out.println("Giá trị x1: " + x1);
                System.out.println("Giá trị x2: " + x2);
            }
        }
    }

    public static void tiendien() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập số điện sử dụng: ");
        int so = Sc.nextInt();
        double tien;
        if (so < 50) {
            tien = so * 1000;
        } else {
            tien = 50 * 1000 + (so - 50) * 1200;
        }
        System.out.println("Số tiền phải trả: " + tien);
    }
}
