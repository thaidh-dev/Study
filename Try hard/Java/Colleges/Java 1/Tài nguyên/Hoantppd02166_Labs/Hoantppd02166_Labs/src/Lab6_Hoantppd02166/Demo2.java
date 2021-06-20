package Lab6_Hoantppd02166;

import java.util.Scanner;

public class Demo2 {

    public static void main(String[] args) {
        int soluong;
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số lượng phần sinh viên: ");
        soluong = Sc.nextInt();
        double[] diem = new double[soluong];
        while (true) {
            System.out.println("1.Nhập điểm sinh viên");
            System.out.println("2.Xuất điểm sinh viên");
            int menu = Sc.nextInt();
            switch (menu) {
                case 1:
                    nhap(diem, soluong);
                    break;
                case 2:
                    xuat(diem);
                    break;
                case 3:
                    if (timkiem(diem) == -1) {
                        System.out.println("Không tìm thấy");
                    } else {
                        System.out.println("Tìm thấy ở vị trí: " + timkiem(diem));
                    }
                case 11:
                    System.exit(0);
            }
        }
    }

    static void nhap(double[] diem, int n) {
        Scanner Sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Phần tử thứ: " + (i + 1));
            diem[i] = Sc.nextDouble();

        }
    }

    static void xuat(double[] d) {
        System.out.println("Danh sách điểm: ");
        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i]);
        }
    }

    static int timkiem(double[] d) {
        double x;
        Scanner Sc = new Scanner(System.in);
        int kq = -1;
        System.out.println("Nhập giá trị cần tìm");
        x = Sc.nextDouble();
        for (int i = 0; i < d.length; i++) {
            if (x == d[i]);
            {
                kq = i;
                break;
            }
        }
        return kq;

    }
}
