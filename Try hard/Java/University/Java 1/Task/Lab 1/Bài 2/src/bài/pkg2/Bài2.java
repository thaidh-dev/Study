/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bài.pkg2;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Bài2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner bai2 = new Scanner(System.in);
        System.out.print("Chiều dài: ");
        int dai = bai2.nextInt();
        System.out.print("Chiều rộng: ");
        int rong = bai2.nextInt();
        System.out.println("Chu vi: "+(dai+rong)*2);
        System.out.println("Diện tích: "+dai*rong);
        System.out.println("Cạnh nhỏ nhất: "+Math.min(dai,rong));
    }
    
}
