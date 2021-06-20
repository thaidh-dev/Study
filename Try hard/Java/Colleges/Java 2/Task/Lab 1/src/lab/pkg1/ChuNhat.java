package lab.pkg1;

import java.util.Scanner;

public class ChuNhat {
    double rong, dai;

    ChuNhat(double dai, double rong) {
        this.rong = rong;
        this.dai = dai;
    }
    
    double getChuVi() {
        return (rong + dai)*2;
    }
    
    double getDienTich(){
        return rong*dai;
    }
    
    void xuat(String message) {
        System.out.print(message);
        System.out.printf("Rộng = %.1f\t Dài = %.1f\n", rong, dai);
        System.out.printf("Chu vi = %.1f\t Diện tích = %.1f\n", getChuVi(), getDienTich());
    }
    
}

class Vuong extends ChuNhat {

    Vuong(double canh) {
        super(canh, canh);
        canh = rong;
    }
    
    void xuat(String message) {
        System.out.print(message);
        System.out.printf("Cạnh = %.1f\n", rong);
        System.out.printf("Chu vi = %.1f\t Diện tích = %.1f\n", getChuVi(), getDienTich());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cạnh hình vuông = ");
        double x = scanner.nextDouble();
        System.out.print("Chiều dài hình chữ nhật = ");
        double y = scanner.nextDouble();
        System.out.print("Chiều rộng hình chữ nhật = ");
        double z = scanner.nextDouble();
        Vuong a = new Vuong(x);
        a.xuat("Hình Vuông: ");
        ChuNhat b = new ChuNhat(y, z);
        b.xuat("Hình chữ nhật: ");
    }
}
