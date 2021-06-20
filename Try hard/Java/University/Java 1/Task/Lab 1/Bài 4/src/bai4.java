
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
public class bai4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner bai4 = new Scanner(System.in);
        System.out.print("Hệ số a: ");
        int a = bai4.nextInt();
        System.out.print("Hệ số b: ");
        int b = bai4.nextInt();
        System.out.print("Hệ số c: ");
        int c = bai4.nextInt();
        double Delta = Math.pow(b,2)-4*a*c;
        System.out.println("Delta: "+Delta);
        System.out.println("Căn Delta: "+Math.sqrt(Delta));
    }
    
}
