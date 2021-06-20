/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

/**
 *
 * @author Dang Hung
 */
import java.util.Scanner;

public class Bai3Lab6 {
    static Scanner nhap=new Scanner(System.in);
    public static String nhapxau(String xaukytu){
        System.out.print(xaukytu);
        String nhapxaukt=nhap.nextLine();
        return nhapxaukt;
        
    }
    public static int nhapInt(String xaukytu){
        System.out.print(xaukytu);
        int nhap_nguyen=nhap.nextInt();
        return nhap_nguyen;
    }
    public static double nhapDouble(String xaukytu){
        System.out.print(xaukytu);
        double nhap_double=nhap.nextDouble();
        return nhap_double;
    }
    public static boolean nhapBoolean(String xaukytu){
        System.out.print(xaukytu);
        boolean nhap_Boolean=nhap.nextBoolean();
        return nhap_Boolean;
    }
    public static void main(String[] args){
        String hoten=Bai3Lab6.nhapxau("Ho va ten: ");
        String quequan=nhapxau("Que quan: ");
        String hopthu=Bai3Lab6.nhapxau("Hop thu: ");
        int sdt=Bai3Lab6.nhapInt("So dien thoai: ");
        double diem=Bai3Lab6.nhapDouble("Diem trung binh: ");
        boolean dung=Bai3Lab6.nhapBoolean("Nhap dung sai: ");
        System.out.println(hoten+""+quequan+""+hopthu);
        System.out.println(""+sdt+""+diem+""+dung);
    }
}
