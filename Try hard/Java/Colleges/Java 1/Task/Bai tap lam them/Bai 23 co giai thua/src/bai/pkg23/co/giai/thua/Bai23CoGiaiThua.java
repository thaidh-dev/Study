package bai.pkg23.co.giai.thua;

import java.util.Scanner;

public class Bai23CoGiaiThua {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        double s = 1;
        double x;
        double giaithua = 1;
        double mau = 2;
        double n;
        double somu = 1;
        System.out.print("n = ");
        n = thai.nextDouble();
        System.out.print("x = ");
        x = thai.nextDouble();
        if (n > 0) {
        do {
            giaithua *= mau;
            s += (Math.pow(x, somu))/giaithua;
            mau++;
            somu++;
        }
        while (mau <= (n+1) && somu <= n);
        System.out.println("s = "+s);
}
        else {
            System.out.println("Số mũ n phải lớn hơn 0");
        }
}
}