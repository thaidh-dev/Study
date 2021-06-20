
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
public class Lab2Bai1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Tên sản phẩm: ");
        String ten = thai.nextLine();
        System.out.print("Đơn Giá: ");
        double gia = thai.nextDouble();
        System.out.print("Số lượng: ");
        int soluong = thai.nextInt();
        System.out.println("Thuế nhập khẩu: "+gia*10/100);
    }
    
}
