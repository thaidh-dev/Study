/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1_hoantppd02166;
import java.util.Scanner;  
public class Bài31 {
 
    public static void main(String[] args) {
        Scanner Sn = new Scanner(System.in);
        System.out.print("Nhập chiều rộng: ");
        Double Rong = Sn.nextDouble();
        System.out.print("Nhập chiều dài: ");
        Double Dai = Sn.nextDouble();
        Double Dientich = Rong * Dai;
        System.out.printf("Diện tích hình chữ nhật: %.2f \n" , Dientich);
    }
    
}