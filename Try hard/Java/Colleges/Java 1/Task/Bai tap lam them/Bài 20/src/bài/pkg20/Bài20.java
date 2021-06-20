package bài.pkg20;

import java.util.Scanner;

public class Bài20 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        double lol;
        int nhapso = 1;
        double tongsoduong = 0;
        double tongsoam = 0;
        double dem = 0;
        do {
        System.out.printf("Nhập số thứ %d: ", nhapso);
        lol = thai.nextInt();
        nhapso++;
        if (lol > 0) {
            tongsoduong += lol;
        }
        else if (lol < 0) {
            tongsoam += lol;
            dem++;
        }
    }
        while (lol != 0);
        System.out.println("Tổng số dương: "+tongsoduong);
        System.out.println("Trung bình cộng số âm: "+(tongsoam/dem));
}
}