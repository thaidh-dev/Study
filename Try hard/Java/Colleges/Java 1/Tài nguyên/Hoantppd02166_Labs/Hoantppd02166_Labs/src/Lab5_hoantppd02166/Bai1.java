package Lab5_hoantppd02166;

import java.util.Scanner;

public class Bai1 {

    // Nhập các số nguyên từ bàn phím
    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số lượng phần tử: ");
        int n = Sc.nextInt();
        int[] so = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Số thứ " + (i + 1) + ": ");
            so[i] = Sc.nextInt();
        }
        // Tìm Số nhỏ nhất
        int min = so[0];
        for (int i = 1; i < n; i++) {
            if (min > so[i]) {
                min = so[i];
            }
        }
        System.out.println("Số nhỏ nhất là: " + min);
        // Tính trung bình cộng
        int tong, dem;
        tong = 0;
        dem = 0;
        for (int i = 0; i < n; i++) {
            if (so[i] % 3 == 0) {
                tong = tong + so[i];
                dem++;
            }
        }
        System.out.println("Trung bình cộng các số chia hết cho 3 là: " + (tong / dem));
        for (int i = 0; i < so.length - 1; i++) {
            for (int j = i + 1; j < so.length; j++) {
                if (so[i] > so[j]) {
                    int temp = so[i];
                    so[i] = so[j];
                    so[j] = temp;
                }
            }
        }
        System.out.println("Xuất thông tin");
        for (int i = 0; i < n; i++) {
            System.out.println("Số thứ " + (i + 1) + ": " + so[i]);
        }
    }
}
