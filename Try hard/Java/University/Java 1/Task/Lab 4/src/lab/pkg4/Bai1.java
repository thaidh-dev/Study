package lab.pkg4;

import java.util.Scanner;

public class Bai1 {
    private String tensp;
    private double gia;
    private double giamgia;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Bai1 a = new Bai1();
        a.nhap();
        a.xuat();
    }
    
    void nhap() {
        System.out.print("Nhập tên sản phẩm: ");
        tensp = sc.nextLine();
        System.out.print("Nhập giá: ");
        gia = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập giảm giá: ");
        giamgia = sc.nextDouble();
        System.out.println("");
    }
    
    double thue() {
        double thue = gia*10/100;
        return thue;
    }
    
    void xuat() {
        System.out.printf("Tên sản phẩm: %s\t Giá: %.1f\t Giảm giá: %.1f\t Thuế: %.1f\n", tensp, gia, giamgia, thue());
    }
    
    Bai1(String ten, double gia, double giamgia) {
        tensp = ten;
        this.gia = gia;
        this.giamgia = giamgia;
    }
    
    Bai1(String ten, double gia) {
        tensp = ten;
        this.gia = gia;
    }
    
    Bai1() {}
    
    void setTen(String ten) {
        tensp = ten;
    }
    
    String getTen() {
        return tensp;
    }
    
    void setGia(double gia) {
        this.gia = gia;
    }
    
    double getGia() {
        return gia;
    }
    
    void setGiamGia(double giamgia) {
        this.giamgia = giamgia;
    }
    
    double getGiamGia() {
        return giamgia;
    }
}
