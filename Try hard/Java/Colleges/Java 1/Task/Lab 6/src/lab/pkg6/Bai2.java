package lab.pkg6;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập điểm: ");
        double diem = sc.nextDouble();
        System.out.println("Học lực: "+xeploai(diem));
        
    }
    public static String xeploai(double diem) {
        if (diem < 5 && diem >= 0) {
            return "Yếu";
        }
        if (diem >= 5 && diem < 6.5) {
            return "Trung bình";
        }
        if (diem >= 6.5 && diem < 7.5) {
            return "Khá";
        }
        if (diem >= 7.5 && diem < 9) {
            return "Giỏi";
        }
        if (diem >= 9 && diem <= 10) {
            return "Xuất sắc";
        }
        return "Nhập lại";
    }
}
