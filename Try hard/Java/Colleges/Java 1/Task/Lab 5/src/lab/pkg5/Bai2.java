package lab.pkg5;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        String hoten[] = new String[3];
        int diem[] = new int[3];
        int dem = 0;
        do {
        System.out.print("Họ tên: ");
        hoten[dem] = thai.nextLine();
        System.out.print("Điểm: ");
        diem[dem] = thai.nextInt();
        thai.nextLine();
        if (diem[dem] < 0 || diem[dem] > 10 ) {
            System.out.println("Bạn hãy nhập điểm từ 0 đến 10");
            continue;
        }        
        dem++;
}
        while (dem < hoten.length);
        
        for (int m = 0; m < diem.length - 1; m++) {
            for (int n = m + 1; n < diem.length; n++) {
                if (diem[m] > diem[n]) {
                    int temp = diem[m];
                    diem[m] = diem[n];
                    diem[n] = temp;
                    String templa = hoten[m];
                    hoten[m] = hoten[n];
                    hoten[n] = templa;
                }
            }
        }

        System.out.println("");
        System.out.println("Thông tin sinh viên sau khi sắp xếp điểm tăng dần");
        for (int a = 0; a < hoten.length; a++) {
            System.out.println("Họ tên: "+hoten[a]);
            System.out.println("Điểm: "+diem[a]);
        if (diem[a] >= 9 && diem[a] <=10) {
            System.out.println("Xuất sắc");
        }
        else if (diem[a] >= 7.5 && diem[a] < 9) {
            System.out.println("Giỏi");
        }
        else if (diem[a] >= 6.5 && diem[a] < 7.5) {
            System.out.println("Khá");
        }
        else if (diem[a] >= 5 && diem[a] < 6.5) {
            System.out.println("Trung bình");
        } 
        else if (diem[a] >= 0 && diem[a] < 5) {
            System.out.println("Yếu");
        }
            System.out.println("");
        }
    }
}
