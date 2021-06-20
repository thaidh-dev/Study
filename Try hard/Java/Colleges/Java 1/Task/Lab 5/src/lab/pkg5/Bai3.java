package lab.pkg5;

import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        double a[][] = new double[3][2];
        for (int m = 0; m <= 2; m++) {
            for (int n = 0; n <= 1; n++) {
                System.out.printf("Nhập phần tử cột %d của hàng %d: ", (n+1), (m+1));
                a[m][n] = thai.nextInt();
            }
        }
        
        System.out.print("a[][] = {");
        for (int x = 0; x <= 2; x++) {
            System.out.print("{");
            for (int y = 0; y <= 1; y++) {
                a[x][y] = Math.pow(a[x][y], 2);
                System.out.print(""+a[x][y]);
                if (y != 1) {
                    System.out.print(", ");
                }
                if (y == 1 && x != 2) {
                    System.out.print("}, ");
                }
                if (y == 1 && x == 2) {
                    System.out.print("}");
                }
            }
        }
        System.out.print("}");
    }
}
