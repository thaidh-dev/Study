package Lab4_hoantppd02166;

import java.util.Scanner;
// Tính giai thừa N

public class Demo3 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập N: ");
        int N = Sc.nextInt();
        int i = 1;
        double kq = 1;
        while (i <= N) {
            kq = kq * i;
            i++;
        }
        System.out.println("Kết Quả: " + kq);
    }

}
