package lab3;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số: ");
        int x = sc.nextInt();
        boolean m = true;
        for (int a = 2; a < x - 1; a++) {
            if (x % a == 0) {
                m = false;
                System.out.println("Không phải số nguyên tố");
                break;
            }
        }
        if (m == true) {
            System.out.println("Số nguyên tố");
    }
}
}