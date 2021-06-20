package lab.pkg5;

import java.util.Scanner;

public class Bai2 {
    Scanner sc = new Scanner(System.in);
    lol s = new lol();
    String ten[];
    double diem[];
    public static void main(String[] args) {
        Bai2 q = new Bai2();
        q.menu();
    }
    
    void menu() {
        int chon;
        do {
        System.out.println("MENU");
        System.out.println("1. Nhập");
        System.out.println("2. Xuất");
        System.out.println("3. Xuất ngẫu nhiên");
        System.out.println("4. Sắp xếp giảm dần và xuất danh sách");
        System.out.println("5. Tìm và xóa họ tên nhập từ bàn phím");
        System.out.println("6. Kết thúc");
        System.out.print("Mời bạn chọn 1 chức năng: ");
        chon = sc.nextInt();
        switch (chon) {
            case 1:
                yeucau1();
                break;
            case 2:
                yeucau2();
                break;
            case 3:
                yeucau3();
                break;
            case 4:
                yeucau4();
                break;
            case 5:
                yeucau5();
                break;
            case 6:
                System.out.println("Thoát");
                System.exit(0);
            default:
                System.out.println("\nBạn hãy nhập chức năng từ 1 đến 6\n");
        }
    }
        while (chon != 6);
        }
    
    void yeucau1() {
        System.out.println("\nNHẬP\n");
        System.out.print("Bạn muốn nhập bao nhiêu tên: ");
        int a = sc.nextInt();
        ten = new String[a];
        diem = new double[a];
        for (int x = 0; x < a; x++) {
            ten[x] = s.nhapString("Họ và tên: ");
            diem[x] = s.nhapDouble("Nhập điểm: ");
        }
        System.out.println("");
    }
    
    void yeucau2() {
        System.out.println("\nXUẤT\n");
        for (int x = 0; x < ten.length; x++) {
            System.out.printf("Họ tên: %s\t Điểm: %.1f\n", ten[x], diem[x]);
        }
        System.out.println("");
    }
    
    void yeucau3() {
        System.out.println("\nTÌM\n");
        double m = s.nhapDouble("Điểm min: ");
        double n = s.nhapDouble("Điểm max: ");
        for (int x = 0; x < ten.length; x++) {
            if (diem[x] >= m && diem[x] <= n) {
                System.out.printf("Họ tên: %s\t Điểm: %.1f\n", ten[x], diem[x]);
            }
        }
        System.out.println("");
    }
    
    void yeucau4() {
        System.out.println("\nSẮP XẾP");
        for (int x = 0; x < ten.length - 1; x++) {
            for (int y = x+1; y < ten.length; y++) {
                if (diem[x] < diem[y]) {
                    s.swap(diem, x, y);
                    s.swap(ten, x, y);
                }
            }
        }
        yeucau2();
    }
    
    void yeucau5() {
        System.out.println("\nXÓA\n");
        String a = s.nhapString("Họ tên muốn xóa: ");
        for (int m = 0; m < ten.length; m++) {
            if (ten[m].equalsIgnoreCase(a)) {
                ten = s.xoa(ten, m);
                diem = s.xoa(diem, m);
            }
        }
        System.out.println("");
    }
}
