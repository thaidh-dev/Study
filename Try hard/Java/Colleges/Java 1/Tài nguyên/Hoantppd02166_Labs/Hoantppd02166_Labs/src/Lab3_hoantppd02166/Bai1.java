package Lab3_hoantppd02166;

import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
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

}
