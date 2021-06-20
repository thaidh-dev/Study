package Lab4_hoantppd02166;

import java.util.Scanner;

public class Bai2 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số m = ");
        int m = Sc.nextInt();
        int f0 = 1, f1 = 2, next = f0 + f1;
        System.out.println(f0);
        System.out.println(f1);
        while (next < m) {
            System.out.println(next);
            f0 = f1;
            f1 = next;
            next = f0 + f1;
        }

    }

}
