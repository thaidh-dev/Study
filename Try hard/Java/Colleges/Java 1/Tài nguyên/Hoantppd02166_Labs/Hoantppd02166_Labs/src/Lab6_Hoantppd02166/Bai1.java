
package Lab6_Hoantppd02166;

import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số a: ");
        double a = Sc.nextDouble();
        double b = 5 + 15*Math.random();
        System.out.println("Số b đã làm tròn " + tron(b));
        System.out.println("Số nhỏ nhất: " + min(a, b));
        System.out.println("Lũy thừa cơ số " + a + "^" + b + " = " + Math.round(luythua(a, b)));
    }
    public static double min(double x, double y){
        return Math.min(x, y);
    }
    public static double luythua(double x, double y){
        return Math.pow(x, y);
    }
    public static double tron(double x){
        return Math.round(x);
    }
}
