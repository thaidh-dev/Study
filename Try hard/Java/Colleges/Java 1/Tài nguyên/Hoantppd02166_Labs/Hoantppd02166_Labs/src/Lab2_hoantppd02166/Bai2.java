package Lab2;

import java.util.Scanner;

public class Bai2 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập bán kính: ");
        double r = Sc.nextDouble();
        double d = r * 2;
        Double S = Math.PI * r * r;
        Double C = Math.PI * d;
        System.out.printf("Diện tích: %.2f \n" + S);
        System.out.printf("Chu vi: %.2f \n", C);
    }

}
