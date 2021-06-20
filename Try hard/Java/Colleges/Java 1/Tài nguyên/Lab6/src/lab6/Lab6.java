/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.util.Scanner;

/**
 *
 * @author Dang Hung
 */
public class Lab6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double diem;
        String ten;
         Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten hoc sinh: ");
        ten=sc.nextLine();
        System.out.print("Nhap diem: ");
        diem=sc.nextDouble();
            String xl;
            if (diem < 5) {

                xl = "yeu";
            } else if (diem <= 6.5) 
            {
                xl = "Trung binh";
            } else if (diem < 7.5) 
            {
                xl = "Kha";
            } else if (diem < 9) 
            {
                xl = "Gioi";

            } else {
                xl = "Xuat sac";
            }
            
            System.out.println("Hoc luc:"+xl);

            
     
        }
}


        
        

