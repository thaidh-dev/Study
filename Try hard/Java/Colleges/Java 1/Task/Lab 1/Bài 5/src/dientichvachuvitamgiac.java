
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class dientichvachuvitamgiac {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Hệ số a: ");        
        int a = thai.nextInt();
        System.out.print("Hệ số b: ");        
        int b = thai.nextInt();
        System.out.print("Hệ số c: ");
        int c = thai.nextInt();
        double p = (a+b+c)/2;
        double chuvi = a+b+c;
        double dientich = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println("Chu vi: "+chuvi);
        System.out.println("Diện tích: "+dientich);
    }
    
}
