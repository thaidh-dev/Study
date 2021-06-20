package Lab2;

import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập điểm toán: ");
        double math = Sc.nextDouble();
        System.out.print("Nhập điểm ly: ");
        double geography = Sc.nextDouble();
        System.out.print("Nhập điểm hóa: ");
        double chemistry = Sc.nextDouble();
        double average = (math * 3 + geography * 2 + chemistry) / 6;
        System.out.printf("Điểm trung bình: %.2f \n", average);
    }

}
