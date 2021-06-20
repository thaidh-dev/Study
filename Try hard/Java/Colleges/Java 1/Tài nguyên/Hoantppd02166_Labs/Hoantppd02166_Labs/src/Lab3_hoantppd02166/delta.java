package Lab3_hoantppd02166;

import java.util.Scanner;

public class delta {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        double x1, x2, x;
        System.out.print("Nhập a: ");
        double a = Sc.nextDouble();
        System.out.print("Nhập b: ");
        double b = Sc.nextDouble();
        System.out.print("Nhập c: ");
        double c = Sc.nextDouble();
        double delta = (b * b - 4 * a * c);
        if (delta < 0) {
            System.out.println("Vô nghiệm");
        } else if (delta == 0) {
            x = -b / 2 * a;
            System.out.println("Giá trị của X là: x1 = x2 = " + x);
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            System.out.println("Giá trị x1: " + x1);
            System.out.println("Giá trị x2: " + x2);
        }

    }
}
