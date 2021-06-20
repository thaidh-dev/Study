package bài.pkg4;

import java.util.Scanner;

public class Bài4 {

    public static void main(String[] args) {
        menu();
    }
    static void menu() {
        int a;
        do {
        Scanner thai = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1. Giải phương trình bậc 1");
        System.out.println("2. Giải phương trình bậc 2");
        System.out.println("3. Tính tiền điện");
        System.out.println("4. Kết thúc");
        System.out.print("Mời bạn chọn 1 chức năng: ");
        a = thai.nextInt();
        switch (a) {
            case 1: bai1();
            break;
            case 2: bai2();
            break;
            case 3: bai3();
            break;
            case 4: 
                System.out.println("Kết thúc");
                System.exit(0);
            default: System.out.println("Bạn hãy nhập số từ 1 đến 4");
            break;
        }
    }
        while (a != 4);
        }
    
    static void bai1() {
        Scanner lol = new Scanner(System.in);
            System.out.print("Hệ số a: ");
        double a = lol.nextDouble();
        System.out.print("Hệ số b: ");
        double b = lol.nextDouble();
        if(a==0){
            if(b==0){
                System.out.println("Vô số nghiệm");
            }
                else{
                System.out.println("Vô nghiệm");
            }
        }
        else{
            System.out.println("Nghiệm: "+ -b/a);
        }
    }
    static void bai2() {
            Scanner thai = new Scanner(System.in);
        System.out.print("Hệ số a: ");
        double a = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b = thai.nextDouble();
        System.out.print("Hệ số c: ");
        double c = thai.nextDouble();

        if(a==0){
            System.out.println("Phương trình bậc 2 có điều kiện là a phải khác 0");
        }
        else{
           double delta = Math.pow(b,2) -4*a*c;
           if(delta>0){
               System.out.println("Nghiệm 1: "+ (-b+Math.sqrt(delta))/(2*a));
               System.out.println("Nghiệm 2: "+ (-b-Math.sqrt(delta))/(2*a));
           }
           else if(delta==0){
               System.out.println("Nghiệm kép: "+ -b/(2*a));
    }
           else {    
            System.out.println("Vô nghiệm");
}
        }
    }
    static void bai3() {
            Scanner thai = new Scanner(System.in);
        System.out.print("Số điện sử dụng: ");
        double a = thai.nextDouble();
        if(a<50){
            System.out.println("Tiền điện: "+a*1000);
    }
        else{
            System.out.println("Tiền điện: "+ (50*1000+(a-50)*1200));
}
    }
}
