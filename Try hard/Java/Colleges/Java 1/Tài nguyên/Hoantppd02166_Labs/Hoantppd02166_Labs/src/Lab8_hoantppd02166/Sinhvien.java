
package Lab8_hoantppd02166;

import java.util.Scanner;

public class Sinhvien {
    String maSv;
    String hoTen;
    String email;
    double diem;
    String hocLuc;
    void nhap(){
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập mã Sv: ");
        maSv = Sc.next();
        System.out.println("Nhập tên Sv: ");
        hoTen = Sc.next();
        System.out.println("Nhập Mail Sv: ");
        email = Sc.next();
        System.out.println("Nhập điểm Sv: ");
        diem = Sc.nextDouble();
}
    void xuat(){
        xepLoai();
        System.out.println(maSv + " ," + hoTen + " ," + hocLuc);
    }
    void xepLoai(){
        if (diem < 5) hocLuc="yếu";
        else if(diem <=7) hocLuc="Trung bình";
        else hocLuc="tốt";
    }
}
