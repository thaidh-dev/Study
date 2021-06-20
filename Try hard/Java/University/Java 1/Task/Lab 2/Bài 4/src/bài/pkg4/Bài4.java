package bài.pkg4;

import java.util.Scanner;

public class Bài4 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1. Giải phương trình bậc nhất");
        System.out.println("2. Giải phương trình bậc 2");
        System.out.println("3. Tính tiền điện");
        System.out.println("4. Kết thúc");
        System.out.print("Chọn chức năng: ");
        int lol = thai.nextInt();
        
        switch (lol) {
            case 1:
                        System.out.print("Hệ số a: ");
        double a = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b = thai.nextDouble();
        if(a==0){
            if (b==0){
            System.out.println("Vô số nghiệm");
        }
            else {
                System.out.println("Vô nghiệm");
            }
        }
        else {
            double x = -b/a;
            System.out.println("Ngiệm của phương trình là: "+x);
        }
        break;
        
            case 2:
                        System.out.print("Hệ số a: ");
        double a2 = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b2 = thai.nextDouble();
        System.out.print("Hệ số c: ");
        double c = thai.nextDouble();
        double delta = Math.pow(b2,2) - 4*a2*c;
        
        if (a2==0) {
            System.out.println("Phương trình bậc 2 có điều kiện là a phải khác 0");
        }
        else {
            if (delta<0) {
                System.out.println("Phương trình vô ngiệm");
            }
            else if (delta==0) {
                System.out.println("Ngiệm kép: "+-b2/(2*a2));
            }
            else {
                System.out.println("X1 = "+(-b2+Math.sqrt(delta))/(2*a2));
                System.out.println("X2 = "+(-b2-Math.sqrt(delta))/(2*a2));
            }
        }
        break;
        
            case 3:
                        System.out.print("Số điện sử dụng: ");
        double a3 = thai.nextDouble();
        
        if (a3<50) {
            System.out.println("Tiền điện: "+a3*1000);
        }
        else {
            System.out.println("Tiền điện: "+(50*1000+(a3-50)*1200));
        }
        break;
        
            case 4:
                System.out.println("Kết thúc");
                break;
                
            default:
                System.out.println("Hãy chọn số từ 1 đến 4");
                break;
        } 
    }
}
