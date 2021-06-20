/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bài.pkg6;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Bài6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Hãy nhập vào 1 số: ");
        int a = thai.nextInt();
        if (a%2==0) {
            System.out.println("Số chẵn");
        }
        else {
            System.out.println("Số lẻ");
        }
}
}