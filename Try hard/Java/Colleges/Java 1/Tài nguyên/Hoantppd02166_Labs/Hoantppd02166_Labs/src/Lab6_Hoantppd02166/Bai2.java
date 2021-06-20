
package Lab6_Hoantppd02166;

import java.util.Scanner;

public class Bai2 {

    public static void main(String[] args) {
         Scanner sn= new Scanner(System.in);
        System.out.println("Nhập điểm: ");
        double diem=sn.nextDouble();
        System.out.println("Xếp loại: " + xeploai(diem));
        
    }   
    public static String xeploai(double diem){
        String kq = "Nhập sai điểm";
        if (diem<5)
            kq="Yếu";
        else if (diem<6.5)
            kq="Trung Bình";
        else if(diem<7.5)
            kq="Khá";
        else if(diem<9)
            kq="Giỏi";
        else if (diem >=9 && diem <=10)
            kq="Xuất sắc";
        return kq;
    }
}