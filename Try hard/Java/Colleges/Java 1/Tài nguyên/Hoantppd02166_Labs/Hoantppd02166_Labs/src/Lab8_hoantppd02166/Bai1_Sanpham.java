
package Lab8_hoantppd02166;

import java.util.Scanner;

public class Bai1_Sanpham {
    private String tenSP;
    private double donGia;
    private double giamGia;

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }
    
    private double getThue(){
        return (donGia - giamGia)*0.1;
    }
    void nhap(){
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập tên sản phẩm: ");
        tenSP = Sc.next();
        System.out.print("Nhập đơn giá sản phẩm: ");
        donGia = Sc.nextDouble();
        System.out.print("Nhập mã giảm giá: ");
        giamGia = Sc.nextDouble();
    }
    public void xuat(){
        System.out.println("Hóa đơn thanh toán");
        System.out.println("Tên sản phẩm: " + tenSP);
        System.out.println("Đơn giá sản phẩm: " + donGia);
        System.out.println("Mã giảm giá: " + giamGia);
        System.out.println("Thuế nhập khẩu: " + getThue());
        System.out.println("Tổng tiền phải trả: " + (donGia - giamGia + getThue()));
        System.out.println("--------------------------------------------------------");
    }
      public Bai1_Sanpham(){
        this.tenSP = "Iphone";
        this.donGia = 25000000;
        this.giamGia = 1200000;
}
      public Bai1_Sanpham(String tsp, double dg, double gg){
          tenSP = tsp; donGia = dg; giamGia = gg;
      }
}