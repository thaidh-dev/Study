package bài.pkg27;

import java.util.Scanner;

public class Bài27 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        int a = 1;
        double tong = 0;
        double so;
        int dem = 0;
        double trungbinhcong;
        do {
            System.out.printf("Nhập số thứ %d: ", a);
            a++;
            so = thai.nextDouble();
            dem++;
            tong += so;
            trungbinhcong = tong/dem;
        }
        while (tong < 2016);
        System.out.println("Trung bình cộng của dãy số vừa nhập là: "+trungbinhcong);
    }
    
}
