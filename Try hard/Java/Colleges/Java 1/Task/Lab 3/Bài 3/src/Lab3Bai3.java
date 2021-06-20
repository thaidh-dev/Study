
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
public class Lab3Bai3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số điện sử dụng: ");
        double a = thai.nextDouble();
        if(a<50){
            System.out.println("Tiền điện: "+a*1000);
    }
        else{
            System.out.println("Tiền điện: "+ (50*1000+(a-50)*1200));
}
    }
}