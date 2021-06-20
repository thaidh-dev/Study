package Lab4_hoantppd02166;

import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập min = ");
        double min = Sc.nextDouble();
        System.out.println("Nhập max = ");
        double max = Sc.nextDouble();
        double tong = 0, dem = 0;
        double i = min;
        while (i <= max) {
            if (i % 5 == 0) {
                tong = tong + i;
                dem++;
            }
            i++;
        }
        System.out.println("Trung bình cộng = " + (double) (tong / dem));
    }
}
