
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
public class bai3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner bai3 = new Scanner(System.in);
        System.out.print("Độ dài cạnh hình lập phương: ");
        int dodai = bai3.nextInt(); 
        System.out.println("Thể tích lập phương: "+Math.pow(dodai,3));
    }
    
}
