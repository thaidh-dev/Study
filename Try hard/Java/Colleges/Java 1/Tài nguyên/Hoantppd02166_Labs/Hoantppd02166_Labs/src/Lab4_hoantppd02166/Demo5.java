package Lab4_hoantppd02166;

import java.util.Scanner;

public class Demo5 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int i;
        do {
            System.out.println("Nhap diem ");
            i = Sc.nextInt();
        } while (i > 10 || i < 0);
        System.out.println("Điểm: " + i);
    }
}
