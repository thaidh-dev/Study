package lab.pkg5;

import java.util.Arrays;
import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn muốn nhập bao nhiêu số thực: ");
        int a = sc.nextInt();
        int m[] = new int[a];
        int tong = 0;
        for (int x = 0; x < a; x++) {
            System.out.printf("Nhập số thư %d: ",x+1);
            m[x] = sc.nextInt();
            tong += m[x];
        }
        System.out.println("m = "+Arrays.toString(m));
        System.out.println("Tổng = "+tong);
    }
}
