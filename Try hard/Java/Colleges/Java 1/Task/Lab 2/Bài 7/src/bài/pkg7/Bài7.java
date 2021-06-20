/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bài.pkg7;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Bài7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số a: ");
        int a = thai.nextInt();
        System.out.print("Số b: ");
        int b = thai.nextInt();
        if (a>b) {
            System.out.println("Số lớn nhất: "+a);
            System.out.println("Số bé nhất: "+b);
        }
        else {
            System.out.println("Số bé nhất: "+a);
            System.out.println("Số lớn nhất "+b);
        }
    }
    
    
}
