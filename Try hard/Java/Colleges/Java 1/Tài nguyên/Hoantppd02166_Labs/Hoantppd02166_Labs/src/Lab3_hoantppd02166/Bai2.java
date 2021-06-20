package Lab3_hoantppd02166;

import java.util.Scanner;

public class Bai2 {

    public static void main(String[] args) {
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
}
