package Lab4_hoantppd02166;
// Học cửu chương

import java.util.Scanner;

public class Demo4 {

    public static void main(String[] args) {
        int i = 1;
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập bảng cửu chương muốn học: ");
        int kq = Sc.nextInt();
        while (i <= 10) {
            System.out.println(kq + "x" + i + "=" + kq * i);
            i++;
        }

    }

}
