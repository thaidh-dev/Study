
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
public class Lab2Bai4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner thai = new Scanner(System.in);
        System.out.print("Nhập năm: ");
        int nam = thai.nextInt();
        if(nam%4 == 0 && nam%100 != 0){
            System.out.println("Năm nhuận");
        }
        else{
            System.out.println("Năm không nhuận");
        }
    }
    
}
