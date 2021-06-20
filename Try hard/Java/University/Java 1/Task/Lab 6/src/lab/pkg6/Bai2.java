package lab.pkg6;

import java.util.Scanner;

public class Bai2 {
    Scanner sc = new Scanner(System.in);
    String [] ten = new String[5];
    String [] hang = new String[5];
    double gia[] = new double[5];
    Bai2lol s = new Bai2lol();

    public static void main(String[] args) {
        Bai2 w = new Bai2();
        w.nhap();
        for (int x = 0; x < 5; x++) {
            if (w.hang[x].equalsIgnoreCase("nokia")) {
                System.out.printf("\nTên sản phẩm: %s\t Hãng: %s\t Giá: %.1f", w.ten[x], w.hang[x], w.gia[x]);
            }
        }
    }
    
    void nhap() {
        for (int x = 0; x < 5; x++) {
            ten[x] = s.nhapString("Nhập tên sản phẩm: ");
            hang[x] = s.nhapString("Nhập hãng: ");
            gia[x] = s.nhapDouble("Nhập giá: ");
        }
    }
}
