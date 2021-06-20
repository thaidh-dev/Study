
package Lab6_Hoantppd02166;

import java.util.Scanner;

public class Bai3 {
public static void main(String[] args){
        String hoten = readString("Mời nhập họ tên");
        int tuoi = readInt("Mời nhập tuổi: ");
        double diem = readDouble("Mời nhập điểm: ");
        boolean gioitinh = readBootlean("Mời nhập giới tính (true hoặc false): ");
      }

    public static String readString(String message){
        Scanner sc = new Scanner(System.in);
        String kq;
        System.out.print(message + ": ");
        kq= sc.next();
        return kq;
    }
    public static int readInt(String message){
        Scanner sc = new Scanner(System.in);
        int kq;
        System.out.print(message + ": ");
        kq=sc.nextInt();
        return kq;
}
    public static double readDouble(String message){
        Scanner Sc = new Scanner(System.in);
        double kq;
        System.out.print(message + ": ");
        kq = Sc.nextDouble();
        return kq;
    }
    public static boolean readBootlean(String message){
        Scanner Sc = new Scanner(System.in);
        boolean kq;
        System.out.print(message + ": ");
        kq = Sc.nextBoolean();
        return kq;
        }
}

