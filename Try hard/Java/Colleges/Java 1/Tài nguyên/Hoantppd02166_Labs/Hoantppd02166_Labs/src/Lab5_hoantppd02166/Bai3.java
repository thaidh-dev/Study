package Lab5_hoantppd02166;

import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
        int[][] a = new int[3][2];
        Scanner Sc = new Scanner(System.in);
        //Nhập
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println("a[" + i + "][" + j + "] = ");
                a[i][j] = Sc.nextInt();
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(a[i][j] * a[i][j] + "\t");
            }
        }
    }
}
