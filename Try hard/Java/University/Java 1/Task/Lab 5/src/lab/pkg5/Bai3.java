package lab.pkg5;

import java.util.Scanner;

public class Bai3 {
    static Scanner sc = new Scanner(System.in);
    static String ten[];
    static double gia[];
    
    public static void main(String[] args) {
        menu();
    }
    
    static void menu() {
        int chon;
        do {
        System.out.println("MENU");
        System.out.println("1. Nhập");
        System.out.println("2. Sắp xếp");
        System.out.println("3. Xóa");
        System.out.println("4. Xuất giá trung bình");
        System.out.println("0. Thoát");
        System.out.print("Mời bạn chọn 1 chức năng: ");
        chon = sc.nextInt();
        switch (chon) {
            case 1:
                yeucau1();
                break;
            case 2:
                yeucau2();
                break;
            case 3:
                yeucau3();
                break;
            case 4:
                yeucau4();
                break;
            case 0:
                System.out.println("Thoát");
                System.exit(0);
        }
        }
        while (chon != 0);
        }
    
    static void yeucau1() {
        System.out.println("\nNHẬP\n");
        System.out.print("Bạn muốn nhập bao nhiêu sản phẩm: ");
        int a = sc.nextInt();
        ten = new String[a];
        gia = new double[a];
        for (int x = 0; x < a; x++) {
            ten[x] = Lmao.nhapString("Nhập tên sản phẩm: ");
            gia[x] = Lmao.nhapDouble("Nhập giá: ");
        }
        System.out.println("");
    }
    
    static void yeucau2() {
        System.out.println("\nSẮP XẾP VÀ XUẤT\n");
        for (int x = 0; x < gia.length - 1; x++) {
            for (int y = x+1; y < gia.length; y++) {
                if (gia[x] < gia[y]) {
                    Lmao.swap(ten, x, y);
                    Lmao.swap(gia, x, y);
                }
            }
        }
        
        for (int x = 0; x < gia.length; x++) {
            System.out.printf("Tên sản phẩm: %s\t Giá: %.1f\n", ten[x], gia[x]);
        }
        System.out.println("");
    }
    
    static void yeucau3() {
        System.out.println("\nXÓA VÀ XUẤT\n");
        String a = Lmao.nhapString("Nhập tên sản phẩm muốn xóa: ");
        for (int x = 0; x < ten.length; x++) {
            if (ten[x].equalsIgnoreCase(a)) {
                ten = Lmao.xoa(ten, x);
                gia = Lmao.xoa(gia, x);
            }
        }
        
        for (int x = 0; x < ten.length; x++) {
            System.out.printf("Tên sản phẩm: %s\t Giá: %.1f\n", ten[x], gia[x]);
        }
        System.out.println("");
    }
    
    static void yeucau4() {
        System.out.println("\nGIÁ TRUNG BÌNH\n");
        double tong = 0;
        double gtb = 0;
        for (int x = 0; x < gia.length; x++) {
            tong += gia[x];
            gtb = tong/(x+1);
        }
        System.out.println("Giá trung bình = "+gtb);
        System.out.println("");
    }
}
