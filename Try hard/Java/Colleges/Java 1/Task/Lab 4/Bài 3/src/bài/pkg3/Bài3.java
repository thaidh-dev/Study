package bài.pkg3;

import java.util.Scanner;

public class Bài3 {

    public static void main(String[] args) {
    Scanner thai = new Scanner(System.in);
        System.out.print("Nhập số: ");
    int a = thai.nextInt();
    int b;
    boolean snt = true;
        for (b = 2; b <= a/2; b++) {
        if (a % b == 0) {
            snt = false;
            break;
        }
    }
        if (snt == false) {
            System.out.println("Không phải là số nguyên tố");
        }
        else {
            System.out.println("Số nguyên tố");
        }
}
}
