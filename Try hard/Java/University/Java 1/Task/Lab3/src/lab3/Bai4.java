package lab3;

import java.util.Scanner;

public class Bai4 {
    static String hoten[] = new String[3];
    static double diem[] = new double[3];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        for (int a = 0; a < hoten.length; a++) {
            System.out.printf("Sinh viên %d\n",a+1);
            hoten[a] = docten();
            diem[a] = docdiem();
        }
        xuat();
        for (int x = 0; x < 2; x++) {
            for (int y = x+1; y < 3; y++) {
                if (diem[x] > diem[y]) {
                    swap(diem, x, y);
                    swap(hoten, x, y);
                }
            }
        }
        xuat();
    }
    
    static String docten() {
        System.out.print("Họ tên: ");
        String a = sc.nextLine();
        return a;
    }
    
    static double docdiem() {
        System.out.print("Điểm: ");
        double a = Double.parseDouble(sc.nextLine());
        return a;
    }
    
    static String xeploai(double diem) {
        String hocluc;
        if (diem < 5) {
            hocluc =  "Yếu";
        }
        else if (diem < 6.5) {
            hocluc =  "Trung bình";
        }
        else if (diem < 7.5) {
            hocluc =  "Khá";
        }
        else if (diem < 9) {
            hocluc = "Giỏi";
        }
        else {
            hocluc =  "Xuất sắc";
        }
        return hocluc;
    }
    
    static void xuat() {
        for (int a = 0; a < 3; a++) {
                System.out.printf("\nSinh viên %d: \tHọ tên: %s\tĐiểm: %f\tHọc lực: %s", a+1, hoten[a], diem[a], xeploai(diem[a]));
        }
        System.out.println("");
    }
    
    static void swap(double arr[], int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    static void swap(String arr[], int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
