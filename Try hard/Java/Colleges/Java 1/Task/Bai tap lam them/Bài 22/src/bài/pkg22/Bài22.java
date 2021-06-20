package bài.pkg22;

import java.util.Scanner;

public class Bài22 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        double x;
        int n;
        int demcan = 0;
        double s = 0;
            System.out.print("Nhập x: ");
            x = thai.nextDouble();
            System.out.print("Nhập n: ");
            n = thai.nextInt();
            if (x < 0) {
                System.out.println("Không thể căn được số âm");
            }
            else {
        while (demcan < n && x >= 0) {
            s = Math.sqrt(x + s);
            demcan++;
        }
        System.out.println("Giá trị của biểu thức là: "+s);
    }
    
}
}