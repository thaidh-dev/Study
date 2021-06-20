package lab.pkg6;

import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Nhập 1 số: ");
        double a = thai.nextDouble();
        double b = 5 + 7.5 * Math.random();
        System.out.printf("Số tự sinh ra: %.1f", b);
        System.out.printf("\nSố nhỏ nhất là: %.1f", Math.min(a, b));
        System.out.printf("\na lũy thừa b: %.1f", Math.pow(a, b));
        System.out.println("\nLàm tròn b: "+Math.round(b));
    }
    
}
