package Lab2;

import java.util.Scanner;

public class BTSlide1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ và tên: ");
        String hovaten = sc.nextLine();
        System.out.print("Nhập diểm: "); 
        double diem = sc.nextDouble();
        System.out.print("Nhập tuổi: ");
        int tuoi = sc.nextInt();
        System.out.println("Họ và tên: "+ hovaten);
        System.out.println("Điểm: " + diem);
        System.out.println("Tuổi: " + tuoi);
    }
    
}
