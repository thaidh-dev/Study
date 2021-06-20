package bài.pkg13;

import java.util.Scanner;

public class Bài13 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số nhỏ nhất: ");
        int min = thai.nextInt();
        System.out.print("Số lớn nhất: ");
        int max = thai.nextInt();
        int tong = 0;
        while (min <= max) {
            tong += min;
            min++;
        }
        System.out.println(""+tong);
    }
    
}
