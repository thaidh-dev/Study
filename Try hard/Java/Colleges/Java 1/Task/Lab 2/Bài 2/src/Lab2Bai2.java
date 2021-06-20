
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
public class Lab2Bai2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
Scanner thai = new Scanner(System.in);
        System.out.print("Bán kính hình tròn: ");
        double bankinh = thai.nextDouble();
       if (bankinh >0) {
        System.out.println("Chu vi: "+bankinh*2*3.14);
        System.out.println("Diện tích: "+Math.pow(bankinh,2)*3.14);
    }
       else {
           System.out.println("Bán kính phải có giá trị lớn hơn không");
       }
    }
}
