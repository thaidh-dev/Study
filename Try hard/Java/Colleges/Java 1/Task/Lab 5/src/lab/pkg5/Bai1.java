package lab.pkg5;

import java.util.Arrays;
import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        int lol = 1;
        int a[] = new int[5];
        int i = 0;
        double tong = 0;
        double dem = 0;
        double trungbinhcong;
        do {
        System.out.printf("Nhập số thứ %d: ", lol);
        a[i] = thai.nextInt();
        lol++;
        if (a[i] % 3 == 0) {
            tong += a[i];
            dem++;
        }
        trungbinhcong = tong/dem;
        i++;
        }
        while (lol <= a.length);
        int min;
        min = a[0];
        for (lol = 1; lol < a.length; lol++) {
            if (min > a[lol]) {
                min = a[lol];
            }
        }
        System.out.println("Số nhỏ nhất là: "+min);
        System.out.println("Trung bình cộng các phần tử chia hết cho 3 là: "+trungbinhcong);
        Arrays.sort(a);
        System.out.println("a[] = "+Arrays.toString(a));
    }
}
