package Lab2;

import java.util.Scanner;

public class Bai4 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập năm: ");
        int year = Sc.nextInt();
        String result = year % 4 == 0 && year % 100 != 0 ? "Năm nhuận" : "Không phải năm nhuận";
        System.out.println("Kết quả " + result);
    }
}
