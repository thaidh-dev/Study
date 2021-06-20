package max.min.pkg4.so;

import java.util.Scanner;

public class MaxMin4So {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
                System.out.print("Số a: ");
        double a = thai.nextDouble();
        System.out.print("Số b: ");
        double b = thai.nextDouble();
        System.out.print("Số c: ");
        double c = thai.nextDouble();
        System.out.print("Số d: ");
        double d = thai.nextDouble();
        
        if (a>b) {
            if (a>c) {
                if (a>d) {
                    System.out.println("Số lớn nhất: "+a);
                }
                else {
                    System.out.println("Số lớn nhất: "+d);
                }
            }
            else {
                if (c>d) {
                    System.out.println("Số lớn nhất: "+c);
                }
                else {
                    System.out.println("Số lớn nhất: "+d);
                }
            }
        }
        else {
            if (b>c) {
                if (b>d) {
                    System.out.println("Số lớn nhất: "+b);
                }
                else {
                    System.out.println("Số lớn nhất: "+d);
                }
            }
            else {
                if (c>d) {
                    System.out.println("Số lớn nhất: "+c);
                }
                else {
                    System.out.println("Số lớn nhất: "+d);
                }
            }
        }

    }
    
}
