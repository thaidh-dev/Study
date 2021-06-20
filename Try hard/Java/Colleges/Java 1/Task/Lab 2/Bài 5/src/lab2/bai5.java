/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class bai5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.println("Cạnh a: ");
        int a = thai.nextInt();
        System.out.println("Cạnh b: ");
        int b = thai.nextInt();
        System.out.println("Cạnh c: ");
        int c = thai.nextInt();
        if (a>0 && b>0 && c>0) {
            if ((a+b)>c && (a+c)>b && (b+c)>a) {
                double p = (a+b+c)/2;
                System.out.println("Chu vi tam giác: "+(a+b+c));
                System.out.println("Diện tích tam giác: "+Math.sqrt(p*(p-a)*(p-b)*(p-c)));
            }
            else {
                System.out.println("Đây không phải là 3 cạnh của tam giác");
            }
        }
        else {
            System.out.println("Cạnh của tam giác cần lớn hơn 0");
        }    
    }
    
}
