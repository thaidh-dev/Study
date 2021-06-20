package Lab3_hoantppd02166;

import static java.lang.Math.sqrt;
import java.util.Scanner;

public class Demo2 {

    public static void main(String[] args) {

        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số bất kì: ");
        Double a = Sc.nextDouble();
        if (a >= 0) {
            double b;
            b = sqrt(a);
            System.out.println("Căn bậc 2 của a: " + b);
        } else {
            System.out.println("Số nhập vào nhỏ hơn 0");

        }
    }
}
