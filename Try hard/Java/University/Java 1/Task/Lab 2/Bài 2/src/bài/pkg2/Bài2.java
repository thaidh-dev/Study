package bài.pkg2;

import java.util.Scanner;

public class Bài2 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Hệ số a: ");
        double a = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b = thai.nextDouble();
        System.out.print("Hệ số c: ");
        double c = thai.nextDouble();
        double delta = Math.pow(b,2) - 4*a*c;
        
        if (a==0) {
            System.out.println("Phương trình bậc 2 có điều kiện là a phải khác 0");
        }
        else {
            if (delta<0) {
                System.out.println("Phương trình vô ngiệm");
            }
            else if (delta==0) {
                System.out.println("Ngiệm kép: "+-b/(2*a));
            }
            else {
                System.out.println("X1 = "+(-b+Math.sqrt(delta))/(2*a));
                System.out.println("X2 = "+(-b-Math.sqrt(delta))/(2*a));
            }
        }
    }
}
