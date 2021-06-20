/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Bai1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Hệ số a: ");
        double a = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b = thai.nextDouble();
        if(a==0){
            if (b==0){
            System.out.println("Vô số nghiệm");
        }
            else {
                System.out.println("Vô nghiệm");
            }
        }
        else {
            double x = -b/a;
            System.out.println("Ngiệm của phương trình là: "+x);
        }
        }
    }
