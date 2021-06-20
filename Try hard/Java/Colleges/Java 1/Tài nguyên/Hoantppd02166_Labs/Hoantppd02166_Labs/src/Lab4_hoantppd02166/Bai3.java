/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab4_hoantppd02166;

import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số nguyên N = ");
        int n = Sc.nextInt();
        int i = 2;
        boolean songuyento = true;
        while (i < n) {
            if (n % i == 0) {
                System.out.println("Không phải số nguyên tố");
                songuyento = false;
                break;
            }
            i++;
        }
        if (songuyento == true) {
            System.out.println(n + " Là số nguyên tố");
        }
    }
}
