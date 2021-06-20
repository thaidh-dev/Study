package lab3;

import java.util.Arrays;
import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a[] = new int[7];
        int x = 1;
        while (x <= a.length) {
        System.out.printf("Nhập phần tử thứ %d: ", x);
        a[x - 1] = sc.nextInt();
        x++;
        }
        sapxepgiam(a);
        System.out.print("Sắp xếp giảm: ");
        for (int y : a) {
            System.out.print(" "+y);
        }
        Arrays.sort(a);
        System.out.println("\nSắp xếp tăng: "+Arrays.toString(a));
        System.out.println("Phần tử nhỏ nhất là: "+a[0]);
        double tong = 0;
        int dem = 0;
        for (int m = 0; m < a.length; m++) {
            if (a[m] % 3 == 0) {
                tong += a[m];
                dem++;
            }
        }
        System.out.println("Trung bình cộng: "+tong/dem);
    }
    
    static void sapxepgiam(int x[]) {
        for (int a = 0; a < x.length - 1; a++) {
            for (int b = a + 1; b < x.length; b++) {
                if (x[a] < x[b]) {
                    int temp  = x[a];
                    x[a] = x[b];
                    x[b] = temp;
                }
            }
        }
    }
}

