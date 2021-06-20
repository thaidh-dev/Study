package bài.pkg3;

import java.util.Scanner;

public class Bài3 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số điện sử dụng: ");
        double a = thai.nextDouble();
        
        if (a<50) {
            System.out.println("Tiền điện: "+a*1000);
        }
        else {
            System.out.println("Tiền điện: "+(50*1000+(a-50)*1200));
        }
    }
}
