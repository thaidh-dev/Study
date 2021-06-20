
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
public class Lab2Bai3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Toán: ");
        double toan = thai.nextDouble();
        System.out.print("Lý: ");
        double ly = thai.nextDouble();
        System.out.print("Hóa: ");
        double hoa = thai.nextDouble();
        if (toan>=0 && ly>=0 && hoa>=0) {
        double diemtrungbinh = (toan*3+ly*2+hoa)/6;
        System.out.println("Điểm trung bình: "+diemtrungbinh);
        }
        else{
            System.out.println("Điểm phải có giá trị lớn hơn hoặc bằng 0");
        }
    }
    
    
    
}
