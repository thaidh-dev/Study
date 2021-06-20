package Lab5_hoantppd02166;

import java.util.Scanner;

public class Demo1 {
// Nhập danh sách

    public static void main(String[] args) {
        int n; //khai bao so luong
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập số lượng sinh viên = ");
        n = Sc.nextInt();
        double[] dtb = new double[n];
        String[] ten = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Sinh viên thứ " + i + ":");
            System.out.print("Tên: ");
            ten[i] = Sc.next();
            System.out.println("Điểm trung bình: ");
            dtb[i] = Sc.nextDouble();
        }
        // Xuất danh sách
        System.out.println("Danh sách sinh viên ");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " > " + ten[i] + ", dbt = " + dtb[i]);
        }
        // Tinh diem trung binh ca lop
        double tong = 0;
        for (int i = 0; i < n; i++) {
            tong = tong + dtb[i];
        }
        System.out.println("Điểm trung bình của cả lớp = " + (tong / n));
        // Tim kiem ten sinh vien
        boolean kt = false;
        System.out.println("Nhập tên cần tìm: ");
        String x = Sc.next();
        for (int i = 0; i < n; i++) {
            if (ten[i].equalsIgnoreCase(x)) {
                System.out.println("Đã tìm thấy tên " + x + " ở vị trí thứ " + i + ", Dtb = " + dtb[i]);
                kt = true;
            }
        }
        if (kt == false) {
            System.out.println("Không tìm thấy ");
        }

        // Tìm sinh viên có điểm nhỏ nhất
        double min = dtb[0];
        int v = 0;
        for (int i = 1; i < n; i++) {
            if (min > dtb[i]) {
                min = dtb[i];
                v = i;
            }
        }
        System.out.println("Điểm nhỏ nhất " + min + " ở vị trí " + v);

    }

}
