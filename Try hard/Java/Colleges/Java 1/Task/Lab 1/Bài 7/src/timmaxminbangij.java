
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
public class timmaxminbangij {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số a: ");
        int a = thai.nextInt();
        System.out.print("Số b: ");
        int b = thai.nextInt();
        int max = a>b ? a : b;
        int min = a>b ? b : a;
        System.out.println("Số lớn nhất: "+max);
        System.out.println("Số nhỏ nhất: "+min);
    }
    
}
