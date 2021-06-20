
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Lab3Bai2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Hệ số a: ");
        double a = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b = thai.nextDouble();
        System.out.print("Hệ số c: ");
        double c = thai.nextDouble();

        if(a==0){
            System.out.println("Phương trình bậc 2 có điều kiện là a phải khác 0");
        }
        else{
           double delta = Math.pow(b,2) -4*a*c;
           if(delta>0){
               System.out.println("Nghiệm 1: "+ (-b+Math.sqrt(delta))/(2*a));
               System.out.println("Nghiệm 2: "+ (-b-Math.sqrt(delta))/(2*a));
           }
           else if(delta==0){
               System.out.println("Nghiệm kép: "+ -b/(2*a));
    }
           else {    
            System.out.println("Vô nghiệm");
}
        }
    }
}