package lab.pkg6;

import java.util.Scanner;

public class Bai3 {
        
    public static void main(String[] args) {
        String a = nhapten("Họ tên: ");
        int b = nhaptuoi("Tuổi: ");
        double c = nhapdiem("Điểm: ");
        boolean d = nhapgioitinh("Giới tính (nhập true or false): ");
        
            System.out.println("");
            System.out.println("Họ tên: "+a);
            System.out.println("Tuổi: "+b);
            System.out.println("Điểm: "+c);
            System.out.println("Giới tính: "+d);
    }
        
    public static String nhapten(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String ten = sc.nextLine();
        return ten;
    }
    
    public static int nhaptuoi(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        int tuoi = sc.nextInt();
        return tuoi;
    }
    
    public static double nhapdiem(String message) {
        Scanner sc = new Scanner(System.in);
        double diem;
        do {
        System.out.print(message);
        diem = sc.nextDouble();
        if (diem < 0 || diem > 10) {
            System.out.println("Nhập lại");
        }
        }
        while (diem < 0 || diem > 10);
        return diem;
    }
    
    public static boolean nhapgioitinh(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        boolean gioitinh = sc.nextBoolean();
        return gioitinh;
}
}