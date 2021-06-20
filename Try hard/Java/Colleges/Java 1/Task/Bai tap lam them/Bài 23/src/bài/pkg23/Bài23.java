package bài.pkg23;

import java.util.Scanner;

public class Bài23 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        int chon;
        do {
        System.out.print("Chọn 1 để chạy chương trình và chọn 0 để kết thúc: ");
        chon = thai.nextInt();
        switch (chon) {
            case 1:
        double s = 1;
        double x;
        double n;
        double mau = 2;
        double somu = 1;
        System.out.print("n = ");
        n = thai.nextDouble();
        System.out.print("x = ");
        x = thai.nextDouble();
        if (n > 0) {
        do {
            s += (Math.pow(x, somu))/mau;
            somu++;
            mau++;
        }
        while (somu <= n && mau <= (n + 1));
        System.out.println("s = "+s);
            System.out.println("");
    }
        else {
            System.out.println("Số mũ n phải lớn hơn 0");
            System.out.println("");
        }
        break;
            case 0:
                System.out.println("Thoát");
                System.exit(0);
            default:
                System.out.println("Hạy chọn 0 hoặc 1");
                System.out.println("");
    }
}
        while (chon != 0);
}
}