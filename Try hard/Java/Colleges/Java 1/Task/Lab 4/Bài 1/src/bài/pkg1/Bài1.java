package bài.pkg1;

import java.util.Scanner;

public class Bài1 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số min: ");
        double min = thai.nextDouble();
        System.out.print("Số max: ");
        double max = thai.nextDouble();
        double tong = 0;
        double dem = 0;
        while (min <= max) {
            if (min % 5 == 0) {
                tong += min;
                dem++;
            }
            min++;
        }
        System.out.println("Trung bình cộng = "+tong/dem);
    }
    
}
