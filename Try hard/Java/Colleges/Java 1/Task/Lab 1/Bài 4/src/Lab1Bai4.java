
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
public class Lab1Bai4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner chahieugihet = new Scanner(System.in);
        System.out.print("Họ và tên: ");
        String hoten = chahieugihet.nextLine();
        System.out.print("Điểm TB: ");
        double diemTB = chahieugihet.nextDouble();
                System.out.println("Họ và tên: "+hoten);
        System.out.println("Điểm TB: "+diemTB);
                
    }
    
}
