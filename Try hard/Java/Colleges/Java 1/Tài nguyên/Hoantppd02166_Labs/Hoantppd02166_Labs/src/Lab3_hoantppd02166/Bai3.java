package Lab3_hoantppd02166;

import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
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
