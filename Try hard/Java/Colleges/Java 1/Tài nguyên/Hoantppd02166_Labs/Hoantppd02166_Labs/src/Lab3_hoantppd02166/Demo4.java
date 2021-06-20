package Lab3_hoantppd02166;

import java.util.Scanner;

public class Demo4 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        int th, nm, songay;
        System.out.print("Nhập tháng: ");
        th = Sc.nextInt();
        System.out.print("Nhập năm: ");
        nm = Sc.nextInt();
        if (th >= 1 && th <= 12) {
            switch (th) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    songay = 31;
                    System.out.println("Số ngày: " + songay);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    songay = 30;
                    System.out.println("Số ngày: " + songay);
                    break;
                case 2:
                    if (nm % 400 == 0 || (nm % 4 == 0 && nm % 100 != 0)) // nam nhuan
                    {
                        songay = 29;
                        System.out.println("songay: " + songay);
                    } else {
                        songay = 28;
                        System.out.println("songay: " + songay);
                    }
            }
        } else {
            System.out.println("=> Thang khong hop le! \n");
        }
    }
}
