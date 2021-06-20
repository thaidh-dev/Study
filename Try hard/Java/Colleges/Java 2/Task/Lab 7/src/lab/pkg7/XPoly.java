package lab.pkg7;

import java.util.Scanner;

public class XPoly {
    static int so, tong;
    
    @Deprecated
    public static boolean isCardNumber(String number) {
        so = Integer.parseInt(number);
        tong = 0;
        while (so > 0) {
            int a = so % 10;
            so = so/10;
            tong += a;
        }
        System.out.println("Tổng các chữ số = "+tong);
        if (tong % 2 == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            System.out.print("Hãy nhập 1 dãy số nguyên: ");
            String soNguyen = sc.nextLine();
            System.out.println(isCardNumber(soNguyen));   
    }
}

/*
Double a = (double) 10;
Double b = (double) 7;
a.compareTo(b);
đặt kiểu dữ liệu đối tượng Double cho a và b

Double c = a.doubleValue(); 
đưa a về kiểu nguyên thủy là double và nó có ý nghĩa giống kiểu viết này
Double c = (double) a;
*/