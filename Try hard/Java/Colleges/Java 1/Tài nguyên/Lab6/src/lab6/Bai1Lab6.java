/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6;

import java.util.Scanner;

/**
 *
 * @author Dang Hung
 */
public class Bai1Lab6 {

    public static void main(String[] args) {
        double a;
        Scanner nhap = new Scanner(System.in);
        System.out.print("Nhap so a: ");
        a = nhap.nextDouble();
        double b = 5 + 15 * Math.random();
        int rand = (int) Math.round(b);
        System.out.printf("\nSo ngau nhien b: %.0f", b);

        double min, pow;
        min = Math.min(a, b);
        System.out.println("\nSo nho nhat trong 2 so la: " + min);
        pow = Math.pow(a, b);
        System.out.println("So a luy thua b la: " + pow);
    }

}
