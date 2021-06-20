package lab.pkg8;

import java.util.Scanner;

public class Bai1 {
    public String tensp;
    public double dongia;
    public double giamgia;
    Scanner sc = new Scanner(System.in);
    
    public String nhapstring(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }
    
    public double nhapdouble(String message) {
        System.out.print(message);
        double input = sc.nextDouble();
        return input;
    }
    
    public double thue(double dongia, double giamgia) {
        double thuethunhap = dongia*10/100 - giamgia;
        return thuethunhap;
    }
    
    public void xuat() {
        System.out.println("\nTên sản phẩm: "+tensp);
        System.out.println("Đơn giá: "+dongia);
        System.out.println("Giảm giá: "+giamgia);
        System.out.println("Thuế: "+thue(dongia, giamgia));
    }
    
    public Bai1(String ten, double gia, double giamgia) {
            tensp = ten;
            dongia = gia;
            this.giamgia = giamgia;
        }
    
    public Bai1(String ten, double gia) {
            tensp = ten;
            dongia = gia;
    }
    
    public void setTenSp(String sp) {
        tensp = sp;
    }
    
    public String getTenSp() {
        return tensp;
    }
    
    public void setDonGia(double gia) {
        dongia = gia;
    }
    
    public double getDonGia() {
        return dongia;
    }
    
    public void setGiamGia(double giam) {
        giamgia = giam;
    }
    
    public double getGiamGia() {
       return giamgia; 
    }
    
    public Bai1() {
    }
}



